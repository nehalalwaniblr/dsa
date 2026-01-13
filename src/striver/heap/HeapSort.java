package striver.heap;

import java.util.Arrays;

class HeapSort {

    public void sort(int[] arr) {
        int n= arr.length;
       //build max heap; every parent is greater than its child
        //non-leaf nodes start from n/2-1 in any binary tree
        for(int i= n/2-1;i>=0;i--){
            heapify(arr,n,i);
        }

        for(int i=n-1;i>0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr,i,0);
        }
    }

    void heapify(int[] arr, int n, int startIndex) {
        int largest = startIndex;
        int left = startIndex*2+1;
        int right = startIndex*2+2;

        if(left<n && arr[left]>arr[largest]){
            largest = left;
        }

        if(right<n && arr[right]>arr[largest]){
            largest = right;
        }

        if(largest!=startIndex){
            int temp = arr[largest];
            arr[largest] = arr[startIndex];
            arr[startIndex] = temp;

            heapify(arr,n,largest);
        }

    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
//        int[] arr = new int[]{17,3,2,1,100,7,19,36,25};
        int[] arr = new int[]{3,5,1,2,4};

        hs.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
