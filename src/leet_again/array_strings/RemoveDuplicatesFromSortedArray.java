package leet_again.array_strings;

public class RemoveDuplicatesFromSortedArray {
    // iterate from 1 to end; fix position pos at 0 and increment only when element and pos aren't equal
    public int removeDuplicates(int[] nums) {
        int pos=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[pos]){
                pos++;
                nums[pos] = nums[i];
            }
        }
        return pos+1;
    }


    public static void main(String[] args) {
        System.out.println(new RemoveDuplicatesFromSortedArray().removeDuplicates(new int[]{1,1,2}));
        System.out.println(new RemoveDuplicatesFromSortedArray().removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }
}
