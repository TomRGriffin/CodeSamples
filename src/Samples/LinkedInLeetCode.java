package Samples;

import java.util.ArrayList;
import java.util.List;

public class LinkedInLeetCode {
//    744. Find Smallest Letter Greater Than Target
//    Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
//    Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
//    Examples:
//    Input:
//    letters = ["c", "f", "j"]
//    target = "a"
//    Output: "c"
//    Input:
//    letters = ["c", "f", "j"]
//    target = "c"
//    Output: "f"
//    Input:
//    letters = ["c", "f", "j"]
//    target = "d"
//    Output: "f"
//    Input:
//    letters = ["c", "f", "j"]
//    target = "g"
//    Output: "j"
//    Input:
//    letters = ["c", "f", "j"]
//    target = "j"
//    Output: "c"
//    Input:
//    letters = ["c", "f", "j"]
//    target = "k"
//    Output: "c"
//    Note:
//    letters has a length in range [2, 10000].
//    letters consists of lowercase letters, and contains at least 2 unique letters.
//    target is a lowercase letter.
    public static void testNextGreatestLetter()  {
        char[] letters = new char[] {'c', 'f', 'j' };
        char target = 'j';
        char ch = new LinkedInLeetCode().nextGreatestLetter(letters, target);
        char ch1 = new LinkedInLeetCode().nextGreatestLetterBS(letters, target);
        System.out.println("Next largest char = " + ch);
        System.out.println("Next largest char = " + ch1);
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int tVal = target - 'a';
        for (int i = 1; i <= letters.length ; i++) {
            int cVal = letters[i - 1] - 'a';
            if (tVal < cVal) {
                return letters[i - 1];
            }
        }
        return letters[0];
    }
    public char nextGreatestLetterBS(char[] letters, char target) {
        int start = 0, end = letters.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start >= letters.length ? letters[0] : letters[start];
    }

//
//    671. Second Minimum Node In a Binary Tree
//    Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree
//    has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller
//    value among its two sub-nodes.
//    Given such a binary tree, you need to output the second minimum value in the set made of all the nodes
//    ' value in the whole tree.
//    If no such second minimum value exists, output -1 instead.
//    Example 1:
//    Input:
//            2
//            / \
//            2   5
//            / \
//            5   7
//
//    Output: 5
//    Explanation: The smallest value is 2, the second smallest value is 5.
//    Example 2:
//    Input:
//            2
//            / \
//            2   2
//    Output: -1
//    Explanation: The smallest value is 2, but there isn't any second smallest value.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void testfindSecondMinimumValue() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        int min = new LinkedInLeetCode().findSecondMinimumValue(root);
        System.out.println("Second minimum = " + min);
    }
    public int findSecondMinimumValueA(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) {
            return -1;
        }
        int left = root.left.val;
        int right = root.right.val;

        if (left == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (right == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }
    int min1 = 0;
    long min2 = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        min1 = root.val;
        findSecondMinimumValueRec(root);
        return (int) min2;
    }

    private void findSecondMinimumValueRec(TreeNode root) {
        if (root != null) {
            if (min1 < root.val && root.val < min2) {
                min2 = root.val;
            } else if (min1 == root.val){
                findSecondMinimumValueRec(root.left);
                findSecondMinimumValueRec(root.right);
            }
        }
    }


//    244. Shortest Word Distance II
//    This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your
//    method will be called repeatedly many times with different parameters. How would you optimize it?
//    Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
//            and word2 and return the shortest distance between these two words in the list.
//    For example,
//    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//    Given word1 = “coding”, word2 = “practice”, return 3.
//    Given word1 = "makes", word2 = "coding", return 1.
//    Note:
//    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
    public static void testWordDistance() {
//        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes" };
        String[] words = new String[] {"a", "b" };
        WordDistance obj = new WordDistance(words);
        String word1 = "a", word2 = "b";
        int param_1 = obj.shortest(word1,word2);
        System.out.println("Word distance = " + param_1);
        int param_2 = obj.shortest(word2, word1);
        System.out.println("Word distance = " + param_2);
    }
    public static class WordDistance {
        String[] words;
        LocationPair current;
        LocationPair best;
        public WordDistance(String[] words) {
            this.words = words;
        }

        public int shortest(String word1, String word2) {
            current = new LocationPair(-1, -1);
            best = new LocationPair(-1, -1);
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i])) {
                    current.firstWordPos = i;
                    best.updateWithMin(current);
                } else if (word2.equals(words[i])) {
                    current.secondWordPos = i;
                    best.updateWithMin(current);
                }
            }
            return best.distance();
        }

        public static class LocationPair {
            int firstWordPos, secondWordPos;
            public LocationPair(int firstWordPos, int secondWordPos) {
                this.firstWordPos = firstWordPos;
                this.secondWordPos = secondWordPos;
            }
            int distance() {
                return Math.abs(secondWordPos - firstWordPos);
            }
            public void setLocations(int firstWordPos, int secondWordPos) {
                this.firstWordPos = firstWordPos;
                this.secondWordPos = secondWordPos;
            }
            private boolean isValid() {
                return firstWordPos >= 0 && secondWordPos >= 0;
            }
            public void updateWithMin(LocationPair locationPair) {
                if (!isValid() || distance() > locationPair.distance()) {
                    setLocations(locationPair.firstWordPos, locationPair.secondWordPos);
                }
            }
        }
    }

//    256. Paint House
//    There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of
//    painting each house with a certain color is different. You have to paint all the houses such that no two adjacent
//    houses have the same color.
//    The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
//    For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting
//    house 1 with color green, and so on... Find the minimum cost to paint all houses.
//    Note:
//    All costs are positive integers.
    public static void testPaintHouse() {
        int[][] costs = new int[][] { {1, 1, 2}, {1, 2, 1}, {2, 1, 1} };
        Microsoft.printMatrix(costs);
        int cost = new LinkedInLeetCode().minCost(costs);
        System.out.println("Min cost = " + cost);
    }
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(costs[n - 1][0], Math.min(costs[n - 1][1], costs[n - 1][2]));
    }


//    647. Palindromic Substrings
//    Given a string, your task is to count how many palindromic substrings in this string.
//    The substrings with different start indexes or end indexes are counted as different substrings even they consist
//    of same characters.
//    Example 1:
//    Input: "abc"
//    Output: 3
//    Explanation: Three palindromic strings: "a", "b", "c".
//    Example 2:
//    Input: "aaa"
//    Output: 6
//    Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
    public static void testPalindromicSubstrings() {
        String s = "aaa";
        int count = new LinkedInLeetCode().countSubstrings(s);
        System.out.println("Count of palindromes = " + count);
    }
    int counts = 0;
    public int countSubstrings(String s) {
        if(s == null || s.isEmpty()) return 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            expandMiddle(s, i, i, count);
            expandMiddle(s, i, i + 1, count);
        }
        return counts;
    }

    private void expandMiddle(String s, int left, int right, int count) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            counts++;
            left--;
            right++;
        }
    }

    public int countSubstringsBruetForce(String s) {
        if (s == null || s.isEmpty()) return 0;
        List<String> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j > i; j--) {
                String str = s.substring(i, j);
                if (list.contains(str)){
                    count++;
                } else if (isPalindrome(str)) {
                    list.add(str);
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
