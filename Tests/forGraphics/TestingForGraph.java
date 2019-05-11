package forGraphics;

import SerializerAndDeserializer.JavaDeserializer;
import SerializerAndDeserializer.JavaSerializer;
import Syntacse.XML.XmlDeserializingSAX;
import Syntacse.XML.XmlSerializingSyntacse;
import forGraphics.Classes.ClassWith0Fields;
import forGraphics.Classes.ClassWith1Fields;
import forGraphics.Classes.ClassWith2Fields;
import forGraphics.Classes.ClassWith3Fields;
import forGraphics.Classes.ClassWith4Fields;
import forGraphics.Classes.ClassWith5Fields;
import forGraphics.Classes.ClassWith6Fields;
import forGraphics.Classes.ClassWith7Fields;
import forGraphics.Classes.ClassWith8Fields;
import forGraphics.Classes.ClassWith9Fields;
import java.beans.IntrospectionException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class TestingForGraph {
    @Test
    public void createClasses() throws IOException {
        for (int j = 0; j < 10; j++) {
            FileOutputStream fileOutputStream =new FileOutputStream("ClassWith"+j+"Fields.java");
            long numOfFileds=(long) Math.pow(2, j+3);
            fileOutputStream.write(("package forGraphics.Classes;\n"
                + "\n"
                + "public class ClassWith"+j+"Fields {").getBytes());
            for(long l=0;l<numOfFileds;l++){
                fileOutputStream.write(("    private long f"+l+";\n"
                    + "    \n"
                    + "    public long getF"+l+"() {\n"
                    + "        return f"+l+";\n"
                    + "    }\n"
                    + "    \n"
                    + "    public void setF"+l+"(long f"+l+") {\n"
                    + "        this.f"+l+" = f"+l+";\n"
                    + "    }\n").getBytes());
            }
            fileOutputStream.write(("    public ClassWith"+j+"Fielda(){\n").getBytes());
            for (long l=0;l<numOfFileds;l++){
                fileOutputStream.write(("        this.f"+l+"="+l+";\n").getBytes());
            }
            fileOutputStream.write(("    }\n").getBytes());

            fileOutputStream.write("}".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }
    @Test
    public void fieldTest()
        throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException, ParserConfigurationException, SAXException, ClassNotFoundException, InstantiationException {

        ClassWith0Fields cl0=new ClassWith0Fields();
        cl0.ClassWith0Field();
        ClassWith1Fields cl1=new ClassWith1Fields();
        cl1.ClassWith1Field();
        ClassWith2Fields cl2=new ClassWith2Fields();
        cl2.ClassWith2Field();
        ClassWith3Fields cl3=new ClassWith3Fields();
        cl3.ClassWith3Field();
        ClassWith4Fields cl4=new ClassWith4Fields();
        cl4.ClassWith4Field();
        ClassWith5Fields cl5=new ClassWith5Fields();
        cl5.ClassWith5Field();
        ClassWith6Fields cl6=new ClassWith6Fields();
        cl6.ClassWith6Field();
        ClassWith7Fields cl7=new ClassWith7Fields();
        cl7.ClassWith7Field();
        ClassWith8Fields cl8=new ClassWith8Fields();
        cl8.ClassWith8Field();
        ClassWith9Fields cl9=new ClassWith9Fields();
        cl9.ClassWith9Field();


        Object[] objects = new Object[]{cl0, cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9};
        
        for (int i = 0; i < 10; i++) {
            Long start = System.currentTimeMillis();
                FileOutputStream fileWriter=new FileOutputStream("cl/numOfFields" + i+".txt");
                JavaSerializer javaSerializer = new JavaSerializer(objects[i]);
                javaSerializer.Make(fileWriter, new XmlSerializingSyntacse());
                fileWriter.flush();
                fileWriter.close();
            Long end =System.currentTimeMillis();

            Long start2 = System.currentTimeMillis();
                FileOutputStream fileOutputStream =new FileOutputStream("cl/num"+i+".txt");
                XMLEncoder xmlEncoder= new XMLEncoder(fileOutputStream);
                xmlEncoder.writeObject(objects[i]);
                xmlEncoder.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            Long end2 =System.currentTimeMillis();

            Long start3=System.currentTimeMillis();
            FileInputStream fileInputStream1= new FileInputStream("cl/numOfFields" + i+".txt");
            JavaDeserializer javaDeserializer= new JavaDeserializer();
            Object company2=javaDeserializer.Make(fileInputStream1, new XmlDeserializingSAX());
            Long end3=System.currentTimeMillis();

            Long Start3 =System.currentTimeMillis();
            FileInputStream fileInputStream=new FileInputStream("cl/num"+i+".txt");
            XMLDecoder xmlDecoder= new XMLDecoder(fileInputStream);
            Object company1=xmlDecoder.readObject();
            fileInputStream.close();
            Long End3=System.currentTimeMillis();

            System.out.println("numof fields "+(long)Math.pow(2,i+3)+"\t\t\t"+(end-start)+"\t"+(end2-start2)+"\t"+(end3-start3)+"\t"+(End3-Start3));
        }
    }
}
