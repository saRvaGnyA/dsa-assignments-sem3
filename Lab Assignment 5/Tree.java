package com.company;

import java.lang.Iterable;
import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {

    // ----- ACCESSOR METHODS -------->

    Position<E> root();

    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    int numChildren(Position<E> p) throws IllegalArgumentException;

    // ----- QUERY METHODS -------->

    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    // ----- GENERAL METHODS -------->

    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    Iterable<Position<E>> positions();

}
