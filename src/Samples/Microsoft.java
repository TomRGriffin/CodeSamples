package Samples;

import java.util.*;

public class Microsoft {

    public static void testAToi() {
//        System.out.println("Atoi = " + new Microsoft().myAtoi("2147483648"));
//        System.out.println("Atoi = " + new Microsoft().myAtoi("-2147483648"));
        System.out.println("Atoi = " + new Microsoft().myAtoi("      -11919730356x"));
    }

    public int myAtoi(String str) {
        int i = 0, n = str.length();
        int sign = 1;
        int num = 0;
        boolean isSigned = false;
        int maxDiv10 = Integer.MAX_VALUE / 10;
        while(i < n && str.charAt(i) == ' ') i++;
        if (i < n && str.charAt(i) == '+' ) {
            i++;
        }
        if (i < n && str.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        while(i < n && Character.isDigit(str.charAt(i))) {
            int digit = Character.getNumericValue(str.charAt(i));
            if (num > maxDiv10 || num == maxDiv10 &&  digit >= 8) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            num = num * 10 + digit;
            i++;
        }
        return sign * num;
    }

    public static void testSetZeroes() {
        int[][] matrix = new int[][] {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
//        int[][] matrix = new int[][] { { 1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        printMatrix(matrix);

        new Microsoft().setZeroes(matrix);
//        System.out.println();
//        for (int i = 0; i < matrix.length ; i++) {
//            for (int j = 0; j < matrix[0].length ; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println();
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[0].length ; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setZeroes(int[][] matrix) {
        boolean[] rowZero = new boolean[matrix.length];
        boolean[] colZero = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowZero[i] || colZero[i])
                    matrix[i][j] = 0;
            }
        }
    }
//    Longest Palindromic Substring
//    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//    Example:
//    Input: "babad"
//    Output: "bab"
//    Note: "aba" is also a valid answer.
//    Example:
//    Input: "cbbd"
//    Output: "bb"
    public static void testLongestPalindrome() {
        System.out.println("Longest palindrome = " + new Microsoft().longestPalindrome("cabad"));
    }
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= s.length(); i++) {
            expandPalindrome(s, i, i, sb);
            expandPalindrome(s, i, i + 1, sb);
        }
        return sb.toString();
    }
    public void expandPalindrome(String s, int i, int j, StringBuilder sb) {
        while(i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                if (j - i + 1 > sb.length()) {
                    sb.delete(0, sb.length());
                    sb.append(s.substring(i, j + 1));
                }
                i--;
                j++;;
            } else {
                break;
            }

        }
    }

//    Group Anagrams
//    Given an array of strings, group anagrams together.
//    For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
//    Return:
//            [
//            ["ate", "eat","tea"],
//            ["nat","tan"],
//            ["bat"]
//            ]
//    Note: All inputs will be in lower-case.
    public static void testGroupAnagrams() {
        String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> anagrams = new Microsoft().groupAnagrams(strs);
        System.out.println("Anagrams = " + anagrams.toString());
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for(String str : strs) {
            int bitVector = 0;
            for (int i = 0; i < str.length(); i++) {
                int bitVal = str.charAt(i) - 'a';
                bitVector |= 1 << bitVal;
            }
            List<String> list;
            if (map.containsKey(bitVector)) {
                list = map.get(bitVector);

            } else {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(bitVector, list);
        }
        for(List<String> value : map.values()){
            result.add(value);
        }
        return result;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
//    [[],[-1,5,11],[],[6,10]]
    public static void testMergeKLists() {
        ListNode l1 = null;
        ListNode l2 = new ListNode(-1);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(11);
        ListNode l3 = null;
        ListNode l4 = new ListNode(6);
        l4.next = new ListNode(10);
        ListNode[] lists = new ListNode[] { l1, l2, l3, l4 };
//        ListNode head = new Microsoft().mergeKLists(lists);
//        printLinkedList(head);
        ListNode head = new Microsoft().mergeQueueKLists(lists);
        System.out.println("Merged using queue");
        printLinkedList(head);
    }

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print("->");
            head = head.next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        int j = lists.length - 1;
        ListNode head = lists[0];
        while (j > 0) {
            int i = 0;
            while (i < j) {
                lists[i] = mergeLists(lists[i], lists[j]);;
                i++;
                j--;
            }
        }
        return lists[0];
    }
    private ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode head = dummyHead;
        while (l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        if (l1 != null) head.next = l1;
        if (l2 != null) head.next = l2;

        return dummyHead.next;
    }

    public static final Comparator<ListNode> comparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode l1, ListNode l2) {
            return l1.val - l2.val;
        }
    };
    public ListNode mergeQueueKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, comparator);
        for(ListNode node : lists) {
            if (node != null)  queue.add(node);
        }
        ListNode dummyHead = new ListNode(0);
        ListNode head = dummyHead;
        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            head.next = node;
            head = head.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }
        return dummyHead.next;
    }


