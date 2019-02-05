package SyntacseMaker;

import Syntacse.Syntacse;

public class JsonMaker extends Syntacse {

    @Override
    public String header() {
        return "begin\n";
    }

    @Override
    public String end() {
        return "end";
    }

    @Override
    public String primitive(String name, String param) {
        return name + " : " + param + ";\n";
    }

    @Override
    public String nonPrimitiveBegin(String type) {
        return type + "{\n";
    }

    @Override
    public String nonPrimitiveEnd(String type) {
        return "}\n";
    }

    @Override
    public String arrayBegin(String type) {
        return "ArrayOf" + type + "\b\b" + " {\n";
    }

    @Override
    public String arrayEnd(String type) {
        return "}\n";
    }
}