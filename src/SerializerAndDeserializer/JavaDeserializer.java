package SerializerAndDeserializer;

import SyntacseForDeserializing.DeserializationSyntacseWithParse;
import SyntacseForDeserializing.DeserializingSyntacse;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;

public class JavaDeserializer {
    
    public JavaDeserializer(){ }
    
    public Object Make(FileInputStream stream, DeserializingSyntacse deserializingSyntacse)
        throws IOException, SAXException {
        if(deserializingSyntacse instanceof DeserializationSyntacseWithParse)
            return ((DeserializationSyntacseWithParse) deserializingSyntacse).Parse(stream);
        return null;
    }
}