//    Add Two Numbers
//    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//    Example
//    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//    Output: 7 -> 0 -> 8
//    Explanation: 342 + 465 = 807.

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public static void testInorderTraversal() {
//        [1,4,2,3]
//        [1,4,2,3,5,6,7]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left = new TreeNode(7);
        List<Integer> inorderList = new Microsoft().inorderTraversal(root);
        System.out.println("Inorder list = " + inorderList.toString());
    }

    public List<Integer> inorderTraversalRec(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        inorderTraversal(root, result);
        return result;
    }
    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (root != null) {
            queue.add(root);
            queue.add(root.right);
            queue.add(root.left);
        }
        while (!queue.isEmpty()) {
            TreeNode node = ((ArrayDeque<TreeNode>) queue).pollLast();
            if (node != null && node.left != null) {
                result.add(node.left.val);
            }
        }
        return result;
    }
//
//    Binary Tree Level Order Traversal
//    Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//
//    For example:
//    Given binary tree [3,9,20,null,null,15,7],
//            3
//            / \
//            9  20
//            /  \
//            15   7
//            return its level order traversal as:
//            [
//            [3],
//            [9,20],
//            [15,7]
//            ]
    public static void testLevelOrder() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> levelValues = new Microsoft().levelOrder(root);
        System.out.println("Values = " + levelValues.toString());
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return null;
        List<List<Integer>> levelValues = new ArrayList<>();
        levelOrder(root, 0, levelValues);
        return levelValues;
    }
    private void levelOrder(TreeNode root, int level, List<List<Integer>> levelValues) {
        if (root == null) return;
        if (levelValues.size() == level) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            levelValues.add(list);
        } else {
            List<Integer> list = levelValues.get(level);
            list.add(root.val);
        }
        levelOrder(root.left, level + 1, levelValues);
        levelOrder(root.right, level + 1, levelValues);
    }

