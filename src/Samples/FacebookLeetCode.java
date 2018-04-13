package Samples;

import sun.text.normalizer.Trie;

import java.util.*;

public class FacebookLeetCode {
    //    Minimum Size Subarray Sum
//    Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
//    For example, given the array [2,3,1,2,4,3] and s = 7,
//    the subarray [4,3] has the minimal length under the problem constraint.
//    11
//            [1,2,3,4,5]
    public static void testSubarraySum() {
//        int[] nums = new int[] { 2, 3, 1, 2, 4, 3 };
//        int s = 7;
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int s = 11;
        System.out.println("Subarray = " + new FacebookLeetCode().getSubarrayLength(s, nums));
//        System.out.println("Subarray = " + new FacebookLeetCode().minSubArrayLen(s, nums));
    }

    private int getSubarrayLength(int s, int[] nums) {
//        int[] sumArray = new int[nums.length];
        int minArrayLength = Integer.MAX_VALUE;
        int i = 0;
        int sum = 0;
        int runningCount = 0;
        while (i < nums.length - 1) {
            int j = i;
            while (sum < s && j <= nums.length - 1) {
                sum += nums[j];
                runningCount++;
                j++;
            }
            if (sum >= s) {
                minArrayLength = Math.min(runningCount, minArrayLength);
            }
            runningCount = 0;
            sum = 0;
            i++;
        }
        return minArrayLength == Integer.MAX_VALUE ? 0 : minArrayLength;
    }
//
//    public int minSubArrayLen(int s, int[] a) {
//        if (a == null || a.length == 0)
//            return 0;
//
//        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
//
//        while (j < a.length) {
//            sum += a[j++];
//
//            while (sum >= s) {
//                min = Math.min(min, j - i);
//                sum -= a[i++];
//            }
//        }
//
//        return min == Integer.MAX_VALUE ? 0 : min;
//    }

    //    Given preorder and inorder traversal of a tree, construct the binary tree.
//            For example, given
//    preorder = [3,9,20,15,7]
//    inorder = [9,3,15,20,7]
//    Return the following binary tree:
//
//            3
//            / \
//            9  20
//            /  \
//            15   7
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void testBuildTree() {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = new FacebookLeetCode().buildTree(preorder, inorder);
        System.out.println("Root value = " + root.val);
//        int[] preorder1 = new int[]{1, 2};
//        int[] inorder1 = new int[]{2, 1};
//        TreeNode root1 = new FacebookLeetCode().buildTree(preorder1, inorder1);
//        int[] preorder2 = new int[]{1, 2};
//        int[] inorder2 = new int[]{1, 2};
//        TreeNode root2 = new FacebookLeetCode().buildTree(preorder2, inorder2);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        buildTree(root, 0, preorder, inorder);

        return root;
    }

    private void buildTree(TreeNode root, int i, int[] preorder, int[] inorder) {
        if (i >= preorder.length - 1) return;

        if (root.left == null && root.right == null) {
            root.left = new TreeNode((preorder[++i]));
            root.right = new TreeNode((preorder[++i]));
            if (preorder[i] > preorder[i - 1]) {
                buildTree(root.right, i, preorder, inorder);
            } else if (preorder[i] < preorder[i - 1]) {
                buildTree(root.left, i, preorder, inorder);
            }
        }
    }

