package SyntacseForDeserializing;

import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;

public interface DeserializationSyntacseWithParse extends DeserializingSyntacse {
    Object Parse(FileInputStream fileInputStream) throws IOException, SAXException;
}
