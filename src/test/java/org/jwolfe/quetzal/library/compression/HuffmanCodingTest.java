package org.jwolfe.quetzal.library.compression;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.compression.HuffmanCoding;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanCodingTest {

    @Test
    void build() {
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] frequencies = {5, 9, 12, 13, 16, 45};

        HuffmanCoding coding = new HuffmanCoding();
        var codes = coding.build(characters, frequencies);
        assertNotNull(codes);
        for (var item : codes) {
            System.out.println(item.getFirst() + ": " + item.getSecond());
            switch (item.getFirst()) {
                case 'a':
                    assertEquals("1100", item.getSecond());
                    break;
                case 'b':
                    assertEquals("1101", item.getSecond());
                    break;
                case 'c':
                    assertEquals("100", item.getSecond());
                    break;
                case 'd':
                    assertEquals("101", item.getSecond());
                    break;
                case 'e':
                    assertEquals("111", item.getSecond());
                    break;
                case 'f':
                    assertEquals("0", item.getSecond());
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    void buildForSortedInput() {
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] frequencies = {5, 9, 12, 13, 16, 45};

        HuffmanCoding coding = new HuffmanCoding();
        var codes = coding.buildForSortedInput(characters, frequencies);
        assertNotNull(codes);
        for (var item : codes) {
            System.out.println(item.getFirst() + ": " + item.getSecond());
            switch (item.getFirst()) {
                case 'a':
                    assertEquals("1100", item.getSecond());
                    break;
                case 'b':
                    assertEquals("1101", item.getSecond());
                    break;
                case 'c':
                    assertEquals("100", item.getSecond());
                    break;
                case 'd':
                    assertEquals("101", item.getSecond());
                    break;
                case 'e':
                    assertEquals("111", item.getSecond());
                    break;
                case 'f':
                    assertEquals("0", item.getSecond());
                    break;
                default:
                    break;
            }
        }
    }

}