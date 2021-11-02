package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Iterable;

// We will implement the Positional List ADT as a doubly linked list
public class LinkedPositionalList<E> implements PositionalList<E> {

    // ----- NESTED NODE CLASS -------->

    // We use a linked list node to implement the Position ADT
    private static class Node<E> implements Position<E> {

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
            return node; // Node<E> implements Position<E>, hence can be returned
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
        return position(this.trailer.getPrev());
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

    // ----- NESTED POSITION ITERATOR CLASS -------->

    // java.util.Iterator implementation for Position<E>
    private class PositionIterator implements Iterator<Position<E>> {

        private Position<E> cursor = first(); // position of the next element, initialize to header
        Position<E> recent = null; // a local variable that stores the last accessed position

        // Implement(override) the 3 java.util.Iterator interface methods

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null)
                throw new NoSuchElementException("No element found");
            else {
                recent = cursor;
                cursor = after(cursor);
                return recent;
            }
        }

        @Override
        public void remove() throws IllegalStateException {
            if (recent == null)
                throw new IllegalStateException("No element can be removed");
            LinkedPositionalList.this.remove(recent); // remove the node from the actual list
            recent = null;
        }
    }

    // ----- NESTED POSITION ITERABLE CLASS -------->

    // java.lang.Iterable implementation for Position<E>
    private class PositionIterable implements Iterable<Position<E>> {
        // Implement(override) the iterator() method for Position<E>
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    // public method to create and return the positional list's iterable for positions
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    // ----- NESTED ELEMENT ITERATOR CLASS -------->

    // java.util.Iterator implementation for the element E
    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> posIterator = new PositionIterator(); // use the corresponding position iterator

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement(); // return element now
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    // public method to create and return the element iterators
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
