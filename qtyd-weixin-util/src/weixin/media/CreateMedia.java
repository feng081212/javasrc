package weixin.media;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;
import com.common.param.WeixinParameter;
import com.common.util.ReadFile;

public class CreateMedia {

	public static void main(String[] args) {
		createMedia("image","D:/weixin/image/lysj.jpg");
	}
	
	public static String image = "image";
	public static String voice = "voice";
	public static String video = "video";
	public static String thumb = "thumb";
	
	public static void createMedia(String type,String filePath) {
		if(!isMediaType(type)) {
			System.out.println("Media Type is error");
			return ;
		}
		try {
			// 边界信息
			String BOUNDARY = "----------" + System.currentTimeMillis();
			// 请求正文信息
			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
					+ "0.jpg\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] head = sb.toString().getBytes(WeixinParameter.weixin_charset);

			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			byte[] body = getValue(filePath);
			
			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(WeixinParameter.weixin_charset);// 定义最后数据分隔线
			
			byte[][] centent = new byte[3][];
			centent[0] = head ;
			centent[1] = body ;
			centent[2] = foot ;
			
			String retstr = JavaURLConnection.sendlist(getUrl(type),centent,BOUNDARY);
			System.out.println(retstr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getUrl(String type) {
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=");
		url.append(AccessToken.access_taken);
		url.append("&type=").append(type);
		return url.toString();
	}

	private static byte[] getValue(String filePath) {
		return ReadFile.readByte(filePath);
	}
	
	public static boolean isMediaType(String type){
		if(type==null||type.equals(""))
			return false;
		if(type.equals(image))
			return true;
		if(type==null||type.equals(video))
			return true;
		if(type==null||type.equals(voice))
			return true;
		if(type==null||type.equals(thumb))
			return true;
		return false ;
	}
}
