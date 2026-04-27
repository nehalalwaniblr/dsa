package leet_again.graphs.topological_sort;

/*
Topological sort is a linear ordering of vertices in a Directed Acyclic Graph (DAG) such that for every directed edge

, vertex
 comes before
. It arranges nodes to satisfy dependency constraints, commonly used in task scheduling, build systems, and dependency resolution


For directed acyclic graphs only. Why?
 * For undirected graph like this; we dont know which is the source/parent
 *      1--2
 * For cyclic graph like below; we run in loop 1->2 and 2->1 and so on
 *    1--2
 *    2--1
 *
 * Topological sort can be implemented with :
 * 1. DFS same code as it is only additional thing is to add stack
 * Stack should be populated with neighbours first and then at the end with the source
 *
 * Poppping out will give in reverse order i.e.source first
 *
 *
 * 2. BFS: Kahn's algorithm:
 * - Get the indegrees of all the nodes()iterate over adjacnecy list and for every element in the sublist do ++ in indegree array
 * - add all nodes with indegrees 0 to the queue. why? coz nodes with indegrees 0 will appear first as starting nodes
 * - iterate till queue is not empty:
 *      - pop from Q and add to result; now for this popped node reduce the indegrees of all its neigbouring nodes by 1
 *      - if indegree for that neigbouring node becomes 0 add to queue
 *      - Everytime you pop from queue maintain a count; if the final count != no. of vertices then that means the topological sort cant be constructed and their is a cycle; why?
 *          coz of cycle every node will have eventually or initially indegree >0 so the queue wont have enough notes.
 * e.g.
 *    1-->2
 *   |    |
 *    4<--3
 *
 * here all nodes will have indegree 1. so queue will be empty only
 *
 * another example:
 * *    1-->2
 *     |    ||
 *          3
 *  1 has indegree 0-->added to queue
 *  2 has indegree 2(from 1 and 3)
 *  3 has indegree 1
 *
 * once 1 is popped the indegree of 2 will be 1 and 3 will be also be 1; no node with indegree 0; hence queue processed only 1 element
 * */

import java.util.*;

public class TopologicalSort {
    Stack<Integer> stack = new Stack<>();

    public ArrayList<Integer> topoSortdfs(int V, int[][] edges) {
        // code here
        boolean[] visited = new boolean[V];
        ArrayList<Integer> result = new ArrayList<>();
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
        }
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adjacencyList, visited);
            }
        }
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    private void dfs(int i, List<List<Integer>> adjacencyList, boolean[] visited) {
        visited[i] = true;
        for (Integer neighbour : adjacencyList.get(i)) {
            if (!visited[neighbour])
                dfs(neighbour, adjacencyList, visited);
        }
        stack.push(i);
    }

    //Kahn's algorithm--bfs
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        int[] indegrees = new int[V];
        Arrays.fill(indegrees,0);
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
            //populate indegree matrix
            indegrees[v] = indegrees[v]+1;
        }
        //populate queue with indegree 0 nodes
        for(int i=0;i<indegrees.length;i++){
            if(indegrees[i]==0)
                queue.add(i);
        }
        while(!queue.isEmpty()){
            int node = queue.remove();
            result.add(node);
            for(Integer indexIndegree : adjacencyList.get(node)){
                indegrees[indexIndegree] = indegrees[indexIndegree]-1;
                if(indegrees[indexIndegree]==0)
                    queue.add(indexIndegree);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new TopologicalSort().topoSort(5, new int[][]{{0, 1}, {1, 2}, {2, 3},{3,4}}));
    }
}
