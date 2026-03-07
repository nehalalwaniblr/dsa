package leet_again.trees.bt.bst;


import leet_again.trees.bt.general.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MinAbslouteDiff {
    public int getMinimumDifference(TreeNode root) {
        //perform in order traversal to get the sorted list and find the diff between each pair
        List<Integer> sortedList = new ArrayList<>();
        traverse(root, sortedList);
        int min = Integer.MAX_VALUE;
        for(int i =1;i<sortedList.size();i++){
            min = Math.min(min, Math.abs(sortedList.get(i) - sortedList.get(i-1)));
        }
        return min;

    }

    void traverse(TreeNode root, List<Integer> sortedList){
        if(root==null)
            return;
        traverse(root.left,sortedList);
        sortedList.add(root.val);
        traverse(root.right, sortedList);
    }
}
