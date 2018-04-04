package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Child extends Parent {
	
	public Child(String hello){
		//super(hello);
//		String url = "app:share:{\"title\":\"%E3%80%90%E5%BF%AB%E4%B9%90%E8%B5%9A%E7%9B%98%E3%80%91%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%E5%85%8D%E8%B4%B9%E7%8E%A9%EF%BC%8Ciphone7%E7%AD%89%E6%82%A8%E6%8B%BF%EF%BC%81%E9%80%9F%E9%80%9F%E5%8F%82%E4%B8%8E%EF%BC%81\",\"content\":\"%E5%BF%AB%E4%B9%90%E8%B5%9A%E7%9B%98%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%E5%85%8D%E8%B4%B9%E7%8E%A9%E5%95%A6!iphone7%E3%80%81Apple Watch%E3%80%8110g%E9%87%91%E6%9D%A1%E3%80%81888%E5%85%83%E7%BA%A2%E5%8C%85%E3%80%81500%E5%85%83%E4%BA%AC%E4%B8%9C%E8%B4%AD%E7%89%A9%E5%8D%A1%E2%80%A6%E9%82%80%E8%AF%B7%E8%B6%8A%E5%A4%9A%EF%BC%8C%E5%A5%96%E5%93%81%E8%B6%8A%E5%A4%9A%EF%BC%8C100%%E4%B8%AD%E5%A5%96%E4%B8%8D%E5%AE%B9%E9%94%99%E8%BF%87%EF%BC%81\",\"img\":\"http://weixin.qtyd.com/assets/dist/topic/happylottery/images/happylottery_share.jpg?_r=0.2853897458408028\",\"url\":\"http%3A%2F%2Fweixin.qtyd.com%2Factivity%2Fturntable5.html\",\"type\":[\"wxtimeline\"]}&device_port=android" ;
//		url = url.replaceAll("%(?![0-9a-fA-F]{2})", "%25");  
//		System.out.println(url);
//		String jsonStr = url.replace("app:", "") ;
//		try {
//			jsonStr = URLDecoder.decode(jsonStr,"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Child running");
		int total = 21 ;
		int pageSize = 6 ;
		System.out.println(total / pageSize);
		System.out.println(total / pageSize + (total % pageSize > 0 ? 1 : 0));
		System.out.println( total / pageSize + (total % pageSize > 0 ? 1 : 0 ) + "");
		
	}
	
	public static void main(String[] args) {
		//new Child("") ;
		//app:share:{"title":"%E3%80%90%E5%BF%AB%E4%B9%90%E8%B5%9A%E7%9B%98%E3%80%91%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%E5%85%8D%E8%B4%B9%E7%8E%A9%EF%BC%8Ciphone7%E7%AD%89%E6%82%A8%E6%8B%BF%EF%BC%81%E9%80%9F%E9%80%9F%E5%8F%82%E4%B8%8E%EF%BC%81","content":"%E5%BF%AB%E4%B9%90%E8%B5%9A%E7%9B%98%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%E5%85%8D%E8%B4%B9%E7%8E%A9%E5%95%A6!iphone7%E3%80%81Apple Watch%E3%80%8110g%E9%87%91%E6%9D%A1%E3%80%81888%E5%85%83%E7%BA%A2%E5%8C%85%E3%80%81500%E5%85%83%E4%BA%AC%E4%B8%9C%E8%B4%AD%E7%89%A9%E5%8D%A1%E2%80%A6%E9%82%80%E8%AF%B7%E8%B6%8A%E5%A4%9A%EF%BC%8C%E5%A5%96%E5%93%81%E8%B6%8A%E5%A4%9A%EF%BC%8C100%25%E4%B8%AD%E5%A5%96%E4%B8%8D%E5%AE%B9%E9%94%99%E8%BF%87%EF%BC%81","img":"http://weixin.qtyd.com/assets/dist/topic/happylottery/images/happylottery_share.jpg?_r=0.2853897458408028","url":"http%3A%2F%2Fweixin.qtyd.com%2Factivity%2Fturntable5.html","type":["wxtimeline"]}&device_port=android
		//app:share:{"title":"%E3%80%90%E5%BF%AB%E4%B9%90%E8%B5%9A%E7%9B%98%E3%80%91%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%E5%85%8D%E8%B4%B9%E7%8E%A9%EF%BC%8Ciphone7%E7%AD%89%E6%82%A8%E6%8B%BF%EF%BC%81%E9%80%9F%E9%80%9F%E5%8F%82%E4%B8%8E%EF%BC%81","content":"%E5%BF%AB%E4%B9%90%E8%B5%9A%E7%9B%98%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%E5%85%8D%E8%B4%B9%E7%8E%A9%E5%95%A6!iphone7%E3%80%81Apple Watch%E3%80%8110g%E9%87%91%E6%9D%A1%E3%80%81888%E5%85%83%E7%BA%A2%E5%8C%85%E3%80%81500%E5%85%83%E4%BA%AC%E4%B8%9C%E8%B4%AD%E7%89%A9%E5%8D%A1%E2%80%A6%E9%82%80%E8%AF%B7%E8%B6%8A%E5%A4%9A%EF%BC%8C%E5%A5%96%E5%93%81%E8%B6%8A%E5%A4%9A%EF%BC%8C100%%E4%B8%AD%E5%A5%96%E4%B8%8D%E5%AE%B9%E9%94%99%E8%BF%87%EF%BC%81","img":"http://weixin.qtyd.com/assets/dist/topic/happylottery/images/happylottery_share.jpg?_r=0.2853897458408028","url":"http%3A%2F%2Fweixin.qtyd.com%2Factivity%2Fturntable5.html","type":["wxtimeline"]}&device_port=android
		System.out.println(Child.class.getClassLoader().getResource("").getPath()) ;
	}
}
