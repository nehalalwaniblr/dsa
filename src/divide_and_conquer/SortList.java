package divide_and_conquer;

/*
 Given the head of a linked list, return the list after sorting it in ascending order.



Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is in the range [0, 5  104].
-105 <= Node.val <= 105


Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortList {
    /*Idea is to do the merge sort; starting with finding mid node and calling merge sort passing second half;seeting mid to null and then passing first half
    *once the two lists are sorted call merge to merge both; for this think you have just one node in both the list
    * */
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    ListNode mergeSort(ListNode head) {
        if (head == null || head.next==null)
            return head;
        // 2 nodes
        if (head.next.next == null) {
            ListNode temp = head;
            if (temp.val > temp.next.val) {
                head = temp.next;
                temp.next = null;
                head.next = temp;
            }
            return head;
        }
        //find mid
        ListNode fastPtr= head;
        ListNode slowPtr = head;

        while(fastPtr!=null && fastPtr.next!=null){
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
        }
        ListNode node1= mergeSort(slowPtr.next);
        slowPtr.next=null;
        ListNode node2= mergeSort(head);
        return merge(node1,node2);
    }
    ListNode merge(ListNode node1, ListNode node2){
        if(node1!=null && node2!=null){
            if(node1.val<= node2.val){
                node1.next = merge(node1.next,node2);
                return node1;
            }else{
                node2.next = merge(node1,node2.next);
                return node2;
            }
        }
        if(node1==null)
            return node2;
        return node1;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3= new ListNode(3);
        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(0);


        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next=node5;
//        node5.next=null;
        ListNode n =new SortList().sortList(head1);
        System.out.println(n);
    }
}



class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
