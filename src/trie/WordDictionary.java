package trie;

class WordDictionary {
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
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
        return dfsSearch(word, root, 0);
    }

    // DFS search helper
    private boolean dfsSearch(String word, TrieNode curr, int index) {
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') { // Wildcard handling
                for (TrieNode child : curr.children.values()) {
                    if (dfsSearch(word, child, i + 1)) {
                        return true;
                    }
                }
                return false; // No match found for '.'
            } else {
                if (!curr.children.containsKey(ch)) return false; // If path doesn't exist
                curr = curr.children.get(ch);
            }
        }
        return curr.isEow; // Return true if it's a valid word
    }

    public static void main(String[] args) {
//        Trie trie = new Trie();
//        trie.insert("bad");
//        trie.insert("dad");
//        trie.insert("mad");

//        System.out.println(trie.search("pad"));
//
//        System.out.println(trie.search("bad"));
            WordDictionary trie=new WordDictionary();
        trie.addWord("bad");
        trie.addWord("dad");
        trie.addWord("mad");
        trie.search(".ad");
//        System.out.println(trie.search(".ad"));
//
//        trie.insert("app");
//        System.out.println(trie.search("b.."));
//
//        System.out.println(trie.search("b.d"));

    }
}