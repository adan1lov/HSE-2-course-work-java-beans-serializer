package test2;

import java.util.List;

public class Person {

    
    public boolean Equals(Person p){
        return age==p.age;
    }
    public Person(int age, int telephone, Company company, String[] childs,
                  List<Integer> ratingHistory, float rate, String name) {
        this.age = age;
        this.childs = childs;
        this.company = company;
        this.telephone = telephone;
        this.ratingHistory = ratingHistory;
        this.rate = rate;
        this.name = name;
    }

    private String name;
    private int age;
    private int telephone;
    private Company company;
    private String[] childs;
    private List<Integer> ratingHistory;
    private float rate;

    public Person() {
    }

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
