package com.janson.thread.chapter18;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/4 18:42
 **/
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(Person p) {
        this(p.getName(), p.getAge());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Employee {
    private Person person;

    Employee(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return new Person(person);
    }

    public static void printEmployeeDetail(Employee emp) {
        Person person = emp.getPerson();
        System.out.println("Employee's name:" + person.getName() + ": age:" + person.getAge());
    }


    public static void main(String[] args) {
        Person person = new Person("小鱼儿", 11);
        printEmployeeDetail(new Employee(person));

    }
}
