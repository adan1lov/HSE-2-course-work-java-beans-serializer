package SerializerAndDeserializer;

import SyntacseForDeserializing.DeserializationSyntacseWithParse;
import SyntacseForDeserializing.DeserializingSyntacse;
import java.io.IOException;
import org.xml.sax.SAXException;

public class JavaDeserializer {

    public JavaDeserializer() {
    }

    /**
     * Main method that starts deserializing
     *
     * @param path path to read file
     * @param deserializingSyntacse type of deserialization
     * @return deserialized Object
     * @throws IOException if tou get this Exception, you have problem with your file
     * @throws SAXException if tou get this Exception, you have problem with XML serialization
     */
    public Object Make(String path, DeserializingSyntacse deserializingSyntacse)
        throws IOException {
        if (deserializingSyntacse instanceof DeserializationSyntacseWithParse) {
            return ((DeserializationSyntacseWithParse) deserializingSyntacse).Parse(path);
        }
        return null;
    }

}
