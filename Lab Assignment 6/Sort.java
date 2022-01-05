package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Sort<E> {

    // ----- HEAP SORT -------->

    public <E> void HeapSort(LinkedPositionalList<E> list, PriorityQueue<E> pq) {
        int n = list.size();
        for (int j = 0; j < n; j++) {
            E element = list.remove(list.first());
            pq.add(element);
        }
        for (int j = 0; j < n; j++) {
            E element = pq.poll();
            list.addLast(element);
            System.out.print(element + "\n");
        }
        System.out.println("\n");
    }

    // ----- MERGE SORT -------->

    public static <K> K[] mergeSort(K[] arr, Comparator<K> comparator) {
        int n = arr.length;
        if (n < 2) return arr;
        int mid = n / 2;
        K[] arr1 = Arrays.copyOfRange(arr, 0, mid);
        K[] arr2 = Arrays.copyOfRange(arr, mid, n);
        mergeSort(arr1, comparator);
        mergeSort(arr2, comparator);
        merge(arr1, arr2, arr, comparator);
        return arr;
    }

    public static <K> void merge(K[] arr1, K[] arr2, K[] arr, Comparator<K> comparator) {
        int i = 0, j = 0;
        while (i + j < arr.length) {
            if (j == arr2.length || (i < arr1.length && comparator.compare(arr1[i], arr2[j]) < 0)) {
                arr[i + j] = arr1[i++];

            } else {
                arr[i + j] = arr2[j++];
            }

        }
    }

    // ----- QUICK SORT -------->

    public static <K> K[] quickSortInPlace(K[] arr, Comparator<K> comparator, int l, int r) {
        if (l >= r) return arr;
        int left = l;
        int right = r - 1;
        K pivot = arr[r];
        K temp;
        while (left <= right) {

            while (left <= right && comparator.compare(arr[left], pivot) < 0) {
                left++;
            }

            while (left <= right && comparator.compare(arr[right], pivot) > 0) {
                right--;
            }

            if (left <= right) {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }

        }
        temp = arr[left];
        arr[left] = arr[r];
        arr[r] = temp;
        quickSortInPlace(arr, comparator, l, left - 1);
        quickSortInPlace(arr, comparator, left + 1, r);
        return arr;
    }

    // ----- RADIX SORT -------->

    // A utility function to get maximum value in arr[]
    static int getMax(int[] arr, int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n]; // output array
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    static void radixSort(int[] arr, int n) {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

}
