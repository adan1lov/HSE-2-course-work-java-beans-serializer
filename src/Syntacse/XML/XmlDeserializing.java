package Syntacse.XML;

import SyntacseForDeserializing.DeserializationSyntacseWithParse;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class XmlDeserializing implements DeserializationSyntacseWithParse {


    private Map<String, Object> objectMap = new HashMap<>();
    private String input;
    private long ObjectBegin = 0;
    private Stack<Object> dfs = new Stack<>();
    private Stack<Invoker> dfsVoid = new Stack<>();
    private Map<Class, BeanInfo> beanInfoMap = new HashMap<>();
    private boolean check = false;


    public XmlDeserializing() {
    }

    @Override
    public Object Parse(String path) throws IOException {
        List<String> xml = Files.readAllLines(Paths.get(path));
        StringBuilder s = new StringBuilder();
        for (String str : xml) {
            s.append(str);
        }
        input = s.toString();
        removeHeader();
        while (true) {
            boolean b;
            long start = ObjectBegin;
            while (input.charAt((int) start) != '<') {
                start++;
            }
            long end = start;
            while (input.charAt((int) end) != '>') {
                end++;
            }
            long start2 = end;
            b = input.charAt((int) start + 1) != '/';
            String str = input.substring((int) start + 1, (int) end);
            String[] params = str.split(" ");
            String qName = params[0];

            if (b) {
                Element elem = new Element();
                for (int i = 1; i < params.length; i++) {
                    elem.setValue(params[i].substring(0, params[i].indexOf('=')), params[i]
                        .substring(params[i].indexOf('\"') + 1, params[i].lastIndexOf('\"')));
                }
                startElement(qName, elem);
            } else {
                endElement(qName);
            }
            if (start2 + 1 == input.length()) {
                break;
            }
            while (input.charAt((int) start2) != '<') {
                start2++;
            }
            if (b) {
                characters(input.substring((int) end + 1, (int) start2));
            }
            ObjectBegin = start2;
        }
        return dfs.peek();
    }

    private void startElement(String qName, Element attributes) {
        if ("void".equals(qName)) {
            String methodName = attributes.getValue("method");
            if (methodName != null) {
                dfsVoid.push(new MethodInvoker(methodName, dfs.peek()));
                return;
            }
            String property = attributes.getValue("property");
            if (property != null) {

                BeanInfo beanInfo = beanInfoMap.get(dfs.peek().getClass());
                if (beanInfo == null) {
                    try {
                        beanInfo = Introspector
                            .getBeanInfo(dfs.peek().getClass(), Object.class);
                        beanInfoMap.put(dfs.peek().getClass(), beanInfo);
                    } catch (IntrospectionException e) {
                        e.printStackTrace();
                    }
                }
                assert beanInfo != null;
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor p : propertyDescriptors) {
                    if (property.equals(p.getName()) && p.getWriteMethod() != null) {
                        dfsVoid.push(new PropertyInvoker(p, dfs.peek()));
                        return;
                    }
                }
                return;
            }
            String indexString = attributes.getValue("index");
            if (indexString != null) {
                dfsVoid.push(new Indexer(Integer.parseInt(indexString), dfs.peek()));
            }
        } else if ("object".equals(qName)) {
            String reference = attributes.getValue("idref");
            if (reference != null) {
                dfs.push(objectMap.get(reference));
            } else {
                String id = attributes.getValue("id");
                try {
                    Class cl = Class.forName(attributes.getValue("class"));
                    Object o = cl.newInstance();
                    objectMap.put(id, o);
                    dfs.push(o);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        } else if ("array".equals(qName)) {
            try {
                String id = attributes.getValue("id");
                int length = Integer.parseInt(attributes.getValue("length"));
                String className = attributes.getValue("class");
                Class cl;
                if ("byte".equals(className)) {
                    cl = byte.class;
                } else if ("int".equals(className)) {
                    cl = int.class;
                } else if ("boolean".equals(className)) {
                    cl = boolean.class;
                } else if ("short".equals(className)) {
                    cl = short.class;
                } else if ("double".equals(className)) {
                    cl = double.class;
                } else if ("float".equals(className)) {
                    cl = float.class;
                } else if ("char".equals(className)) {
                    cl = char.class;
                } else if ("long".equals(className)) {
                    cl = long.class;
                } else if ("string".equals(className)) {
                    cl = String.class;
                } else {
                    cl = Class.forName(className);
                }

                Object o = Array.newInstance(cl, length);
                dfs.push(o);
                objectMap.put(id, o);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if ("int".equals(qName)) {
            dfs.push(0);
            check = true;
        } else if ("short".equals(qName)) {
            dfs.push((short) 0);
            check = true;
        } else if ("double".equals(qName)) {
            dfs.push((double) 0);
            check = true;
        } else if ("float".equals(qName)) {
            dfs.push((float) 0);
            check = true;
        } else if ("long".equals(qName)) {
            dfs.push((long) 0);
            check = true;
        } else if ("boolean".equals(qName)) {
            dfs.push(false);
            check = true;
        } else if ("byte".equals(qName)) {
            dfs.push((byte) 0);
            check = true;
        } else if ("char".equals(qName)) {
            dfs.push((char) 0);
            check = true;
        } else if ("string".equals(qName)) {
            dfs.push("");
            check = true;
        }
    }

    private void characters(String str) {
        if (dfs.empty() || !check) {
            return;
        }

        Object o = dfs.pop();
        if (o.getClass() == Integer.class) {
            dfs.push(Integer.parseInt(str));
            check = false;
        } else if (o.getClass() == Long.class) {
            dfs.push(Long.parseLong(str));
            check = false;
        } else if (o.getClass() == Byte.class) {
            dfs.push(Byte.parseByte(str));
            check = false;
        } else if (o.getClass() == Short.class) {
            dfs.push(Short.parseShort(str));
            check = false;
        } else if (o.getClass() == Character.class) {
            dfs.push(str.charAt(0));
            check = false;
        } else if (o.getClass() == Double.class) {
            dfs.push(Double.parseDouble(str));
            check = false;
        } else if (o.getClass() == Float.class) {
            dfs.push(Float.parseFloat(str));
            check = false;
        } else if (o.getClass() == Boolean.class) {
            dfs.push(Boolean.parseBoolean(str));
            check = false;
        } else if (o.getClass() == String.class) {
            dfs.push(str);
            check = false;
        } else {
            dfs.push(o);
            check = false;
        }
    }

    private void endElement(String qName) {
        if ("/void".equals(qName)) {
            dfsVoid.pop().invoke(dfs.pop());
        }
    }

    private void removeHeader() {
        if (input.substring(2, 5).toLowerCase().equals("xml")) {
            int i = 5;
            while (input.charAt(i) != '>') {
                i++;
            }
            ObjectBegin = i + 1;

        }
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
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
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
            } catch (IllegalAccessException | InvocationTargetException e) {
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
}

