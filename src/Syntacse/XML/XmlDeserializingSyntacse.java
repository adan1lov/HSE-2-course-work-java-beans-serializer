package Syntacse.XML;

import SyntacseForDeserializing.DeserializationSyntacseWithParse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class XmlDeserializingSyntacse implements DeserializationSyntacseWithParse {
    SAXParser parser;
    Object object = null;
    Map<String, Object> objectMap = new HashMap<>();
    
    public XmlDeserializingSyntacse() throws ParserConfigurationException, SAXException, IOException {
        parser = SAXParserFactory.newInstance().newSAXParser();
    }
    
    @Override
    public Object Parse(FileInputStream fileInputStream) throws IOException, SAXException {
        parser.parse(fileInputStream, new XMLParser());
        
        return object;
    }
    
    interface Invoker {
        void invoke(Object o);
    }
    
    class MethodInvoker implements Invoker {
        
        Object fromObject;
        String name;
        
        MethodInvoker(String name, Object fromObject) {
            this.name = name;
            this.fromObject = fromObject;
        }
        
        @Override
        public void invoke(Object o) {
            try {
                fromObject.getClass().getMethod(name, Object.class).invoke(fromObject, o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
    
    class PropertyInvoker implements Invoker {
        
        PropertyDescriptor propertyDescriptor;
        Object fromObject;
        
        PropertyInvoker(PropertyDescriptor propertyDescriptor, Object fromObject) {
            this.propertyDescriptor = propertyDescriptor;
            this.fromObject = fromObject;
        }
        
        @Override
        public void invoke(Object o) {
            try {
                propertyDescriptor.getWriteMethod().invoke(fromObject, o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    
    class Indexer implements Invoker {
        int index;
        Object arrayObject;
        
        Indexer(int index, Object arrayObject) {
            this.index = index;
            this.arrayObject = arrayObject;
        }
        
        @Override
        public void invoke(Object objectToPush) {
            if (arrayObject.getClass() == byte[].class) {
                ((byte[]) arrayObject)[index] = (byte) objectToPush;
            } else if (arrayObject.getClass() == short[].class) {
                ((short[]) arrayObject)[index] = (short) objectToPush;
            } else if (arrayObject.getClass() == int[].class) {
                ((int[]) arrayObject)[index] = (int) objectToPush;
            } else if (arrayObject.getClass() == double[].class) {
                ((double[]) arrayObject)[index] = (double) objectToPush;
            } else if (arrayObject.getClass() == float[].class) {
                ((float[]) arrayObject)[index] = (float) objectToPush;
            } else if (arrayObject.getClass() == long[].class) {
                ((long[]) arrayObject)[index] = (long) objectToPush;
            } else if (arrayObject.getClass() == char[].class) {
                ((char[]) arrayObject)[index] = (char) objectToPush;
            } else if (arrayObject.getClass() == boolean[].class) {
                ((boolean[]) arrayObject)[index] = (boolean) objectToPush;
            } else if (arrayObject.getClass() == String[].class) {
                ((String[]) arrayObject)[index] = (String) objectToPush;
            } else {
                ((Object[]) arrayObject)[index] = objectToPush;
            }
        }
    }
    
    class XMLParser extends DefaultHandler {
        Stack<Object> dfs = new Stack<>();
        Stack<Invoker> dfsVoid = new Stack<>();
        
        @Override
        public void characters(char[] c, int start, int length)
            throws SAXException {
            if (dfs.empty())
                return;
            boolean check = false;
            String str = "";
            for (int i = start; i < start + length; ++i) {
                str += c[i];
                if (c[i] != '\n' && c[i] != '\t' &&  c[i]!=' ')
                    check = true;
            }
            if (!check)
                return;
            Object o = dfs.pop();
            if (o.getClass() == Integer.class) {
                dfs.push(Integer.parseInt(str));
            } else if (o.getClass() == Long.class) {
                dfs.push(Long.parseLong(str));
            } else if (o.getClass() == Byte.class) {
                dfs.push(Byte.parseByte(str));
            } else if (o.getClass() == Short.class) {
                dfs.push(Short.parseShort(str));
            } else if (o.getClass() == Character.class) {
                dfs.push(str.charAt(0));
            } else if (o.getClass() == Double.class) {
                dfs.push(Double.parseDouble(str));
            } else if (o.getClass() == Float.class) {
                dfs.push(Float.parseFloat(str));
            } else if (o.getClass() == Boolean.class) {
                dfs.push(Boolean.parseBoolean(str));
            } else if (o.getClass() == String.class) {
                dfs.push(str);
            } else dfs.push(o);
            
        }
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("object".equals(qName)) {
                String reference = attributes.getValue("idref");
                if (reference != null) {
                    dfs.push(objectMap.get(reference));
                } else {
                    String id = attributes.getValue("id");
                    try {
                        Class cl = Class.forName(attributes.getValue("class"));
                        Object o = cl.newInstance();
                        if (dfs.empty() && objectMap.isEmpty())
                            object = o;
                        objectMap.put(id, o);
                        dfs.push(o);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            } else if ("void".equals(qName)) {
                String methodName = attributes.getValue("method");
                if (methodName != null) {
                    dfsVoid.push(new MethodInvoker(methodName, dfs.peek()));
                }
                String property = attributes.getValue("property");
                if (property != null) {
                    try {
                        BeanInfo beanInfo = Introspector.getBeanInfo(dfs.peek().getClass(), Object.class);
                        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                        for (PropertyDescriptor p : propertyDescriptors)
                            if (property.equals(p.getName()) && p.getWriteMethod() != null)
                                dfsVoid.push(new PropertyInvoker(p, dfs.peek()));
                    } catch (IntrospectionException e) {
                        e.printStackTrace();
                    }
                }
                String indexString = attributes.getValue("index");
                if (indexString != null) {
                    dfsVoid.push(new Indexer(Integer.parseInt(indexString), dfs.peek()));
                }
            }else
            if ("array".equals(qName)) {
                try {
                    String id = attributes.getValue("id");
                    int length = Integer.parseInt(attributes.getValue("length"));
                    Class cl = Class.forName(attributes.getValue("class"));
                    Object o=Array.newInstance(cl,length);
                    dfs.push(o);
                    objectMap.put(id,o);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if ("int".equals(qName)) {
                dfs.push(0);
            } else if ("short".equals(qName)) {
                dfs.push((short) 0);
            } else if ("double".equals(qName)) {
                dfs.push((double) 0);
            } else if ("float".equals(qName)) {
                dfs.push((float) 0);
            } else if ("long".equals(qName)) {
                dfs.push((long) 0);
            } else if ("boolean".equals(qName)) {
                dfs.push(false);
            } else if ("byte".equals(qName)) {
                dfs.push((byte) 0);
            } else if ("char".equals(qName)) {
                dfs.push((char) 0);
            } else if ("string".equals(qName)) {
                dfs.push("");
            } else if ("java".equals(qName)) {
            
            }
        }
        
        @Override
        public void endElement(String uri, String localName, String qName)
            throws SAXException {
            if ("void".equals(qName))
                dfsVoid.pop().invoke(dfs.pop());
        }
    }
}

