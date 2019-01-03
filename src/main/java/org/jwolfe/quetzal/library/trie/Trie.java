package org.jwolfe.quetzal.library.trie;

import java.util.LinkedList;

public class Trie {
    TrieNode root;

    public TrieNode getRoot() {
		return root;
	}

	public Trie() {
        root = new TrieNode();
    }

    public void insert(String key) {
        int length = key.length();

        TrieNode crawler = root;
        for(int level = 0; level < length; level++) {
            Character c = key.charAt(level);
            if(!crawler.children.containsKey(c)) {
                crawler.children.put(c, new TrieNode());
            }

            crawler = crawler.children.get(c);
        }

        crawler.isEndOfWord = true;
    }

    public boolean search(String key) {
        int length = key.length();

        TrieNode crawler = root;
        for(int level = 0; level < length; level++) {
            Character c = key.charAt(level);
            if(!crawler.children.containsKey(c)) {
                return false;
            }

            crawler = crawler.children.get(c);
        }

        return crawler.isEndOfWord;
    }

    public void delete(String key) {
        delete(key, root, 0);
    }

    private boolean delete(String key, TrieNode node, int index) {
        if(index == key.length()) {
            if(!node.isEndOfWord) {
                return false;
            }

            node.isEndOfWord = false;
            return node.children.isEmpty();
        }

        Character c = key.charAt(index);
        if(!node.children.containsKey(c)) {
            return false;
        }
        TrieNode child = node.children.get(c);

        boolean deleteNode = delete(key, child, index+1)
                && !node.isEndOfWord;
        if(deleteNode) {
            node.children.remove(c);
            return node.children.isEmpty();
        }

        return false;
    }

    public void listKeys() {
        listKeys(root, new LinkedList<>());
    }

    private void listKeys(TrieNode node, LinkedList<Character> characters) {
        if(node.isEndOfWord) {
            String key = getString(characters);
            System.out.println(key);
        }

        for(Character c : node.children.keySet()) {
            characters.push(c);
            listKeys(node.children.get(c), characters);
            characters.pop();
        }
    }

    private String getString(LinkedList<Character> characters) {
        StringBuilder builder = new StringBuilder();

        var iterator = characters.listIterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }

        return builder.reverse().toString();
    }
}

