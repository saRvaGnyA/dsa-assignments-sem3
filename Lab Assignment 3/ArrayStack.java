package com.company;

// create an array-based implementation based on the interface

public class ArrayStack<E> implements Stack<E> {
    private E[] a; // holds the elements
    private int stack_ptr; //points to the topmost element of the stack

    ArrayStack(int s) {
        a = (E[]) new Object[s]; // creates an array of elements of generic type
        stack_ptr = -1;
    }

    // returns current size
    public int size() {
        return (stack_ptr + 1);
    }

    // returns true if stack is empty
    public boolean isEmpty() {
        return stack_ptr == -1;
    }

    // returns topmost element of the stack, if any
    public E top() {
        if (isEmpty())
            return null;
        return a[stack_ptr];
    }

    // push operation of the stack, if no overflow occurs
    public void push(E val) {
        if (stack_ptr == a.length - 1) {
            System.out.println("Stack overflow error");
        } else {
            stack_ptr++;
            a[stack_ptr] = val;
            System.out.println("Value has been pushed");
        }
    }

    // pops an element off the stack(if not empty) and returns it
    public E pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty, cannot pop any element");
            return null;
        } else {
            E temp = a[stack_ptr];
            a[stack_ptr] = null;
            stack_ptr--;
            return temp;
        }
    }

    // displays the stack in top to bottom manner
    public void display() {
        for (int i = stack_ptr; i >= 0; --i) {
            System.out.println(a[i]);
        }
    }
}