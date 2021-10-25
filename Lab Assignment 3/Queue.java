package com.company;

// create an interface of the Queue ADT with the necessary abstract methods

public interface Queue<E> {

    int size();

    boolean isEmpty();

    void enqueue(E val);

    E first();

    E dequeue();
}
