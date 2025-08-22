package trees.bst;


import java.util.ArrayList;
import java.util.List;

public class MinimumDistanceBetweenBSTNodes {
    public int getMinimumDifference(TreeNode root) {
        int result = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (Math.abs(list.get(i - 1) - list.get(i)) < result) {
                result = Math.abs(list.get(i - 1) - list.get(i));
            }
        }
        return result;
    }

    void inorderTraversal(TreeNode node, List<Integer> list) {
        if(node==null){
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    public static void main(String[] args) {

    }
}
