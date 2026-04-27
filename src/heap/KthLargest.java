package heap;

import java.util.PriorityQueue;

public class KthLargest {

    public int kthLargestNumber(int[] nums, int k) {
        // Min-Heap to store top k the largest numbers
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add elements to heap
        for (Integer num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element if heap exceeds size k
            }
        }

        // The root of the heap is the k-th largest number
        return minHeap.poll();
    }

    public static void main(String[] args) {
//        System.out.println(new KthLargest().kthLargestNumber(new int[]{3,6,7,10},4));
//        System.out.println(new KthLargest().kthLargestNumber(new int[]{2,21,12,1},3));
//        System.out.println(new KthLargest().kthLargestNumber(new int[]{0,0},2));
        System.out.println(new KthLargest().kthLargestNumber(new int[]{3,2,1,5,6,4},2));


    }
}
