package system_desig_use_cases.proximity_ds;

/*Trie:

A data structure that stores strings efficiently and is great for prefix search.

Geohash + Trie:

Store user geohashes in a trie.
People sharing the same geohash prefix are likely nearby — and you can find them instantly.
*/

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // Assuming only lowercase a-z
        isEndOfWord = false;
    }
}

class Trie {
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }

    // Insert a word like "cat"
    public void insert(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';             // map 'a'→0, 'b'→1 ...

            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();  // create node if missing
            }

            current = current.children[idx];
        }

        current.isEndOfWord = true;         // mark the full word
    }

    // Search full word: returns true only if complete word exists
    public boolean search(String word) {
        TrieNode current = root;
        for(char ch : word.toCharArray()) {
            int index = ch-'a';
            if(current.children[index]==null){
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    // Check if any word starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(char ch : prefix.toCharArray()) {
            int index = ch-'a';
            if(current.children[index]!=null){
                current = current.children[index];
            }else if(current.isEndOfWord){
                return true;
            }
        }
        return true;
    }
}

public class TrieDemo {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("ca");
        trie.insert("car");
        trie.insert("dog");

        System.out.println(trie.search("cat"));      // false
        System.out.println(trie.search("caa"));      // false
        System.out.println(trie.startsWith("ca"));   // true (cat, car exist)
        System.out.println(trie.startsWith("do"));   // true
        System.out.println(trie.startsWith("da"));   // false
    }
}