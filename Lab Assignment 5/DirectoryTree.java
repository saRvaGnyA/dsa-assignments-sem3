package com.company;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DirectoryTree {

    // ----- NESTED NODE CLASS  -------->

    static class Node {

        // ----- ATTRIBUTES OF A DIRECTORY NODE  -------->

        private String name;
        private int type;
        private long size;
        private Date date;
        private List<Node> children;

        // ----- CONSTRUCTOR FOR NODE -------->
        Node(String n, int t, long s, long timeStamp) {
            this.name = n;
            this.type = t;
            this.size = s;
            date = new Date(timeStamp);
            children = new ArrayList<>();
        }

        // ----- PUBLIC METHODS OF NODE -------->

        public List<Node> getChildren() {
            return children;
        }

        public void addChild(File f) {
            int t = f.isDirectory() ? 1 : 0;
            Node n = new Node(f.getName(), t, f.length(), f.lastModified());
            this.children.add(n);
        }

        public void print() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String typeLabel = "";
            if (this.type == 1)
                typeLabel = "DIR";
            System.out.println(dateFormat.format(this.date) + "\t" + typeLabel + "\t" + String.format("%,d", this.size) + "\t" + this.name);
        }

    }

    private Node root;

    // ----- CONSTRUCTOR FOR DIRECTORY TREE -------->
    DirectoryTree() {

    }
    // ----- PUBLIC METHODS OF DIRECTORY TREE -------->

    public Node addRoot(File r) {
        int t = r.isDirectory() ? 1 : 0;
        this.root = new Node(r.getName(), t, r.length(), r.lastModified());
        if (t == 1) {
            File[] contents = r.listFiles();
            for (int i = 0; i < Objects.requireNonNull(contents).length; ++i) {
                this.root.addChild(contents[i]);
            }
        }
        return this.root;
    }

    void printDirectory(Node r) {
        r.print();
        for (Node c : r.getChildren())
            c.print();
    }
}
