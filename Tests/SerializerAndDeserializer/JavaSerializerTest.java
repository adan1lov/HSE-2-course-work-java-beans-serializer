package SerializerAndDeserializer;


import Syntacse.XML.XmlDeserializing;
import Syntacse.XML.XmlSerializingSyntacse;
import java.beans.IntrospectionException;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test1.PrimitiveExample;
import test2.Company;
import test2.Person;
import test3.Arrays;
import test4.ArraysInArrays;
import test5.CyclicGraph;

class JavaSerializerTest {


    @Test
    void primitiveTests() {
        try {
            PrimitiveExample primitiveExample = new PrimitiveExample();
            JavaSerializer serializer = new JavaSerializer(primitiveExample);
            serializer.Make(new FileOutputStream("test1.xml"), new XmlSerializingSyntacse());
            primitiveExample.setaBoolean(true);
            primitiveExample.setaByte((byte) 1);
            primitiveExample.setaChar('2');
            primitiveExample.setaDouble(3d);
            primitiveExample.setaFloat(4f);
            primitiveExample.setaLong(5L);
            primitiveExample.setAnInt(6);
            primitiveExample.setaShort((short) 7);
            primitiveExample.setaString("8");
            FileOutputStream fileWriter = new FileOutputStream("test1.xml");
            serializer.Make(fileWriter, new XmlSerializingSyntacse());
            fileWriter.close();
            JavaDeserializer javaDeserializer = new JavaDeserializer();
            PrimitiveExample pe = (PrimitiveExample) javaDeserializer
                .Make("test1.xml", new XmlDeserializing());
            Assertions.assertTrue(pe.eq(primitiveExample));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void simpleExample()
        throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {
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

        JavaSerializer javaSerializer = new JavaSerializer(company);
        FileOutputStream fileWriter = new FileOutputStream("test2.xml");
        javaSerializer.Make(fileWriter, new XmlSerializingSyntacse());
        fileWriter.close();
        FileInputStream fileInputStream = new FileInputStream("test2.xml");
        XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
        Company company1 = (Company) xmlDecoder.readObject();
        fileInputStream.close();
        JavaDeserializer javaDeserializer = new JavaDeserializer();
        Company company2 = (Company) javaDeserializer.Make("test2.xml", new XmlDeserializing());
        Assertions.assertEquals(company.eq(company1), company.eq(company2));
    }

    @Test
    void arraysTest()
        throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        Arrays arrays = new Arrays();
        JavaSerializer serializer = new JavaSerializer(arrays);
        FileOutputStream fileWriter = new FileOutputStream("test3.xml");
        serializer.Make(fileWriter, new XmlSerializingSyntacse());
        fileWriter.close();
        arrays.setaBoolean(new boolean[]{true, false});
        arrays.setaByte(new byte[]{0, 1});
        arrays.setaChar(new char[]{'a', 'b'});
        arrays.setaDouble(new double[]{3.2, 3.3});
        arrays.setaFloat(new float[]{3.2f, 3.3f});
        arrays.setaLong(new long[]{5, 5});
        arrays.setAnInt(new int[]{6, 6});
        arrays.setaShort(new short[]{7, 7});
        arrays.setaString(new String[]{"hello", "world"});
        fileWriter = new FileOutputStream("test3.xml");
        serializer.Make(fileWriter, new XmlSerializingSyntacse());
        fileWriter.close();
        JavaDeserializer javaDeserializer = new JavaDeserializer();
        Arrays pe = (Arrays) javaDeserializer.Make("test3.xml", new XmlDeserializing());
        Assertions.assertTrue(pe.eq(arrays));
    }

    @Test
    void arraysInArraysTest()
        throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        ArraysInArrays arrays = new ArraysInArrays();
        JavaSerializer serializer = new JavaSerializer(arrays);
        FileOutputStream fileWriter = new FileOutputStream("test4.xml");
        serializer.Make(fileWriter, new XmlSerializingSyntacse());
        fileWriter.close();
        arrays.setaBoolean(new boolean[][]{new boolean[]{true, false}});
        arrays.setaByte(new byte[][]{new byte[]{0, 1}});
        arrays.setaChar(new char[][]{new char[]{'a', 'b'}});
        arrays.setaDouble(new double[][]{new double[]{3.2, 3.3}});
        arrays.setaFloat(new float[][]{new float[]{3.2f, 3.3f}});
        arrays.setaLong(new long[][]{new long[]{5, 5}});
        arrays.setAnInt(new int[][]{new int[]{6, 6}});
        arrays.setaShort(new short[][]{new short[]{7, 7}});
        arrays.setaString(new String[][]{new String[]{"hello", "world"}});
        fileWriter = new FileOutputStream("test3.xml");
        serializer.Make(fileWriter, new XmlSerializingSyntacse());
        fileWriter.close();
        JavaDeserializer javaDeserializer = new JavaDeserializer();
        ArraysInArrays pe = (ArraysInArrays) javaDeserializer
            .Make("test3.xml", new XmlDeserializing());
        Assertions.assertTrue(pe.eq(arrays));
    }


    @Test
    void cyclicGraphTest()
        throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        CyclicGraph c1 = new CyclicGraph();
        CyclicGraph c2 = new CyclicGraph();
        c1.setCyclicGraph(c2);
        c2.setCyclicGraph(c1);
        JavaSerializer serializer = new JavaSerializer(c1);
        FileOutputStream fileWriter = new FileOutputStream("test5.xml");
        serializer.Make(fileWriter, new XmlSerializingSyntacse());
        fileWriter.close();
        JavaDeserializer javaDeserializer = new JavaDeserializer();
        CyclicGraph pe = (CyclicGraph) javaDeserializer.Make("test5.xml", new XmlDeserializing());
        Assertions.assertSame(pe, pe.getCyclicGraph().getCyclicGraph());
    }

}