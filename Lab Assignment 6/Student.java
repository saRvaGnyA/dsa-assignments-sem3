package com.company;

public class Student {
    private final int id;
    private final String name;
    private final String address;
    private final char grade;

    Student(int id, String name, String address, char grade) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", grade=" + grade +
                '}';
    }

}