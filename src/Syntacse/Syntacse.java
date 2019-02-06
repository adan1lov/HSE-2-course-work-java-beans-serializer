package Syntacse;

abstract public class Syntacse {
    abstract public void header(StringBuilder output);

    abstract public void end(StringBuilder output);

    abstract public void primitive(String type,String name, Object param,StringBuilder output);

    abstract public void nonPrimitiveBegin(String type,String name,StringBuilder output);

    abstract public void nonPrimitiveEnd(String type,String name,StringBuilder output);

    abstract public void arrayBegin(String type,String name,StringBuilder output);

    abstract public void arrayEnd(String type,String name,StringBuilder output);

}
