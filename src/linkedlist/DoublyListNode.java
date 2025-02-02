package linkedlist;

public class DoublyListNode {
    int key;
    int val;
    DoublyListNode next;
    DoublyListNode prev;

    DoublyListNode() {
    }

    DoublyListNode(int key, int val) {
        this.key=key;
        this.val = val;
    }

    DoublyListNode(int key,int val, DoublyListNode next,  DoublyListNode prev) {
        this.val = val;
        this.next = next;
        this.prev=prev;
    }
}
