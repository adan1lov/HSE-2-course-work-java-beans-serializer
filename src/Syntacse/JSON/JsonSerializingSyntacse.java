//TODO: make it please
//package Syntacse.JSON;
//
//
//import SyntacseForSerializing.SerializingSyntacse;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class JsonMaker implements SerializingSyntacse {
//
//    @Override
//    public void header(FileWriter output, int tabs) throws IOException {
//
//    }
//
//    @Override
//    public void end(FileWriter output, int tabs) throws IOException {
//
//    }
//
//    @Override
//    public void primitive(String type, String name, Object param, FileWriter output, int tabs, int index) throws IOException {
//
//    }
//
//    @Override
//    public void nonPrimitiveBegin(String type, String name, FileWriter output, String id, int tabs, int index, boolean superObject) throws IOException {
//
//    }
//
//    @Override
//    public void nonPrimitiveEnd(String type, String name, FileWriter output, int tabs, int index, boolean superObject) throws IOException {
//
//    }
//
//    @Override
//    public void arrayBegin(String type, String name, FileWriter output, String id, int tabs, int index, int length) throws IOException {
//
//    }
//
//    @Override
//    public void arrayEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException {
//
//    }
//
//    @Override
//    public void itarableBegin(String type, String name, FileWriter output, String id, int tabs, int index) throws IOException {
//
//    }
//
//    @Override
//    public void iterableEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException {
//
//    }
//
//    @Override
//    public void reference(String type, String name, String id, FileWriter output, int tabs, int index) throws IOException {
//
//    }
//
//    @Override
//    public void header(StringBuilder output, int tabs) {
//    }
//
//    @Override
//    public void end(StringBuilder output, int tabs) {
//        output.delete(output.length() - 2, output.length() - 1);
//    }
//
//    @Override
//    public void primitive(String type, String name, Object param, StringBuilder output, int tabs) {
//        for (int i = 0; i < tabs; i++)
//            output.append("\t");
//        String s = param.toString();
//        if (param.getClass() == char.class)
//            s = '\'' + param.toString() + '\'';
//        if (param.getClass() == String.class)
//            s = "\"" + param + "\"";
//
//        if ("".equals(name)) {
//            output.append(s + ",\n");
//            return;
//        }
//
//        output.append("\"" + name + "\"")
//            .append(" : ")
//            .append(s)
//            .append(",\n");
//    }
//
//    @Override
//    public void nonPrimitiveBegin(String type, String name, StringBuilder output, String id, int tabs) {
//        for (int i = 0; i < tabs; i++)
//            output.append("\t");
//        if ("".equals(name)) {
//            output.append("{\n");
//            return;
//        }
//        output.append("\"" + name + "\"")
//            .append(" : { \n");
//    }
//
//    @Override
//    public void nonPrimitiveEnd(String type, String name, StringBuilder output, int tabs) {
//        output.delete(output.length() - 2, output.length() - 1);
//        for (int i = 0; i < tabs; i++)
//            output.append("\t");
//        output.append("},\n");
//        //end
//        //
//    }
//
//    @Override
//    public void arrayBegin(String type, String name, StringBuilder output, int tabs) {
//        for (int i = 0; i < tabs; i++)
//            output.append("\t");
//        if ("".equals(name)) {
//            output.append("[\n");
//            return;
//        }
//        output.append("\"" + name + "\"")
//            .append(" : [\n");
//    }
//
//    @Override
//    public void arrayEnd(String type, String name, StringBuilder output, int tabs) {
//        output.deleteCharAt(output.length() - 2);
//        output.deleteCharAt(output.length() - 1);
//        output.append("\n");
//        for (int i = 0; i < tabs; i++)
//            output.append("\t");
//        output.append("],\n");
//    }
//
//    @Override
//    public void reference(String type, String name, String id, StringBuilder output, int tabs) {
//
//    }
//}