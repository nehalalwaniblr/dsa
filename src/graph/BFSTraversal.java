package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSTraversal {
//    1. start at any vertex; add to queue
//    2. mark the given vertex as visited; add neighbours of given vertex to queue;
//    Keep processing the queue with step 1
    static void bfs(List<List<Integer>> adj, int s) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        if(adj.isEmpty())
            return;
        queue.add(s);
        visited[s] = true;
        System.out.println(s);
        while (!queue.isEmpty()){
            int val = queue.poll();
            for(Integer i : adj.get(val)){
                if(!visited[i]){
                    visited[i]=true;
                    queue.add(i);
                    System.out.println(i);
                }
            }
        }
    }

    // Function to add an edge to the graph
    static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // Undirected graph
    }

    public static void main(String[] args) {

        // Number of vertices in the graph
        int V = 5;

        // Adjacency list representation of the graph
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 4);

        // Perform BFS traversal starting from vertex 0
        System.out.println("BFS starting from 0:");
        bfs(adj, 0);
    }
}
