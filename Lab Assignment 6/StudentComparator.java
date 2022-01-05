package com.company;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        int reg1 = o1.getId();
        int reg2 = o2.getId();
        return Integer.compare(reg1, reg2);
    }
}
