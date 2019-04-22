package JavaSerializer;

//import SyntacseMaker.JsonMaker;
import SyntacseMaker.XmlMaker;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        // write your code here
        Company company= new Company("Yandex Taxi",999999999);
        
        ArrayList<Integer> p1rating= new ArrayList<>();
        p1rating.add(5);
        p1rating.add(4);
        p1rating.add(4);
        p1rating.add(5);
        Person p1= new Person(21,2343,company,new String[]{"Боря","Вася","Федя"},p1rating,  4.5f,"Маша");
    
        ArrayList<Integer> p2rating= new ArrayList<>();
        p2rating.add(2);
        p2rating.add(4);
        p2rating.add(4);
        p2rating.add(2);
        Person p2= new Person(19,7490,company,new String[0],p2rating,  3f,"Леша");
    
        ArrayList<Integer> p3rating= new ArrayList<>();
        p3rating.add(5);
        p3rating.add(5);
        p3rating.add(5);
        p3rating.add(5);
        Person p3= new Person(43,9842,company,new String[]{"Саша"},p3rating,  5f,"Гоша");
    
        company.getPersons().add(p1);
        company.getPersons().add(p2);
        company.getPersons().add(p3);
    
    
        JavaSerializer serializer = new JavaSerializer(company);
        serializer.Make("", new XmlMaker());
    }
    
}

class  Company{
    private String companyName;
    private ArrayList<Person> persons;
    private long budget;
    
    public Company(String companyName,long budget){
        this.budget=budget;
        this.companyName=companyName;
        persons= new ArrayList<Person>();
    }
    
    
    public ArrayList<Person> getPersons() {
        return persons;
    }
    
    public long getBudget() {
        return budget;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setBudget(long budget) {
        this.budget = budget;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
}

class Person{
    public Person(int age,int telephone,Company company,String [] childs,List<Integer> ratingHistory,float rate,String name){
        this.age=age;
        this.childs=childs;
        this.company=company;
        this.telephone=telephone;
        this.ratingHistory=ratingHistory;
        this.rate=rate;
        this.name=name;
    }
    private String name;
    private int age;
    private int telephone;
    private Company company;
    private String[] childs;
    private List<Integer> ratingHistory;
    private float rate;
    public Person(){}
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public float getRate() {
        return rate;
    }
    
    public int getTelephone() {
        return telephone;
    }
    
    public List<Integer> getRatingHistory() {
        return ratingHistory;
    }
    
    public String[] getChilds() {
        return childs;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setChilds(String[] childs) {
        this.childs = childs;
    }
    
    public void setRate(float rate) {
        this.rate = rate;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public void setRatingHistory(List<Integer> ratingHistory) {
        this.ratingHistory = ratingHistory;
    }
    
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    
}