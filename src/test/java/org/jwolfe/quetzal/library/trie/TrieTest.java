package org.jwolfe.quetzal.library.trie;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.trie.Trie;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void insert() {
        String[] keys = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        Trie trie = new Trie();
        for(String key : keys) {
            trie.insert(key);
        }

        boolean result;

        result = trie.search("the");
        assertEquals(true, result);

        result = trie.search("these");
        assertEquals(false, result);

        result = trie.search("their");
        assertEquals(true, result);

        result = trie.search("thaw");
        assertEquals(false, result);

        System.out.println();
        trie.listKeys();
    }

    @Test
    void delete() {
        String[] keys = {"she", "sells", "sea", "shore", "the", "by", "sheer"};

        Trie trie = new Trie();
        for(String key : keys) {
            trie.insert(key);
        }

        System.out.println();
        trie.listKeys();

        boolean result;

        result = trie.search("she");
        assertEquals(true, result);

        result = trie.search("the");
        assertEquals(true, result);

        result = trie.search("shore");
        assertEquals(true, result);

        result = trie.search("sheer");
        assertEquals(true, result);

        trie.delete("she");

        result = trie.search("she");
        assertEquals(false, result);

        result = trie.search("the");
        assertEquals(true, result);

        result = trie.search("shore");
        assertEquals(true, result);

        result = trie.search("sheer");
        assertEquals(true, result);

        System.out.println();
        trie.listKeys();
    }
}