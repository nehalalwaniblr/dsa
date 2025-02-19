package heap;

import java.util.PriorityQueue;

public class KthLargest {

    public String kthLargestNumber(String[] nums, int k) {
        // Min-Heap to store top k largest numbers
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> new java.math.BigInteger(a).compareTo(new java.math.BigInteger(b)));

        // Add elements to heap
        for (String num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element if heap exceeds size k
            }
        }

        // The root of the heap is the k-th largest number
        return minHeap.poll();
    }

    public static void main(String[] args) {
//        System.out.println(new KthLargest().kthLargestNumber(new String[]{"3","6","7","10"},4));
        System.out.println(new KthLargest().kthLargestNumber(new String[]{"2","21","12","1"},3));
//        System.out.println(new KthLargest().kthLargestNumber(new String[]{"0","0"},2));

    }
}
