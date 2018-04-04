package com.qttz.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.omg.Dynamic.Parameter;

import com.common.param.WeixinParameter;

import weixin.accesstoken.JavaCoreConstant;

public class CallJavaCoreApi {
	public static CallJavaCoreApi getInstance(){
		return new CallJavaCoreApi() ;
	}
	
	public HttpURLConnection getConnection(String sign,String content){
		try {
			if(content == null || content.equals("") || sign == null || sign.equals("")){
				return null ;
			}
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(JavaCoreConstant.URL).openConnection();
			// 设置通用的请求属性
			urlConnection.setRequestProperty("accept", "*/*");
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			urlConnection.setRequestProperty("Accept-Charset", "UTF-8");  
			urlConnection.setRequestProperty("format", JavaCoreConstant.format);
			urlConnection.setRequestProperty("sign", encode(sign,JavaCoreConstant.charset));
			urlConnection.setRequestProperty("reqlength", String.valueOf(getRealLength(content,JavaCoreConstant.charset)));
			
			// 发送POST请求必须设置如下两行
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			// 用POST传输；如果设置为GET，而且有OutputStream输出，则还是用POST传输
			// 如果设置为GET，而且没有OutputStream输出，则是用GET传输
			urlConnection.setRequestMethod("POST");
			urlConnection.setReadTimeout(30 * 1000);
            // 建立实际的连接
			urlConnection.connect();
			return urlConnection ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String PostRequest(String sign,String content){
		OutputStream out = null ;
		BufferedReader in = null ;
		String result = "";
		try{
			HttpURLConnection conn = (HttpURLConnection) getConnection(sign,content);
			if(conn == null )
				return "" ;
			if(content != null && !content.equals("")){
				out = conn.getOutputStream();
				out.write(content.getBytes(WeixinParameter.weixin_charset));
				out.flush();
				logger.write(content + " ==> OutputStream 输出完成");
			}
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            logger.write(result);
		}catch(Exception e){
			logger.write(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
			} catch (IOException e) {
				out = null ;
				in = null ;
				logger.write(e.getMessage());
			}
		}
		return result;
	}
	
	public String encode(String str, String enc) {
		String strEncode = "";
		try {
			if (str != null)
				strEncode = URLEncoder.encode(str, enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strEncode;
	}
	
	public int getRealLength(String str, String charsetName) {

		if(str == null )
			str = "" ;
		try {
			return str.getBytes(charsetName).length;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static void main(String[] args) {
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
			System.out.println(retStr);
			JSONObject retJson = new JSONObject(retStr);
			String taken = retJson.getJSONObject("response").getJSONObject("content").getString("taken");
			System.out.println(taken);
		}catch(Exception e){
			
		}
	}
}
