import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        rotate(new int[]{1,2,3,4,5,6}, 11);
    }
//    public static void rotate(int[] nums, int k) {
//        int index = k>nums.length?k-(k%nums.length)-1:nums.length-k;
//        int[] result = new int[nums.length];
//        for(int i=0;i<nums.length;i++){
//            if(index<0 || index>=nums.length)
//                index=0;
//            result[i] = nums[index];
//            index++;
//        }
//        for(int i=0;i<nums.length;i++)
//            nums[i]=result[i];
//        System.out.println(Arrays.toString(nums));
//    }

    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public static void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
