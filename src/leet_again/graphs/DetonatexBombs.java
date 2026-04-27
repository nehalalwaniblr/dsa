package leet_again.graphs;

import java.util.HashSet;
import java.util.Set;

public class DetonatexBombs {
    public int maximumDetonation(int[][] bombs) {
        int m = bombs.length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            //set which tells what nodes are detonated; once detonated for one iteration should be set to null for other iteration
            //i.e. say you start with 1 st node find what all nodes can be detonated(do the dfs) - all detonated nodes will be in detonated set
            //then you take 2nd node and do the same i.e. find what all nodes can be detonated.
            //keep maintaing the max result
            Set<Integer> detonated = new HashSet<>();
            dfs(bombs, i,detonated);
            result = Math.max(result, detonated.size());
        }
        return result;
    }

    void dfs(int[][] bombs, int i, Set<Integer> detonated) {
        if(detonated.contains(i))
            return;
        detonated.add(i);
        for (int j = 0; j < bombs.length; j++) {
            if (i == j)
                continue;
            //calculate the distance between 2 nodes
            long dx= bombs[j][0]-bombs[i][0];
            long dy= bombs[j][1]-bombs[i][1];
            double distance = Math.sqrt((dx*dx)+(dy*dy));
            //can the current node detonate other node? i.e. if radius of first node goes beyond or at-least till the distance between 2 then the other one can be detonated
            if (bombs[i][2] >= distance) {
                dfs(bombs, j, detonated);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new DetonatexBombs().maximumDetonation(new int[][]{{2, 1, 3}, {6, 1, 4}}));
        System.out.println(new DetonatexBombs().maximumDetonation(new int[][]{{1, 1, 5}, {10, 10, 5}}));
        System.out.println(new DetonatexBombs().maximumDetonation(new int[][]{{1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}}));

    }
}
