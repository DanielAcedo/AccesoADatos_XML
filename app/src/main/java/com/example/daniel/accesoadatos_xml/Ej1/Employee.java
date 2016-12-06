package com.example.daniel.accesoadatos_xml.Ej1;

/**
 * Created by daniel on 6/12/16.
 */

public class Employee {
    private String name;
    private String position;
    private int age;
    private double salary;

    public Employee(){}

    public Employee(String name, String position, int age, double salary){
        this.name = name;
        this.position = position;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Nombre: "+this.name+"\n"+
                "Puesto: "+this.position+"\n"+
                "Edad: "+this.age+"\n"+
                "Salario: "+String.format("%.2f", this.salary);
    }
}
