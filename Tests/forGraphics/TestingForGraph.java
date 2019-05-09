package forGraphics;

import SerializerAndDeserializer.JavaSerializer;
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
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

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
    public void fieldTest() throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {

        ClassWith0Fields cl0=new ClassWith0Fields();
        ClassWith1Fields cl1=new ClassWith1Fields();
        ClassWith2Fields cl2=new ClassWith2Fields();
        ClassWith3Fields cl3=new ClassWith3Fields();
        ClassWith4Fields cl4=new ClassWith4Fields();
        ClassWith5Fields cl5=new ClassWith5Fields();
        ClassWith6Fields cl6=new ClassWith6Fields();
        ClassWith7Fields cl7=new ClassWith7Fields();
        ClassWith8Fields cl8=new ClassWith8Fields();
        ClassWith9Fields cl9=new ClassWith9Fields();


        Object[] objects = new Object[]{cl0, cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9};
        
        for (int i = 0; i < 10; i++) {
            Long start = System.currentTimeMillis();
            for (int j = 0; j < 1; j++) {
                FileOutputStream fileWriter=new FileOutputStream("cl/numOfFields" + i+".txt");
                JavaSerializer javaSerializer = new JavaSerializer(objects[i]);
                javaSerializer.Make(fileWriter, new XmlSerializingSyntacse());
                fileWriter.flush();
                fileWriter.close();
            }
            Long end =System.currentTimeMillis();
            Long start2 = System.currentTimeMillis();
            for (int j = 0; j < 1; j++) {
                FileOutputStream fileOutputStream =new FileOutputStream("cl/num"+i+".txt");
                XMLEncoder xmlEncoder= new XMLEncoder(fileOutputStream);
                xmlEncoder.writeObject(objects[i]);
                xmlEncoder.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            Long end2 =System.currentTimeMillis();
            System.out.println("numof fields "+(long)Math.pow(2,i+3)+"\t"+(end-start)+"\t"+(end2-start2));
        }
    }
}
