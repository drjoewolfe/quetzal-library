package org.jwolfe.quetzal.library.trie;

import java.util.HashMap;

public class TrieNode {
    static final int ALPHABET_SIZE = 26;
    HashMap<Character, TrieNode> children;

    boolean isEndOfWord;

    public TrieNode() {
        isEndOfWord = false;
        children = new HashMap<>();
    }
}
