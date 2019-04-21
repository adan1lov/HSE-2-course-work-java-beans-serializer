package JavaSerializer;

import SyntacseMaker.JsonMaker;
import SyntacseMaker.XmlMaker;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        // write your code here
        A a= new A();
        B b= new B();
        a.setFieldB(b);
        b.setFieldA(a);
        JavaSerializer serializer = new JavaSerializer(a);
        serializer.Make("", new XmlMaker());
        
    }
    
    static public void doit(StringBuilder s) {
        s.append("s\n");
        s.deleteCharAt(s.length() - 1);
    }
}

class A{
    private B fieldB;
    
    public B getFieldB() {
        return fieldB;
    }
    
    private int anInt;
    
    public int getAnInt() {
        return anInt;
    }
    
    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
    
    public void setFieldB(B fieldB) {
        this.fieldB = fieldB;
    }
    
    public A(){}
}

class  B{
    private A fieldA;
    
    public A getFieldA() {
        return fieldA;
    }
    
    public void setFieldA(A fieldA) {
        this.fieldA = fieldA;
    }
    
    public B(){}
}