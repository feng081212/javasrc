package com.common.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.common.param.WeixinParameter;

public class JavaURLConnection {

	public static URL getJavaURL(String url){
		try {
			System.out.println(url);
			return new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static URLConnection buildPost(String url){
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) getJavaURL(url).openConnection();
			// 设置通用的请求属性
			urlConnection.setRequestProperty("accept", "*/*");
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			urlConnection.setRequestProperty("Accept-Charset", "UTF-8");  

			// 发送POST请求必须设置如下两行
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setRequestMethod("POST");
            // 建立实际的连接
			urlConnection.connect();
			return urlConnection;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HttpURLConnection build(String url,String BOUNDARY){
		try{
			// 连接
			HttpURLConnection urlConnection = (HttpURLConnection) getJavaURL(url).openConnection();
			/**
			 * 设置关键值
			 */
			urlConnection.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false); // post方式不能使用缓存

			// 设置请求头信息
			urlConnection.setRequestProperty("Connection", "Keep-Alive");
			urlConnection.setRequestProperty("Charset", "UTF-8");

			// 设置边界
			urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			return urlConnection;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null ;
	}
	
	public static URLConnection build(String url){
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) getJavaURL(url).openConnection();
			// 设置通用的请求属性
			urlConnection.setRequestProperty("accept", "*/*");
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			urlConnection.setRequestProperty("Accept-Charset", "UTF-8");  
            // 建立实际的连接
			urlConnection.connect();
			return urlConnection;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String send(String url,String value){
		OutputStream out = null ;
		BufferedReader in = null ;
		String result = "";
		try{
			HttpURLConnection conn = (HttpURLConnection) build(url);
			
			if(value!=null&&!value.equals("")){
				out = conn.getOutputStream();
				out.write(value.getBytes("utf-8"));
				out.flush();
			}
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
           
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendPost(String url,String value){
		OutputStream out = null ;
		BufferedReader in = null ;
		String result = "";
		try{
			HttpURLConnection conn = (HttpURLConnection) buildPost(url);
			System.out.println(value);
			if(value!=null&&!value.equals("")){
				out = conn.getOutputStream();
				out.write(value.getBytes(WeixinParameter.weixin_charset));
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static byte[] sendReturnByte(String url,String value){
		OutputStream out = null ;
		InputStream in = null ;
		try{
			HttpURLConnection conn = (HttpURLConnection) buildPost(url);
			System.out.println(value);
			if(value!=null&&!value.equals("")){
				out = conn.getOutputStream();
				out.write(value.getBytes(WeixinParameter.weixin_charset));
				out.flush();
			}
			
			in = conn.getInputStream();
            byte[] b = new byte[1024];
            int length = 0 ;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((length = in.read(b)) >0) {
            	baos.write(b,0,length);
            }
            return baos.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String sendlist(String url,byte[][] value,String BOUNDARY){
		OutputStream out = null ;
		InputStream in = null ;
		try{
			HttpURLConnection conn = build(url,BOUNDARY);
			if(value!=null&&value.length>0){
				out = new DataOutputStream(conn.getOutputStream());
				for(int i=0;i<value.length;i++)
					out.write(value[i]);
				out.flush();
				out.close();
			}
			
			in = conn.getInputStream();
            byte[] b = new byte[1024];
            int length = 0 ;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((length = in.read(b)) >0) {
            	baos.write(b,0,length);
            }
            return new String(baos.toByteArray(),WeixinParameter.weixin_charset);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public static String send(String url){
		OutputStream out = null ;
		BufferedReader in = null ;
		String result = "";
		try{
			HttpURLConnection conn = (HttpURLConnection) build(url);
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
