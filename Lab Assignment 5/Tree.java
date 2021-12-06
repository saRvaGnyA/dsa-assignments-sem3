package com.company;

import java.lang.Iterable;
import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {

    // ----- ACCESSOR METHODS -------->

    Position<E> root(); // returns position of the root of the tree

    Position<E> parent(Position<E> p) throws IllegalArgumentException; // returns position of the parent (if any) of the passed node

    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException; // returns an iterable of children positions (if any) of the passed node

    int numChildren(Position<E> p) throws IllegalArgumentException; // returns number of children of the passed position

    // ----- QUERY METHODS -------->

    boolean isExternal(Position<E> p) throws IllegalArgumentException; // returns true if the passed node is a leaf node

    boolean isInternal(Position<E> p) throws IllegalArgumentException; // returns true if the passed node has children

    boolean isRoot(Position<E> p) throws IllegalArgumentException; // returns true if the passed node is the root

    // ----- GENERAL METHODS -------->

    int size(); // returns size of the tree

    boolean isEmpty(); // returns true if the tree is empty

    Iterator<E> iterator(); // returns an iterator of all the elements of the tree

    Iterable<Position<E>> positions(); // returns an iterable of all the positions of the tree

}
