public class MaxProfit {
    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit2(new int[]{7,1,5,3,6,4}));
    }

    public int maxProfit(int[] prices) {
        int result =0;
        for(int i=0;i<prices.length;i++){
            int index = getMax(prices, i+1, prices.length);
            if(prices[index]-prices[i]>result){
                result = prices[index]-prices[i];
            }
        }
        return result;

    }

    int getMax(int[] prices, int i , int n){
        if(i==n)
            return i-1;
        int index = i;
        for(int j=i+1;j<n;j++){
            if(prices[j]>prices[index]){
                index=j;
            }
        }
        return index;
    }

//    Using Kadane's Algorithm
    /*
    * Kadane's Algorithm is a dynamic programming technique used to find the maximum subarray sum
    * in an array of numbers. The algorithm maintains two variables: max_current represents the maximum
    *  sum ending at the current position, and max_global represents the maximum subarray sum encountered
    *  so far. At each iteration, it updates max_current to include the current element or start a new subarray
    *  if the current element is larger than the accumulated sum. The max_global is updated if max_current
    * surpasses its value.
    * */
    public int maxProfit2(int[] prices) {
        int maxProfit =0;
        int buyAt = prices[0];
        for(int i =1;i<prices.length;i++){
            if(prices[i]<buyAt){
                buyAt = prices[i];
            }else{
                if(prices[i]-buyAt>maxProfit){
                    maxProfit = prices[i]-buyAt;
                }
            }
        }
        return maxProfit;

    }
}
