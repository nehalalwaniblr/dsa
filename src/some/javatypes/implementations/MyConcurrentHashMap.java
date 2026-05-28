package some.javatypes.implementations;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyConcurrentHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final Node<K, V>[] buckets;
    private final ReentrantReadWriteLock[] locks;

    MyConcurrentHashMap() {
        this.buckets = new Node[DEFAULT_INITIAL_CAPACITY];
        this.locks = new ReentrantReadWriteLock[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    private int bucketIndex(K key) {
        return Math.abs(key.hashCode() % DEFAULT_INITIAL_CAPACITY);
    }

    public V get(K key) {
        int index = bucketIndex((K) key);
        locks[index].readLock().lock();
        try {
            Node<K, V> current = buckets[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }
            return null;
        } finally {
            locks[index].readLock().unlock();
        }
    }

    public void put(K key, V value) {
        int index = bucketIndex(key);
        locks[index].writeLock().lock();
        try {
            Node<K, V> current = buckets[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            Node<K, V> node = new Node<>(key, value);
            node.next = buckets[index];
            buckets[index] = node;
        } finally {
            locks[index].writeLock().unlock();
        }
    }

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
        }

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}

