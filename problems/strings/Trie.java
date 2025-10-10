package problems.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A Trie (prefix tree) data structure is good for insertion, search, deletion, and listing of words
 * with a given prefix.  It is how your IDE, phone, or search engine autocompletes words.
 *
 * <p>Example usage:
 * <pre>
 *     Trie trie = new Trie();
 *     trie.insert("to");
 *     trie.insert("toy");
 *     trie.insert("tea");
 *     trie.insert("ten");
 *     trie.insert("tent");
 *     trie.insert("teammate");
 *     trie.insert("in");
 *     trie.insert("into");
 *     System.out.printf("is team in trie? %s\n",trie.search("team"));           // false
 *     System.out.printf("which words have prefix 'te'? %s\n", trie.getWordsWithPrefix("te")); // [tea, ten, tent, teammate]
 * </pre>
 * @see <a href="https://en.wikipedia.org/wiki/Trie">Trie (Wikipedia)</a>
 * 
 * Time complexity: O(m) for insert, search, delete, startsWith where m is the length of the word or prefix.
 * Space complexity: O(n) where n is the number of nodes in the trie.
 */
public class Trie {

    /**
     * A single node of the trie.
     * Each node contains a map from characters to child nodes
     * and a flag if those characters form a valid word.
     */
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    private final TrieNode root;

    /** Constructs an empty Trie object. */
    public Trie() {
        root = new TrieNode();
    }

    /** Returns true if the word is in the trie. */
    public boolean contains(String word) {
        return search(word);
    }

    /** Deletes the word if it is in the trie. */
    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            return current.children.isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false; // Word not found
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.isEmpty() && !current.isEndOfWord;
        }
        return false;
    }

    private TrieNode findNode(String text) {
        TrieNode current = root;
        for (char ch : text.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    private void getWords(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix.toString());
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            prefix.append(entry.getKey());
            getWords(entry.getValue(), prefix, results);
            prefix.deleteCharAt(prefix.length() - 1); // backtrack
        }
    }

    /** Returns a list of all words in the trie that begin with the given prefix. */
    public List<String> getWordsWithPrefix(String prefix) {
        TrieNode node = findNode(prefix);
        List<String> results = new ArrayList<>();
        if (node == null) return results; // no such prefix
        getWords(node, new StringBuilder(prefix), results);
        return results;
    }

    /** Inserts a word in the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new TrieNode());
            }
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    /** Returns true if the trie is empty. */
    public boolean isEmpty() {
        return root.children.isEmpty();
    }

    /** Returns true if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    /** Returns true if there is any word in the trie starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("ten");
        trie.insert("in");
        trie.insert("to");
        trie.insert("teammate");
        trie.insert("into");
        trie.insert("toy");
        trie.insert("tent");
        trie.insert("tea");

        System.out.println("Before deletion:");
        System.out.println("search(ten): " + trie.search("ten")); // true
        System.out.println("search(team): " + trie.search("team")); // false
        System.out.println("startsWith(te): " + trie.startsWith("te")); // true
        System.out.println("Words with prefix 'te': " + trie.getWordsWithPrefix("te")); // [tea, ten, tent, teammate]

        trie.delete("ten");
        System.out.println("\nAfter deleting 'ten':");
        System.out.println("search(ten): " + trie.search("ten")); // false
        System.out.println("search(team): " + trie.search("team")); // false
        System.out.println("startsWith(te): " + trie.startsWith("te")); // true
        System.out.println("Words with prefix 'te': " + trie.getWordsWithPrefix("te")); // [tea, tent, teammate]
    }

}
