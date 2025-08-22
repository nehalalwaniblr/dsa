package real_world_examples.netflix;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Our third task is building a filter that will fetch relevant content based on the age of the users for a specific country or region. For this, we use the median age of users and the preferred content for users that fall into that specified category.

Because the number of users is constantly increasing on the Netflix platform, we need to figure out a structure or design that updates the median age of users in real-time. We will have an array that constantly receives age values, and we will output the median value after each new data point.



Max Heap	Stores smaller half (left side)
Min Heap	Stores larger half (right side)
Balancing	Keeps heaps within 1 element size difference
Median Logic	- Odd total → peek from larger heap
- Even → avg of both peeks

Time Complexity:
Insert Age:O(logn)
Find Median: O(1)

Memory complexity:O(n)
*/
public class MedianOfAges {
    PriorityQueue<Integer> smallList; //containing first half of numbers
    PriorityQueue<Integer> largeList; //containing second half of numbers

    public MedianOfAges() {
        largeList = new PriorityQueue<>(Comparator.reverseOrder()); //max heap ;to store the lower half of  list
        smallList = new PriorityQueue<>(); //min heap;to store the upper half of  list
    }

    public void addAge(int age){
        if(largeList.isEmpty() || largeList.peek()>=age){
            largeList.offer(age);
        }else {
            smallList.offer(age);
        }

        //balance
        if(largeList.size()>smallList.size()+1){
            smallList.offer(largeList.poll());
        }else if(smallList.size()>largeList.size()){
            largeList.offer(smallList.poll());
        }
    }

    public double getMedian(){
        if (largeList.isEmpty()) {
            throw new IllegalStateException("No elements added");
        }
        if(largeList.size()!=smallList.size()){
            return largeList.peek();
        }else
            return (largeList.peek()+smallList.peek())/2.0;
    }

    public static void main(String[] args) {
        MedianOfAges medianOfAges = new MedianOfAges();
        medianOfAges.addAge(22);
        medianOfAges.addAge(35);
        System.out.println("Median = "+medianOfAges.getMedian());
        medianOfAges.addAge(30);
        System.out.println("Median = "+medianOfAges.getMedian());
        medianOfAges.addAge(25);
        System.out.println("Median = "+medianOfAges.getMedian());
    }
}
