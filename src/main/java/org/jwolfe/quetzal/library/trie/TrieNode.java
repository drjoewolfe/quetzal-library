package org.jwolfe.quetzal.library.trie;

import java.util.HashMap;

public class TrieNode {
    static final int ALPHABET_SIZE = 26;
        
    HashMap<Character, TrieNode> children;

    public HashMap<Character, TrieNode> getChildren() {
		return children;
	}

	boolean isEndOfWord;
	
	public boolean isEndOfWord() {
		return isEndOfWord;
	}

    public TrieNode() {
        isEndOfWord = false;
        children = new HashMap<>();
    }
}
