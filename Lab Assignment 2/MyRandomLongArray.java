package com.company;

import java.util.Random;

public class MyRandomLongArray extends MyLongArray {
    MyRandomLongArray(int size) {
        super(size);
    }

    //initialize array with random numbers using java.util.Random
    public void initArray() {
        Random r = new Random(); //create a Random object
        r.setSeed(System.currentTimeMillis()); //seed the pseudo-random generator using system time
        for (int i = 0; i < a.length; ++i) {
            a[i] = 1 + r.nextLong(100000);
            //assign a random number in the range (1, 100000) to array elements
            //r.nextLong(1000) generates a random number in the range (0,999)
        }
        currentIndex = a.length; //update final index
    }

    //Bubble sort
    public void bubbleSort() {
        for (int i = a.length - 1; i > 1; --i) { //outer loop(backward)
            for (int j = 0; j < i; ++j) { //inner loop(forward)
                if (a[j] > a[j + 1]) {
                    //swap the elements
                    long temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    //Selection sort
    public void selectionSort() {
        for (int i = 0; i < a.length - 1; ++i) { //outer loop
            int min = i;  //stores index of min element
            for (int j = i + 1; j < a.length; ++j) { //inner loop
                if (a[j] < a[min]) //if j greater than current min
                    min = j; //update j to new min
            }
            if (min != i) { //swap if min is updated
                long temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }

    //Insertion sort
    public void insertionSort() {
        long positional;
        int j;
        for (int i = 1; i < a.length; ++i) {
            positional = a[i]; //remove element at selected position
            j = i - 1; //start to the left subarray
            while (j >= 0 && a[j] > positional) {
                //keep shifting elements in left subarray one ahead till an appropriate spot is found for the removed element
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = positional; //insert the removed element at the appropriate spot
        }
    }

}
