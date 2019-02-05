package JavaSerializer;


import java.beans.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Danilov Alexey HSE BSE171
 */
public class JavaSerializer {

    //fields
    private Object _object;
    private Syntacse _syntacse;

    public JavaSerializer(Object o) {
        _object = o;
    }

    public void Make(String path, String serializationType) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(_object.getClass());

        String output = "";
        if ("json".equals(serializationType))
            _syntacse = new JsonMaker();
        if (_object == null)
            output = _syntacse.header() + _syntacse.end();
        else {
            output = _syntacse.header();
            String type = _object.getClass().getSimpleName();
            output += _syntacse.nonPrimitiveBegin(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : propertyDescriptors) {
                if (!"class".equals(pd.getDisplayName())) {
                    if (pd.getPropertyType().isPrimitive() || pd.getPropertyType().isAssignableFrom(String.class))
                        output += primitiveType(pd, 1, _object);
                    else {
                        output += nonPrimitiveType(pd, 1, _object);
                    }
                }
            }

            output += _syntacse.nonPrimitiveEnd(type);
            output += _syntacse.end();
        }

        System.out.println(output);
    }

    private String writeAll(BeanInfo beanInfo, int tabs, Object obj) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        String output = "";
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
            if (!"class".equals(propertyDescriptor.getDisplayName()))
                if (propertyDescriptor.getPropertyType().isPrimitive() || propertyDescriptor.getPropertyType().isAssignableFrom(String.class))
                    output += primitiveType(propertyDescriptor, tabs + 1, obj);
                else
                    output += nonPrimitiveType(propertyDescriptor, tabs + 1, obj);
        return output;
    }

    private String primitiveType(PropertyDescriptor pd, int tabs, Object o) throws InvocationTargetException, IllegalAccessException {
        String output = "";
        String tabsOut = "";
        if (pd.getReadMethod() != null) {
            for (int i = 0; i < tabs; i++)
                tabsOut += "\t";
            output = tabsOut +
                    _syntacse.primitive(
                            pd.getDisplayName(),
                            pd.getReadMethod().invoke(o).toString()
                    ) + "\n";
        }
        return output;
    }

    private String nonPrimitiveType(PropertyDescriptor pd, int tabs, Object o) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        String output = "";
        String tabsOut = "";

        if (pd.getReadMethod() != null) {
            for (int i = 0; i < tabs; i++)
                tabsOut += "\t";

            Object obj = pd.getReadMethod().invoke(o);
            if (pd.getPropertyType().isArray()) {
                output += tabsOut + _syntacse.arrayBegin(pd.getPropertyType().getSimpleName());
                Class var6 = pd.getPropertyType();
                if (var6 == byte[].class) {
                    for (byte b : (byte[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(byte.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == short[].class) {
                    for (short b : (short[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(short.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == int[].class) {
                    for (int b : (int[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(int.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == long[].class) {
                    for (long b : (long[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(long.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == char[].class) {
                    for (char b : (char[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(char.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == float[].class) {
                    for (float b : (float[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(float.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == double[].class) {
                    for (double b : (double[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(double.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == boolean[].class) {
                    for (boolean b : (boolean[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(boolean.class.getSimpleName(), b + "") + "\n";
                } else if (var6 == String[].class) {
                    for (String b : (String[]) obj)
                        output += tabsOut + "\t" + _syntacse.primitive(String.class.getSimpleName(), b) + "\n";
                } else {
                    for (Object b : (Object[]) obj) {
                        output += tabsOut + "\n" + _syntacse.nonPrimitiveBegin(b.getClass().getSimpleName());
                        BeanInfo beanInfo = Introspector.getBeanInfo(b.getClass());
                        output += writeAll(beanInfo, tabs + 1, b);
                        output += tabsOut + "\n" + _syntacse.nonPrimitiveEnd(b.getClass().getSimpleName());
                    }
                }
                output += tabsOut + _syntacse.arrayEnd(pd.getPropertyType().getSimpleName());
            }
            else if(obj instanceof Iterable) {
                while (((Iterable) obj).iterator().hasNext()){
                    Object o1=((Iterable) obj).iterator().next();
                    System.out.println(o1.toString());
                }
            }
            else {
                output += tabsOut + _syntacse.nonPrimitiveBegin(pd.getReadMethod().invoke(o).getClass().getSimpleName());
                BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
                output += writeAll(beanInfo, tabs, obj);
                output += tabsOut + _syntacse.nonPrimitiveEnd(o.getClass().getSimpleName());
            }

        }
        return output;
    }


    abstract private class Syntacse {
        abstract String header();

        abstract String end();

        abstract String primitive(String name, String param);

        abstract String nonPrimitiveBegin(String type);

        abstract String nonPrimitiveEnd(String type);

        abstract String arrayBegin(String type);

        abstract String arrayEnd(String type);

    }

    private class JsonMaker extends Syntacse {

        @Override
        String header() {
            return "begin\n";
        }

        @Override
        String end() {
            return "end";
        }

        @Override
        String primitive(String name, String param) {
            return name + " : " + param + ";";
        }

        @Override
        String nonPrimitiveBegin(String type) {
            return type + "{\n";
        }

        @Override
        String nonPrimitiveEnd(String type) {
            return "}\n";
        }

        @Override
        String arrayBegin(String type) {
            return "ArrayOf" + type + "\b\b" + " {\n";
        }

        @Override
        String arrayEnd(String type) {
            return "}\n";
        }
    }
}
