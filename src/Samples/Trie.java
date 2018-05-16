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
//
//    LeetCode
//            Explore
//    Problems
//            Mock
//    Contest
//            Articles
//    Discuss
//            Store
//    New Playground
//    Rahuln
//    Word Squares
//    Submission Detail
//16 / 16 test cases passed.
//            Status: Accepted
//    Runtime: 292 ms
//    Submitted: 0 minutes ago
//    Accepted Solutions Runtime Distribution
//0501001502002503003504004505005500.00.51.01.52.0
//    java
//    You are here!
//    Your runtime beats 5.55 % of java submissions.
//            Runtime (ms)Distribution (%)
//
//0501001502002503003504004505005500.00.51.01.52.0
//    Zoom area by dragging across this chart
//    Invite friends to challenge Word Squares
//3
//    Submitted Code: 0 minutes ago
//    Language: java
//
//
//    Back to problem
//× Close
//    sample 33 ms submission
//    class Solution {
//
//        public List<List<String>> wordSquares(String[] words) {
//            List<List<String>> result = new ArrayList<>();
//            if(words == null || words.length == 0) {
//                return result;
//            }
//
//            //例如 [“area”,“lead”,“wall”,“lady”,“ball”]，长度为4
//            //初始化为4个"","","",""
//            //[0,0] , a,b,l,w可以放置（分别是四个字母的头部），从"a"开始-->"a","","",""
//            //[0，1],向右走，r可以放到a后面？然后查trie tree，没有word以r开头，结束
//            //从"b"开始--> "b","","",""
//            //[0,1],向右走，a可以放到b后面？查trie tree，可以，"area"以"a"开始 --> "ba","a","",""
//            //[0,2],向右走继续，l可以吗？查trie tree，可以，lady以"l"开始 --> "bal","a","l",""
//            //[0,3],向右走继续，l可以吗？查trie tree，可以，lead以"l"开始 --> "bal","a","l","l"
//            //第一行结束，第二行开始 -- >[1,1] ....
//            int len = words[0].length();
//            trieNode root = new trieNode();
//            //将各个词加入到trie tree中
//            for(String word : words) {
//                add(root, word);
//            }
//            trieNode[] rows = new trieNode[len];
//
//            for(int i = 0; i < len; i++) {
//                rows[i] = root;
//            }
//            helper(0, 0, len, rows, result);
//            return result;
//        }
//
//        private void helper(int row, int col, int len, trieNode[] rows, List<List<String>> result) {
//            //处理到最后一个字符
//            if((col == row) && (row == len)) {
//                List<String> list = new ArrayList<String>();
//                for(int i = 0; i < len; i++) {
//                    list.add(new String(rows[i].word));
//                }
//                result.add(list);
//
//                //从左到右，然后再到下一行
//            } else {
//                //先处理从左到右
//                if(col < len) {
//                    //entering the node,保存！！
//                    trieNode prerow = rows[row];
//                    trieNode precol = rows[col];
//                    //例如现在已有"ball"，"ar"，"l","l"，开始找r后面的字母 [1,2]
//                    //prerow=rows[1],precol=rows[2]
//                    //从a~z开始找，rows[1].node[e] 存在("are") 以及rows[2].node[e]存在("le")
//                    //因此将rows[1]=e(e在这是trie node)，rows[2]=e
//                    for(int i = 0; i < 26; i++) {
//
//                        if((rows[row].nodes[i] != null) && (rows[col].nodes[i] != null)) {
//                            rows[row] = rows[row].nodes[i];
//                            if(col != row) {
//                                rows[col] = rows[col].nodes[i];
//                            }
//                            //向右找
//                            helper(row, col + 1, len, rows, result);
//
//                            //leaving the node,remove！！（即回到原来的点）
//                            rows[row] = prerow;
//                            if(col != row) {
//                                rows[col] = precol;
//                            }
//                        }
//                    }
//                } else{
//                    //到了列的末尾，开始查找下一行
//                    helper(row + 1, row + 1, len, rows, result);
//                }
//            }
//        }
//
//
//        //将words里面的词加到trie中去
//        void add(trieNode root, String word) {
//            trieNode node = root;
//            for(char c : word.toCharArray()) {
//                int idx = c - 'a';
//                if(node.nodes[idx] == null) {
//                    node.nodes[idx] = new trieNode();
//                }
//                node = node.nodes[idx];
//            }
//            node.word = word;
//        }
//
//
//        class trieNode {
//            trieNode[] nodes;
//            String word;
//            trieNode() {
//                this.nodes = new trieNode[26];
//                this.word = null;
//            }
//        }
//    }
//    Copyright © 2018 LeetCode Contact Us  |  Jobs  |  Frequently Asked Questions  |  Terms of Service  |  Privacy Policy
//    private TrieNode root = new TrieNode();
//    public List<List<String>> wordSquares(String[] words) {
//        List<List<String>> res = new ArrayList<>();
//        if (words.length==0) return res;
//        buildTrie(words);
//        dfs(res, words, new ArrayList<>(), words[0].length());
//        return res;
//    }
//
//    private void dfs(List<List<String>> res, String[] words, List<String> list, int size) {
//        if (list.size()==size) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//
//        int index = list.size();
//        StringBuilder sb = new StringBuilder();
//        for (String word: list) {
//            sb.append(word.charAt(index));
//        }
//
//        TrieNode node = search(sb.toString());
//        if (node==null) return;
//
//        for (String word: node.startsWith) {
//            list.add(word);
//            dfs(res, words, list, size);
//            list.remove(list.size()-1);
//        }
//    }
//
//    private TrieNode search(String word) {
//        TrieNode node = root;
//        for (char c: word.toCharArray()) {
//            if (node.children[c-'a']==null) return null;
//            node = node.children[c-'a'];
//        }
//        return node;
//    }
//
//    private void buildTrie(String[] words) {
//        for (String word: words) {
//            TrieNode node = root;
//            for (char c: word.toCharArray()) {
//                if (node.children[c-'a']==null) node.children[c-'a'] = new TrieNode();
//                node.startsWith.add(word);
//                node = node.children[c-'a'];
//            }
//        }
//    }
//
//    class TrieNode {
//        List<String> startsWith;
//        TrieNode[] children;
//        public TrieNode() {
//            startsWith = new ArrayList<>();
//            children = new TrieNode[26];
//        }
//    }
}
