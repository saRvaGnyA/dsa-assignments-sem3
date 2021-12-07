package com.company;

import java.util.*;

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
        return new Node<>(e, parent, left_node, right_node);
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
            node.setRight(t2.root);
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

    // ----- NESTED ELEMENT ITERATOR CLASS  -------->
    // this class is the corresponding element Iterator implementation adapted to position Iterator
    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> positionIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement(); // returns the next element
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    // return iterator of all elements in the tree
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    // ----- TREE TRAVERSALS  -------->

    // PREORDER TRAVERSAL
    // add positions of a subtree rooted at passed position p to a passed ArrayList in preorder manner - first the node, then its children
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p))
            preorderSubtree(c, snapshot);
    }

    // return an iterable of positions of the tree in preorder fashion
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        // call the recursive subroutine for the root node (i.e. entire tree preorder)
        if (!isEmpty())
            preorderSubtree(root(), snapshot);
        return snapshot;
    }

    // POSTORDER TRAVERSAL
    // add positions of a subtree rooted at passed position p to a passed ArrayList in postorder manner - first the children, then the node
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(p);
    }

    // return an iterable of positions of the tree in preorder fashion
    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        // call the recursive subroutine for the root node (i.e. entire tree postorder)
        if (!isEmpty())
            postorderSubtree(root(), snapshot);
        return snapshot;
    }

    // INORDER TRAVERSAL
    // add positions of a subtree rooted at passed position p to a passed ArrayList in postorder manner: inorder pattern is - LEFT, NODE, RIGHT
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }

    // return an iterable of positions of the tree in an inorder fashion
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        // call the recursive subroutine for the root node (i.e. entire tree inorder)
        if (!isEmpty())
            inorderSubtree(root(), snapshot);
        return snapshot;
    }

    // make inorder traversal the default iterable for the tree
    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }

    // BFS TRAVERSAL
    // we use a queue ADT (its FIFO nature) to add nodes in a level-order
    public Iterable<Position<E>> BFS() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!this.isEmpty()) {
            Deque<Position<E>> levelOrder = new ArrayDeque<>();
            levelOrder.addFirst(this.root);
            while (!levelOrder.isEmpty()) {
                Position<E> next = levelOrder.removeFirst();
                snapshot.add(next);
                for (Position<E> child : children(next)) {
                    levelOrder.addLast(child);
                }
            }
        }
        return snapshot;
    }
    
    // ----- TREE APPLICATIONS  -------->

    // ----- Q3  -------->

    // Printing the arithmetic expression is equivalent to inorder traversal of the tree, with printing an opening bracket everytime inorder for the left node is called for a node and printing the corresponding right bracket when inorder for the right node is called for that node

    public void printArithmeticInorder(Position<E> p) {
        if (left(p) != null) {
            System.out.print("(");
            printArithmeticInorder(left(p));
        }
        System.out.print(p.getElement());
        if (right(p) != null) {
            printArithmeticInorder(right(p));
            System.out.print(")");
        }
    }

}
