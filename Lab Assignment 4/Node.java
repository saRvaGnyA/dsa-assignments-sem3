package com.company;

// REDUNDANT CLASS

// We use a linked list node to implement the Position ADT
public class Node<E> implements Position<E> {

    // ----- PRIVATE ATTRIBUTES OF A NODE -------->

    private E element; // stores the element at the position
    private Node<E> prev; // stores reference to the previous node of the list
    private Node<E> next; // stores reference to the next node of the list

    // parameterized constructor for the Node
    public Node(E e, Node<E> p, Node<E> n) {
        this.element = e;
        this.prev = p;
        this.next = n;
    }

    // ----- GETTER METHODS FOR THE NODE -------->

    public E getElement() throws IllegalStateException {
        if (next == null) // defunct node
            throw new IllegalStateException("Position is no longer valid");
        else
            return element;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public Node<E> getNext() {
        return next;
    }

    // ----- SETTER METHODS FOR THE NODE -------->

    public void setElement(E e) {
        this.element = e;
    }

    public void setNext(Node<E> n) {
        this.next = n;
    }

    public void setPrev(Node<E> p) {
        this.prev = p;
    }
}
