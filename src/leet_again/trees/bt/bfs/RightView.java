package leet_again.trees.bt.bfs;


import leet_again.trees.bt.general.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightView {
    //Using queue
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root==null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node  = queue.poll();
                if(i==size-1){
                    result.add(node.val);
                }
                if(node!=null){
                    if(node.left!=null)
                        queue.add(node.left);
                    if(node.right!=null)
                        queue.add(node.right);
                }
            }
        }
        return result;
    }

    //without using queue
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        right(root, result,0);
        return result;
    }

    void right(TreeNode node, List<Integer> result, int currLevel ){
        if(node == null)
            return ;
        if(result.size() == currLevel){
            result.add(node.val);
        }
        right(node.right, result, currLevel+1);//important;right first
        right(node.left, result, currLevel+1);
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, null, new TreeNode(5, null, null));
        TreeNode right = new TreeNode(3, null, new TreeNode(4, null, null));
        TreeNode root = new TreeNode(1, left, right);
        new RightView().rightSideView(root);
        
    }
}
