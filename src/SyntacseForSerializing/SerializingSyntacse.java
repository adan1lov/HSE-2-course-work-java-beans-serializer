package SyntacseForSerializing;

import java.io.FileWriter;
import java.io.IOException;

/**
 * this is special class that you can use to create your own serializing format
 * <p>
 * How to:
 * Create your own class, implement this interface and override all methods below
 * after that you can use your class in Java serializer in method make
 *
 * @author Danilov Alexey
 * @see SerializerAndDeserializer.JavaSerializer
 */
public interface SerializingSyntacse {
    /**
     * Some of formats had header for example XML. This method writes down header
     *
     * @param output stream to write a file
     * @param tabs   nubs of tabs P.S. just for beautiful syntax
     *
     * @throws IOException file problem exception
     */
    void header(FileWriter output, int tabs) throws IOException;
    
    /**
     * Some of formats had end. This method writes down header
     *
     * @param output stream to write a file
     * @param tabs   nubs of tabs P.S. just for beautiful syntax
     *
     * @throws IOException file problem exception
     */
    void end(FileWriter output, int tabs) throws IOException;
    
    /**
     * This method used to write a primitive types
     *
     * @param type   type of primitive
     * @param name   name of variable
     * @param param  value of variable
     * @param output stream to write a file
     * @param tabs   nubs of tabs P.S. just for beautiful syntax
     * @param index  this is special param for arrays
     *
     * @throws IOException file problem exception
     */
    void primitive(String type, String name, Object param, FileWriter output, int tabs, int index) throws IOException;
    
    /**
     *
     * @param type
     * @param name
     * @param output
     * @param id
     * @param tabs
     * @param index
     * @param superObject
     * @throws IOException
     */
    void nonPrimitiveBegin(String type, String name, FileWriter output, String id, int tabs, int index, boolean superObject) throws IOException;
    
    /**
     *
     * @param type
     * @param name
     * @param output
     * @param tabs
     * @param index
     * @param superObject
     * @throws IOException
     */
    void nonPrimitiveEnd(String type, String name, FileWriter output, int tabs, int index, boolean superObject) throws IOException;
    
    /**
     *
     * @param type
     * @param name
     * @param output
     * @param id
     * @param tabs
     * @param index
     * @param length
     * @throws IOException
     */
    void arrayBegin(String type, String name, FileWriter output, String id, int tabs, int index, int length) throws IOException;
    
    /**
     *
     * @param type
     * @param name
     * @param output
     * @param tabs
     * @param index
     * @throws IOException
     */
    void arrayEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException;
    
    /**
     *
     * @param type
     * @param name
     * @param output
     * @param id
     * @param tabs
     * @param index
     * @throws IOException
     */
    void itarableBegin(String type, String name, FileWriter output, String id, int tabs, int index) throws IOException;
    
    /**
     *
     * @param type
     * @param name
     * @param output
     * @param tabs
     * @param index
     * @throws IOException
     */
    void iterableEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException;
    
    /**
     *
     * @param type
     * @param name
     * @param id
     * @param output
     * @param tabs
     * @param index
     * @throws IOException
     */
    void reference(String type, String name, String id, FileWriter output, int tabs, int index) throws IOException;
    
}
