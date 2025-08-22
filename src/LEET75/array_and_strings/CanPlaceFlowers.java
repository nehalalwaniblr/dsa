package LEET75.array_and_strings;

/*You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.



Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false


Constraints:

1 <= flowerbed.length <= 2 * 104
flowerbed[i] is 0 or 1.
There are no two adjacent flowers in flowerbed.
0 <= n <= flowerbed.length*/
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed.length==1 && n==1)
            return flowerbed[0] == 0 ;
        for(int i=0;i<flowerbed.length && n!=0;i++){
            if(flowerbed[i]==1)
                continue;
//            if first bed is 0 and 2nd is also zero; as 0-1 need not to be checked
            if((i==0  && flowerbed[i]==0 && flowerbed[i+1]==0)
//                   check back and check next
                    || (i-1>=0 && i+1<flowerbed.length && flowerbed[i]==0
                    && flowerbed[i-1]==0 && flowerbed[i+1]==0)
//            check last bed
                    || (i==flowerbed.length-1 && flowerbed[i]==0 && flowerbed[i-1]==0)
            ){
                n--;
                flowerbed[i] = 1;
            }
        }
        if(n!=0)
            return false;
        for(int i =0;i< flowerbed.length-1;i++){
            if(flowerbed[i]==1 && flowerbed[i+1]==1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));

    }
}
