package leet_again.trees.bt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*https://leetcode.com/problems/time-needed-to-inform-all-employees/
It is an n-ary tree question; here we are given an array which tells at index i who is the manager, i.e. ith employee has manager a[i]
- now we need to iterate on all the managers and see how much time they are taking to inform their subordinates
- So lets create a map of manger and its employee list
- Now iterate over the map and for each manager, iterate its children
- each child should be checked if it's a manager then it'll contribute to result by using its informTime
- Also, note for each manager you need to include the informTime just once for all its children. so if there are 2 managers overall
then informTime would be accessed just twice, hence it is out of the for loop. and since we have to find the max time we need to get max at each stage for each child
*/

public class TimeNeededToInformAllEmployees {
    public int numOfMinutes(final int n, final int headID, final int[] manager, final int[] informTime) {
        Map<Integer, List<Integer>> managerToEmployeeMap = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            if (!managerToEmployeeMap.containsKey(manager[i])) {
                managerToEmployeeMap.put(manager[i], new ArrayList<>());
            }
            List<Integer> employees = managerToEmployeeMap.get(manager[i]);
            employees.add(i);
            managerToEmployeeMap.put(manager[i], employees);
        }
        return dfs(headID, managerToEmployeeMap, informTime);
    }

    int dfs(int current, Map<Integer, List<Integer>> map, int[] informTime) {
        int max = 0;
        //if current is not manager i.e.not in map
        if (!map.containsKey(current)) {
            return max;
        }
        List<Integer> employees = map.get(current);
        for (Integer employee : employees) {
            max = Math.max(max, dfs(employee, map, informTime));
        }
        return max + informTime[current];
    }

    public static void main(String[] args) {
        System.out.println(new TimeNeededToInformAllEmployees().numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0}));
        System.out.println(new TimeNeededToInformAllEmployees().numOfMinutes(7, 6, new int[]{1, 2, 3, 4, 5, 6, -1}, new int[]{0, 6, 5, 4, 3, 2, 1}));
    }
}
