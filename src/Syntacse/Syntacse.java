package Syntacse;

abstract public class Syntacse {
    abstract public void header(StringBuilder output,int tabs);

    abstract public void end(StringBuilder output,int tabs);

    abstract public void primitive(String type,String name, Object param,StringBuilder output,int tabs);

    abstract public void nonPrimitiveBegin(String type,String name,StringBuilder output,int tabs);

    abstract public void nonPrimitiveEnd(String type,String name,StringBuilder output,int tabs);

    abstract public void arrayBegin(String type,String name,StringBuilder output,int tabs);

    abstract public void arrayEnd(String type,String name,StringBuilder output,int tabs);

}
