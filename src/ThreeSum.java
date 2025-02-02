import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {

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

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int []{-1,0,1,2,-1,-4}));
    }
}
