package Syntacse;

abstract public class Syntacse {
    abstract public void header(StringBuilder output, int tabs);
    
    abstract public void end(StringBuilder output, int tabs);
    
    abstract public void primitive(String type, String name, Object param, StringBuilder output, int tabs,int index);
    
    abstract public void nonPrimitiveBegin(String type, String name, StringBuilder output, String id, int tabs,int index);
    
    abstract public void nonPrimitiveEnd(String type, String name, StringBuilder output, int tabs,int index);
    
    abstract public void arrayBegin(String type, String name, StringBuilder output, String id, int tabs,int index,int length);
    
    abstract public void arrayEnd(String type, String name, StringBuilder output, int tabs,int index);
    
    abstract public void itarableBegin(String type, String name, StringBuilder output,String id, int tabs,int index);
    
    abstract public void iterableEnd(String type, String name, StringBuilder output, int tabs,int index);
    
    abstract public void reference(String type, String name,String id, StringBuilder output,int tabs,int index);
    
}