    //    79. Word Search
//    DescriptionHintsSubmissionsDiscussSolution
//    Given a 2D board and a word, find if the word exists in the grid.
//    The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//            For example,
//    Given board =
//[
//        ['A','B','C','E'],
//        ['S','F','C','S'],
//        ['A','D','E','E']
//        ]
//    word = "ABCCED", -> returns true,
//    word = "SEE", -> returns true,
//    word = "ABCB", -> returns false.
    public static void testWordSearch() {
        String word = "ABCCED";
//        String word = "ABCE";
        String word1 = "ABCB";
        String word2 = "SEE";
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word3 = "A";
        char[][] board1 = new char[][]{
                {'A'}
        };
        String word4 = "AAA";
        char[][] board2 = new char[][]{
                {'A'},
                {'A'}
        };

        String word5 = "cdba";
        char[][] board3 = new char[][]{
                {'a', 'b'},
                {'c', 'd'}
        };
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board, word));
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board, word1));
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board, word2));
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board1, word3));
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board2, word4));
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board3, word5));
    }
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        if(board.length == 0 || board[0].length == 0) return false;
        if(word == null || word.isEmpty()) return false;
        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board [i][j] && searchNextChar(board, word, i, j, 0)) {
                    return true;
                }

            }
        }
        return false;
    }

    private boolean searchNextChar(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
                word.charAt(index) != board[row][col] || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        if (searchNextChar(board, word, row - 1, col, index + 1) ||
                searchNextChar(board, word, row + 1, col, index + 1) ||
                searchNextChar(board, word, row, col - 1, index + 1) ||
                searchNextChar(board, word, row, col + 1, index + 1)) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }
    public static void testWordSearchTrie() {
        String word = "ABCCED";
//        String word = "ABCE";
        String word1 = "ABCB";
        String word2 = "SEE";
        String word3 = "FEA";
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println("Word exist = " + new FacebookLeetCode().existTrie(board, word));
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board, word1));
        System.out.println("Word exist = " + new FacebookLeetCode().exist(board, word2));
        System.out.println("Word exist = " + new FacebookLeetCode().existTrie(board, word3));
    }

    public boolean existTrie(char[][] board, String word) {
        Samples.Trie trie = populateTrie(board);
        Samples.Trie.TrieNode root = trie.getRoot();
        for (int i = 0; i < word.length() - 1; i++) {
            Samples.Trie.TrieNode childNode = root.getChild(word.charAt(i));
            if(childNode == null || childNode.getChild(word.charAt(i + 1)) == null) {
                return false;
            }
        }
        return true;
//        visited = new boolean[board.length][board[0].length];
//        if(board.length == 0 || board[0].length == 0) return false;
//        if(word == null || word.isEmpty()) return false;
//        for (int i = 0; i < board.length ; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (word.charAt(0) == board [i][j] && searchNextChar(board, word, i, j, 0)) {
//                    return true;
//                }
//
//            }
//        }
//        return false;
    }
    private Samples.Trie populateTrie(char[][] board) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int rowEnd = board.length, colEnd = board[0].length;
        for (int i = 0; i < rowEnd; i++) {
            for (int j = 0; j < colEnd; j++) {
                sb.delete(0, sb.length());
                sb.append(board[i][j]);
                // Row above
                if (i > 0) {
                    sb.append(board[i - 1][j]);
                }
                // Row belove
                if (i + 1 < rowEnd) {
                    sb.append(board[i + 1][j]);
                }
                if (j > 0) {
                    sb.append(board[i][j - 1]);
                }
                if (j + 1 < colEnd) {
                    sb.append(board[i][j + 1]);
                }
                list.add(sb.toString());
            }
        }
        return new Samples.Trie(list);
//        System.out.println("Root value = " + trie.getRoot().getCharacter());
    }

