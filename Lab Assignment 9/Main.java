package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of cities involved in your map: ");
        int cities = s.nextInt();
        Graph g = new Graph(cities);
        System.out.print("Enter the source vertex for BFS Traversals: ");
        int source = s.nextInt();
        g.BFS(source);
        System.out.print("Enter the source vertex for DFS Traversals: ");
        source = s.nextInt();
        g.DFS(source);
        System.out.print("Enter the source vertex for Prims Minimal Spanning Tree: ");
        source = s.nextInt();
        g.prims(source);
        System.out.print("Kruskal's Minimal Spanning Tree is: ");
        g.kruskal();
    }

}
