package leet_again.graphs;

import java.util.ArrayList;
import java.util.List;

//
// Topological sort is a linear ordering of vertices in a Directed Acyclic Graph (DAG) such that for every directed edge
//A->B
//,A vertex
// comes before B
//. It arranges nodes to satisfy dependency constraints, commonly used in task scheduling, build systems, and dependency resolution


// Cycles in directed graphs; for cycles in un-directed graph see CycleInUnDirectedGraph
// this is different from undirected graph like below coz in that we do dfs and the visited aray is applicable to all nodes in any recursive call
//           1--2--3
//but in directed graph like this
//           1-->2<--3       see 2 is parent of 1 but 2 is also parent of 3. If we use the same visited array and parent check it will fail for directed graph
// hence we need to have a separate array inReucrsion[] which tells whether the node is involved for that dfs or not. Once we return back we reset the inRecursion array
public class CourseSchedule {
    boolean[] visited;
    boolean[] inRecursion;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacenyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacenyList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            int v = edge[0];
            int u = edge[1];
            adjacenyList.get(u).add(v);
        }
        visited = new boolean[numCourses];
        inRecursion = new boolean[numCourses];
        if (prerequisites.length == 0 && numCourses == 1)
            return true;
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                boolean hasCycle = isCycleDfs(adjacenyList, i, visited);
                if (hasCycle)
                    return false;
            }
        }
        return true;
    }
    //if there is no cycle then course can be finished; see CycleInUnDirectedGraph.java; it returns true when there is cycle

    boolean isCycleDfs(List<List<Integer>> adjacencyList, int vertex, boolean[] visited) {
        visited[vertex] = true;
        inRecursion[vertex] = true;
        List<Integer> neighbours = adjacencyList.get(vertex);
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {
                if (isCycleDfs(adjacencyList, neighbour, visited))
                    return true;
            } else if (inRecursion[neighbour]) {
                return true;
            }
        }
        inRecursion[vertex] = false;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new CourseSchedule().canFinish(2, new int[][]{{1,0 }}));
        System.out.println(new CourseSchedule().canFinish(2, new int[][]{{1,0 },{0,1}}));
        System.out.println(new CourseSchedule().canFinish(20, new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}}));
        System.out.println(new CourseSchedule().canFinish(2, new int[][]{{0, 1}}));


    }
}
