package linkedlist;

import java.util.HashMap;
import java.util.Map;
/*
* For LRU always use a head and tail dummy pointer for faster reference
* to the pointer where the new node has to be attached and from where the lru node should be removed(tail)
* */
public class LruCacheSecondAttempt {
    Map<Integer,Node> cache;
    Node head;
    Node tail;
    Integer capacity;
    public LruCacheSecondAttempt(int capacity) {
        cache = new HashMap<>(capacity);
        this.capacity=capacity;
        head= new Node();
        tail= new Node();
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            removeKey(key);
            addToFront(cache.get(key));
            return cache.get(key).value;
        }
        return -1;
    }

    void removeKey(int key){
        Node node = cache.get(key);
        node.prev.next=node.next;
        node.next.prev = node.prev;
    }

    void addToFront(Node n){
        n.next=head.next;
        n.prev=head;
        head.next.prev=n;
        head.next=n;
    }
//1-2
    public void put(int key, int value) {
        if(!cache.containsKey(key)){
            if(cache.size()>=capacity){
//                remove from tail
                int lruKey = tail.prev.key;
                removeKey(lruKey);
                cache.remove(lruKey);
            }
                Node newNode = new Node();
                newNode.key=key;
                newNode.value=value;
                addToFront(newNode);
                cache.put(key,newNode);
        }else{
            Node n = cache.get(key);
            n.value =value;
            removeKey(key);
            addToFront(cache.get(key));
        }
    }
}

class Node{
    int key;int value;
    Node next;Node prev;
}
