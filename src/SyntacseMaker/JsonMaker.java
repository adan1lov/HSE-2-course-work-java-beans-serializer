package SyntacseMaker;

import Syntacse.Syntacse;

public class JsonMaker extends Syntacse {
    
    @Override
    public void header(StringBuilder output, int tabs) {
    }
    
    @Override
    public void end(StringBuilder output, int tabs) {
        output.delete(output.length() - 2, output.length() - 1);
    }
    
    @Override
    public void primitive(String type, String name, Object param, StringBuilder output, int tabs) {
        for (int i = 0; i < tabs; i++)
            output.append("\t");
        String s = param.toString();
        if (param.getClass() == char.class)
            s = '\'' + param.toString() + '\'';
        if (param.getClass() == String.class)
            s = "\"" + param + "\"";
        
        if ("".equals(name)) {
            output.append(s + ",\n");
            return;
        }
        
        output.append("\"" + name + "\"")
            .append(" : ")
            .append(s)
            .append(",\n");
    }
    
    @Override
    public void nonPrimitiveBegin(String type, String name, StringBuilder output, int tabs) {
        for (int i = 0; i < tabs; i++)
            output.append("\t");
        if ("".equals(name)) {
            output.append("{\n");
            return;
        }
        output.append("\"" + name + "\"")
            .append(" : {\n");
    }
    
    @Override
    public void nonPrimitiveEnd(String type, String name, StringBuilder output, int tabs) {
        output.delete(output.length() - 2, output.length() - 1);
        for (int i = 0; i < tabs; i++)
            output.append("\t");
        output.append("},\n");
    }
    
    @Override
    public void arrayBegin(String type, String name, StringBuilder output, int tabs) {
        for (int i = 0; i < tabs; i++)
            output.append("\t");
        if ("".equals(name)) {
            output.append("[\n");
            return;
        }
        output.append("\"" + name + "\"")
            .append(" : [\n");
    }
    
    @Override
    public void arrayEnd(String type, String name, StringBuilder output, int tabs) {
        output.deleteCharAt(output.length() - 2);
        output.deleteCharAt(output.length() - 1);
        output.append("\n");
        for(int i=0;i<tabs;i++)
            output.append("\t");
        output.append("],\n");
    }
}