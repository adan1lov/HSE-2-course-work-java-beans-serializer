package SyntacseForSerializing;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * this is special class that you can use to create your own serializing format
 * <p>
 * How to: Create your own class, implement this interface and override all methods below after that
 * you can use your class in Java serializer in method make
 *
 * @author Danilov Alexey
 * @see SerializerAndDeserializer.JavaSerializer
 */
public interface SerializingSyntacse {

    /**
     * Some of formats had header for example XML. This method writes down header
     *
     * @param output stream to write a file
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @throws IOException file problem exception
     */
    void header(FileOutputStream output, int tabs) throws IOException;

    /**
     * Some of formats had end. This method writes down header
     *
     * @param output stream to write a file
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @throws IOException file problem exception
     */
    void end(FileOutputStream output, int tabs) throws IOException;

    /**
     * This method used to write a primitive types
     *
     * @param type type of primitive
     * @param name name of variable
     * @param param value of variable
     * @param output stream to write a file
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @throws IOException file problem exception
     */
    void primitive(String type, String name, Object param, FileOutputStream output, int tabs,
        int index) throws IOException;

    /**
     * A lot of serialization types has begin statement and end statement
     *
     * @param type name of class
     * @param name name of field
     * @param output stream to write
     * @param id id of object for reference
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @param superObject is this Object a object to serialize
     * @throws IOException file problem exception
     * @example <Object class="Foo" id="Foo0">
     */
    void nonPrimitiveBegin(String type, String name, FileOutputStream output, String id, int tabs,
        int index, boolean superObject) throws IOException;

    /**
     * A lot of serialization types has begin statement and end statement
     *
     * @param type name of class
     * @param name name of field
     * @param output stream to write
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @param superObject is this Object a object to serialize
     * @throws IOException file problem exception
     * @example </Object>
     */
    void nonPrimitiveEnd(String type, String name, FileOutputStream output, int tabs, int index,
        boolean superObject) throws IOException;

    /**
     * A lot of serialization types has begin statement and end statement
     *
     * @param type name of class
     * @param name name of field
     * @param output stream to write
     * @param id id of object for reference
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @param length length of array
     * @param superObject is this Object a object to serialize
     * @throws IOException file problem exception
     * @example <array class="Foo" id="Foo0">
     */
    void arrayBegin(String type, String name, FileOutputStream output, String id, int tabs,
        int index, int length, boolean superObject) throws IOException;

    /**
     * A lot of serialization types has begin statement and end statement
     *
     * @param type name of class
     * @param name name of field
     * @param output stream to write
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @param superObject is this Object a object to serialize
     * @throws IOException file problem exception
     * @example </array>
     */
    void arrayEnd(String type, String name, FileOutputStream output, int tabs, int index,
        boolean superObject) throws IOException;

    /**
     * A lot of serialization types has begin statement and end statement
     *
     * @param type name of class
     * @param name name of field
     * @param output stream to write
     * @param id id of object for reference
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @param superObject is this Object a object to serialize
     * @throws IOException file problem exception
     * @example </object>
     */
    void itarableBegin(String type, String name, FileOutputStream output, String id, int tabs,
        int index, boolean superObject) throws IOException;

    /**
     * A lot of serialization types has begin statement and end statement
     *
     * @param type name of class
     * @param name name of field
     * @param output stream to write
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @param superObject is this Object a object to serialize
     * @throws IOException file problem exception
     * @example </Object>
     */
    void iterableEnd(String type, String name, FileOutputStream output, int tabs, int index,
        boolean superObject) throws IOException;

    /**
     * When you have a class aggregation and references of the object is the same you should tag
     * that this is not a new object
     *
     * @param type name of class
     * @param name name of field
     * @param id id of object for reference
     * @param output stream to write
     * @param tabs nubs of tabs P.S. just for beautiful syntax
     * @param index this is special param for arrays
     * @throws IOException file problem exception
     * @example company1 has field person1 and person1 has field company1 class Company{ Person p; }
     * class Person{ Company c; }
     *
     * ...main(){ Company c= new Company(); Person p= new Person(); c.p=p; p.c=c; }
     */
    void reference(String type, String name, String id, FileOutputStream output, int tabs,
        int index) throws IOException;

}
