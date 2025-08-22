package trees.bst;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElement {
    int count = 0;
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return -1;
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list.get(k - 1);

    }

    void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    /*OPTIMIZED AND BETTER*/
    void inorderTraversal2(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        inorderTraversal2(node.left, k);
        count++;
        if (count == k)
            result = node.val;
        inorderTraversal2(node.right, k);

    }


}
