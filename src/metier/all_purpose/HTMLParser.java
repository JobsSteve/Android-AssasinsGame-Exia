package metier.all_purpose;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HTMLParser {
	public Document parseSource(String response)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = bdf.newDocumentBuilder();

		InputSource inputSource = new InputSource();
		inputSource.setCharacterStream(new StringReader(response));

		return db.parse(inputSource);

	}
}
