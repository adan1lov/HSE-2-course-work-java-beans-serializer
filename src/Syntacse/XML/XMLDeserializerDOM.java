package Syntacse.XML;

import SyntacseForDeserializing.DeserializationSyntacseWithoutParse;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

public class XMLDeserializerDOM implements DeserializationSyntacseWithoutParse {

    Node java;

    @Override
    public void setStream(FileInputStream stream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(stream);
            java = document.getChildNodes().item(0);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(Object o, String type) {

    }

    @Override
    public void removeHeader() {
    }
}
