package com.company;

public interface BinaryTree<E> extends Tree<E> {

    Position<E> left(Position<E> p) throws IllegalArgumentException; // returns left child of the passed node (if any)

    Position<E> right(Position<E> p) throws IllegalArgumentException; // returns right child of the passed node (if any)

    Position<E> sibling(Position<E> p) throws IllegalArgumentException; // returns sibling of the passed node (if any)

}
