package Syntacse;

abstract public class Syntacse {
    abstract public String header();

    abstract public String end();

    abstract public String primitive(String name, String param);

    abstract public String nonPrimitiveBegin(String type);

    abstract public String nonPrimitiveEnd(String type);

    abstract public String arrayBegin(String type);

    abstract public String arrayEnd(String type);

}
