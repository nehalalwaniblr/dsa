package leet_again.trees.bt.general;

import java.util.HashMap;
import java.util.Map;

/*https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=study-plan-v2&envId=top-interview-150*/
public class ConstructBTFromPreANdInOrderTraversal {
    private int preorderIndex = 0; // Tracks the current root in the preorder array

    public TreeNode buildTree(int[] preorder, int[] inorder) {
//    1. Construct in-order map
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
//    2. construct map recursively on preorder
        return constructTree(preorder, inorderIndexMap, 0, preorder.length-1);
    }

    TreeNode constructTree(int[] preorder, Map<Integer, Integer> inorderIndexMap, int left, int right) {
        if (left > right)
            return null;
        TreeNode root = new TreeNode();
        root.val = preorder[preorderIndex++];
        // Get the index of the root in the inorder array
        int rootIndex = inorderIndexMap.get(root.val);
        root.left = constructTree(preorder, inorderIndexMap, left, rootIndex - 1);
        root.right = constructTree(preorder, inorderIndexMap, rootIndex + 1, right);
        return root;

    }

    public static void main(String[] args) {
        new ConstructBTFromPreANdInOrderTraversal().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
    }

}
