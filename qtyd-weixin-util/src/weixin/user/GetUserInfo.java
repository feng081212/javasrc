package weixin.user;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;

public class GetUserInfo {

	public static void main(String[] args) {
		System.out.println(getUserInfo("ohVUJuGZTdYPT99d3oDFUPqp8ZIM"));
	}
	
	public static String getUserInfo(String... oppenId){
		try{
			String ret = JavaURLConnection.sendPost(getUrl(oppenId[0]), "");
			System.out.println(ret);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getUrl(String oppenId){
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN" ;
		url = url.replaceAll("ACCESS_TOKEN", AccessToken.access_taken) ;
		url = url.replaceAll("OPENID", oppenId) ;
		return url;
	}
}
