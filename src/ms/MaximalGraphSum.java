package ms;

import java.util.Arrays;

public class MaximalGraphSum {
     public int solution(int N, int[] A, int[] B) {
        int[] degree = new int[N];
        for (int i = 0; i < A.length; i++) {
            degree[A[i] - 1]++;
            degree[B[i] - 1]++;
        }

        Integer[] vertices = new Integer[N];
        for (int i = 0; i < N; i++) {
            vertices[i] = i;
        }
        Arrays.sort(vertices, (v1, v2) -> degree[v2] - degree[v1]);

        int[] values = new int[N];
        int currentValue = N;
        for (int vertex : vertices) {
            values[vertex] = currentValue--;
        }

        int totalWeight = 0;
        for (int i = 0; i < A.length; i++) {
            totalWeight += values[A[i] - 1] + values[B[i] - 1];
        }

        return totalWeight;
    }

    public static void main(String[] args) {
        System.out.println(new MaximalGraphSum().solution(5, new int[]{2,2,1,2},new int[]{1,3,4,4}));
        System.out.println(new MaximalGraphSum().solution(3, new int[]{1},new int[]{3}));
        System.out.println(new MaximalGraphSum().solution(4, new int[]{1,3},new int[]{2,4}));

    }
}
