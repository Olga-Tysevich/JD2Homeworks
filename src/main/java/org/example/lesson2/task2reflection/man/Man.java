package org.example.lesson2.task2reflection.man;

public class Man {
    protected String name = "default name";
    private int age = 20;
    public double height = 170;
    private double weight = 65;

    public Man(String name, int age, double height, double weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }


    private Man() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    private void setHeight(double height) {
        this.height = height;
    }

    protected double getWeight() {
        return weight;
    }

     void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
