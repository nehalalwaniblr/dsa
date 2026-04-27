import java.util.*;

/*
* https://leetcode.com/problems/3sum/description/?envType=study-plan-v2&envId=top-interview-150
*
* In this first sort the array; fix one number and do a two sum on rest
* use a for loop i=n
* use a while loop where j<k; j starts with i+1 and k = n
* at each iteration check if the sum formed using indexes i,j and k ==0 if so return ;
* if not check if sum > 0 if so then reduce from right else from left
* */
public class ThreeSum {
    public List<List<Integer>> threeSum2(int[] nums) {

        Arrays.sort(nums);
        Set<List<Integer>> list = new HashSet<>();
        for (int i = 0; i < nums.length-1; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int total = nums[i]+nums[j] + nums[k];
                if (total == 0) {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[j]);
                    list1.add(nums[k]);
                    list.add(list1);
                    j++;
                }else if(total> 0){
                    k--;
                }else {
                    j++;
                }
            }
        }
        return new ArrayList<>(list);
    }

    //without using set
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length-1; i++) {
            if(i>0 && nums[i]==nums[i-1]) // for ignoring duplicates
                continue;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int total = nums[i]+nums[j] + nums[k];
                if (total == 0) {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[j]);
                    list1.add(nums[k]);
                    list.add(list1);
                    j++;
                    while (nums[j] == nums[j-1] && j < k) {
                        j++;
                    }
                }else if(total> 0){
                    k--;
                }else {
                    j++;
                }
            }
        }
        return  list;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int []{-1,0,1,2,-1,-4}));
    }
}
