package com.url;

public class URLOfClassPath {

	public static final String CLASS_PATH_URL = URLOfClassPath.class.getResource("/").getPath() ;
	
	public static final String getFullURLInCP(String url){
		if(!url.startsWith("/")){
			url = "/" + url ;
		}
		return URLOfClassPath.class.getResource(url).getPath() ;
	}
	
	public static void main(String[] args) {
		System.out.println(CLASS_PATH_URL);
		System.out.println(getFullURLInCP("log4j.properties"));
	}
}