//    [0, 1, 2, 1, 2, 0]
    public static void testSortColors() {
//        int[] colors = new int[] { 0, 1, 2, 1, 2, 0 };
        int[] colors = new int[] { 1, 0 };
        new Microsoft().sortColors(colors);
        System.out.println("Sorted colors = " + Arrays.toString(colors));
    }
    public void sortColors(int[] nums) {
        int lastZero = 0, lastSecond = nums.length - 1;
        for (int i = 0; i <= lastSecond; i++) {
            while(nums[i] == 2 && i < lastSecond) swap(nums, i, lastSecond--);
            while(nums[i] == 0 && i > lastZero) swap(nums, i, lastZero++);
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


//    273. Integer to English Words
//    Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
//    For example,
//        123 -> "One Hundred Twenty Three"
//        12345 -> "Twelve Thousand Three Hundred Forty Five"
//        1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
    public static void testNumberToWords() {
        System.out.println("Number to words 123 = " + new Microsoft().numberToWords(1234567));
    }
    public String numberToWordsSeq(int num) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, String> map = new HashMap<>();
        prepareMap(map);
        int largeUnit = 1;

        while (num > 0) {
            int unit = getUnit(num);
            if (unit >= 1000 ) {
                int div = num / 1000;
                sb.append(map.get(num / unit)).append(" ").append("Thousand").append(" ");
            } else if (unit >= 100) {
                sb.append(map.get(num / unit)).append(" ").append("Hundred").append(" ");
            } else {
                if (num < 20) {
                    sb.append(map.get(num)).append(" ");
                } else {
                    sb.append(map.get(num - (num % 10))).append(" ");
                    sb.append(map.get(num % 10)).append(" ");

                }
                break;
            }
            num = num % unit;
        }
        return sb.toString();
    }

    private int getUnit(int num){
        if (num > 100 && num < 1000) {
            return 100;
        } else if (num > 1000 && num < 1000000) {
            return 1000;
        }
        return 0;
    }

    private void prepareMap(HashMap<Integer, String> map) {
        map.put(1, "One");map.put(2, "Two");map.put(3, "Three");map.put(4, "Four");map.put(5, "Five");map.put(6, "Six");
        map.put(7, "Seven");map.put(8, "Eight");map.put(9, "Nine");map.put(10, "Ten");map.put(11, "Eleven");
        map.put(12, "Twelve");map.put(13, "Thirteen");map.put(14, "Fourteen");map.put(15, "Fifteen");map.put(16, "Sixteen");
        map.put(17, "Seventenn");map.put(18, "Eighteen");map.put(19, "Nineteen");map.put(20, "Twenty");
        map.put(30, "Thirty");map.put(40, "Forty");map.put(50, "Fifty");map.put(60, "Sixty");map.put(70, "Seventy");
        map.put(80, "Eighty");map.put(90, "Ninty");map.put(100, "Hundred"); map.put(1000, "Thousand");
    }

    private final String[] belowTen = new String[] { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eigth", "Nine"};
    private final String[] belowTwenty = new String[] { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eigteen", "Nineteen"};
    private final String[] belowHundred = new String[] { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eigthy", "Ninty"};

    public String numberToWords(int num) {
        if (num == 0) return belowTen[num];
        return numberToWordsRec(num);
    }
    private String numberToWordsRec(int num) {
        String result = "";
        if (num < 10) { result = belowTen[num]; }
        else if(num < 20) { result = belowTwenty[num - 10]; }
        else if (num < 100) { result = belowHundred[num/10] + " " + belowTen[num % 10];  }
        else if (num < 1000) { result = numberToWordsRec(num / 100) + " Hundred " + numberToWordsRec(num % 100); }
        else if (num < 1000000) { result = numberToWordsRec(num / 1000) + " Thousand " + numberToWordsRec(num % 1000); }
        else if (num < 1000000000) { result = numberToWordsRec(num / 1000000) + " Million " + numberToWordsRec(num % 1000000); }
        else { result = numberToWordsRec(num / 1000000000) + " Billion " + numberToWordsRec(num % 1000000000); }
        return result.trim();
    }

    public static void testRemoveDuplicates() {
        int[] nums = new int[] {1, 1, 2, 2, 4, 4};
        int newLength = new Microsoft().removeDuplicates(nums);
        System.out.println("New length = " + newLength);
    }

    public int removeDuplicates(int[] nums) {
        int i = 0, j = 0;
        for (int k = 0; k < nums.length - 1; k++) {
            if (nums[k] != nums[k + 1]) {
                nums[j++] = nums[k];
            }
        }
        nums[j++] = nums[nums.length - 1];
        return j;
    }

    public static void testSearchRotatedArray() {
//        int[] nums = new int[] { 4, 5, 0, 1, 2, 3 };
        int[] nums = new int[] { 1, 3 };
//        int[] nums = new int[] { 3, 1 };
//        int[] nums = new int[] { 1, 3, 5};
        int index = new Microsoft().search(nums, 3);
        System.out.println("Target index = " + index);
    }
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int i = 0, j = matrix[0].length - 1;
        while(i < j) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public int lengthOfLIS(int[] nums) {

    }
}