//    139. Word Break
//    DescriptionHintsSubmissionsDiscussSolution
//    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
//    For example, given
//    s = "leetcode",
//    dict = ["leet", "code"].
//    Return true because "leetcode" can be segmented as "leet code".

    public static void testWordBreak() {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("cod");
//        String s = "aaaaaaa";
//        wordDict.add("aaaa");
//        wordDict.add("aaa");
//        String s = "bb";
//        wordDict.add("a");
//        wordDict.add("b");
//        wordDict.add("bbb");
//        wordDict.add("bbbb");

        System.out.println("Is word break = " + new FacebookLeetCode().wordBreak(s, wordDict));
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (breakable[j] && wordDict.contains(s.substring(j, i))) {
                    breakable[i] = true;
                    break;
                }
            }
        }
        return breakable[s.length()];
    }
    public static void testMultiplyStrings() {
        String a = "498828660196";
        String b = "840477629533";
//        System.out.println("Binary sum = " + new FacebookLeetCode().multiply("4", "5"));
        System.out.println("Binary sum = " + new FacebookLeetCode().multiply(a, b));
//        System.out.println("Binary sum = " + new FacebookLeetCode().multiply("4", "15"));
    }
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] products = new int[(m+n)];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                products[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
//        Double result = 0.0;
//        for (int i = 0; i < products.length; i++) {
//            result = result * 10 + products[i];
//        }
//        return String.valueOf(result);
        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void testAddBinary() {
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        System.out.println("Binary sum = " + new FacebookLeetCode().addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {

        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        int i = aArray.length - 1;
        int j = bArray.length - 1;
        int carry = 0;
        int aBit = 0, bBit = 0;
        int result;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry == 1) {
            aBit = (i >= 0) ? Character.getNumericValue(aArray[i--]) : 0;
            bBit = (j >= 0) ? Character.getNumericValue(bArray[j--]) : 0;
            result = aBit ^ bBit ^ carry;
            carry = ((aBit + carry + bBit) >= 2) ? 1 : 0;
            sb.append(result);
        }
        return sb.reverse().toString();
    }

    public String addBinaryWithFunc(String a, String b) {
        System.out.println("a = " + a + ", b = " + b);
        Long a_i = binStringToInt(a);
        Long b_i = binStringToInt(b);
        System.out.println("a_i = " + a_i + ", b = " + b_i);
        Long result = a_i + b_i;
        System.out.println("Result = " + result);
        return intToBinString(result);
    }

    private long binStringToInt(String a) {
        long retVal = 0;
        int power = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            retVal += (a.charAt(i) - '0') * Math.pow(2, power++);
        }
        return retVal;
    }

    private String intToBinString(long val) {
        StringBuilder sb = new StringBuilder();
        while(val > 0) {
            long rem = val % 2;
            sb.append(rem);
            val /= 2;
        }
        System.out.println("REtval = " + sb.reverse());
        return sb.reverse().toString();
    }

//    Maximum Size Subarray Sum Equals k
//    Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//    Note:
//    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
//    Example 1:
//    Given nums = [1, -1, 5, -2, 3], k = 3,
//            return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
//    Example 2:
//    Given nums = [-2, -1, 2, 1], k = 1,
//            return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
//    Follow Up:
//    Can you do it in O(n) time?
    public static void testMaxSizeSubarray() {
//        int[] nums = new int[] {1, -1, 5, -2, 3};
//        System.out.println("Mas sub array size = " + new FacebookLeetCode().maxSubArrayLen(nums, 3));
        int[] nums1 = new int[] {-2,-1,2,1};
        System.out.println("Mas sub array size = " + new FacebookLeetCode().maxSubArrayLen(nums1, 1));
    }
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) {
                max = i + 1;
            } else if (map.containsKey(sum - k))  {
                max = Math.max(max, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }
    class LocationPair {
        int start;
        int end;
        LocationPair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        int distance() {
            return end - start;
        }
        void setLocations(LocationPair locationPair) {
            start = locationPair.start;
            end = locationPair.end;
        }
        void updateWithCurrent(LocationPair locationPair) {
            if (locationPair.distance() > distance()) {
                setLocations(locationPair);
            }
        }
    }

    public static void testBadVersion() {
        System.out.println("Bad version = " + new FacebookLeetCode().firstBadVersion(1));
    }

    public int firstBadVersion(int n) {
        int start = 0, end = n;
        int mid = 0;
        for(int i = start; i <= end; i++) {
            mid = (end + start) / 2;
            if(isBadVersion(mid)) {
                if (isBadVersion(mid - 1)) {
                    mid = end;
                } else {
                    return  mid;
                }
            } else {
                mid = start + 1;
            }
        }
        return mid;
    }

    boolean isBadVersion(int n) {
        return n == 1;
    }
}
