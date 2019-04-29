package JavaSerializer;

//import SyntacseMaker.JsonMaker;

import SyntacseMaker.XmlSerializingSyntacse;

import java.beans.ExceptionListener;
import java.beans.IntrospectionException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
        throws IllegalAccessException, IntrospectionException, InvocationTargetException, IOException {
        // write your code here
        Company company = new Company("Yandex Taxi", 999999999);

        ArrayList<Integer> p1rating = new ArrayList<>();
        p1rating.add(5);
        p1rating.add(4);
        p1rating.add(4);
        p1rating.add(5);
        Person p1 = new Person(21, 2343, company, new String[]{"Боря", "Вася", "Федя"}, p1rating,
            4.5f, "Маша");

        ArrayList<Integer> p2rating = new ArrayList<>();
        p2rating.add(2);
        p2rating.add(4);
        p2rating.add(4);
        p2rating.add(2);
        Person p2 = new Person(19, 7490, company, new String[0], p2rating, 3f, "Леша");

        ArrayList<Integer> p3rating = new ArrayList<>();
        p3rating.add(5);
        p3rating.add(5);
        p3rating.add(5);
        p3rating.add(5);
        Person p3 = new Person(43, 9842, company, new String[]{"Саша"}, p3rating, 5f, "Гоша");

        company.getPersons().add(p1);
        company.getPersons().add(p2);
        company.getPersons().add(p3);

        JavaDeserializer javaDeserializer= new JavaDeserializer(company.getClass());
        JavaSerializer serializer = new JavaSerializer(company);
        try(FileWriter writer = new FileWriter("notes.xml", false)) {
            serializer.Make(writer, new XmlSerializingSyntacse());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        FileOutputStream fos = new FileOutputStream("settings.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :"+e.toString());
            }
        });
        encoder.writeObject(company);
        encoder.close();
        fos.close();
        
        
        FileInputStream fis = new FileInputStream("notes.xml");
        XMLDecoder decoder = new XMLDecoder(fis);
        Company decodedSettings = (Company) decoder.readObject();
        decoder.close();
        fis.close();
        System.out.println(
        
        );
        
    }

}

