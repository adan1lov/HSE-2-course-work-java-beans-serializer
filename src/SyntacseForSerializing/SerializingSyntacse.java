package SyntacseForSerializing;

import java.io.FileWriter;
import java.io.IOException;

abstract public class SerializingSyntacse {
    abstract public void header(FileWriter output, int tabs) throws IOException;
    
    abstract public void end(FileWriter output, int tabs) throws IOException;
    
    abstract public void primitive(String type, String name, Object param, FileWriter output, int tabs,int index) throws IOException;
    
    abstract public void nonPrimitiveBegin(String type, String name, FileWriter output, String id, int tabs,int index) throws IOException;
    
    abstract public void nonPrimitiveEnd(String type, String name, FileWriter output, int tabs,int index) throws IOException;
    
    abstract public void arrayBegin(String type, String name, FileWriter output, String id, int tabs,int index,int length) throws IOException;
    
    abstract public void arrayEnd(String type, String name, FileWriter output, int tabs,int index) throws IOException;
    
    abstract public void itarableBegin(String type, String name, FileWriter output,String id, int tabs,int index) throws IOException;
    
    abstract public void iterableEnd(String type, String name, FileWriter output, int tabs,int index) throws IOException;
    
    abstract public void reference(String type, String name,String id, FileWriter output,int tabs,int index) throws IOException;
    
}
