package in.appikonda.gifs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
	private static final String filePath = "http://jeswanth.info/gifsApp/Gifs.json";

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	
	// get all the gifs
	public List<String> getItAll(String searchTag) throws JSONException, IOException {
		JSONObject json = readJsonFromUrl(filePath);
		System.out.println(json.toString());

		JSONArray celebArray = (JSONArray) json.get("celebrity");

		Iterator iter = celebArray.iterator();
		List<String> allUrls = new ArrayList<String>();
		while (iter.hasNext()) {

			JSONObject innerObject = (JSONObject) iter.next();
			String name = (String) innerObject.get("name");
			System.out.println("name : " + name);
			if (searchTag.contains(name)) {
				JSONArray contentArray = (JSONArray) innerObject.get("content");

				for (int i = 0; i < contentArray.length(); i++) {
					JSONObject gifContentObject = (JSONObject) contentArray.get(i);
					String gifNumString = (String) gifContentObject.get("gifNum");
					int gifNum = Integer.parseInt(gifNumString);
					String url = (String) gifContentObject.get("url");
					System.out.println("gifNum : " + gifNum + " url:" + url);
					allUrls.add(url);
				}

			}

		}
		return allUrls;
	}

}
