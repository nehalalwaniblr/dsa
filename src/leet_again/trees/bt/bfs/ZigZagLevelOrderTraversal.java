package leet_again.trees.bt.bfs;

import leet_again.trees.bt.general.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagLevelOrderTraversal {
    List<List<Integer>> result = null;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        result = new ArrayList<>();
        if (root == null)
            return result;
        queue.add(root);
        int level =0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list =new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    if(level%2==0){
                        if(node.right!=null){
                            queue.add(node.right);
                        }
                        if(node.left!=null){
                            queue.add(node.left);
                        }
                    }else{
                        if(node.left!=null){
                            queue.add(node.left);
                        }
                        if(node.right!=null){
                            queue.add(node.right);
                        }
                    }
                }
            }
            level++;
            result.add(list);
        }
        return result;
    }
}
