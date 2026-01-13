package striver.arrays;

public class MaximumProductSubArray {
    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int currentProduct =1;
       for(int i=0;i<nums.length;i++){
           if(nums[i]==0){
                currentProduct =1;
           }else {
               currentProduct *=nums[i];

           }
           maxProduct= Math.max(maxProduct,currentProduct);
       }
        currentProduct =1;
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]==0){
                currentProduct =1;
            }else {
                currentProduct *=nums[i];

            }
            maxProduct= Math.max(maxProduct,currentProduct);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        MaximumProductSubArray mps = new MaximumProductSubArray();
        int[] nums = new int[]{1,2,3,4,5,0};
        System.out.println(mps.maxProduct(nums));

        nums = new int[]{1,2,-3,0,-4,-5};
        System.out.println(mps.maxProduct(nums));

        nums = new int[]{-3,0,1,-2};
        System.out.println(mps.maxProduct(nums));

        nums = new int[]{-1,-2,-3,0};
        System.out.println(mps.maxProduct(nums));

        nums = new int[]{-2, 0, -1};
        System.out.println(mps.maxProduct(nums));

    }

}
