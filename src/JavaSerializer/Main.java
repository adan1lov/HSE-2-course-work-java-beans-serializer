package JavaSerializer;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
	// write your code here
        Person p= new Person(20,"Alex",new Zompany(1,"Yandex"));
        Person[] lp= new Person[2];
        lp[0]=p;
        lp[1]=p;
        JavaSerializerTese serializer= new JavaSerializerTese(lp);
        serializer.Make("","json");
    }
}
class  Person{
    private int[] age;
    private String name;
    private Zompany zmp;
    Person(int i, String s,Zompany cmpa){
        age=new int[2];
        age[0]=0;
        age[1]=2;
        name=s;
        zmp=cmpa;
    }
    public int[] getAge(){
        return  age;
    }
    public String getName(){
        return  name;
    }
    public Zompany getZmp(){
        return zmp;
    }
    Person(){}
}
class  Zompany{
    private String companyName;
    private int id;

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }
    Zompany(int i, String s){
        id=i;
        companyName=s;
    }
    Zompany(){}
}
