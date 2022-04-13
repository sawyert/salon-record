package uk.co.drumcoder.salon.framework;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.springframework.core.io.ClassPathResource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public interface XmlHelper {
    static Document parse(String xmlFilePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            InputStream inputStream = new ClassPathResource(xmlFilePath).getInputStream();
            org.w3c.dom.Document w3cDocument = documentBuilder.parse(inputStream);
            Document document = new DOMBuilder().build(w3cDocument);
            return document;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    static int getInt(Element element, String childName) {
        String text = element.getChildText(childName);
        if (text == null) {
            return 0;
        }
        return Integer.parseInt(text);
    }
}
