package leet_again.ll;

import java.util.HashMap;
import java.util.Map;

//Create 2 dummy nodes head and tail;they wont change ever, only nodes between them will be changed
public class LRUCache {
    Map<Integer, DoubleLLNode> map;
    int capacity = 0;
    DoubleLLNode head = null;
    DoubleLLNode tail = null;
    int size = 0;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        head = new DoubleLLNode();
        tail = new DoubleLLNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int result = -1;
        if (map.containsKey(key)) {
            result = map.get(key).val;
            //move the node to the end - 1. remove from front, back or middle
            DoubleLLNode node = map.get(key);
            //Node is not at the head
            removeNode(node);
//            put the node to the end; as it is most frequently used
            addToTail(node);
        }
        return result;
    }

    private void removeNode(DoubleLLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            //Size not within limits ?
            if (size == capacity) {
                //Size not within limits; remove from front then add to tail
                DoubleLLNode nodeToRemove = head.next;
                removeNode(nodeToRemove);
                map.remove(nodeToRemove.key);
                size--;
            }
            //add to map and list
            DoubleLLNode node = new DoubleLLNode(key, value);
            map.put(key, node);
            //add to tail
            addToTail(node);
            size++;
        } else {
            DoubleLLNode node = map.get(key);
            node.val = value;
            map.put(key, node);
            //this node needs to go to tail as it is recently used;
            //1. remove from position
            removeNode(node);
            //2. add to tail
            addToTail(node);
        }

    }

    private void addToTail(DoubleLLNode node) {
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        node.next = tail;
    }
}

class DoubleLLNode {
    DoubleLLNode next;
    DoubleLLNode prev;
    int val;
    int key;

    DoubleLLNode() {
    }

    DoubleLLNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}