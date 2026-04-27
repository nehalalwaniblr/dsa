package leet_again.ll;

public class CycleInLL {
    public boolean hasCycle(ListNode head) {
        if(head==null)
            return false;
        if(head.next==null)
            return false;
        ListNode node = head;
        ListNode next = node;
        while (node!=null && next!=null && next.next!=null){
            node = node.next;
            next= next.next.next;
            if(node==next)
                return false;

        }
        return false;
    }
}
