package SyntacseMaker;

import Syntacse.Syntacse;

public class JsonMaker extends Syntacse {

    @Override
    public void header(StringBuilder output) { }

    @Override
    public void end(StringBuilder output) { }

    @Override
    public void primitive(String type,String name, Object param,StringBuilder output) {

        String s=param.toString();
        if(param.getClass()==char.class)
            s='\''+param.toString()+'\'';
        if(param.getClass()==String.class)
            s="\""+param+"\"";

        if("".equals(name)){
            output.append(s+"\n");
            return;
        }

        output.append("\""+name+"\"")
                .append(" : ")
                .append(s)
                .append(",\n");
    }

   @Override
   public void nonPrimitiveBegin(String type,String name,StringBuilder output) {
        if("".equals(name)){
            output.append("{\n");
            return;
        }
        output.append("\""+name+"\"")
                .append(" : {\n");
   }

    @Override
    public void nonPrimitiveEnd(String type,String name,StringBuilder output) {
        output.append("},\n");
    }

    @Override
    public void arrayBegin(String type,String name,StringBuilder output) {
            if("".equals(name)){
                output.append("[\n");
                return;
            }
            output.append("\""+name+"\"")
                    .append(" : [\n");
    }

    @Override
    public void arrayEnd(String type,String name,StringBuilder output) {
        output.deleteCharAt(output.length()-2);
        output.deleteCharAt(output.length()-1);
        output.append("]\n");
    }
}