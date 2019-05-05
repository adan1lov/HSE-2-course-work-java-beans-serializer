package Syntacse.XML;

import SyntacseForSerializing.SerializingSyntacse;

import java.io.FileWriter;
import java.io.IOException;

public class XmlSerializingSyntacse implements SerializingSyntacse {
    
    boolean begin = true;
    
    @Override
    public void header(FileWriter output, int tabs) throws IOException {
        output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<java version=\"1.8.0_191\">\n");
    }
    
    @Override
    public void end(FileWriter output, int tabs) throws IOException {
        output.write("</java>");
    }
    
    @Override
    public void primitive(String type, String name, Object param, FileWriter output, int tabs, int index) throws IOException {
        
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        
        if("Integer".equals(type))
            type="int";
        else if("Boolean".equals(type))
            type="boolean";
        else if("Short".equals(type))
            type="short";
        else if("Double".equals(type))
            type="double";
        else if("Float".equals(type))
            type="float";
        else if("Byte".equals(type))
            type="byte";
        else if("Character".equals(type))
            type="char";
        else if("Long".equals(type))
            type="long";
        else if("String".equals(type))
            type="string";
        
        if (!begin) {
            if (index != -1)
                output.write(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.write(tabString + "<void property=\"" + name + "\">\n");
            else
                output.write(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        output.write("\t" + tabString + "<" + type + ">" + param + "</" + type + ">" + "\n");
        output.write(tabString + "</void>\n");
        
    }
    
    @Override
    public void nonPrimitiveBegin(String type, String name, FileWriter output, String id, int tabs, int index, boolean superObject) throws IOException {
//        <object class="com.company.A" id="10">
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.write(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.write(tabString + "<void property=\"" + name + "\">\n");
            else
                output.write(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        
        output.write(tabString + "<object class=\"" + type + "\" id=\"" + id + "\">" + "\n");
    }
    
    @Override
    public void nonPrimitiveEnd(String type, String name, FileWriter output, int tabs, int index, boolean superObject) throws IOException {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        output.write(tabString + "</object>\n");
        if(!superObject)
            output.write(tabString + "</void>\n");
    }
    
    @Override
    public void arrayBegin(String type, String name, FileWriter output, String id, int tabs, int index, int length) throws IOException {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.write(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.write(tabString + "<void property=\"" + name + "\">\n");
            else
                output.write(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        
        output.write(tabString + "<array class=\"" + type + "\" id=\"" + id + "\" length=\"" + length + "\">" + "\n");
    }
    
    @Override
    public void arrayEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        
        
        output.write(tabString + "</array>\n");
        if(index!=-2)
            output.write(tabString + "</void>\n");
    }
    
    @Override
    public void itarableBegin(String type, String name, FileWriter output, String id, int tabs, int index) throws IOException {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.write(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.write(tabString + "<void property=\"" + name + "\">\n");
            else
                output.write(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        output.write(tabString + "<object class=\"" + type + "\" id=\"" + id + "\">" + "\n");
//        if(index!=-1)
//            output.append(tabString+"<void index=\""+index+"\">\n");
//        else if(!"".equals(name))
//            output.append(tabString+"<void property=\""+name+"\">\n");
//        else
//            output.append(tabString+"<void method=\"add\">\n");
    }
    
    @Override
    public void iterableEnd(String type, String name, FileWriter output, int tabs, int index) throws IOException {
        String tabString = "";
        
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        output.write(tabString + "</object>\n");
        if(index!=-2)
        output.write(tabString + "</void>\n");
    }
    
    @Override
    public void reference(String type, String name, String id, FileWriter output, int tabs, int index) throws IOException {
//        <void property="fieldA">
//     <object idref="10"/>
//    </void>
        String tabString = "";
        
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        
        if (index != -1)
            output.write(tabString + "<void index=\"" + index + "\">\n");
        else if (!"".equals(name))
            output.write(tabString + "<void property=\"" + name + "\">\n");
        else
            output.write(tabString + "<void method=\"add\">\n");
        output.write(tabString + "\t<object idref=\"" + id + "\"/>\n");
        output.write(tabString + "</void>\n");
        
    }
}