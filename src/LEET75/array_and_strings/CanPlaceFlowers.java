package LEET75.array_and_strings;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed.length==1 && n==1)
            return flowerbed[0] == 0 ;
        for(int i=0;i<flowerbed.length && n!=0;i++){
            if(flowerbed[i]==1)
                continue;
//            if first bed is 0 and 2nd is also zero; as 0-1 need not to be checked
            if((i==0 && i+1>=0 && flowerbed[i]==0 && flowerbed[i+1]==0)
//                   check back and check next
                    || (i-1>=0 && i+1<flowerbed.length && flowerbed[i]==0
                    && flowerbed[i-1]==0 && flowerbed[i+1]==0)
//            check last bed
                    || (i==flowerbed.length-1 && flowerbed[i]==0 && i-1>=0 && flowerbed[i-1]==0)
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
