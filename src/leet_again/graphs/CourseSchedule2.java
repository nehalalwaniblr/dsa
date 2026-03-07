package leet_again.graphs;

import java.util.*;

public class CourseSchedule2 {
    //Using dfs
    Stack<Integer> stack = new Stack<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // code here
        boolean[] visited = new boolean[numCourses];
        boolean[] inRecursion = new boolean[numCourses];

        int[] result = new int[numCourses];
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            int v = edge[0];
            int u = edge[1];
            adjacencyList.get(u).add(v);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (dfs(i, adjacencyList, visited, inRecursion)) {
                    return new int[]{};
                }
            }
        }
        int count = 0;
        while (!stack.isEmpty()) {
            result[count] = stack.pop();
            count++;
        }
        return result;
    }

    boolean dfs(int i, List<List<Integer>> adjacencyList, boolean[] visited, boolean[] inRecursion) {
        visited[i] = true;
        inRecursion[i] = true;
        for (Integer neighbour : adjacencyList.get(i)) {
            if (!visited[neighbour]) {
                if (dfs(neighbour, adjacencyList, visited, inRecursion)) {
                    return true;
                }
            } else if (inRecursion[neighbour])
                return true;

        }
        inRecursion[i] = false;
        stack.push(i);
        return false;
    }

    //Kahn's algo
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        Arrays.fill(indegrees, 0);
        int[] result = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            int v = edge[0];
            int u = edge[1];
            adjacencyList.get(u).add(v);
            //populate indegree matrix
            indegrees[v] = indegrees[v] + 1;
        }
        //populate queue with indegree 0 nodes
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0)
                queue.add(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.remove();
            result[count] = node;
            count++;
            for (Integer indexIndegree : adjacencyList.get(node)) {
                indegrees[indexIndegree] = indegrees[indexIndegree] - 1;
                if (indegrees[indexIndegree] == 0)
                    queue.add(indexIndegree);
            }
        }
        if(count!=numCourses)
            return new int[]{};
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(1, new int[][]{})));
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(2, new int[][]{{0, 1}, {1, 0}})));


    }
}
