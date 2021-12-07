package com.company;

import java.util.ArrayList;
import java.util.List;

public class GeneralTree<E> {

    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private List<Node<E>> children;

        Node(E e, Node<E> p) {
            this.element = e;
            this.parent = p;
            this.children = new ArrayList<>();
        }

        @Override
        public E getElement() {
            return this.element;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public List<Node<E>> getChildren() {
            return this.children;
        }

        public boolean isLeaf() {
            return this.children.size() == 0;
        }

        public void setParent(Node<E> p) {
            this.parent = p;
        }

        public void addChild(Node<E> child) {
            this.children.add(child);
        }
    }

    protected Node<E> createNode(E e, Node<E> parent) {
        return new Node<>(e, parent);
    }

    private Node<E> root;
    private int size;

    GeneralTree() {
        this.root = null;
        this.size = 0;
    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> posNode = (Node<E>) p; // typecast
        if (posNode.getParent() == posNode) // check for defunct node
            throw new IllegalArgumentException("p is no longer a valid position");
        return posNode;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!this.isEmpty())
            throw new IllegalStateException("Tree is not empty");
        this.root = createNode(e, null);
        this.size = 1;
        return root;
    }

    public Position<E> addChild(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> child = createNode(e, node);
        node.addChild(child);
        this.size++;
        return child;
    }

    public void attach(Position<E> p, GeneralTree<E> t) throws IllegalArgumentException {
        Node<E> node = validate(p);
        this.size += t.size() - 1;
        if (!t.isEmpty()) {
            t.root.setParent(node);
            node.addChild(t.root);
            t.root = null;
            t.size = 0;
        }
    }

    // ----- TREE APPLICATIONS  -------->
    // ----- Q1  -------->

    // p is the position whose subtree is to be displayed
    // level is for maintaining level of indentations b/w recursive calls
    // label is for maintaining the prefix label b/w recursive calls - forEach loop is not used to use index as label
    public void display(Position<E> p, int level, String label) throws IllegalArgumentException {
        Node<E> node = validate(p);
        for (int i = 0; i < level; ++i)
            System.out.print("  ");
        System.out.print(label + " " + node.getElement() + "\n");
        if (!node.isLeaf()) {
            for (int i = 0; i < node.getChildren().size(); ++i) {
                String lab = label + (i + 1) + ".";
                display(node.getChildren().get(i), level + 1, lab);
            }
        }
    }

}
