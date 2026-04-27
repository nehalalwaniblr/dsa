package leet_again.ll;

import java.util.HashMap;
import java.util.Map;

/*
 * 2 Approaches:
 * 1. Using map:
 * - iterate and keep the details of for the current node which copied node you have created
 * - i.e. old node and its corresponding new node
 * fill all the next pointers
 * in next loop fill all random pointers using map
 *
 *
 * Approach:
 * 1. Here, modify the original list and each node's next pointer to point to copied node
 * 2. this will create a list like this(consider original to be a->b->c):
 * a->a->b->b->c->c where the all a's b's and c's are separate node.
 * 3. Now once this is done iterate again and set the new copied nodes' random pointer. whichever original node random pointer is null; their copied(next node) random pointer will also be null
 * But when it is not null then go to that node and its next be the random pointer
 * https://www.youtube.com/watch?v=OLgXN2Yg3xQ
 *
 * */
public class CopyListWithRandomPointer {
    //using map
    public Node copyRandomList2(Node head) {

        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        Node prev = null;
        Node copiedHead = null;
        //copy the node with val and next pointers
        while (current != null) {
            Node temp = new Node(current.val);
            map.put(current, temp);
            if (prev != null) {
                prev.next = temp;
                prev = temp;
            } else {
                copiedHead = temp;
                prev = copiedHead;
            }
            current = current.next;
        }
        //copy the node with random pointers
        current = head;
        Node newCopiedNode = copiedHead;
        while (current != null) {
            if (current.random == null) {
                newCopiedNode.random = null;
            } else {
                newCopiedNode.random = map.get(current.random);
            }
            current = current.next;
            newCopiedNode = newCopiedNode.next;
        }
        return copiedHead;
    }

    //2nd approach without map
    public Node copyRandomList(Node head) {
        if (head == null)
            return head;
        Node current = head;
        Node newHead = null;
        //created new node next to every original node
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            if (newHead == null) {
                newHead = newNode;
            }
            current = current.next != null ? current.next.next : null;
        }

        //copy random pointers
        current = head;
        while (current != null) {
            if (current.random == null) {
                current.next.random = null;
            } else {
                current.next.random = current.random.next;
            }
            current = current.next != null ? current.next.next : null;
        }

        //separate original and copied nodes
        current = head;
        Node newCopiedNode = newHead;
        while (current != null && newCopiedNode != null) {
            current.next = current.next!=null?current.next.next:null;
            newCopiedNode.next = newCopiedNode.next!=null?newCopiedNode.next.next:null;
            current = current.next;
            newCopiedNode = newCopiedNode.next;
        }
        return newHead;
    }
}
