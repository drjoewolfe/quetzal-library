package org.jwolfe.quetzal.library.trie;

public class BinaryTrieNode {
    public boolean isEndMarker() {
        return endMarker;
    }

    public void setEndMarker(boolean endMarker) {
        this.endMarker = endMarker;
    }

    public BinaryTrieNode[] getChildren() {
        return children;
    }

    boolean endMarker;

    BinaryTrieNode[] children = new BinaryTrieNode[2];

    public BinaryTrieNode() {
        endMarker = false;
        children[0] = null;
        children[1] = null;
    }
}
