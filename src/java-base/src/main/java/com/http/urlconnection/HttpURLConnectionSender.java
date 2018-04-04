package com.http.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.io.AutoCloseableFactory;
import com.io.IOStream;

public class HttpURLConnectionSender {

	protected String url = "" ;
	protected Map<String,Object> head = null ;
	
	public HttpURLConnectionSender(String url) {
		this(url,null) ;
	}
	
	public HttpURLConnectionSender(String url,Map<String,Object> head) {
		this.url = url ;
		this.head = new HashMap<String,Object>() ;
		this.head.put("accept", "*/*") ;
		this.head.put("connection", "Keep-Alive") ;
		this.head.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)") ;
		this.head.put("Accept-Charset", "UTF-8") ;
		if(head != null && head.size() > 0){
			this.head.putAll(head);
		}
	}
	
	/**
	 * URLConnection 连接之前执行
	 * @param urlConnection
	 */
	public void doBeforeBuildURLConnection(URL url) {
		
	}
	
	/**
	 * URLConnection 连接之前执行
	 * @param urlConnection
	 */
	public void doBeforeConnect(HttpURLConnection urlConnection) {
		
	}
	
	/**
	 * URLConnection 连接之前执行
	 * @param urlConnection
	 */
	public void doAfterConnect(HttpURLConnection urlConnection) {
		
	}
	
	protected HttpURLConnection buildURLConnection(){
		try {
			URL urll = new URL(url) ;
			doBeforeBuildURLConnection(urll);
			HttpURLConnection urlConnection = (HttpURLConnection) urll.openConnection() ;
			for(String key : head.keySet()){
				urlConnection.setRequestProperty(key,head.get(key).toString()) ;
			}
			// 发送POST请求必须设置如下两行
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			return urlConnection;
		} catch (IOException e) {
			return null;
		}
	}
	
	public byte[] post(byte[] value) throws Exception {
		OutputStream out = null ;
		try{
			HttpURLConnection urlConnection = buildURLConnection();
			urlConnection.setRequestMethod("POST");
			doBeforeConnect(urlConnection) ;
			urlConnection.connect();
			if(value != null && value.length > 0){
				out = urlConnection.getOutputStream();
				out.write(value);
				out.flush();
			}
			doAfterConnect(urlConnection) ;
			return readInput(urlConnection) ;
		}catch(Exception e){
			throw e ;
		} finally {
			try {
				if(out != null){
					AutoCloseableFactory.close(out);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public byte[] get() throws Exception {
		try{
			HttpURLConnection urlConnection = buildURLConnection();
			urlConnection.setRequestMethod("GET");
			doBeforeConnect(urlConnection) ;
			urlConnection.connect();
			doAfterConnect(urlConnection) ;
			return readInput(urlConnection) ;
		}catch(Exception e){
			throw e ;
		}
	}
	
	private byte[] readInput(HttpURLConnection urlConnection) throws Exception {
		InputStream in = null ;
		if(urlConnection.getResponseCode() >= 400){
			in = urlConnection.getErrorStream();
		} else {
			in = urlConnection.getInputStream();
		}
		return IOStream.getInstance().readInputStream(in, true) ;
	}
}
