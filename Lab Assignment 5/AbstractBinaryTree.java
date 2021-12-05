package com.company;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (parent == null) // root node
            return null;
        if (p == left(p)) // if p is left child position, return its right sibling
            return right(p);
        return left(p); // if p is right child position, return its left sibling
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int no_of_children = 0;
        if (left(p) != null) // check if the left child exists
            no_of_children++;
        if (right(p) != null) // check if the right child exists
            no_of_children++;
        return no_of_children;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> child_posns = new ArrayList<>(2); // initialize an ArrayList of 2 children
        // append the left and right children if they exist
        if (left(p) != null)
            child_posns.add(left(p));
        if (right(p) != null)
            child_posns.add(right(p));
        return child_posns;
    }
    
}
