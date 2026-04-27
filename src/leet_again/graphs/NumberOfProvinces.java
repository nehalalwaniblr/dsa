package leet_again.graphs;

import java.util.Arrays;

/*https://leetcode.com/problems/number-of-provinces/description/*/
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        int[] visited = new int[m];
        int result = 0;
        Arrays.fill(visited, -1);

        for (int i = 0; i < m; i++) {
            if (visited[i] == -1) {
                dfs(isConnected, visited, i);
                result++;
            }
        }
        return result;


    }

    void dfs(int[][] isConnected, int[] visited, int i) {
        visited[i] = 1;
        for (int j=0;j<isConnected.length;j++) {
            if (isConnected[i][j] == 1 && visited[j] == -1)
                dfs(isConnected, visited, j);
        }


    }

    public static void main(String[] args) {
        System.out.println(new NumberOfProvinces().findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(new NumberOfProvinces().findCircleNum(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
        System.out.println(new NumberOfProvinces().findCircleNum(new int[][]{{1,0,0,1}, {0,1,1,0}, {0, 1,1, 1},{1,0,1,1}}));


    }
}
