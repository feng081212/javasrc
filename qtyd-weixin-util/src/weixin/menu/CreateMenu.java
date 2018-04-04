package weixin.menu;

import org.json.JSONObject;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;
import com.common.param.WeixinParameter;
import com.common.util.ReadFile;

public class CreateMenu {

	public static String fileName = "CreateMenu.json";
	
	public static void main(String[] args) {
		createMenu();
//		HashMap requestContent = new HashMap<String, Object>() ;
//		Object obj = requestContent.get("sss") ;
//		if(obj == null) 
//			System.out.println("sb");
//		String val = String.valueOf(obj) ;
//		System.out.println(val);
	}
	
	public static void createMenu(){
		try{
			String ret = JavaURLConnection.sendPost(getUrl(),getValue());
			System.out.println(ret);
			JSONObject jsonret = new JSONObject(ret);
			String errcode = String.valueOf( jsonret.get("errcode"));
			if(AccessToken.isAccessTokenError(errcode)){
				AccessToken.getAccessToken();
				createMenu();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getUrl(){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=");
		url.append(AccessToken.access_taken);
		return url.toString();
	}
	
	public static String getValue(){	
		String filePath = CreateMenu.class.getResource("").getPath() + "/" + fileName ;
		filePath = filePath.replaceAll("%20", " ");
		String value = ReadFile.read(filePath, WeixinParameter.weixin_charset);
		return value.replaceAll(" ", "").replaceAll("\r\n", "").replaceAll("\n", "");
	}
	
}
