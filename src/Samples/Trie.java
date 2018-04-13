package Samples;

import java.util.*;

public class Trie {

    private TrieNode root;

    public Trie(ArrayList<String> list) {
        root = new TrieNode();
        for(String word : list) {
            root.addWord(word);
        }
    }

    public Trie(String[] list) {
        root = new TrieNode();
        for(String word : list) {
            root.addWord(word);
        }
    }

    public void removeWord(String prefix) {
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
        }
        if (lastNode != null) {
            lastNode.setTerminates(false);
        }
    }

    public TrieNode getRoot() {
        return root;
    }

    public boolean contains(String prefix, boolean exact) {
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null) {
                return false;
            }
        }
        return !exact || lastNode.terminates();
    }

    public boolean contains(String prefix) {
        return contains(prefix, false);
    }

    public static class TrieNode {
        private HashMap<Character, TrieNode> children = new HashMap<>();
        private boolean terminates = false;
        private char character;
        public TrieNode() {
            children = new HashMap<>();
            terminates = false;
        }
        public TrieNode(char ch) {
            this();
            this.character = ch;
        }
        public void addWord(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            char ch = word.charAt(0);
            TrieNode childNode = getChild(ch);
            if (childNode == null) {
                childNode = new TrieNode(ch);
                children.put(ch, childNode);
            }

            if (word.length() > 1) {
                childNode.addWord(word.substring(1));
            } else {
                childNode.setTerminates(true);
            }

        }

        public void removeWord(Character c) {
            children.remove(c);
        }

        public void setTerminates(boolean terminates) {
            this.terminates = terminates;
        }

        public boolean terminates() {
            return terminates;
        }

        public TrieNode getChild(char c) {
            return children.get(c);
        }

        public char getCharacter() {
            return character;
        }
    }

    public static void testTrie() {
        ArrayList<String> list = new ArrayList<>();
        list.add("ABCABCABC");
//        list.add("Hello");
//        list.add("Been");
//        list.add("yes");
//        list.add("water");
//        list.add("be");
        Trie trie = new Trie(list);
//        trie.removeWord("Hello");
        System.out.println("Trie contains " + "yes : " + trie.contains("Hello"));

    }


//    140. Word Break II
//    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
//    Return all such possible sentences.
//    For example, given
//    s = "catsanddog",
//    dict = ["cat", "cats", "and", "sand", "dog"].
//    A solution is ["cats and dog", "cat sand dog"].
    public static void testWordBreak() {
        Set<String> wordDict = new HashSet<>();
//        wordDict.add("cat");
//        wordDict.add("cats");
//        wordDict.add("and");
//        wordDict.add("sand");
//        wordDict.add("dog");
//        String word = "catsanddog";
        wordDict.add("aaaa");
        wordDict.add("aaa");
        String word = "aaaaaaa";
        List<String> sentences = wordBreak(word, wordDict);
        System.out.println("List of sentences = " + sentences.toString());
    }
    public static List<String> wordBreak(String s, Set<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                String subword = s.substring(j, i);
                System.out.println("Subword = " + subword);
                if (dp[j].size() > 0 && wordDict.contains(subword)) {
                    for (String l : dp[j]) {
                        System.out.println("String in list = " + l);
                        list.add(l + (l.equals("") ? "" : " ") + subword);
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }
//
//    public static List<String> wordBreak(String s, List<String> wordDict) {
//        return wordBreak(s, wordDict, 0);
//    }

    static HashMap<String, List<String>> map = new HashMap<>();
    private List<String> wordBreak(String s, List<String> wordDict, int index) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> results = new ArrayList<>();
        if (s.isEmpty()) {
            results.add("");
            return results;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subWords = wordBreak(s.substring(word.length()), wordDict, 0);
                for (String subWord: subWords) {
                    results.add(word + " " + subWord);
                }
            }
        }
        map.put(s, results);
        return results;
    }
    public static List<String> wordBreakTrie(String s, List<String> wordDict) {
        StringBuilder sb = new StringBuilder();
        Trie trie = new Trie((ArrayList<String>) wordDict);
        return wordBreakTrie(s, "", sb, trie);
    }
    public static List<String> wordBreakTrie(String s, String prevPrefix, StringBuilder sb, Trie trie) {
        List<String> sentences = new ArrayList<>();
        if (s.isEmpty()) {
            sentences.add(sb.toString());
            return sentences;
        }

        for (int i = 0; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (trie.contains(prefix, true)){
                sb.append(prefix);
                if (i < s.length()) {
                    sb.append(" ");
                }

                sentences.addAll(wordBreakTrie(s.substring(i), prefix, sb, trie));
                sb.delete(0, sb.length());
            }
        }
        return sentences;
    }
}
