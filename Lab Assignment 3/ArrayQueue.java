package com.company;

// implement the queue ADT interface with an array-based implementation class

public class ArrayQueue<E> implements Queue<E> {

    // private attributes of the class
    private E[] a;
    private int rear;
    private int front;
    private int currentSize;

    ArrayQueue(int s) {
        a = (E[]) new Object[s]; // create an array of elements of generic type
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    // return current size of the queue
    public int size() {
        return currentSize;
    }

    // returns true if the queue is empty
    public boolean isEmpty() {
        return currentSize == 0;
    }

    // enqueue an element to the queue, if the queue is not full
    public void enqueue(E val) {
        if (rear == a.length - 1) {
            System.out.println("The queue is full");
        } else {
            rear++;
            a[rear] = val;
            currentSize++;
        }
    }

    // return the first element of the queue, if not empty
    public E first() {
        if (isEmpty())
            return null;
        return a[front];
    }

    // dequeue the first element off the queue and return it, if not empty
    public E dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            E temp = a[front];
            a[front] = null;
            front++;
            currentSize--;
            return temp;
        }
    }

    // display the queue from the first added element to the recently added element
    public void display() {
        for (int i = front; i <= rear; ++i) {
            System.out.print(a[i] + "\t");
        }
        System.out.println();
    }
}