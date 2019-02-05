package JavaSerializer;

import Syntacse.Syntacse;

import java.beans.*;

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
    public void Make(String path, Syntacse s) throws IntrospectionException {

        //syntacse that we would use
        if (s == null)
            throw new NullPointerException("Syntacse was null");
        _syntacse = s;

        //result of serializing
        String output = _syntacse.header();


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
        output.append("\n");
        return output.toString();
    }

    private String nonPrimitiveToString(Object o, int tabs) {

    }
}
