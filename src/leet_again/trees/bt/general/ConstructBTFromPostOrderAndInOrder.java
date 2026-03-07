package leet_again.trees.bt.general;

import java.util.HashMap;
import java.util.Map;

/*https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/?envType=study-plan-v2&envId=top-interview-150
 * */
public class ConstructBTFromPostOrderAndInOrder {
    int postOrderIndex = 0;
    Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postOrderIndex = postorder.length - 1;
//    1. Construct in-order map
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
//    2. construct map recursively on postorder
        return build(postorder, 0, inorder.length - 1, 0, postorder.length - 1);

    }

    public TreeNode build(int[] postorder, int inOrderLeft, int inOrderRight, int postOrderLeft, int postOrderRight) {
        if (inOrderLeft > inOrderRight || postOrderLeft>postOrderRight)
            return null;
        TreeNode root = new TreeNode();
        root.val = postorder[postOrderRight];
        int inorderIndex = inorderIndexMap.get(root.val);

        int postOrderNoOfLeftElements = inorderIndex - inOrderLeft;
        int postOrderNoOfRightElements = inOrderRight - inorderIndex;

        root.left = build(postorder, inOrderLeft, inorderIndex - 1, postOrderLeft, postOrderLeft + postOrderNoOfLeftElements - 1);
        root.right = build(postorder, inorderIndex + 1, inOrderRight, postOrderRight - postOrderNoOfRightElements, postOrderRight - 1);
        return root;
    }

    public static void main(String[] args) {
        new ConstructBTFromPreANdInOrderTraversal().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
    }
}
