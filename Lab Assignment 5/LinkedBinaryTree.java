package com.company;

import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    // ----- NESTED NODE CLASS  -------->
    protected static class Node<E> implements Position<E> {

        // ----- ATTRIBUTES OF A NODE  -------->
        private E element; // element at the node
        // references to other linked nodes
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        // -----  CONSTRUCTOR FOR NODE -------->
        public Node(E e, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        // ----- GETTER METHODS  -------->

        @Override
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // ----- SETTER METHODS  -------->

        public void setElement(E e) {
            this.element = e;
        }

        public void setParent(Node<E> p) {
            this.parent = p;
        }

        public void setLeft(Node<E> l) {
            this.left = l;
        }

        public void setRight(Node<E> r) {
            this.right = r;
        }

    }

    // ----- CORRESPONDING FACTORY FUNCTION TO CREATE NEW NODE  -------->
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left_node, Node<E> right_node) {
        return new Node<E>(e, parent, left_node, right_node);
    }

    // ----- ATTRIBUTES OF A LINKED BINARY TREE  -------->
    protected Node<E> root = null;
    private int size = 0;

    // -----  CONSTRUCTOR FOR TREE -------->
    LinkedBinaryTree() {
        // create an empty binary tree
    }

    // ----- OTHER PRIVATE UTILITY TO VALIDATE POSITIONS -------->
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> posNode = (Node<E>) p; // typecast
        if (posNode.getParent() == posNode) // check for defunct node
            throw new IllegalArgumentException("p is no longer a valid position");
        return posNode;
    }

    // ----- ACCESSOR METHODS -------->

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    // ----- UPDATE METHODS -------->

    // add root to an empty tree, set the root element as the passed element
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!this.isEmpty())
            throw new IllegalStateException("Tree is not empty");
        this.root = createNode(e, null, null, null);
        this.size = 1;
        return root;
    }

    // add left child to a passed position, if not already present
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException, IllegalStateException {
        Node<E> node = validate(p);
        if (node.getLeft() != null)
            throw new IllegalStateException("Passed node p already has a left child");
        Node<E> l = createNode(e, node, null, null);
        node.setLeft(l);
        ++this.size;
        return l;
    }

    // add right child to a passed position, if not already present
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException, IllegalStateException {
        Node<E> node = validate(p);
        if (node.getRight() != null)
            throw new IllegalStateException("Passed node p already has a left child");
        Node<E> r = createNode(e, node, null, null);
        node.setRight(r);
        ++this.size;
        return r;
    }

    // set element at a passed position with the passed element, and return the old element
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    // attach passed trees t1 and t2 as left and right sub-trees resp to the passed position
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            // attach the subtree with the given tree at the given node
            t1.root.setParent(node);
            node.setLeft(t1.root);
            // dissolve the independent subtree units
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            // attach the subtree with the given tree at the given node
            t2.root.setParent(node);
            node.setLeft(t2.root);
            // dissolve the independent subtree units
            t2.root = null;
            t2.size = 0;
        }
    }

    // remove a node associated with the passed position, and replace it with its child(if any)
    public E remove(Position<E> p) throws IllegalArgumentException, IllegalStateException {
        Node<E> node = validate(p);
        E temp = node.getElement(); // value to return

        // if 2 children are present, not possible to replace
        if (numChildren(p) == 2)
            throw new IllegalStateException("The position p has 2 children");

        // 1 or 0 children case - if left node is null, take the right node
        Node<E> child_to_replace = (node.getLeft() != null) ? node.getLeft() : node.getRight();

        // in case the right child is not null
        if (child_to_replace != null)
            child_to_replace.setParent(node.getParent());

        // if the node to be removed is root
        if (node == root)
            this.root = child_to_replace;

            // if the node isn't root
        else {
            // check if the left or right node is going to replace the removed node
            if (child_to_replace == node.getLeft()) {
                node.getParent().setLeft(child_to_replace);
            } else {
                node.getParent().setRight(child_to_replace);
            }
        }

        size--;

        // allow the JVM garbage collector to recognize the defunct node
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        node.setElement(null);

        return temp;
    }

    // iterator() and positions() method remain to override - depending on traversals

}
