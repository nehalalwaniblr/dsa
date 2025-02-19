package trie;

import java.util.HashMap;
import java.util.Map;


class Trie {

    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch:word.toCharArray()
             ) {
            if(!node.children.containsKey(ch)){
                node.children.put(ch,new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.isEow=true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch:word.toCharArray()
        ) {
            if(!node.children.containsKey(ch))
                return false;
            node = node.children.get(ch);
        }
        return node.isEow;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch:prefix.toCharArray()
        ) {
            if(!node.children.containsKey(ch))
                return false;
            node = node.children.get(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));

        System.out.println(trie.search("app"));

        trie.startsWith("apple");
        System.out.println(trie.search("app"));

        trie.insert("app");
        System.out.println(trie.search("app"));

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
