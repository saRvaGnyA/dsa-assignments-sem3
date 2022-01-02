package com.company;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // --- SEPARATE CHAINING ------------>

        System.out.println("Implementing hashmaps using separate chaining:");

        // enter size
        System.out.print("Enter number of students: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        // create hashmap
        ChainHashMap<Integer, Student> students = new ChainHashMap<>(n);

        // take student inputs
        for (int i = 0; i < n; ++i) {
            System.out.println("Enter student reg no, name, address and grade: ");
            int reg = s.nextInt();
            String name = s.next();
            String add = s.next();
            char grade = s.next().charAt(0);
            students.put(reg, new Student(reg, name, add, grade));
        }

        // display students using Iterable
        Iterable<Entry<Integer, Student>> t = students.entrySet();
        for (Entry<Integer, Student> student : t) {
            student.getValue().display();
        }

        // remove a student by registration number
        System.out.print("Enter reg number of the student to remove: ");
        int to_remove = s.nextInt();
        Student removed = students.remove(to_remove);
        if (removed != null) {
            removed.display();
        } else {
            System.out.println("Reg number entered was not found");
        }

        // ---LINEAR PROBING------------ >

        System.out.println("\nImplementing hashmaps using linear probing:");

        // enter size
        System.out.print("Enter number of students: ");
        int n1 = s.nextInt();

        // create hashmap
        ProbeHashMap<Integer, Student> studentsProbeHashMap = new ProbeHashMap<>(n1);

        // take student inputs
        for (int i = 0; i < n1; ++i) {
            System.out.println("Enter student reg no, name, address and grade: ");
            int reg = s.nextInt();
            String name = s.next();
            String add = s.next();
            char grade = s.next().charAt(0);
            studentsProbeHashMap.put(reg, new Student(reg, name, add, grade));
        }

        // display students using Iterator
        Iterator<Entry<Integer, Student>> l = studentsProbeHashMap.entrySet().iterator();
        while (l.hasNext()) {
            l.next().getValue().display();
        }

        // search for a student
        System.out.print("Enter reg number of the student to search: ");
        int to_search1 = s.nextInt();
        studentsProbeHashMap.get(to_search1).display();
    }
}
