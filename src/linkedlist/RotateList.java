package linkedlist;

/*Given the head of a linked list, rotate the list to the right by k places.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]*/
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next == null)
            return null;
        if(k==0)
            return head;
        ListNode temp =head;
        int size=1;
        while(temp.next != null){
            temp=temp.next;
            size++;
        }
        //making it a curcular LL
        temp.next = head;

        k = k % size;
        int breakPoint = size - k;

        ListNode newTemp =head;
        for(int i=1;i<breakPoint;i++){
            newTemp=newTemp.next;
        }
        head = newTemp.next;
        newTemp.next=null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);

        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);


        head1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;


        new RotateList().rotateRight(head1, 1);
    }
}
