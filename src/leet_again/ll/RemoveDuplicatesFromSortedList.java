package leet_again.ll;

/*
* Take 2 ptrs
*  slow and fast
* fast keeps moving till it doesnt get duplicates and slow will be fast first then fast keeps moving - 1st while loop
* if duplicate exist slow will be on the starting position of non-duplicate node and fast will be on 1st occurrence of duplicate node
* Now keep moving the fast ptr till the ned of current duplicate
* update the slowPtr.next to fastPtr.next
* Handle cases when slowPtr never got updated as the start of list itself contains duplicates. In this case head will point to fastPtr.next- else if (hasDuplicate) {
                head = fastPtr.next;
            }
            *
* */
public class RemoveDuplicatesFromSortedList {
    //[1,2,3,3,4,4,5]; this will return 1,2,3,4,5; expectation is 1,2,5
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode temp = head;
        if (head == null)
            return head;
        ListNode prev = head;
        temp = head.next;
        while (temp != null) {
            while (prev.val == temp.val) {
                temp = temp.next;
            }
            prev.next = temp;
            prev = temp;
            temp = temp.next;
        }
        return head;
    }


    public ListNode deleteDuplicates(ListNode head) {
        // Fast & slow pointers;
        // fast pointer looks for duplicate
        // slow pointer remains at the position till no-duplicates
        ListNode fast = head;
        // Slow to be initialized with null in case list starts with duplicates
        ListNode slow=null;
        // boolean flag to update the slow pointer to point to the node next to duplicate node (fast pointer node)
        boolean hasDuplicate=false;
        // Iterate till you dont reach end
        while(fast!=null){
            // Keep moving till there are no duplicates
            while(fast.next!=null && fast.val!=fast.next.val){
                slow= fast;
                fast=fast.next;
            }
            //    keep moving till there are duplicates
            while(fast.next!=null && fast.val==fast.next.val){
                fast=fast.next;
                hasDuplicate = true;
            }
            //    when exist a duplicate and slow is already updated; update the slow pointer to point to fast(duplicated node's next) pointer
            if(hasDuplicate && slow!=null){
                hasDuplicate=false;
                slow.next=fast.next;
                //    when duplicate exist and slow is still null; i.e. list starts with duplicates say 1->1>1->2->3
            }else if(hasDuplicate){
                head = fast.next;
                hasDuplicate=false;
            }
            //    keep moving irrespective duplicate or no-duplicates found!
            fast=fast.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);

        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);


        head1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;


        new RemoveDuplicatesFromSortedList().deleteDuplicates(head1);
    }
}
