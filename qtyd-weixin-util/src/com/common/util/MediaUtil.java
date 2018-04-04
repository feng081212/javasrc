package com.common.util;

import org.json.JSONArray;
import org.json.JSONObject;

import com.qttz.db.DB;

public class MediaUtil {

	public void MediaListUtil(String value){
		try{
			JSONObject json = new JSONObject(value);
			JSONArray jsonArray = json.getJSONArray("item");
			DB dba = new DB();
			for(int i=0;i<jsonArray.length();i++){
				JSONObject retJson = jsonArray.getJSONObject(i);
				String media_id = retJson.getString("media_id");
				JSONObject content = retJson.getJSONObject("content");
				JSONArray news_item = content.getJSONArray("news_item");
				
				/*************************保存素材*****************************/
				String sql_select = "select id from dw_weixin_material where media_id = '"+media_id+"'" ;
				String id = dba.queryOneRowSet(sql_select, "id");
				if(id==null||id.equals("")){
					String sql_insert = "insert into dw_weixin_material (media_id,count,addtime)values('"+media_id+"','"+news_item.length()+"',UNIX_TIMESTAMP())";
					dba.update(sql_insert);
					id = dba.queryOneRowSet(sql_select, "id");
					if(id==null||id.equals("")) continue ;
				}else{
					String sql_update = "update dw_weixin_material set count = '"+news_item.length()+"' where id = '" + id +"' ";
					dba.update(sql_update);
				}
				/*************************保存素材*****************************/
				
				
				for(int j=0;j<news_item.length();j++){
					JSONObject news = news_item.getJSONObject(j);
					String title = news.getString("title");
					String thumb_media_id = news.getString("thumb_media_id");
					String show_cover_pic = news.getString("show_cover_pic");
					String author = news.getString("author");
					String digest = news.getString("digest");
					String content2 = "content";//news.getString("content");
					String url = news.getString("url");
					String content_source_url = news.getString("content_source_url");
					
					String sql_1 = "insert into dw_weixin_news (material_id,title,thumb_media_id,show_cover_pic,author,digest,content,url,content_source_url,addtime)values(?,?,?,?,?,?,?,?,?,UNIX_TIMESTAMP())";
					String[] values = new String[]{id,title,thumb_media_id,show_cover_pic,author,digest,content2,url,content_source_url};
					dba.update(sql_1,values);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
