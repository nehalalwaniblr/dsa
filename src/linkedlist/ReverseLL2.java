package linkedlist;

/*
*
* Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]*/
public class ReverseLL2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head != null && head.next == null)
            return head;
        ListNode dummy = new ListNode(0); // created dummy node
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode current = prev.next;
        for (int i = 0; i < right - left; i++) {

            ListNode next = current.next;
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
//        ListNode temp2 = new ListNode(1);
//        ListNode temp;
//        ListNode head = temp2;
//        for (int i= 2;i<=5;i++){
//            temp = new ListNode(i);
//            temp2.next = temp;
//            temp2=temp;
//        }
//        new ReverseLL2().reverseBetween(head,2,4);

//        ListNode  temp2 = new ListNode(5);
//        ListNode head = temp2;
//        new ReverseLL2().reverseBetween(head,1,1);

        ListNode temp2 = new ListNode(3);
        ListNode temp = new ListNode(5);

        ListNode head = temp2;
        temp2.next = temp;
        temp.next = null;
        new ReverseLL2().reverseBetween(head, 1, 2);
    }
}