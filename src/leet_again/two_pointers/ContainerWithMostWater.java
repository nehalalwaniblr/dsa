package leet_again.two_pointers;

/*
 * https://leetcode.com/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-interview-150
 * Here, distance between two rods i.e. width(w) we know and height(h) of each rod we know
 * We need to find the max area which is w*h
 * In order to maximize the area we can start with max width i.e. one pointer at start and one at end and calculate the area keep it in a variable - currentArea
 * Now, we need to find other possibilities too - so we need to either move left or right; this will reduce width, so we should atleast consider max height and move from the other side
 * say :
 * 1.6....8
 * here choose 8(coz it is max(1 & 8)) then we should move left ptr coz we need to have max width;if we move right width will be just 1
 * Also, calculate the area which will be min(leftHeight,rightHeight)*width
 * at each iteration we check if current iteration is larger that maxArea calculated till now. if yes replace else continue
 * */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = Integer.MIN_VALUE;
        int currentArea;
        while (left <= right) {
            if (height[left] > height[right]) {
                currentArea = height[right] * (right - left);//min height of two
                right--;
            }else{
                currentArea = height[left] * (right - left);//min height of two
                left++;
            }
            maxArea = Math.max(maxArea,currentArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new ContainerWithMostWater().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(new ContainerWithMostWater().maxArea(new int[]{1,1}));

    }
}
