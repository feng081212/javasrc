package weixin.media;

import org.json.JSONObject;
import weixin.accesstoken.AccessToken;
import com.common.http.JavaURLConnection;
import com.common.param.WeixinParameter;
import com.common.util.MediaUtil;
import com.common.util.ReadFile;

public class GetMediaList {
	
	private static String fileName = "";
	private static int total = 1 ;
	public static void main(String[] args) {
		getMedia("GetMediaList.json");
	}
	
	public static void getMedia(String filename){
		try{
			fileName = filename ;
			JSONObject json = new JSONObject(getValue());
			int cou = total/20;
			System.out.println(cou);
			for(int i=0;i<=cou;i++){
				json.put("offset", String.valueOf(i*20));
				String retstr = JavaURLConnection.sendPost(getUrl(), json.toString());
				System.out.println(retstr);
				new MediaUtil().MediaListUtil(retstr);
			}
		}catch(Exception e){
			
		}
	}
	
	private static String getUrl(){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=");
		url.append(AccessToken.access_taken);
		return url.toString();
	}
	
	private static String getValue(){
		String filePath = CreateMediaNews.class.getResource("").getPath() + "/" + fileName ;
		filePath = filePath.replaceAll("%20", " ");
		String value = ReadFile.read(filePath, WeixinParameter.weixin_charset);
		return value.replaceAll(" ", " ").replaceAll("\r\n", "").replaceAll("\n", "");
	}
}
