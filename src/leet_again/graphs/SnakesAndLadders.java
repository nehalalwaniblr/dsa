package leet_again.graphs;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/snakes-and-ladders/description/?envType=study-plan-v2&envId=top-interview-150
//aproach:
/*
 * 1.Consider cell 1 i.e. [n-1],[0] i.e its 1 from here we can move to 6 possible places as a dice has 6 possibilities so from 1 we can reach to 2,3,4,5,67
 * 2. Now consider next level and let's take 2, from there we can reach 3,4,5,6,7,8 but since 3,4,5,6,7 were already visited in level 1 lets use visited 2 d array to store visited cells
 * 3. So lets start with value 1 and put in queue and do bfs
 * 4. everytime you remove from queue for 1 level you'll add possibilities at next level in queue. Note: we need to traverse here level wise to get the steps/count(while n--)
 * 5. In for each possibility you'll check if you have reached the N*N cell; if so you'll return
 * 6. One thing to note:  when the cell has -1 you can continue adding possible explorations but if not there is a snake or a ladder in that case you need to get the coordinates of that cell to get the value of the cell
 * As you have the cell number and not the coordinate.
 * 7. Once you get the coordinates you can get the value at that cell and add it to queue as that is also one possibility
 * 8. Keep incrementing steps at each level. Inside for check if you have reached destination if so return else you return -1
 * Time complexity will be O(n^2) as we are visiting each cell once.
 * */
public class SnakesAndLadders {
    int N;

    public int snakesAndLadders(int[][] board) {
        Queue<Integer> queue = new LinkedList<>();
        int steps = 0;
        N = board.length;
        boolean[][] visited = new boolean[N][N];
        queue.add(1);
        visited[N - 1][0] = true;

        while (!queue.isEmpty()) {
            int n = queue.size();
            while (n > 0) {
                int val = queue.remove();
                if (val == N * N)
                    return steps;
                for (int i = 1; i <= 6; i++) {
                    int x = val + i;
                    if (x > N * N)
                        break;
                    Cell cell = getCoordinates(x);
                    int r = cell.x();
                    int c = cell.y();
                    if (visited[r][c])
                        continue;
                    visited[r][c] = true;
                    if (board[r][c] != -1) {
                        queue.add(board[r][c]);
                    } else {
                        queue.add(x);
                    }
                }
                n--;
            }
            steps++;
        }
        return -1;
    }

    private Cell getCoordinates(int x) {
        int rowFromBottom = (x - 1) / N;
        int row = N - 1 - rowFromBottom;
        int col = (x - 1) % N;
        if (rowFromBottom % 2 == 1) {//left to right works fine; problem is with right to left; left to right happens for even rows
            col = (N - 1) - col;
        }
        return new Cell(row, col);
    }
}

record Cell(int x, int y) {

}
