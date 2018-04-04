/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hmily.tcc.admin.service.compensate;

import com.hmily.tcc.admin.helper.PageHelper;
import com.hmily.tcc.admin.service.CompensationService;
import com.hmily.tcc.admin.helper.ConvertHelper;
import com.hmily.tcc.admin.page.CommonPager;
import com.hmily.tcc.admin.page.PageParameter;
import com.hmily.tcc.admin.query.CompensationQuery;
import com.hmily.tcc.admin.vo.TccCompensationVO;
import com.hmily.tcc.common.bean.adapter.CoordinatorRepositoryAdapter;
import com.hmily.tcc.common.exception.TccException;
import com.hmily.tcc.common.serializer.ObjectSerializer;
import com.hmily.tcc.common.utils.DateUtils;
import com.hmily.tcc.common.utils.FileUtils;
import com.hmily.tcc.common.utils.RepositoryPathUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>Description: .</p>
 * 文件实现
 *
 * @author xiaoyu(Myth)
 * @version 1.0
 * @date 2017/10/19 17:08
 * @since JDK 1.8
 */
public class FileCompensationServiceImpl implements CompensationService {


    @Autowired
    private ObjectSerializer objectSerializer;


    /**
     * 分页获取补偿事务信息
     *
     * @param query 查询条件
     * @return CommonPager<TransactionRecoverVO>
     */
    @Override
    public CommonPager<TccCompensationVO> listByPage(CompensationQuery query) {

        final String filePath = RepositoryPathUtils.buildFilePath(query.getApplicationName());
        final PageParameter pageParameter = query.getPageParameter();
        final int currentPage = pageParameter.getCurrentPage();
        final int pageSize = pageParameter.getPageSize();

        int start = (currentPage - 1) * pageSize;

        CommonPager<TccCompensationVO> voCommonPager = new CommonPager<>();
        File path;
        File[] files;
        int totalCount;
        List<TccCompensationVO> voList;


        //如果只查 重试条件的
        if (StringUtils.isBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
            path = new File(filePath);
            files = path.listFiles();
            final List<TccCompensationVO> all = findAll(files);
            if (CollectionUtils.isNotEmpty(all)) {
                final List<TccCompensationVO> collect =
                        all.stream().filter(Objects::nonNull)
                                .filter(vo -> vo.getRetriedCount() < query.getRetry())
                                .collect(Collectors.toList());
                totalCount = collect.size();
                voList = collect.stream().skip(start).limit(pageSize).collect(Collectors.toList());
            } else {
                totalCount = 0;
                voList = null;
            }
        } else if (StringUtils.isNoneBlank(query.getTransId()) && Objects.isNull(query.getRetry())) {
            final String fullFileName =RepositoryPathUtils.getFullFileName(filePath,query.getTransId());
            final File file = new File(fullFileName);
            files = new File[]{file};
            totalCount = files.length;
            voList = findAll(files);
        } else if (StringUtils.isNoneBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
            final String fullFileName =RepositoryPathUtils.getFullFileName(filePath,query.getTransId());
            final File file = new File(fullFileName);
            files = new File[]{file};
            totalCount = files.length;
            voList = findAll(files)
                    .stream().filter(Objects::nonNull)
                    .filter(vo -> vo.getRetriedCount() < query.getRetry())
                    .collect(Collectors.toList());
        } else {
            path = new File(filePath);
            files = path.listFiles();
            totalCount = files.length;
            voList = findByPage(files, start, pageSize);
        }
        voCommonPager.setPage(PageHelper.buildPage(query.getPageParameter(), totalCount));
        voCommonPager.setDataList(voList);
        return voCommonPager;
    }


    /**
     * 批量删除补偿事务信息
     *
     * @param ids             ids 事务id集合
     * @param applicationName 应用名称
     * @return true 成功
     */
    @Override
    public Boolean batchRemove(List<String> ids, String applicationName) {
        if (CollectionUtils.isEmpty(ids) || StringUtils.isBlank(applicationName)) {
            return Boolean.FALSE;
        }
        final String filePath = RepositoryPathUtils.buildFilePath(applicationName);
        ids.stream().map(id -> new File(RepositoryPathUtils.getFullFileName(filePath,id)))
                .forEach(File::delete);

        return Boolean.TRUE;
    }


    /**
     * 更改恢复次数
     *
     * @param id              事务id
     * @param retry           恢复次数
     * @param applicationName 应用名称
     * @return true 成功
     */
    @Override
    public Boolean updateRetry(String id, Integer retry, String applicationName) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(applicationName) ||
                Objects.isNull(retry)) {
            return false;
        }
        final String filePath = RepositoryPathUtils.buildFilePath(applicationName);
        final String fullFileName = RepositoryPathUtils.getFullFileName(filePath,id);
        final File file = new File(fullFileName);
        final CoordinatorRepositoryAdapter adapter = readRecover(file);
        if (Objects.nonNull(adapter)) {
            try {
                adapter.setLastTime(DateUtils.getDateYYYY());
            } catch (Exception e) {
                e.printStackTrace();
            }
            adapter.setRetriedCount(retry);
            try {
                FileUtils.writeFile(fullFileName,objectSerializer.serialize(adapter));
            } catch (TccException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }


    private CoordinatorRepositoryAdapter readRecover(File file) {
        try {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] content = new byte[(int) file.length()];

                final int read = fis.read(content);

                return objectSerializer.deSerialize(content, CoordinatorRepositoryAdapter.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private TccCompensationVO readTransaction(File file) {
        try {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] content = new byte[(int) file.length()];

                final int read = fis.read(content);
                final CoordinatorRepositoryAdapter adapter = objectSerializer.deSerialize(content, CoordinatorRepositoryAdapter.class);
                return ConvertHelper.buildVO(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<TccCompensationVO> findAll(File[] files) {
        if (files != null && files.length > 0) {
            return Arrays.stream(files)
                    .map(this::readTransaction)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private List<TccCompensationVO> findByPage(File[] files, int start, int pageSize) {
        if (files != null && files.length > 0) {
            return Arrays.stream(files).skip(start).limit(pageSize)
                    .map(this::readTransaction)
                    .collect(Collectors.toList());
        }
        return null;
    }



}
