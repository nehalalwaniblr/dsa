package leet_again.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**/
public class RottingOranges {
    int M;
    int N;

    public int orangesRotting(int[][] grid) {
        M = grid.length;
        N = grid[0].length;
        Queue<Integer[]> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Integer[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rotted = false;
            for (int i =0;i<size;i++){
                Integer[] current = queue.poll();
                int rIndex = current[0];
                int cIndex = current[1];
                //if the orange is rotten
                if (grid[rIndex][cIndex] == 2) {
                    //check left
                    if (cIndex-1>=0 && grid[rIndex][cIndex - 1] == 1) {
                        grid[rIndex][cIndex - 1] = 2;
                        queue.add(new Integer[]{rIndex, cIndex - 1});
                        rotted=true;
                    }
                    //right
                    if (cIndex+1<N && grid[rIndex][cIndex + 1] == 1) {
                        grid[rIndex][cIndex + 1] = 2;
                        queue.add(new Integer[]{rIndex, cIndex + 1});
                        rotted=true;
                    }
                    //top
                    if (rIndex-1>=0 && grid[rIndex - 1][cIndex] == 1) {
                        grid[rIndex - 1][cIndex] = 2;
                        queue.add(new Integer[]{rIndex - 1, cIndex});
                        rotted=true;
                    }
                    //bottom
                    if (rIndex+1<M && grid[rIndex + 1][cIndex] == 1) {
                        grid[rIndex + 1][cIndex] = 2;
                        queue.add(new Integer[]{rIndex + 1, cIndex});
                        rotted=true;
                    }
                }
            }
            if (rotted)
                count++;

        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{0, 2}}));
    }

}
