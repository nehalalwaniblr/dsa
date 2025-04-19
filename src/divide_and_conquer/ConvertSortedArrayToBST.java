package divide_and_conquer;

import java.util.Arrays;

/*Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.



Example 1:


Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:


Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.*/
public class ConvertSortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root=  solve(0, nums.length-1,nums);
        return root;
    }

    TreeNode solve( int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        TreeNode node = new TreeNode();
        int mid = left + (right - left) / 2;
        node.val = nums[mid];
        node.left = solve(left, mid-1, nums);
        node.right = solve(mid+1, right, nums);
        return node;
    }

    public static void main(String[] args) {
        new ConvertSortedArrayToBST().sortedArrayToBST(new int[]{-10,-3,0,5,9});
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}