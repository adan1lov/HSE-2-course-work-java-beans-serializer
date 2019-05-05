package test2;

import java.util.ArrayList;

public class Company {
    public Company(){}
    private String companyName;
    private ArrayList<Person> persons;
    private long budget;
    
    public boolean eq(Company o) {
        boolean b = companyName.equals(o.companyName) && budget==o.budget;
        for (int i=0;i<persons.size();i++)
            b=b && persons.get(i).eq(o.persons.get(i));
        return b;
    }
    
    public Company(String companyName, long budget) {
        this.budget = budget;
        this.companyName = companyName;
        persons = new ArrayList<Person>();
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
