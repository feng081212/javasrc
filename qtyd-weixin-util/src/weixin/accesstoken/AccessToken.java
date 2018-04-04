package weixin.accesstoken;

import java.net.InetAddress;

import org.json.JSONObject;

import com.common.http.JavaURLConnection;
import com.common.param.WeixinParameter;
import com.qttz.common.utils.CallJavaCoreApi;
import com.qttz.common.utils.MD5Util;

public class AccessToken {
	
	public static String errcode_access_taken_disable = "40014" ;
	public static String errcode_access_taken_timeout = "42001";
	public static String errcode_access_token_miss = "41001";
	public static String errcode_access_token_error = "40001";
	public static long lastRefresh = 0;
	public static String access_taken = "zP5n85AfTLmt7kBSikK-TkhS0Gr7So2pu1w1b0OHHX2kQ_UNdKt0yb15SzMmSgHE8JEe8BnWzep5IdeQF2qNZy8eK9eSA35hTYZC1b8X71abOoRJATjYqUh8DzO9BMYoVKTbADAODM";

	public static void main(String[] args) {
		System.out.println(getAccessToken());
		//System.out.println(MD5Util.getInstance().getMD5("admin:admin")) ;
	}
	
	public static String getAccessToken(){
		if(getTaken()){
		//if(refreshAccessToken()){
			return access_taken;
		}else{
			return "access_token";
		}
	}
	
	public static synchronized boolean refreshAccessToken(){
		try{
			long unixtime = System.currentTimeMillis()/1000 ;
			if((unixtime-lastRefresh)<600)
				return true;
			String returnStr = JavaURLConnection.sendPost(getAccessTokenUrl(), "");
			System.out.println(returnStr);
			JSONObject json = new JSONObject(returnStr);
			access_taken = json.getString("access_token");
			lastRefresh = unixtime ;
			return true;
		}catch(Exception e){
			access_taken = "access_token";
		}
		return false;
	}
	

	public static boolean isAccessTokenError(String errcode){
		if(
			errcode.equals(errcode_access_taken_disable)||
			errcode.equals(errcode_access_taken_timeout)||
			errcode.equals(errcode_access_token_miss)||
			errcode.equals(errcode_access_token_error)
		) return true;
		else return false;
	}
	
	private static String getAccessTokenUrl(){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
		url.append("&appid=").append(WeixinParameter.weixin_appid);
		url.append("&secret=").append(WeixinParameter.weixin_appsecret);
		return url.toString();
	}
	
	
	public static boolean getTaken(){
		try{
			JSONObject common = new JSONObject();
			common.put("ip",  InetAddress.getLocalHost().getHostAddress());
			common.put("trackid", JavaCoreConstant.trackid);
			common.put("device_port", JavaCoreConstant.device_port);
			common.put("version", JavaCoreConstant.version);
			common.put("action", "weixin_getWeiXinTaken");
			common.put("reqtime", System.currentTimeMillis()/1000);
			JSONObject mReqcontent = new JSONObject();
			mReqcontent.put("accessid", JavaCoreConstant.accessid_default) ;
			
			JSONObject map = new JSONObject();
			map.put("common", common);
			map.put("content", mReqcontent);
			
			JSONObject contentJson = new JSONObject();
			contentJson.put("request",map);
			
			String content = contentJson.toString() ;
			String sign = MD5Util.getInstance().getMD5(content);
			sign = MD5Util.getInstance().getMD5(sign + JavaCoreConstant.accesskey_default);
			String retStr = CallJavaCoreApi.getInstance().PostRequest(sign,content);
			JSONObject retJson = new JSONObject(retStr);
			access_taken = retJson.getJSONObject("response").getJSONObject("content").getString("taken");
			return true ;
		}catch(Exception e){
			access_taken = "access_taken" ;
			return false ;
		}
	}
	
	
}
