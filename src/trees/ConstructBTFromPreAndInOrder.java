package trees;

import java.util.HashMap;

public class ConstructBTFromPreAndInOrder {
    private int preorderIndex = 0; // Tracks the current root in the preorder array
    private HashMap<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Populate the hashmap with indices from the inorder array
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return constructTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode constructTree(int[] preorder, int left, int right) {
        if (left > right) {
            return null; // No subtree to construct
        }

        // Get the current root value from preorder and increment the index
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Get the index of the root in the inorder array
        int rootIndex = inorderIndexMap.get(rootValue);

        // Build the left and right subtrees
        root.left = constructTree(preorder, left, rootIndex - 1);
        root.right = constructTree(preorder, rootIndex + 1, right);

        return root;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(9, new TreeNode(1, new TreeNode(5,null,null), null), new TreeNode(2, null, null));
        TreeNode right = new TreeNode(20, new TreeNode(15, null, null), new TreeNode(7, null, null));
        TreeNode root1 = new TreeNode(3, left, right);
        new ConstructBTFromPreAndInOrder().buildTree(new int[]{3,9,1,5,2,20,15,7},new int[]{5,1,9,2,3,15,20,7});
    }
}
