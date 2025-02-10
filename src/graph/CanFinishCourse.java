package graph;

import java.util.*;

/*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 */
public class CanFinishCourse {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int pre = prereq[1];
            list.get(course).add(pre);
        }
        if(numCourses==0 || prerequisites==null)
            return false;
        for(int i=0;i<numCourses;i++){
             if(hasCycle(list, i,numCourses))
                 return false;
        }
        return true;
    }

    boolean hasCycle(List<List<Integer>> vertices, int source, int numCourses){
        Set<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source]=true;
        set.add(source);
        while (!queue.isEmpty()){
            int temp = queue.remove();
            for(int i : vertices.get(temp)){
                if(set.contains(i))
                    return true;
                if(!visited[i]){
                    set.add(i);
                    queue.add(i);
                    visited[i]=true;
                }
            }
//            set.clear();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new CanFinishCourse().canFinish(2,new int[][]{{1, 0}}));
        System.out.println(new CanFinishCourse().canFinish(2,new int[][]{{1, 0},{0,1}}));

        System.out.println(new CanFinishCourse().canFinish(5,new int[][]{{1,4},{2,4},{3,1},{3,2}}));

    }
}
