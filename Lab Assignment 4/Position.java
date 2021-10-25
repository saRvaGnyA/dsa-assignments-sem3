package com.company;

// Java Interface for Position, represents a position ADT
public interface Position<E> {

    E getElement() throws IllegalStateException;
    // this would return the element at the position
    // If the position isn't valid, this getter method would throw an exception

}
