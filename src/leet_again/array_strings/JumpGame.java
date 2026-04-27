package leet_again.array_strings;

/*
* At every position check the reachable and keep calculating max reachable
* reachable = current position+jump you can make i.e. a[currentPos]
* */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReachable =0;
        for(int i =0;i<nums.length;i++){
            int reachable = nums[i]+i;
            //this has to come first
            if(maxReachable<i)
                return false;
            maxReachable =  Math.max(reachable,maxReachable);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump(new int []{2,3,1,1,4}));
        System.out.println(new JumpGame().canJump(new int []{3,2,1,0,4}));
        System.out.println(new JumpGame().canJump(new int []{0}));


    }
}
