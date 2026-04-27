package leet_again.dp;

import java.util.Arrays;

public class HouseRobber {
    int N;

    public int rob(int[] nums) {
        N = nums.length;
        int[] mem = new int[N];
        Arrays.fill(mem, -1);
        return solve(0, nums, mem);
    }

    /*
    solve(0)
    * i =0
    * steal = 1+solve(2) = 1+3=4
    skip = solve(2)=3


    solve(2)
    steal = 3
    skip = solve(3) = 1


    solve(3)
    i =3
    steal = 1
    skip = 0
    * */
    int solve(int i, int[] nums, int[] mem) {
        if (i >= N)
            return 0;
        if (mem[i] != -1)
            return mem[i];
        //either steal or skip
        int steal = nums[i] + solve(i + 2, nums, mem);
        int skip = solve(i + 1, nums, mem);
        return mem[i] = Math.max(steal, skip);
    }
    /*
    * 1,2,3,1
    * dp[0] = 1
    * dp[1] = 2
    * dp[2] = steal = 3+1= 4; skip= 2 ====>4
    * dp[3] = steal = 1+2=3; skip = 4====>4
    * */
    public int rob2(int[] nums) {
        N = nums.length;
        int[] dp = new int[N];
        dp[0]=nums[0];
        dp[1] = Math.max(dp[0],dp[1]);
        for(int i =2;i<nums.length;i++){
            int steal = nums[i]+dp[i-2];
            int skip = dp[i-1];
            dp[i] = Math.max(steal,skip);
        }
        return dp[N-1];

    }

    /*
    * 1,2,3,1
    * i         = 2,3
    * prev      = 2;4;4
    * prevPrev  = 1;2;4
    * steal     = 3+1 =4;1+2=3
    * skip      = 2; 4
    * */
    public int rob3(int[] nums) {
        int prev;
        int prevPrev;
        N = nums.length;
        if(N==1)
            return nums[0];
        prevPrev=nums[0];
        prev = Math.max(nums[0],nums[1]);
        for(int i =2;i<nums.length;i++){
            int steal = nums[i]+prevPrev;
            int skip = prev;
            prevPrev = prev;
            prev =Math.max(steal,skip);
        }
        return prev;

    }


    public static void main(String[] args) {
        System.out.println(new HouseRobber().rob(new int[]{1, 2, 3, 1}));
        System.out.println(new HouseRobber().rob(new int[]{2, 7, 9, 3, 1}));

    }
}
