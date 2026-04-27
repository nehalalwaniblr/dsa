package leet_again.ll;

/*https://leetcode.com/problems/partition-list/description/?envType=study-plan-v2&envId=top-interview-150*/
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        //nodes<x should be in small list
        //nodes>=x should be in largelist
        //merge both and reurn the head
        ListNode smallList = new ListNode();
        ListNode largeList = new ListNode();
        ListNode temp =head;
        ListNode smallH = smallList;
        ListNode largeH = largeList;

        while(temp!=null){
            if(temp.val<x){
                smallList.next = temp;
                smallList = smallList.next;
            }
            if(temp.val>=x){
                largeList.next=temp;
                largeList = largeList.next;
            }
            temp= temp.next;
        }
        //if there are no small elements point head to large list
        if(smallH.next!=null){
            head = smallH.next;
            smallList.next = largeH.next;
        }else{
            head = largeH.next;
        }

        largeList.next=null;
        return head;
    }
}

