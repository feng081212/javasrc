package com.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.json.JSONObject;

import com.common.http.JavaURLConnection;

public class TicketUtils {

	public static String createShareSign() throws Exception
	 {
		String rand =  "1444632042";  //随机字符串
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);// Long.toString(new Date().getTime()); 
		String url = "http://wx.qtyd.com/phone/borrow/borrow_list";
		//url = URLEncoder.encode(url);
		System.out.println(url);
	  //首先获得一个accessToken
	  String token = "jNTAKobeo18O5d2LmW8OyMlwhr6jpckNX74DbRoBdoLm4OdOJBmQuXSskI7WVeO0YTKsiSOQaaYWjF9FkeYFlr3QNrtKIU6QPMDFimsGPR8";//自己写的获取缓存access_token,大家可按自己的方式获取accessToken
	  
	  //根据token获取jsapi
	  String jsapiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	  jsapiUrl = jsapiUrl.replace("ACCESS_TOKEN", token);
	  String retstr = JavaURLConnection.send(jsapiUrl);//调用jsapi接口获取jsapi_ticket
	  System.out.println(retstr);
	  JSONObject json = new JSONObject(retstr);
	  String jsapiTicket = json.getString("ticket");
	 
	  String signature="";
	       
	  String string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + rand + "&timestamp=" + timestamp + "&url=" + url;
	  System.out.println(string1);
	  try{
	       MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	       crypt.reset();
	       crypt.update(string1.getBytes("UTF-8"));
	       signature = byteToHex(crypt.digest());
	   }
	   catch (NoSuchAlgorithmException e)
	   {
	       e.printStackTrace();
	   }
	   catch (UnsupportedEncodingException e)
	   {
	       e.printStackTrace();
	   }
	   System.out.println("签名:"+signature);
	   //84169fe1fe6aba1c4e15b25fd000692c8dc89f46
	   return signature;
	 }
	 private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash)
	        {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
	    }
	 public static void main(String[] args) {
		 try {
			createShareSign();
			 String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3dea6e963a14d910&redirect_uri=http%3A%2F%2Fwx.qtyd.com%2Fmobile%2Fuser%2Fweixinlogin.html%3Ftrackid%3DweixinQTYDcd01&response_type=code&scope=snsapi_userinfo&state=STATE";
				url = URLEncoder.encode(url);
				System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
