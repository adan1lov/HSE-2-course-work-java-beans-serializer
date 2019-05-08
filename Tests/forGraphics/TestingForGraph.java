package forGraphics;

import SerializerAndDeserializer.JavaSerializer;
import Syntacse.XML.XmlSerializingSyntacse;
import forGraphics.Classes.*;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;

public class TestingForGraph {
    @Test
    void fieldTest() throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        NullClass nc= new NullClass();
        OneFieldClass cl1 = new OneFieldClass();
        TwoFieldClass cl2 = new TwoFieldClass();
        ThreeFieldClass cl3 = new ThreeFieldClass();
        FourFieldClass cl4 = new FourFieldClass();
        FiveFieldClass cl5 = new FiveFieldClass();
        SixFieldClass cl6 = new SixFieldClass();
        SevenFieldClass cl7 = new SevenFieldClass();
        EightFieldClass cl8 = new EightFieldClass();
        NineFieldClass cl9 = new NineFieldClass();
        Object[] objects = new Object[]{nc, cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9};
        
        for (int i = 0; i < 10; i++) {
            
            Long start = System.currentTimeMillis();
            for (int j = 0; j < 10000; j++) {
                JavaSerializer javaSerializer = new JavaSerializer(objects[i]);
                FileWriter fileWriter=new FileWriter("numOfFields" + i);
                javaSerializer.Make(fileWriter, new XmlSerializingSyntacse());
            }
            Long end =System.currentTimeMillis();
            
            Long start2 = System.currentTimeMillis();
            for (int j = 0; j < 10000; j++) {
                FileOutputStream fileOutputStream =new FileOutputStream("num"+i);
                XMLEncoder xmlEncoder= new XMLEncoder(fileOutputStream);
                xmlEncoder.writeObject(objects[i]);
            }
            Long end2 =System.currentTimeMillis();
            System.out.println("numof fields "+i+": "+(end-start)+"\t\t"+(end2-start2));
        }
    }
}
