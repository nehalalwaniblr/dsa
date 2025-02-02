import java.util.Arrays;

public class HARDTrappingRainWater {
    public static int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            int rightHighest = i + 1 < height.length ? height[i + 1] : 0;
            int rightHighestIndex = i + 1  ;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] >= rightHighest) {
                    rightHighest = height[j];
                    rightHighestIndex = j;
                }
            }

            Arrays.fill(right, i, rightHighestIndex , rightHighest);
            i = rightHighestIndex - 1;
        }
        System.out.println(Arrays.toString(right));
        for (int i = height.length - 1; i >= 0; i--) {
            int leftHighest = i - 1 >= 0 ? height[i - 1] : 0;
            int leftHighestIndex=i-1;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] >= leftHighest) {
                    leftHighest = height[j];
                    leftHighestIndex = j;
                }
            }
            Arrays.fill( left, leftHighestIndex + 1, i+1, leftHighest);
            i = leftHighestIndex + 1;
        }
        System.out.println(Arrays.toString(left));
        int result=0;
        for(int i=0;i<height.length;i++){
            if(Math.min(left[i],right[i])-height[i]>0)
                result+=Math.min(left[i],right[i])-height[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
