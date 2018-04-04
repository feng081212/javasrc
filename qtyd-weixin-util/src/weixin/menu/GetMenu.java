package weixin.menu;

import org.json.JSONObject;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;

public class GetMenu {

	public static void main(String[] args) {
		System.out.println(getMenu());
	}
	
	public static String getMenu(){
		try{
			String ret = JavaURLConnection.sendPost(getUrl(), getValue());
			System.out.println(ret);
			JSONObject jsonret = new JSONObject(ret);
			return String.valueOf(jsonret.get("menu"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getUrl(){
		StringBuffer url = new StringBuffer();
		//url.append("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=");
		url.append("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=");
		//url.append("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=");
		url.append(AccessToken.access_taken);
		//url.append("&type=jsapi");
		//url.append("jsapi_ticket=kgt8ON7yVITDhtdwci0qeevwcymGyDdJsupAqRHkjj_ZiRsaRSRFB5TaNmjiXY5QsF__IRn9hXOVHuG0D_iFSg&noncestr=GPvxwrQJ4KUBpHIR0CWSFTxim4GXubv6LuzHikJBgMA&timestamp=1439177104&url=http://wx.qtyd.com/mobile/user/weixinreg.html");
		return url.toString();
	}
	
	public static String getValue(){
		return "";
	}
}
