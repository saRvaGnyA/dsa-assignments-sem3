package com.company;

public class Main {

    public static void main(String[] args) {

        // ----- TREE APPLICATIONS  -------->

        // ----- Q3  -------->

        LinkedBinaryTree<String> exp = new LinkedBinaryTree<>();
        Position<String> root = exp.addRoot("-");
        Position<String> left = exp.addLeft(root, "/");
        Position<String> right = exp.addRight(root, "+");

        LinkedBinaryTree<String> exp1 = new LinkedBinaryTree<>();
        Position<String> root1 = exp1.addRoot("*");
        Position<String> left1 = exp1.addLeft(root1, "+");
        exp1.addRight(root1, "3");
        exp1.addLeft(left1, "3");
        exp1.addRight(left1, "1");

        LinkedBinaryTree<String> exp2 = new LinkedBinaryTree<>();
        Position<String> root2 = exp2.addRoot("+");
        Position<String> left2 = exp2.addLeft(root2, "-");
        exp2.addRight(root2, "2");
        exp2.addLeft(left2, "9");
        exp2.addRight(left2, "5");

        exp.attach(left, exp1, exp2);

        LinkedBinaryTree<String> exp3 = new LinkedBinaryTree<>();
        Position<String> root3 = exp3.addRoot("*");
        exp3.addLeft(root3, "3");
        Position<String> right3 = exp3.addRight(root3, "-");
        exp3.addLeft(right3, "7");
        exp3.addRight(right3, "4");

        LinkedBinaryTree<String> exp4 = new LinkedBinaryTree<>();
        exp4.addRoot("6");

        exp.attach(right, exp3, exp4);

        exp.printArithmeticInorder(root);
        System.out.println("\n");

        // ----- Q4  -------->

        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        Position<Integer> tree_root = tree.addRoot(1);
        Position<Integer> root_left = tree.addLeft(tree_root, 2);
        tree.addRight(tree_root, 3);
        tree.addLeft(root_left, 4);
        tree.addRight(root_left, 5);
        tree.display(tree_root, 0);
    }
}
