package weixin.media;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;

public class GetMediaTotal {
	
	public static void main(String[] args) {
		getMediaTotal();
	}
	
	public static void getMediaTotal(){
		String retstr = JavaURLConnection.sendPost(getUrl(), getValue());
		System.out.println(retstr);
	}
	
	private static String getUrl(){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=");
		url.append(AccessToken.access_taken);
		return url.toString();
	}
	
	private static String getValue(){
		return "";
	}
}
