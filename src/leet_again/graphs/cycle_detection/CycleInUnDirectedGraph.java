package leet_again.graphs.cycle_detection;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
/*
* 1. In undirected graph for give array of edges convert to adjaceny list
* 2. For each node dfs for its neigbours and neibours of neighbours
* 3. Check visited as existing dfs and also check that the neighbour node and the parent node arent same;
* i.e. when you do dfs and this is undirected graph you end up reaching the back(start node). So comparing only visited wont help
* You need to use parent node too
*    1-----2
*
* here you traverse 1 and mark visited 1
* then you traverse neighbours of 1 which is 2 mark as well visited
* then you traverse neighbours of 2 which is 1 and here 1 is already visited but its not a cycle.
* so whenever parent of current node(2's parent is 1) and neighbour of current node(2's neighbour is 1) are same, continue.
*
*
* FOR DIRECTED GRAPH SEE -- CourseSchedule
* */
public class CycleInUnDirectedGraph {
    boolean[] visited;

    public boolean isCycle(int V, int[][] edges) {
        // Code here
        List<List<Integer>> adjacenyList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjacenyList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            adjacenyList.get(u).add(v);
            adjacenyList.get(v).add(u);
        }

        visited = new boolean[V];
        if (edges.length == 0)
            return false;
        for (int i = 0; i < V; i++) {
            if (!visited[i] && isCycleDfs(adjacenyList, i, visited, -1)) {
                return true;
            }
        }
        return false;
    }

    boolean isCycleDfs(List<List<Integer>> adjacencyList, int vertex, boolean[] visited, int parent) {
        visited[vertex] = true;
        List<Integer> neighbours = adjacencyList.get(vertex);
        for (int neighbour : neighbours) {
            if (parent == neighbour)
                continue;
            if (visited[neighbour])
                return true;
            if (isCycleDfs(adjacencyList, neighbour, visited, vertex))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new CycleInUnDirectedGraph().isCycle(4, new int[][]{{0, 1}, {0, 2}, {1, 2}, {2, 3}}));
        System.out.println(new CycleInUnDirectedGraph().isCycle(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}));

    }
}
