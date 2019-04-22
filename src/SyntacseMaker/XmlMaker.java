package SyntacseMaker;

import Syntacse.Syntacse;

public class XmlMaker extends Syntacse {
    
    boolean begin = true;
    
    @Override
    public void header(StringBuilder output, int tabs) {
        output.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<java version=\"1.8.0_191\">\n");
    }
    
    @Override
    public void end(StringBuilder output, int tabs) {
        output.append("</java>");
    }
    
    @Override
    public void primitive(String type, String name, Object param, StringBuilder output, int tabs, int index) {
        
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        
        if (!begin) {
            if (index != -1)
                output.append(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.append(tabString + "<void property=\"" + name + "\">\n");
            else
                output.append(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        output.append("\t" + tabString + "<" + param.getClass().getSimpleName() + ">" + param + "</" + param.getClass().getSimpleName() + ">" + "\n");
        output.append(tabString + "</void>\n");
        
    }
    
    @Override
    public void nonPrimitiveBegin(String type, String name, StringBuilder output, String id, int tabs, int index) {
//        <object class="com.company.A" id="10">
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.append(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.append(tabString + "<void property=\"" + name + "\">\n");
            else
                output.append(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        
        output.append(tabString + "<object class=\"" + type + "\" id=\"" + id + "\">" + "\n");
    }
    
    @Override
    public void nonPrimitiveEnd(String type, String name, StringBuilder output, int tabs, int index) {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        output.append(tabString + "</object>\n");
        if(index!=-2)
            output.append(tabString + "</void>\n");
    }
    
    @Override
    public void arrayBegin(String type, String name, StringBuilder output, String id, int tabs, int index, int length) {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.append(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.append(tabString + "<void property=\"" + name + "\">\n");
            else
                output.append(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        output.append(tabString + "<array class=\"" + type + "\b\" id=\"" + id + "\" length=\"" + length + "\">" + "\n");
    }
    
    @Override
    public void arrayEnd(String type, String name, StringBuilder output, int tabs, int index) {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        
        
        output.append(tabString + "</array>\n");
        if(index!=-2)
            output.append(tabString + "</void>\n");
    }
    
    @Override
    public void itarableBegin(String type, String name, StringBuilder output, String id, int tabs, int index) {
        String tabString = "";
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        if (!begin) {
            if (index != -1)
                output.append(tabString + "<void index=\"" + index + "\">\n");
            else if (!"".equals(name))
                output.append(tabString + "<void property=\"" + name + "\">\n");
            else
                output.append(tabString + "<void method=\"add\">\n");
        } else {
            begin = false;
        }
        output.append(tabString + "<object class=\"" + type + "\" id=\"" + id + "\">" + "\n");
//        if(index!=-1)
//            output.append(tabString+"<void index=\""+index+"\">\n");
//        else if(!"".equals(name))
//            output.append(tabString+"<void property=\""+name+"\">\n");
//        else
//            output.append(tabString+"<void method=\"add\">\n");
    }
    
    @Override
    public void iterableEnd(String type, String name, StringBuilder output, int tabs, int index) {
        String tabString = "";
        
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        output.append(tabString + "</object>\n");
        if(index!=-2)
        output.append(tabString + "</void>\n");
    }
    
    @Override
    public void reference(String type, String name, String id, StringBuilder output, int tabs, int index) {
//        <void property="fieldA">
//     <object idref="10"/>
//    </void>
        String tabString = "";
        
        for (int i = 0; i < tabs; i++)
            tabString += '\t';
        
        if (index != -1)
            output.append(tabString + "<void index=\"" + index + "\">\n");
        else if (!"".equals(name))
            output.append(tabString + "<void property=\"" + name + "\">\n");
        else
            output.append(tabString + "<void method=\"add\">\n");
        output.append(tabString + "\t<object idref=\"" + id + "\"/>\n");
        output.append(tabString + "</void>\n");
        
    }
}