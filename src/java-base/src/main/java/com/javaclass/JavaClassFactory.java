package com.javaclass;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JavaClassFactory {
	
	/**
	 * 取实现该接口的类
	 * @param packagename
	 * @param clazz
	 * @return
	 */
	public final static <T> Set<T> getObjectsByInterface(String packagename,Class<T> clazz){
		return getObjectsByInterface(packagename,clazz,null) ;
	}
	/**
	 * 取实现该接口的类
	 * 过滤了Active
	 * @param packagename
	 * @param clazz
	 * @return
	 */
	public final static <T,E extends T> Set<T> getObjectsByInterface(String packagename,Class<T> clazz,Set<Class<E>> ignoreSet){
		Set<Class<T>> set = JavaClassFactory.getClassesByInterface(packagename, clazz) ;
		Set<T> resultSet = new HashSet<T>() ;
		for(Class<T> dubboAspect : set ){
			try {
				if(ignoreSet != null && ignoreSet.contains(dubboAspect))
					continue ;
				T t = dubboAspect.newInstance() ;
				resultSet.add(t) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultSet ;
	}
	/**
	 * 判断路径下的所有类是否实现了指定的接口。
	 * @param packagename
	 * @param clazz
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public static <T> Set<Class<T>> getClassesByInterface(String packagename,Class<T> clazz){
    	Set<Class<T>> set = new HashSet<Class<T>>();
        /** 判断是否是一个接口 */
        try {
        	Set<Class<?>> classes = findClasses(packagename);
            /**
             * 循环判断路径下的所有类是否实现了指定的接口
             * 并且排除接口类自己
             */
            for(Class<?> cls : classes){
            	if (clazz.isAssignableFrom(cls) && !clazz.equals(cls)) {
            		set.add((Class<T>)cls);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 递归查找指定包路径下的所有Class
     * @param name
     */
    public static Set<Class<?>> findClasses(String packagename) {
    	Set<Class<?>> set = new HashSet<Class<?>>();
        String path = packagename.replace('.', '/');
        try {
            Enumeration<URL> enumeration = Thread.currentThread().getContextClassLoader().getResources(path);
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
            	if(url.getProtocol().equalsIgnoreCase("jar")){
            		set.addAll(findClassInJarFile(url)) ;
            	} else if(url.getProtocol().equalsIgnoreCase("file")){
            		set.addAll(findClassInPackageFile(url.getFile())) ;
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 递归查找指定包路径下的所有Class
     * @param filename
     * @param packagename
     * @return
     */
    private static Set<Class<?>> findClassInPackageFile(String filename) {
    	
    	Set<Class<?>> set = new HashSet<Class<?>>();
    	File file = new File(filename) ;
        if (file == null || !file.exists()) {
        	return set;
        }
        File[] fileList = file.listFiles();
        for (File fileItem : fileList) {
            if (fileItem.isDirectory()) {
                assert !fileItem.getName().contains("."); //添加断言用于判断
                Set<Class<?>> arraySet = findClassInPackageFile(fileItem.getPath());
                set.addAll(arraySet);
            }else if(fileItem.getName().endsWith(".class")){
                try {
                    //保存的类文件不需要后缀.class
//                	String classpath = ClassUtils.class.getResource("/").getPath().replaceAll("\\/", "\\\\") ;
//                	while(classpath.charAt(0) == '\\'){
//                		classpath = classpath.substring(1) ;
//                	}
                	String classname = fileItem.getPath().substring(fileItem.getPath().indexOf("classes\\") + "classes\\".length()) ;
                	classname = classname.replaceAll("\\\\", ".") ;
                	set.add(Class.forName(classname.substring(0, classname.length()-6)));
                } catch (Exception e) {
                	e.printStackTrace();
                } catch (Error error) {
                	error.printStackTrace();
				}
            }
        }
        return set ;
    }
    
    /**
     * 递归查找指定包路径下的所有Class
     * @param filename
     * @param packagename
     * @return
     */
    private static Set<Class<?>> findClassInJarFile(URL filename) {
    	JarFile jarFile = null ;
    	try {
    		jarFile = ((JarURLConnection)filename.openConnection()).getJarFile() ;
    		System.out.println("jarFile:" + jarFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
    	Set<Class<?>> set = new HashSet<Class<?>>();
        if (jarFile == null) {
        	return set;
        }
        Enumeration<JarEntry> jarEntrys = jarFile.entries() ;
        while(jarEntrys.hasMoreElements()){
        	JarEntry jarEntry = jarEntrys.nextElement() ;
            if (jarEntry.getName().endsWith(".class")){
                try {
                    //保存的类文件不需要后缀.class
                	set.add(Class.forName(jarEntry.getName().substring(0,jarEntry.getName().length()-6).replaceAll("\\/", ".")));
                } catch (Exception e) {
                	e.printStackTrace();
                } catch (Error error) {
                	error.printStackTrace();
				}
            }
        }
        return set ;
    }
    
    
}
