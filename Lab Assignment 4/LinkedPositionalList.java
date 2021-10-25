package com.company;

// We will implement the Positional List ADT as a doubly linked list
public class LinkedPositionalList<E> implements PositionalList<E> {

    // ----- PRIVATE ATTRIBUTES OF A DOUBLY LINKED LIST -------->

    private int size;
    private Node<E> header; // reference to the sentinel (dummy) header node
    private Node<E> trailer; // reference to the sentinel (dummy) trailer node

    // constructor for the list
    public LinkedPositionalList() {
        this.size = 0;
        this.trailer = new Node<>(null, null, null); // create trailer
        this.header = new Node<>(null, null, this.trailer); // create header, link next to trailer
        this.trailer.setPrev(this.header); // set previous of trailer to header
    }

    // ----- PRIVATE UTILITIES OF A DOUBLY LINKED LIST -------->

    // private method to validate a node - will be used throughout the class methods
    // this will return a passed Position<E> as a Node<E>
    private Node<E> validateNode(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Passed position is invalid");
        Node<E> node = (Node<E>) p; // safe typecast of the reference types since `Node<E> implements Position<E>`
        if (node.getNext() == null) // defunct node
            throw new IllegalArgumentException("The passed node is defunct");
        return node;
    }

    // this will return a passed Node<E> as a Position<E>
    private Position<E> position(Node<E> node) {
        if (node == this.header || node == this.trailer)
            return null; // don't return private attributes of the class
        else
            return node; // Node<E> implements Position<E>F, hence can be returned
    }

    // add a node b/w 2 provided nodes
    private Position<E> addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(e, predecessor, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        ++this.size;
        return position(newNode);
    }

    // ----- ACCESSOR METHODS -------->

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Position<E> first() {
        return position(this.header.getNext());
    }

    public Position<E> last() {
        return position(this.header.getPrev());
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validateNode(p);
        return position(node.getPrev());
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validateNode(p);
        return position(node.getNext());
    }

    // ----- UPDATE METHODS -------->

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, this.header, this.header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, this.trailer.getPrev(), this.trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validateNode(p);
        return addBetween(e, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validateNode(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validateNode(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    // remove node is equivalent to unlinking the node from the list, and allowing the JVM garbage collector to gather it
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validateNode(p);
        E temp = node.getElement(); // store the value to return it
        // bypass the node in the list
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size--;
        // unlink node
        node.setPrev(null);
        node.setNext(null); // defunct node
        node.setElement(null); // helps the garbage collector identify the node and remove it
        return temp;
    }
}
