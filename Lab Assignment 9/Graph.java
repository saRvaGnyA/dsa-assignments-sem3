package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph {

    private final int[][] adj; // adjacency matrix of the graph
    private final int vertices; // number of vertices in the graph
    private final String[] cityNames; // store names of cities corresponding to their vertex numbers

    // constructor
    Graph(int v) {
        this.vertices = v;
        this.adj = new int[this.vertices][this.vertices];
        cityNames = new String[this.vertices];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < this.vertices; ++i) {
            System.out.print("Enter city name for vertex " + i + ": ");
            cityNames[i] = s.next();
            for (int j = 0; j < this.vertices; ++j)
                this.adj[i][j] = Integer.MAX_VALUE;
        }
    }

    // add edge b/w specified nodes
    public void addEdge(int source, int destination, int weight) {
        this.adj[source][destination] = weight;
        this.adj[destination][source] = weight;
    }

    // BFS/Level-order traversal
    public void BFS(int source) {
        System.out.println("BFS Traversal:");
        boolean[] visited = new boolean[this.vertices]; // keeps a track of visited vertices
        Queue<Integer> q = new LinkedList<>(); // queue used for BFS traversal
        q.add(source);
        visited[source] = true;
        while (!q.isEmpty()) {
            System.out.print(q.peek() + ". " + this.cityNames[q.peek()] + ", ");
            int removed = q.remove();
            for (int i = 0; i < this.vertices; ++i) {
                if (this.adj[removed][i] != Integer.MAX_VALUE && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println("\n");
    }

    // DFS Traversal recursive function
    private void DFSRec(int source, boolean[] visited) {
        visited[source] = true;
        System.out.print(source + ". " + this.cityNames[source] + ", ");
        for (int i = 0; i < this.vertices; ++i) {
            if (this.adj[source][i] != Integer.MAX_VALUE && !visited[i])
                DFSRec(i, visited);
        }
    }

    // DFS Traversal main function
    public void DFS(int source) {
        System.out.println("DFS Traversal:");
        boolean[] visited = new boolean[this.vertices];
        DFSRec(source, visited);
        System.out.println("\n");
    }

    public void prims(int source) {
        int[][] t = new int[2][this.vertices - 1]; // to store the edges of the MST
        int[] near = new int[this.vertices]; // to store the nearest vertices, initialize with infinity
        for (int i = 0; i < this.vertices; ++i) {
            near[i] = Integer.MAX_VALUE;
        }
        near[source] = -1; // the source node is included in the tree first
        // find the minimum weight edge and its corresponding vertex for the source node
        int min_edge = Integer.MAX_VALUE;
        int min_vertex = -1;
        for (int i = 0; i < this.vertices; ++i) {
            if (this.adj[source][i] < min_edge) {
                min_vertex = i;
                min_edge = this.adj[source][i];
            }
        }
        // update the `near` and `tree` array for the first edge of the minimum spanning tree
        t[0][0] = source;
        t[1][0] = min_vertex;
        near[min_vertex] = -1;

        for (int i = 0; i < this.vertices; ++i) {
            if (near[i] != -1) {
                if (this.adj[i][source] < this.adj[i][min_vertex])
                    near[i] = source;
                else
                    near[i] = min_vertex;
            }
        }

        // repeat on loop - find the next minimum edge vertex connected to the cut set, include that vertex to the `tree` array and update the `near` array likewise. loop n-2 times since the MST has n-1 edges for n vertices
        for (int i = 0; i < this.vertices - 2; ++i) {
            min_edge = Integer.MAX_VALUE;
            // find minimum edge to the cut set
            for (int j = 0; j < this.vertices; ++j) {
                if (near[j] != -1 && this.adj[j][near[j]] < min_edge) {
                    min_edge = this.adj[j][near[j]];
                    min_vertex = j;
                }
            }
            // include that min edge to the MST
            t[0][i + 1] = near[min_vertex];
            t[1][i + 1] = min_vertex;
            near[min_vertex] = -1;
            // update the `near` array
            for (int j = 0; j < this.vertices; ++j) {
                if (near[j] != -1 && this.adj[j][min_vertex] < this.adj[j][near[j]])
                    near[j] = min_vertex;
            }
        }

        // print the MST
        for (int i = 0; i < this.vertices - 1; ++i) {
            System.out.print(t[0][i] + " -- " + t[1][i] + ", ");
        }

    }
}
