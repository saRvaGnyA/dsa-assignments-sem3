package com.company;

public class MyLongArray {

    //class attributes
    long[] a;
    int currentIndex; //stores the index of the last element available

    //parameterized constructor
    MyLongArray(int size) {
        a = new long[size];
        currentIndex = 0;
    }

    //find method - finds the index corresponding to a key, and returns the index. If not found, returns -1
    int find(long searchKey) {
        int index = -1;
        for (int i = 0; i < currentIndex; ++i) {
            if (a[i] == searchKey) {
                index = i;
                break;
            }
        }
        return index;
    }

    //insertion method - inserts the specified value at currentIndex
    void insert(long value) {
        if (currentIndex < a.length) {
            a[currentIndex] = value;
            currentIndex++;
        } else {
            System.out.println("The array is full");
        }
    }

    //returns the element at the specified index, if valid. Else returns -1
    long getElement(int index) {
        if (index <= currentIndex) {
            return a[index];
        }
        return -1;
    }

    //deletes the first occurrence of the specified element and returns true, if valid.
    boolean delete(long value) {
        int index = find(value);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < currentIndex - 1; ++i) {
            a[i] = a[i + 1];
        }
        currentIndex--;
        return true;
    }

    //prints all the elements of the array
    void display() {
        for (int i = 0; i < currentIndex; ++i) {
            System.out.print(a[i] + "\t");
        }
    }

    //insert element at specified index
    void insert(int index, long value) {
        if (index > currentIndex) {
            System.out.println("Index entered invalid");
            a[currentIndex] = value;
            currentIndex++;
        } else {
            for (int i = currentIndex; i > index; --i) {
                a[i] = a[i - 1];
            }
            currentIndex++;
            a[index] = value;
        }
    }

    //delete duplicates method
    int dupDelete(long value) {
        int count = 0;
        //check if the element has been deleted, if yes, increment the count
        while (delete(value)) {
            count++;
        }
        return count;
    }
}
