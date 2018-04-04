package weixin.media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;
import com.common.param.WeixinParameter;
import com.common.util.ReadFile;

public class GetMedia {
	public static void main(String[] args) {
		//getMedia();
		getMediaNews();
		try {
			//getimage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getimage() throws UnsupportedEncodingException{
		//String value = "{\"request\":{\"common\":{\"action\":\"weixin_request\",\"reqtime\":\"20150703152040\",\"version\":\"1.0\"},\"content\":{\"accessid\":\"96ac0342a3ccf9553e3d4c9da9b821b0\",\"ip\":\"115.29.193.1\",\"signature\":\"qtyd\",\"timestamp\":null,\"nonce\":null,\"echostr\":\"{\"type\":\"image\",\"id\":\"2.png\"}\"}}}";
		
		//String b = JavaURLConnection.send("http://localhost:8080/http/HttpService", value);

		String b = JavaURLConnection.sendPost("http://wx.qtyd.com/weixin?signature=qtyd&echostr={%22type%22:%22image%22,%22id%22:%222.png%22}", "");
		System.out.println(b);
		byte[] bb = b.getBytes("iso_8859_1");
		for(int i=0;i<bb.length;i++)
			System.out.print(bb[i]+"/");
		System.out.println();
		//System.out.println(new String(bb,"utf-8"));
		//InputStream is = new ByteArrayInputStream(b.getBytes("utf-8"));
		try {
			FileOutputStream fos = new FileOutputStream(new File("D:/hello.png"));
//			int length = 0 ;
//			byte[] bb = new byte[2014];
//			while((length=is.read(bb))>0)
//				fos.write(bb,0,length);
			fos.write(bb);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getMedia(){
		byte[] b = JavaURLConnection.sendReturnByte(getUrl(), getValue());
		try {
			FileOutputStream fos = new FileOutputStream(new File("D:/hello.jpg"));
			fos.write(b);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getMediaNews(){
		String retstr = JavaURLConnection.sendPost(getUrl(), getValue());
		System.out.println(retstr);
	}
	
	private static String getUrl(){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=");
		url.append(AccessToken.access_taken);
		return url.toString();
	}
	
	private static String getValue(){
		String filePath = CreateMediaNews.class.getResource("").getPath() + "/GetMedia.json"  ;
		filePath = filePath.replaceAll("%20", " ");
		String value = ReadFile.read(filePath, WeixinParameter.weixin_charset);
		return value.replaceAll(" ", " ").replaceAll("\r\n", "").replaceAll("\n", "");
	}
}
