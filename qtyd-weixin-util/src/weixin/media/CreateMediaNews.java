package weixin.media;

import weixin.accesstoken.AccessToken;

import com.common.http.JavaURLConnection;
import com.common.param.WeixinParameter;
import com.common.util.ReadFile;

public class CreateMediaNews {

	private static String fileName = "";
	public static void main(String[] args) {
		createMediaNews("CreateMediaNews_lysj.json");
	}
	
	public static void createMediaNews(String filename) {
		try {
			fileName = filename ;
			String retstr = JavaURLConnection.sendPost(getUrl(),getValue());
			System.out.println(retstr);
		} catch (Exception e) {
			
		}
	}

	private static String getUrl() {
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=");
		url.append(AccessToken.access_taken);
		return url.toString();
	}

	private static String getValue() {
		String filePath = CreateMediaNews.class.getResource("").getPath() + "/" + fileName ;
		filePath = filePath.replaceAll("%20", " ");
		String value = ReadFile.read(filePath, WeixinParameter.weixin_charset);
		return value.replaceAll(" ", " ").replaceAll("\r\n", "").replaceAll("\n", "");
	}
	
	
}
