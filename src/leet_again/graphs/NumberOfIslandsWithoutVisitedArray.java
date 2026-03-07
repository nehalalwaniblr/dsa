package leet_again.graphs;

import java.util.Stack;

public class NumberOfIslandsWithoutVisitedArray {

    int M;
    int N;

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(new NumberOfIslandsWithoutVisitedArray().numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        M = grid.length;
        N = grid[0].length;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    dfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int i, int j) {
        Stack<Pair2> stack = new Stack<>();
        Pair2 p = new Pair2(i, j);
        stack.push(p);
        while (!stack.isEmpty()) {
            Pair2 currentNode = stack.pop();
            grid[currentNode.x()][currentNode.y()] = '0';
            //Move right
            if (currentNode.x() + 1 < M && grid[currentNode.x() + 1][currentNode.y()] == '1') {
                stack.push(new Pair2(currentNode.x() + 1, currentNode.y()));
                grid[currentNode.x() + 1][currentNode.y()] = '0';
            }
            //Move left
            if (currentNode.x() - 1 >= 0 && grid[currentNode.x() - 1][currentNode.y()] == '1') {
                stack.push(new Pair2(currentNode.x() - 1, currentNode.y()));
                grid[currentNode.x() - 1][currentNode.y()] = '0';

            }
            //Move top
            if (currentNode.y() - 1 >= 0 && grid[currentNode.x()][currentNode.y() - 1] == '1') {
                stack.push(new Pair2(currentNode.x(), currentNode.y() - 1));
                grid[currentNode.x()][currentNode.y() - 1] = '0';

            }
            //Move bottom
            if (currentNode.y() + 1 < N && grid[currentNode.x()][currentNode.y() + 1] == '1') {
                stack.push(new Pair2(currentNode.x(), currentNode.y() + 1));
                grid[currentNode.x()][currentNode.y() + 1] = '0';
            }
        }
    }
}

record Pair2(int x, int y) {
}