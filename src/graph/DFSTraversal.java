package graph;

import java.util.*;

public class DFSTraversal {
    static void dfs(List<List<Integer>> adj, int s) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adj.size()];
        if (adj.isEmpty())
            return;
        stack.add(s);
        visited[s] = true;
        System.out.println(s);
        while (!stack.isEmpty()) {
            int val = stack.pop();
            List<Integer> neighbours = adj.get(val);
            Collections.reverse(neighbours);
            for (int i : neighbours) {
                if (!visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                    System.out.println(i);
                }
            }
        }
    }

    // Function to add an edge to the adjacency list
    static void addEdge(List<List<Integer>> adj,
                        int s, int t) {
        // Add edge from vertex s to t
        adj.get(s).add(t);
        // Due to undirected Graph
        adj.get(t).add(s);
    }


    public static void main(String[] args) {
        int V = 5; // Number of vertices in the graph

        // Create an adjacency list for the graph
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Define the edges of the graph
        int[][] edges = {
                {1, 2}, {1, 0}, {2, 0}, {2, 3}, {2, 4}
        };

        // Populate the adjacency list with edges
        for (int[] e : edges) {
            addEdge(adj, e[0], e[1]);
        }

        int source = 4;
        System.out.println("DFS from source: " + source);
        dfs(adj, source);
    }
}
