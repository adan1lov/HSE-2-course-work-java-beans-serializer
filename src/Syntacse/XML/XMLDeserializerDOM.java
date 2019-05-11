//package Syntacse.XML;
//
//import SyntacseForDeserializing.DeserializationSyntacseWithParse;
//import SyntacseForDeserializing.DeserializationSyntacseWithoutParse;
//import java.beans.BeanInfo;
//import java.beans.IntrospectionException;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Stack;
//import javafx.util.Pair;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import org.w3c.dom.Document;
//import org.xml.sax.SAXException;
//import org.w3c.dom.Node;
//
//public class XMLDeserializerDOM implements DeserializationSyntacseWithParse {
//
//    Stack<Node> dfs= new Stack<>();
//    boolean begin=true;
//    Map<String,Object> objectMap= new HashMap<>();
//
//
//    public void setStream(FileInputStream stream) {
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = null;
//            builder = factory.newDocumentBuilder();
//            Document document = builder.parse(stream);
//            dfs.push(document.getChildNodes().item(0));
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public Object readObject()
//        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        if(begin){
//            String className=getClassName(dfs.peek().getChildNodes().item(1));
//            begin=false;
//            Class cl=Class.forName(className);
//            Object obj=cl.newInstance();
//            objectMap.put(dfs.peek().getChildNodes().item(1).getAttributes().getNamedItem("id").getNodeValue(),obj);
//            return obj;
//        }
//
//    }
//
//
//
//
//
//    public String getClassName(Node node){
//        return node.getAttributes().getNamedItem("class").getNodeValue();
//    }
//
//    @Override
//    public Object Parse(FileInputStream fileInputStream) throws IOException, SAXException {
//        setStream(fileInputStream);
//
//    }
//
//    public Object makeAll(Object o)
//        throws IllegalAccessException, InstantiationException, ClassNotFoundException, IntrospectionException {
//        Object obj=readObject();
//        BeanInfo beanInfo= Introspector.getBeanInfo(obj.getClass(), Object.class);
//        for (PropertyDescriptor pd:beanInfo.getPropertyDescriptors()) {
//
//        }
//        return obj;
//    }
//}
