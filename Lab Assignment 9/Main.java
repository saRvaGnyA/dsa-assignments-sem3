package com.company;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 8);
        g.addEdge(1, 2, 10);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 20);
        g.BFS(2);
        g.DFS(2);
        g.prims(2);
    }
}
