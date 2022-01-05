package com.company;

// Java interface for Positional Lists. Represents the positional list ADT
public interface PositionalList<E> {

    // ----- ACCESSOR METHODS -------->

    int size(); // returns no of elements currently in the list

    boolean isEmpty(); // returns true if the list is empty

    Position<E> first(); // returns first position of the list (null if empty)

    Position<E> last(); // returns last position of the list (null if empty)

    Position<E> before(Position<E> p) throws IllegalArgumentException;
    // returns position before the passed position (returns null if head is passed)
    // if passed position is invalid, throws an exception

    Position<E> after(Position<E> p) throws IllegalArgumentException;
    // returns position after the passed position (returns null if tail is passed)
    // if passed position is invalid, throws an exception

    // ----- UPDATE METHODS -------->

    Position<E> addFirst(E e); // prepends element to the list and returns its position

    Position<E> addLast(E e); // appends element to the list and returns its position

    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
    // adds element right before a specified position, and returns that position
    // throws exception if the passed position is invalid

    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
    // adds element right after a specified position, and returns that position
    // throws exception if the passed position is invalid

    E set(Position<E> p, E e) throws IllegalArgumentException;
    // Replaces the element at position p with element e, returning the element formerly at position p

    E remove(Position<E> p) throws IllegalArgumentException;
    //Removes and returns the element at position p in the list, invalidating the position p
}