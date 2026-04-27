package leet_again.array_strings;

/*Intuition is to update the array at the tracked position(currentPosition) if the element is not the value given
* thats it
*  */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int currentPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[currentPosition] = nums[i];
                currentPosition++;
            }
        }
        return currentPosition;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveElement().removeElement(new int[]{3, 2, 2, 3}, 3));
        System.out.println(new RemoveElement().removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }
}
