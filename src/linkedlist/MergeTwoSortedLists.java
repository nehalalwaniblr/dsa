package linkedlist;

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                dummy.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            dummy = dummy.next;

        }
        while (l1 != null) {
            dummy.next = new ListNode(l1.val);
            dummy = dummy.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            dummy.next = new ListNode(l2.val);
            dummy = dummy.next;
            l2 = l2.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(-9);
        head1.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(7);

        new MergeTwoSortedLists().mergeTwoLists(head1, head2);
    }
}
