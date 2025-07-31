package real_world_examples.netflix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*Feature # 2: Enable the user to view the top-rated movies worldwide,
given that we have movie rankings available separately for different geographic regions.*/
public class MergeSortList {
    public static LinkedListNode mergeKCounty(List<LinkedListNode> lists) {
        if (lists.size() > 1) {
            LinkedListNode result = lists.get(0);
            for (int i = 1; i < lists.size(); i++) {
                result = merge2Country(result, lists.get(i));
            }
            return result;
        }
        return lists.get(0);
    }

    public static LinkedListNode merge2Country(LinkedListNode l1, LinkedListNode l2) {
        LinkedListNode head = new LinkedListNode(-1);
        LinkedListNode dummyPtr = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                dummyPtr.next = l1;
                l1 = l1.next;
            } else {
                dummyPtr.next = l2;
                l2 = l2.next;
            }
            dummyPtr = dummyPtr.next;
        }

        if (l1 != null) {
            dummyPtr.next = l1;
        }
        if (l2 != null) {
            dummyPtr.next = l2;
        }
        return head.next;
    }

    public static void main(String[] args) {
        LinkedListNode a = LinkedListNode.createLinkedList(new int[] {11,41,51});

        LinkedListNode b = LinkedListNode.createLinkedList(new int[] {21,23,42});

        LinkedListNode c = LinkedListNode.createLinkedList(new int[] {25,56,66,72});

//        LinkedListNode a = LinkedListNode.createLinkedList(new int[] {51,41,11});
//
//        LinkedListNode b = LinkedListNode.createLinkedList(new int[] {42,23,21});
//
//        LinkedListNode c = LinkedListNode.createLinkedList(new int[] {72,66,56,25});

        List<LinkedListNode> list1 = new ArrayList<>();
        list1.add(a);
        list1.add(b);
        list1.add(c);

        System.out.print("All movie ID's from best to worse are:\n");
        LinkedListNode.display(mergeKCounty(list1));
    }
}

class LinkedListNode {
    int val;
    LinkedListNode next;
    LinkedListNode prev;


    LinkedListNode(int val) {
        this.val = val;
    }

    public static LinkedListNode createLinkedList(int[] arr) {
        LinkedListNode head = null;
        LinkedListNode tail = null;
        for (int i = 0; i < arr.length; ++i) {
            LinkedListNode newNode = new LinkedListNode(arr[i]);
            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        }
        return head;
    }

    public static void display(LinkedListNode head) {
        LinkedListNode temp = head;
        while (temp != null) {
            System.out.printf("%d", temp.val);
            temp = temp.next;
            if (temp != null) {
                System.out.printf(", ");
            }
        }
        System.out.println();
    }
}
