package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        // ----- TREE APPLICATIONS  -------->

        // ----- Q1  -------->

        System.out.println("!----- Q1  -------->\n");

        GeneralTree<String> t = new GeneralTree<>();
        Position<String> tRoot = t.addRoot("Electronics R’Us");
        t.addChild(tRoot, "R&D");

        GeneralTree<String> t2 = new GeneralTree<>();
        Position<String> t2Root = t2.addRoot("Sales");
        t2.addChild(t2Root, "Domestic");

        GeneralTree<String> t22 = new GeneralTree<>();
        Position<String> t22Root = t22.addRoot("International");
        t22.addChild(t22Root, "Canada");
        t22.addChild(t22Root, "S. America");
        Position<String> t223 = t22.addChild(t22Root, "Overseas");
        t22.addChild(t223, "Africa");
        t22.addChild(t223, "Europe");
        t22.addChild(t223, "Asia");
        t22.addChild(t223, "Australia");

        t2.attach(t2Root, t22);
        t.attach(tRoot, t2);

        t.addChild(tRoot, "Purchasing");

        GeneralTree<String> t4 = new GeneralTree<>();
        Position<String> t4Root = t4.addRoot("Manufacturing");
        t4.addChild(t4Root, "TV");
        t4.addChild(t4Root, "CD");
        t4.addChild(t4Root, "Tuner");
        t.attach(tRoot, t4);

        t.display(tRoot, 0, "");
        System.out.println("\n");

        // ----- Q2  -------->

        System.out.println("!----- Q2  -------->\n");

        File f = new File("C:\\Program Files\\7-Zip");
        DirectoryTree dir = new DirectoryTree();
        DirectoryTree.Node r = dir.addRoot(f);
        dir.printDirectory(r);
        System.out.println("\n");

        // ----- Q3  -------->

        System.out.println("!----- Q3  -------->\n");

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

        System.out.println("!----- Q4  -------->\n");

        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        Position<Integer> tree_root = tree.addRoot(1);
        Position<Integer> root_left = tree.addLeft(tree_root, 2);
        tree.addRight(tree_root, 3);
        tree.addLeft(root_left, 4);
        tree.addRight(root_left, 5);
        tree.display(tree_root, 0);
        System.out.println("\n");

    }
}
