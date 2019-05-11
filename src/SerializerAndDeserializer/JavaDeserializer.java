package SerializerAndDeserializer;

import SyntacseForDeserializing.DeserializationSyntacseWithParse;
import SyntacseForDeserializing.DeserializationSyntacseWithoutParse;
import SyntacseForDeserializing.DeserializingSyntacse;
import java.io.BufferedReader;
import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;

//TODO: Comments and deserialization without parse
public class JavaDeserializer {
    
    public JavaDeserializer(){ }

    public Object Make(FileInputStream stream, DeserializingSyntacse deserializingSyntacse)
        throws IOException, SAXException, ParserConfigurationException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (deserializingSyntacse instanceof DeserializationSyntacseWithParse)
            return ((DeserializationSyntacseWithParse) deserializingSyntacse).Parse(stream);
        return null;

    }
}
