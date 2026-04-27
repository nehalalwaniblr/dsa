package leet_again.graphs.traversal;

import java.util.*;
/*
* Some assumptions for every graph problem:
* - each node is unique that why we'll be able to keep a visited array
* - Each node starts with val 0 or even not we'll assume it;
* - bfs dfs code works the same for both directed and undirected graph
* */
public class BFS {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        boolean[] visited = new boolean[adj.size()];
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    Integer node = queue.poll();
                    if (!visited[node]) {
                        result.add(node);
                        visited[node] = true;
                        queue.addAll(adj.get(node));
                    }
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> adj1 = new ArrayList<>(Arrays.asList(2, 3, 1));
        ArrayList<Integer> adj2 = new ArrayList<>(Arrays.asList(0));
        ArrayList<Integer> adj3 = new ArrayList<>(Arrays.asList(0, 4));
        ArrayList<Integer> adj4 = new ArrayList<>(Arrays.asList(0));
        ArrayList<Integer> adj5 = new ArrayList<>(Arrays.asList(0, 2));

        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        input.add(adj1);
        input.add(adj2);
        input.add(adj3);
        input.add(adj4);
        input.add(adj5);

        System.out.println(new BFS().bfs(input));
    }
}
