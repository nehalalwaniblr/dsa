package leet_again.graphs.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    public static void main(String[] args) {
        ArrayList<Integer> adj1 = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> adj2 = new ArrayList<>(Arrays.asList(0, 2));
        ArrayList<Integer> adj3 = new ArrayList<>(Arrays.asList(0, 1, 3, 4));
        ArrayList<Integer> adj4 = new ArrayList<>(Arrays.asList(2));
        ArrayList<Integer> adj5 = new ArrayList<>(Arrays.asList(2));

//        ArrayList<Integer> adj1 = new ArrayList<>(Arrays.asList(2, 3, 1));
//        ArrayList<Integer> adj2 = new ArrayList<>(Arrays.asList(0));
//        ArrayList<Integer> adj3 = new ArrayList<>(Arrays.asList(0, 4));
//        ArrayList<Integer> adj4 = new ArrayList<>(Arrays.asList(0));
//        ArrayList<Integer> adj5 = new ArrayList<>(Arrays.asList(2));

        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        input.add(adj1);
        input.add(adj2);
        input.add(adj3);
        input.add(adj4);
        input.add(adj5);

        System.out.println(new DFS().dfs(input));
    }

    public ArrayList<Integer> dfs2(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        // code here
        stack.push(0);
        while (!stack.isEmpty()) {
            Integer val = stack.pop();
            if (!visited[val]) {
                visited[val] = true;
                result.add(val);
            }
            for (int j = adj.get(val).size() - 1; j >= 0; j--) {
                if (!visited[adj.get(val).get(j)])
                    stack.push(adj.get(val).get(j));
            }
        }
        return result;
    }

    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> result = new ArrayList<>();
        if (adj.isEmpty())
            return null;
        if (adj.getFirst().isEmpty())
            return null;
        result.add(0);
        dfsRecursive(0, adj, visited, result);
        return result;
    }

    void dfsRecursive(int start, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, ArrayList<Integer> result) {
        visited[start] = true;
        for (Integer neighbour : adjList.get(start)) {
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                result.add(neighbour);
                dfsRecursive(neighbour, adjList, visited, result);
            }
        }
    }
}
