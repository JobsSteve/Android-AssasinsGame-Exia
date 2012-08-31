package metier.all_purpose;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class PageLoaderHelper {

	public static final String SERVER_URL_AND_PORT = "http://192.168.1.4:8080";

	public String getResponseFromUrl(URL url) throws IOException {
		int read;

		URLConnection urlconn = url.openConnection();
		Reader is = new InputStreamReader(new BufferedInputStream(
				urlconn.getInputStream()), "UTF-8");
		StringBuilder stringBuild = new StringBuilder();

		char[] buffer = new char[65535];

		read = is.read(buffer);

		while (read > 0) {
			stringBuild.append(buffer, 0, read-1);
			read = is.read(buffer);
		}

		return stringBuild.toString();

	}

	public String sendPostDataToUrl(URL url, Map<String, String> arguments)
			throws IOException {

		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

		HttpPost postRequest = new HttpPost();
		try {
			postRequest.setURI(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		Set<String> keys = arguments.keySet();

		for (String key : keys) {
			nameValuePairs.add(new BasicNameValuePair(key, arguments.get(key)));
		}

		postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		HttpResponse httpResponse = (HttpResponse) defaultHttpClient
				.execute(postRequest);

		HttpEntity httpEntity = httpResponse.getEntity();
		if (httpEntity != null) {
			Reader is = new InputStreamReader(new BufferedInputStream(
					httpEntity.getContent()), "UTF-8");

			StringBuilder sb = new StringBuilder();

			int read;

			char[] buffer = new char[65535];

			read = is.read(buffer);

			while (read > 0) {
				sb.append(buffer.toString(), 0, read);
				read = is.read(buffer);
			}

			return sb.toString();

		}

		// String finalPost = streBuild.toString();

		// OutputStream os = urlconn.getOutputStream();
		// os.write(finalPost.toString().getBytes("UTF-8"));

		// Reader is = new InputStreamReader(new BufferedInputStream(
		// urlconn.getInputStream()), "UTF-8");
		// StringBuilder stringBuild = new StringBuilder();
		//
		//
		//
		// is.close();
		// os.close();

		return "";
	}
}
