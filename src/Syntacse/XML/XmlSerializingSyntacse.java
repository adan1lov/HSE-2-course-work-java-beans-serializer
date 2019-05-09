package Syntacse.XML;

import SyntacseForSerializing.SerializingSyntacse;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class XmlSerializingSyntacse implements SerializingSyntacse {


    private boolean begin = true;

    @Override
    public boolean hasReferense() {
        return true;
    }

    @Override
    public void header(FileOutputStream output, int tabs) throws IOException {
        output.write(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<java version=\"1.8.0_191\">\n").getBytes());
    }
    
    @Override
    public void end(FileOutputStream output, int tabs) throws IOException {
        output.write("</java>".getBytes());
    }
    
    @Override
    public void primitive(String type, String name, Object param, FileOutputStream output, int tabs, int index) throws IOException {
        
        StringBuilder tabStringBuilder = new StringBuilder();
        for (int i = 0; i < tabs; i++)
            tabStringBuilder.append("\t");
        String tabString = tabStringBuilder.toString();
        
        if ("Integer".equals(type)) type = "int";
        else if ("Boolean".equals(type)) type = "boolean";
        else if ("Short".equals(type)) type = "short";
        else if ("Double".equals(type)) type = "double";
        else if ("Float".equals(type)) type = "float";
        else if ("Byte".equals(type)) type = "byte";
        else if ("Character".equals(type)) type = "char";
        else if ("Long".equals(type)) type = "long";
        else if ("String".equals(type)) type = "string";
        
        if (!begin) {
            if (index != -1)
                output.write((tabString + "<void index=\"" + index + "\">\n").getBytes());
            else if (!"".equals(name))
                output.write((tabString + "<void property=\"" + name + "\">\n").getBytes());
            else
                output.write((tabString + "<void method=\"add\">\n").getBytes());
        } else {
            begin = false;
        }
        output.write(("\t" + tabString + "<" + type + ">" + param + "</" + type + ">" + "\n").getBytes());
        output.write((tabString + "</void>\n").getBytes());
        
    }
    
    @Override
    public void nonPrimitiveBegin(String type, String name, FileOutputStream output, String id, int tabs, int index, boolean superObject) throws IOException {
//        <object class="com.company.A" id="10">
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.write((tabString + "<void index=\"" + index + "\">\n").getBytes());
            else if (!"".equals(name))
                output.write((tabString + "<void property=\"" + name + "\">\n").getBytes());
            else
                output.write((tabString + "<void method=\"add\">\n").getBytes());
        } else {
            begin = false;
        }
        
        output.write((tabString + "<object class=\"" + type + "\" id=\"" + id + "\">" + "\n").getBytes());
    }
    
    @Override
    public void nonPrimitiveEnd(String type, String name, FileOutputStream output, int tabs, int index, boolean superObject) throws IOException {
        StringBuilder tabStringBuilder = new StringBuilder();
        for (int i = 0; i < tabs; i++)
            tabStringBuilder.append('\t');
        String tabString = tabStringBuilder.toString();
        output.write((tabString + "</object>\n").getBytes());
        if (!superObject)
            output.write((tabString + "</void>\n").getBytes());
    }
    
    @Override
    public void arrayBegin(String type, String name, FileOutputStream output, String id, int tabs, int index, int length) throws IOException {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.write((tabString + "<void index=\"" + index + "\">\n").getBytes());
            else if (!"".equals(name))
                output.write((tabString + "<void property=\"" + name + "\">\n").getBytes());
            else
                output.write((tabString + "<void method=\"add\">\n").getBytes());
        } else {
            begin = false;
        }
        
        output.write((tabString + "<array class=\"" + type + "\" id=\"" + id + "\" length=\"" + length + "\">" + "\n").getBytes());
    }
    
    @Override
    public void arrayEnd(String type, String name, FileOutputStream output, int tabs, int index) throws IOException {
        StringBuilder tabStringBuilder = new StringBuilder();
        for (int i = 0; i < tabs; i++)
            tabStringBuilder.append('\t');
        String tabString = tabStringBuilder.toString();
    
    
        output.write((tabString + "</array>\n").getBytes());
        if (index != -2)
            output.write((tabString + "</void>\n").getBytes());
    }
    
    @Override
    public void itarableBegin(String type, String name, FileOutputStream output, String id, int tabs, int index) throws IOException {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.write((tabString + "<void index=\"" + index + "\">\n").getBytes());
            else if (!"".equals(name))
                output.write((tabString + "<void property=\"" + name + "\">\n").getBytes());
            else
                output.write((tabString + "<void method=\"add\">\n").getBytes());
        } else {
            begin = false;
        }
        output.write((tabString + "<object class=\"" + type + "\" id=\"" + id + "\">" + "\n").getBytes());
//        if(index!=-1)
//            output.append(tabString+"<void index=\""+index+"\">\n");
//        else if(!"".equals(name))
//            output.append(tabString+"<void property=\""+name+"\">\n");
//        else
//            output.append(tabString+"<void method=\"add\">\n");
    }
    
    @Override
    public void iterableEnd(String type, String name, FileOutputStream output, int tabs, int index) throws IOException {
    
        StringBuilder tabStringBuilder = new StringBuilder();
        for (int i = 0; i < tabs; i++)
            tabStringBuilder.append('\t');
        String tabString = tabStringBuilder.toString();
        output.write((tabString + "</object>\n").getBytes());
        if (index != -2)
            output.write((tabString + "</void>\n").getBytes());
    }
    
    @Override
    public void reference(String type, String name, String id, FileOutputStream output, int tabs, int index) throws IOException {
//        <void property="fieldA">
//     <object idref="10"/>
//    </void>
        String tabString = "";
        
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        
        if (index != -1)
            output.write((tabString + "<void index=\"" + index + "\">\n").getBytes());
        else if (!"".equals(name))
            output.write((tabString + "<void property=\"" + name + "\">\n").getBytes());
        else
            output.write((tabString + "<void method=\"add\">\n").getBytes());
        output.write((tabString + "\t<object idref=\"" + id + "\"/>\n").getBytes());
        output.write((tabString + "</void>\n").getBytes());
        
    }
}