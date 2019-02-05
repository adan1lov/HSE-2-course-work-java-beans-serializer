package JavaSerializer;

import Syntacse.Syntacse;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Serializer class
 * This is the special class that was made for bean classes
 *
 * @author Danilov Alexey
 */
public class JavaSerializer {

    /*-----------------------------------------------------Fields-----------------------------------------------------*/

    private Object _object;
    private Syntacse _syntacse;

    /*---------------------------------------------------Constructor--------------------------------------------------*/

    /**
     * Simple constructor
     *
     * @param o Object which should be serialized
     */
    public JavaSerializer(Object o) {
        if (o == null || o.getClass().isPrimitive())
            throw new NullPointerException("Parameter is incorrect");
        _object = o;
    }

    /*-----------------------------------------------------Methods----------------------------------------------------*/

    /**
     * Main method that do all work
     *
     * @param path path to serialized file
     * @param s    syntacse of serialization
     */
    public void Make(String path, Syntacse s) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        //syntacse that we would use
        if (s == null)
            throw new NullPointerException("Syntacse was null");
        _syntacse = s;

        //result of serializing
        String output = _syntacse.header();
        output += nonPrimitiveToString(_object, 0);
        output += _syntacse.end();
        //TODO:made a file using path.
        System.out.println(output);
    }

    /**
     * Method to make string about primitive variables
     *
     * @param name name of the parameter
     * @param o    parameter that we should convert to string
     * @param tabs numbers of tabs before description
     * @return string with description of primitive
     */
    private String primitiveToString(String name, Object o, int tabs) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tabs; i++)
            output.append("\t");
        output.append(
                _syntacse.primitive(name, o.toString())
        );
        return output.toString();
    }

    private String nonPrimitiveToString(Object o, int tabs) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        String output = "";
        String tabsOut = "";
        for (int i = 0; i < tabs; i++)
            tabsOut += "\t";
        BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
        if (o.getClass().isArray()) {
            Class objectType = o.getClass();
            output += tabsOut + _syntacse.arrayBegin(objectType.getSimpleName());

            if (objectType == byte[].class) {
                for (byte b : (byte[]) o)
                    output += _syntacse.primitive(byte.class.getSimpleName(), b + "");
            } else if (objectType == short[].class) {
                for (short b : (short[]) o)
                    output += _syntacse.primitive(short.class.getSimpleName(), b + "");
            } else if (objectType == int[].class) {
                for (int b : (int[]) o)
                    output += _syntacse.primitive(int.class.getSimpleName(), b + "");
            } else if (objectType == long[].class) {
                for (long b : (long[]) o)
                    output += _syntacse.primitive(long.class.getSimpleName(), b + "");
            } else if (objectType == char[].class) {
                for (char b : (char[]) o)
                    output += _syntacse.primitive(char.class.getSimpleName(), b + "");
            } else if (objectType == float[].class) {
                for (float b : (float[]) o)
                    output += _syntacse.primitive(float.class.getSimpleName(), b + "");
            } else if (objectType == double[].class) {
                for (double b : (double[]) o)
                    output += _syntacse.primitive(double.class.getSimpleName(), b + "");
            } else if (objectType == boolean[].class) {
                for (boolean b : (boolean[]) o)
                    output += _syntacse.primitive(boolean.class.getSimpleName(), b + "");
            } else if (objectType == String[].class) {
                for (String b : (String[]) o)
                    output += _syntacse.primitive(String.class.getSimpleName(), b);
            } else {
                for (Object b : (Object[]) o) {
                    output += nonPrimitiveToString(b, tabs + 1);
                }
            }
            output += tabsOut + _syntacse.arrayEnd(objectType.getSimpleName());
        } else if (o instanceof Iterable) {
            //TODO: make iterable;
        } else {
            output += tabsOut + _syntacse.nonPrimitiveBegin(o.getClass().getSimpleName());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : propertyDescriptors) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getDisplayName())) {
                    if (pd.getPropertyType().isPrimitive() || pd.getPropertyType().isAssignableFrom(String.class))
                        output += primitiveToString(
                                pd.getName(),
                                pd.getReadMethod().invoke(o),
                                tabs + 1
                        );
                    else
                        output += nonPrimitiveToString(pd.getReadMethod().invoke(o), tabs + 1);
                }
            }
            output += tabsOut + _syntacse.nonPrimitiveEnd(o.getClass().getSimpleName());
        }

        return output;
    }
}
