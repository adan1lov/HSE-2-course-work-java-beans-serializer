package SyntacseForSerializing;

import java.io.FileWriter;
import java.io.IOException;

public interface SerializingSyntacse {
    void header(FileWriter output, int tabs) throws IOException;
    
    void end(FileWriter output, int tabs) throws IOException;
    
    void primitive(String type, String name, Object param, FileWriter output, int tabs, int index) throws IOException;
    
    void nonPrimitiveBegin(String type, String name, FileWriter output, String id, int tabs, int index, boolean superObject) throws IOException;
    
    void nonPrimitiveEnd(String type, String name, FileWriter output, int tabs, int index, boolean superObject) throws IOException;
    
    void arrayBegin(String type, String name, FileWriter output, String id, int tabs, int index, int length) throws IOException;
    
    void arrayEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException;
    
    void itarableBegin(String type, String name, FileWriter output, String id, int tabs, int index) throws IOException;
    
    void iterableEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException;
    
    void reference(String type, String name, String id, FileWriter output, int tabs, int index) throws IOException;
    
}
