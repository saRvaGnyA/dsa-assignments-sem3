package com.company;

// create an interface with abstract methods

public interface Stack<E> {
    int size();

    boolean isEmpty();

    void push(E val);

    E top();

    E pop();
}