package metier.all_purpose;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class PageLoaderHelper {

	public String getResponseFromUrl(URL url) throws IOException {
		int read;

		URLConnection urlconn = url.openConnection();
		Reader is = new InputStreamReader(new BufferedInputStream(
				urlconn.getInputStream()), "UTF-8");
		StringBuilder stringBuild = new StringBuilder();

		char[] buffer = new char[65535];

		read = is.read(buffer);

		while (read > 0) {
			stringBuild.append(buffer, 0, read);
			read = is.read(buffer);
		}

		return stringBuild.toString();

	}

}
