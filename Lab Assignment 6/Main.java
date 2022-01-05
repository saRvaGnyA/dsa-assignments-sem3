package com.company;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        // ----- HEAP SORT -------->

        System.out.println("\n!----- HEAP SORT -------->");
        Sort<Student> t = new Sort<>();
        LinkedPositionalList<Student> pl1 = new LinkedPositionalList<>();
        Student s1 = new Student(201070056, "Sarvagnya", "Thane", 'A');
        pl1.addFirst(s1);
        Student s2 = new Student(201070044, "Amogh", "Mumbai", 'B');
        pl1.addFirst(s2);
        Student s3 = new Student(201070066, "Nirmay", "Pune", 'C');
        pl1.addFirst(s3);
        Student s4 = new Student(201070015, "Arjun", "Navi Mumbai", 'D');
        pl1.addFirst(s4);
        Student s5 = new Student(201070027, "Hrishik", "Vashi", 'E');
        pl1.addFirst(s5);
        PriorityQueue<Student> pq1 = new PriorityQueue<>(pl1.size(), new StudentComparator());
        t.HeapSort(pl1, pq1);

        // ----- MERGE SORT -------->
        System.out.println("\n!----- MERGE SORT -------->");
        Student[] arr1 = new Student[5];
        Student s1m = new Student(201070076, "Shivam", "Thane", 'A');
        Student s2m = new Student(201070048, "Abhinay", "Mumbai", 'B');
        Student s3m = new Student(201070062, "Sidhaant", "Pune", 'C');
        Student s4m = new Student(201070013, "Rohit", "Navi Mumbai", 'D');
        Student s5m = new Student(201070029, "Yashraj", "Vashi", 'E');
        arr1[0] = s1m;
        arr1[1] = s2m;
        arr1[2] = s3m;
        arr1[3] = s4m;
        arr1[4] = s5m;
        Student[] arr2 = Sort.mergeSort(arr1, new StudentComparator());
        for (Student s : arr2) {
            System.out.println(s);
        }

        // ----- QUICK SORT -------->

        System.out.println("\n!----- QUICK   SORT -------->");
        Student[] arrq = new Student[6];
        Student s1q = new Student(201070077, "Fenil", "Thane", 'A');
        Student s2q = new Student(201070088, "Moneel", "Mumbai", 'B');
        Student s3q = new Student(201070099, "Jayant", "Pune", 'C');
        Student s4q = new Student(201070011, "Rohit", "Navi Mumbai", 'D');
        Student s5q = new Student(201070022, "Yashraj", "Vashi", 'E');
        Student s6q = new Student(201070033, "Dharmesh", "Bhiwandi", 'F');
        arrq[0] = s1q;
        arrq[1] = s2q;
        arrq[2] = s3q;
        arrq[3] = s4q;
        arrq[4] = s5q;
        arrq[5] = s6q;
        Student[] arr4 = Sort.quickSortInPlace(arrq, new StudentComparator(), 0, arrq.length - 1);
        for (Student s : arr4) {
            System.out.println(s);
        }

        // ----- RADIX SORT -------->

        System.out.println("\n!----- RADIX SORT -------->");
        int[] arr6 = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr6.length;
        Sort.radixSort(arr6, n);
        for (int i : arr6) {
            System.out.print(i + ", ");
        }
    }
}
