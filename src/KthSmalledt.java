import java.util.PriorityQueue;

public class KthSmalledt {
    // Function to find the kth smallest array element
    public static int kthSmallest(int[] arr, int N, int K) {
        // Create a max heap (priority queue)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // Iterate through the array elements
        for (int i = 0; i < N; i++) {
            // Push the current element onto the max heap
            pq.offer(arr[i]);

            // If the size of the max heap exceeds K, remove the largest element
            if (pq.size() > K)
                pq.poll();
        }

        // Return the Kth smallest element (top of the max heap)
        return pq.peek();
    }

    // Driver's code:
    public static void main(String[] args) {
        int N = 10;
        int[] arr = { 10, 5, 4, 3, 48, 6, 2, 33, 53, 10 };
        int K = 4;

        // Function call
        System.out.println("Kth Smallest Element is: " + kthSmallest(arr, N, K));
    }
}
