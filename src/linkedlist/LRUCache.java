package linkedlist;

import java.util.HashMap;
import java.util.Map;

/*Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4*/
public class LRUCache {
    Map<Integer, DoublyListNode> cache = new HashMap<>();
    DoublyListNode head = null;
    DoublyListNode tailPtr = null;

    int capacity;
    int size = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        int result = -1;
        DoublyListNode node = cache.get(key);
        if (node != null) {
            result = node.val;
            //remove from that position
            if (node.prev != null) {
                node.prev.next = node.next;
                if (node.next != null) {
                    //node is in the middle
                    node.next.prev = node.prev;
                } else {
//                node is at the last;update tail ptr as well
                    tailPtr = tailPtr.prev;
                    if (tailPtr != null) tailPtr.next = null;
                }
            } else {
//           node is at the beginning
                //no need to do anything
            }

            //move the node to the beginning
            node.next = head;
            head.prev = node;
            head = node;
            head.prev = null;
        }

        return result;
    }

    public void put(int key, int value) {
        DoublyListNode node = new DoublyListNode(key, value);

        if(cache.containsKey(key)){
            node = cache.get(key);
            node.val=value;
        }



            if (size >= capacity) {
//        remove from tail
                int keyPosition = tailPtr.key;
                cache.remove(keyPosition);
                if(tailPtr.prev!=null)
                    tailPtr.prev.next = null;
                tailPtr = tailPtr.prev;
                size--;
            }
            //        Put at the front
            if (head == null) {
                head = node;
                tailPtr = head;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
                head.prev=null;
            }
            cache.put(key, node);

            size++;
        }




    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(2, 1);
//        lruCache.put(2, 2);
        System.out.println(lruCache.get(2));
        lruCache.put(2, 6);
        System.out.println(lruCache.get(1));
        lruCache.put(1, 5);
        lruCache.put(1, 2);

        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
//        System.out.println(lruCache.get(4));
//        4,3


    }
}
