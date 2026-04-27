package leet_again.ll;

/*
 * 3 steps:
 * 1. Make the ptr reach the ptr after which the list will end; say 1>2>3>4>5; k =2 you should reach at 3 and you'll put 3.next =null in step 3
 * 2. make the LL circular: start from beginning mark the end node to point to head
 * 3. head = temp.next;  Now mark temp.next = null and
 * */
//1,2,3,4,5 k =2;
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        temp = head;
        k = k % size;
        if (k == 0)
            return head;
        for (int i = 1; i <= size - k - 1; i++) {
            temp = temp.next;
        }
        //make it a cycle
        ListNode next = head;
        while (next.next != null) {
            next = next.next;
        }
        next.next = head;
        if (temp != null) {
            head = temp.next;
            temp.next = null;
        }
        return head;
    }
}
