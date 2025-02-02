package linkedlist;

public class LLAddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        int sum = 0;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            carry = sum;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            int temp = sum % 10;
            carry = sum / 10;
            dummy.next = new ListNode(temp);
            dummy = dummy.next;
        }
        return head.next;
    }

    public static void main(String[] args) {

    }
}