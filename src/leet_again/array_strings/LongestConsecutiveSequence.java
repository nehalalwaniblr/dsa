package leet_again.array_strings;

import java.util.Arrays;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if(nums.length ==0)
            return 0;
        int count = 1;
        int result = 1;



        Arrays.sort(nums);
        int lastMinSeen = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lastMinSeen + 1 == nums[i]) {
                count++;
                result = Math.max(count, result);

            } else if(lastMinSeen < nums[i]) {
                //new start
                count = 1;
            }
            lastMinSeen = nums[i];

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{1,0,1,2}));
    }
}
