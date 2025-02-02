package linkedlist;

public class RemoveDuplicatesFromSortedLL {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fast = head;
        ListNode slow=null;

        boolean hasDuplicate=false;
        while(fast!=null){
           while(fast.next!=null && fast.val!=fast.next.val){
               slow= fast;
               fast=fast.next;
           }
           while(fast.next!=null && fast.val==fast.next.val){
               fast=fast.next;
               hasDuplicate = true;
           }
           if(hasDuplicate && slow!=null){
               hasDuplicate=false;
               slow.next=fast.next;
           }else if(hasDuplicate){
               head = fast.next;
               hasDuplicate=false;
           }
           fast=fast.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
//        ListNode node5 = new ListNode(4);
//        ListNode node6 = new ListNode(5);


        head1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;


        new RemoveDuplicatesFromSortedLL().deleteDuplicates(head1);
    }
}
