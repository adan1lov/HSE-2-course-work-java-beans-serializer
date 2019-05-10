package SerializerAndDeserializer;

import SyntacseForDeserializing.DeserializationSyntacseWithParse;
import SyntacseForDeserializing.DeserializationSyntacseWithoutParse;
import SyntacseForDeserializing.DeserializingSyntacse;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//TODO: Comments and deserialization without parse
public class JavaDeserializer {
    
    public JavaDeserializer(){ }
    
    DeserializationSyntacseWithoutParse des;
    
    public Object Make(FileInputStream stream, DeserializingSyntacse deserializingSyntacse)
        throws IOException, SAXException, ParserConfigurationException, IntrospectionException {
        if(deserializingSyntacse instanceof DeserializationSyntacseWithParse)
            return ((DeserializationSyntacseWithParse) deserializingSyntacse).Parse(stream);
        else {
            des =(DeserializationSyntacseWithoutParse)deserializingSyntacse;
            des.setStream(stream);
            des.removeHeader();
            Object o=readNonPrimitive();
        }
    }
    
    public void readPrimitive(PropertyDescriptor pd,Object o) throws InvocationTargetException, IllegalAccessException {
        pd.getReadMethod().invoke(o, des.readPrimitive());
    }
    
    public Object readNonPrimitive() throws IntrospectionException {
        o=des.readObject();
        BeanInfo beanInfo= Introspector.getBeanInfo(o.getClass(), Object.class);
        PropertyDescriptor[] propertyDescriptors=beanInfo.getPropertyDescriptors();
        
    }
}
