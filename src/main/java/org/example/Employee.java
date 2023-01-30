package org.example;

public class Employee {
    private String name;
    private int age;
    private String profession;

    public Employee(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }
}