package weixin.jssdk.ticket;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Formatter;

import org.json.JSONObject;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;
import com.common.param.WeixinParameter;

public class TicketCreate {

	private static String random = "" ;
	private static String ticket = "" ;
	private static String timestamp = "" ;
	private static String signature = "" ;
	
	public static void main(String[] args) {
		TicketCreate.createShareSign("http://wx.qtyd.com/test/testWeiXin?trackid=weixinQTYDcd02");
		System.out.println(ticket);
		System.out.println(WeixinParameter.weixin_appid);
		System.out.println(random);
		System.out.println(timestamp);
		System.out.println(signature);
	}
	
	public static void refreshTicket(){
		try{
			random = "qtydticket001";
			timestamp = Long.toString(System.currentTimeMillis() / 1000);
			
			String jsapiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
			jsapiUrl = jsapiUrl.replace("ACCESS_TOKEN", AccessToken.access_taken+"2");
			String retstr = new JavaURLConnection().send(jsapiUrl, null);
			System.out.println(retstr);
			JSONObject jsonret = new JSONObject(retstr);
			String errcode = String.valueOf(jsonret.get("errcode"));
			if (AccessToken.isAccessTokenError(errcode)) {
				if (AccessToken.refreshAccessToken())
					refreshTicket();
			} else if (errcode.equals("0")){
				ticket = jsonret.getString("ticket");
			}
			return ;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String createShareSign(String url) {
		try {
			url = URLDecoder.decode(url);
			System.out.println(url);
			refreshTicket() ;
			String string1 = "jsapi_ticket=" + ticket + "&noncestr=" + random
					+ "&timestamp=" + timestamp + "&url=" + url;
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signature ;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
