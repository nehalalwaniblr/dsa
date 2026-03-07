package leet_again.graphs;

import java.util.Stack;

public class NumberOfIslands {

    int M;
    int N;

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
    }
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        M = grid.length;
        N = grid[0].length;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(grid, i, j, visited);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        Stack<Pair> stack = new Stack<>();
        Pair p = new Pair(i, j);
        stack.push(p);
        while (!stack.isEmpty()) {
            Pair currentNode = stack.pop();
            visited[currentNode.x()][currentNode.y()] = true;
            //Move right
            if (currentNode.x() + 1 < M && !visited[currentNode.x() + 1][currentNode.y()]) {
                if (grid[currentNode.x() + 1][currentNode.y()] == '1') {
                    stack.push(new Pair(currentNode.x() + 1, currentNode.y()));
                    visited[currentNode.x() + 1][currentNode.y()] = true;
                }
            }
            //Move left
            if (currentNode.x() - 1 >= 0 && !visited[currentNode.x() - 1][currentNode.y()]) {
                if (grid[currentNode.x() - 1][currentNode.y()] == '1') {
                    stack.push(new Pair(currentNode.x() - 1, currentNode.y()));
                    visited[currentNode.x() - 1][currentNode.y()] = true;
                }
            }
            //Move top
            if (currentNode.y() - 1 >= 0 && !visited[currentNode.x()][currentNode.y() - 1]) {
                if (grid[currentNode.x()][currentNode.y() - 1] == '1') {
                    stack.push(new Pair(currentNode.x(), currentNode.y() - 1));
                    visited[currentNode.x()][currentNode.y() - 1] = true;
                }
            }
            //Move bottom
            if (currentNode.y()+1 < N && !visited[currentNode.x()][currentNode.y() + 1]) {
                if (grid[currentNode.x()][currentNode.y() + 1] == '1') {
                    stack.push(new Pair(currentNode.x(), currentNode.y() + 1));
                    visited[currentNode.x()][currentNode.y() + 1] = true;
                }
            }
        }
    }
}

record Pair(int x, int y) {
}