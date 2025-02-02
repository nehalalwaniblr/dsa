import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        if(nums==null) return 0;
        if(nums.length==0) return 0;
        for (int num : nums) {
            set.add(num);
        }

        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();


        int result1 =1;
        int result2 =1;
        int result= Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            int start = nums[i];
            while(set.contains(start+1) && start<=max ){
                result1++;
                start++;
            }
            start = nums[i];
            while(set.contains(start-1) && start>=min){
                result2++;
                start++;
            }
            result=Math.max(result, Math.max(result1,result2));

        }
        return result==Integer.MIN_VALUE?1:result;
    }

    public static void main(String[] args) {
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[] {100,4,200,1,3,2}));
    }
}
