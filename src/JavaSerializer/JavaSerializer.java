package JavaSerializer;

//TODO:PROBLEM WITH TABS and check commits
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
    public void Make(String path, Syntacse s)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        //syntacse that we would use
        if (s == null)
            throw new NullPointerException("Syntacse was null");
        _syntacse = s;

        //result of serializing
        StringBuilder output = new StringBuilder();
        _syntacse.header(output);
        nonPrimitiveToString(output,"" ,_object ,0 );
        _syntacse.end(output);
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
    private void primitiveToString(StringBuilder output, String name, Object o, int tabs) {
//        for (int i = 0; i < tabs; i++)
//            output.append("\t");
        _syntacse.primitive(o.getClass().getSimpleName(), name, o, output);
    }

    private void nonPrimitiveToString(StringBuilder output, String name, Object o, int tabs)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
//        String tabsOut = "";

//        for (int i = 0; i < tabs; i++)
//            tabsOut += "\t";
//
//        output.append(tabsOut);
        Class objectType = o.getClass();
        if (o.getClass().isArray()) {
            _syntacse.arrayBegin(objectType.getSimpleName(), name, output);

            if (objectType == byte[].class) {
                for (byte b : (byte[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == short[].class) {
                for (short b : (short[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == int[].class) {
                for (int b : (int[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == long[].class) {
                for (long b : (long[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == char[].class) {
                for (char b : (char[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == float[].class) {
                for (float b : (float[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == double[].class) {
                for (double b : (double[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == boolean[].class) {
                for (boolean b : (boolean[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else if (objectType == String[].class) {
                for (String b : (String[]) o)
                    primitiveToString(output, "", b, tabs + 1);
            } else {
                for (Object b : (Object[]) o) {
                    nonPrimitiveToString(output, "", b, tabs + 1);
                }
            }
//            output.append(tabsOut);
            _syntacse.arrayEnd(objectType.getSimpleName(), name, output);
//        } else if (o instanceof Iterable) {
//            //TODO: make iterable;
        } else {
            _syntacse.nonPrimitiveBegin(objectType.getSimpleName(),name,output);
            BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : propertyDescriptors) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getDisplayName())) {
                    if (pd.getPropertyType().isPrimitive() || pd.getPropertyType().isAssignableFrom(String.class))
//                        output += primitiveToString(
//                                pd.getName(),
//                                pd.getReadMethod().invoke(o),
//                                tabs + 1
//                        );
                        primitiveToString(output,pd.getName(),pd.getReadMethod().invoke(o),tabs+1 );
                    else
                        nonPrimitiveToString(output,pd.getName(),pd.getReadMethod().invoke(o), tabs + 1);
                }
            }
//            output.append(tabsOut);
            _syntacse.nonPrimitiveEnd(objectType.getSimpleName(),name,output);
        }
//
    }
}
