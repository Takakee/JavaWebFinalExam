package utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIHelper {
	private String msg;

	public APIHelper(String msg) {
		this.msg = msg;
	}

	public String getJsonMsg() throws Exception, IOException {
		String result="";
		// 访问API
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + msg);
		CloseableHttpResponse closeableResponse = null;
		try {
			closeableResponse = closeableHttpClient.execute(httpGet);
			if (closeableResponse.getStatusLine().getStatusCode() == 200) 
			{
				HttpEntity entity = closeableResponse.getEntity();
				String json = EntityUtils.toString(entity, "utf-8");
				JsonParser jsonParser = new JsonParser();
				JsonElement jsonElement = jsonParser.parse(json);
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				String msgJson = jsonObject.get("content").toString();
				System.out.println("API返回的content为：" + msgJson);
				result = msgJson.replace("\"", "");
			}
			return result;
		} finally {
			if (closeableResponse != null) {
				closeableResponse.close();
			}
			closeableHttpClient.close();
		}
	}
}
