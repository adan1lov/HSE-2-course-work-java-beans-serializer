package JavaSerializer;

import SyntacseMaker.JsonMaker;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
	// write your code here
        Zompany z= new Zompany(1,"Yandex");
        Person p= new Person(20,"Alex",new Zompany(1,"Yandex"));
        ArrayList<Person> lp= new ArrayList<Person>();
        lp.add(p);
        lp.add(new Person(30,"GOSHA",new Zompany(2,"t2")));
        JavaSerializer serializer= new JavaSerializer(lp);
        serializer.Make("",new JsonMaker());

    }
    static public void doit(StringBuilder s)
    {
        s.append("s\n");
        s.deleteCharAt(s.length()-1);
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
