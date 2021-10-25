package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter size of the array");
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        s.nextLine();
        MyLongArray arr = new MyLongArray(size);
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the following options: ");
            System.out.println("1.Find, 2.Insert, 3.getElement, 4.Delete, 5.Display, 6.Insert at index, 7.Delete Duplicates, 0.Exit");
            int option = s.nextInt();
            switch (option) {
                case 0:
                    flag = false;
                    break;
                case 1: {
                    System.out.print("Find the element to search: ");
                    long el = s.nextLong();
                    int ind = arr.find(el);
                    if (ind == -1) {
                        System.out.println("Element" + el + " not found");
                    } else {
                        System.out.println("Element " + el + " found at index " + ind);
                    }
                    break;
                }
                case 2: {
                    System.out.print("Enter element to insert: ");
                    long el = s.nextLong();
                    arr.insert(el);
                    break;
                }
                case 3: {
                    System.out.print("Enter the index: ");
                    int index = s.nextInt();
                    long element = arr.getElement(index);
                    if (element == -1) {
                        System.out.println("Valid index wasn't entered");
                    } else {
                        System.out.println("Element at index " + index + " is " + element);
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter the value to delete: ");
                    long val = s.nextLong();
                    if (arr.delete(val)) {
                        System.out.println("Element " + val + " deleted");
                    } else {
                        System.out.println("Element could not be deleted");
                    }
                    break;
                }
                case 5:
                    arr.display();
                    break;
                case 6: {
                    System.out.print("Enter the index and value to insert: ");
                    int ind = s.nextInt();
                    long val = s.nextLong();
                    arr.insert(ind, val);
                    break;
                }
                case 7: {
                    System.out.print("Enter the value to delete: ");
                    long val = s.nextLong();
                    System.out.println("Number of duplicates deleted are " + arr.dupDelete(val));
                    break;
                }
                default:
                    System.out.println("Enter a valid option");
            }
        }
    }
}
