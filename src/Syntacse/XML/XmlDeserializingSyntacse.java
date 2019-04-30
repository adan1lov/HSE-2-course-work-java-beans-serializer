package Syntacse.XML;

import SyntacseForDeserializing.DeserializationSyntacseWithParse;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class XmlDeserializingSyntacse implements DeserializationSyntacseWithParse {
    SAXParser parser;
    FileInputStream fileInputStream;
    
    
    public XmlDeserializingSyntacse() throws ParserConfigurationException, SAXException, IOException {
        parser = SAXParserFactory.newInstance().newSAXParser();
    }
    
    @Override
    public Object Parse(FileInputStream fileInputStream) throws IOException, SAXException {
        parser.parse(fileInputStream,new XMLParser());
        
        return null;
    }
}

class XMLParser extends DefaultHandler {

}