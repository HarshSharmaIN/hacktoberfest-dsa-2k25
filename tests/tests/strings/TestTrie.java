package tests.strings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import problems.strings.Trie;

import java.util.List;

/**
 * Unit tests for the {@link Trie} class.
 */
class TrieTest {

    private Trie trie;

    @BeforeEach
    public void setUp() {
        trie = new Trie();
        trie.insert("ten");
        trie.insert("in");
        trie.insert("to");
        trie.insert("teammate");
        trie.insert("into");
        trie.insert("toy");
        trie.insert("tent");
        trie.insert("tea");
        // Words used by tests for 'ca' and 'do' prefixes
        trie.insert("car");
        trie.insert("cat");
        trie.insert("cart");
        trie.insert("dog");
        trie.insert("door");
        trie.insert("dove");
    }

    @Test
    public void testInsertAndSearch() {
        assertTrue(trie.search("ten"), "'ten' should be found");
        assertTrue(trie.search("in"), "'in' should be found");
        assertTrue(trie.search("teammate"), "'teammate' should be found");
        assertFalse(trie.search("team"), "'team' should not be found");
        assertFalse(trie.search("te"), "'te' should not be found as a complete word");
    }

    @Test
    public void testStartsWith() {
        assertTrue(trie.startsWith("te"), "Prefix 'te' should exist");
        assertTrue(trie.startsWith("in"), "Prefix 'in' should exist");
        assertFalse(trie.startsWith("an"), "Prefix 'an' should not exist");
        assertFalse(trie.startsWith("ant"), "Prefix 'ant' should not exist");
    }

    @Test
    public void testDeleteExistingWord() {
        assertTrue(trie.search("cart"));
        trie.delete("cart");
        assertFalse(trie.search("cart"), "Deleted word 'cart' should not be found");
        assertTrue(trie.search("car"), "Deleting 'cart' should not remove shared prefix 'car'");
    }

    @Test
    public void testDeleteNonExistingWord() {
        assertFalse(trie.delete("castle"), "Deleting non-existent word should return false");
    }

    @Test
    public void testDeletePartialPrefix() {
        assertTrue(trie.startsWith("car"));
        assertFalse(trie.delete("ca"), "Deleting prefix only should return false");
    }

    @Test
    public void testGetWordsWithPrefix() {
        List<String> caWords = trie.getWordsWithPrefix("ca");
        assertTrue(caWords.containsAll(List.of("car", "cat", "cart")),
                "Words with prefix 'ca' should include 'car', 'cat', and 'cart'");
        assertEquals(3, caWords.size(), "Prefix 'ca' should return exactly 3 words");

        List<String> doWords = trie.getWordsWithPrefix("do");
        assertTrue(doWords.containsAll(List.of("dog", "door", "dove")),
                "Words with prefix 'do' should include 'dog', 'door', and 'dove'");
    }

    @Test
    public void testGetWordsWithNonExistingPrefix() {
        List<String> result = trie.getWordsWithPrefix("z");
        assertTrue(result.isEmpty(), "Non-existing prefix should return empty list");
    }

    @Test
    public void testEmptyTrieBehavior() {
        Trie emptyTrie = new Trie();
        assertFalse(emptyTrie.search("any"), "Empty Trie should not contain any word");
        assertTrue(emptyTrie.getWordsWithPrefix("a").isEmpty(), "Empty Trie should return empty prefix results");
    }

    @Test
    public void testInsertionOfDuplicateWords() {
        trie.insert("car");
        trie.insert("car");
        assertTrue(trie.search("car"), "Word 'car' should remain searchable after duplicate insertions");
        assertEquals(3, trie.getWordsWithPrefix("ca").size(), "Duplicate insertions should not create extra entries");
    }
}