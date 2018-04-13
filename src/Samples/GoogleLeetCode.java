package Samples;

import java.util.*;

public class GoogleLeetCode {
//  Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//  For example,
//  Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
    public static void testTrap() {
        System.out.println(new GoogleLeetCode().trap(new int[] { 0,1,0,2,1,0,1,3,2,1,2,1 }));
    }
    private int trap(int[] height) {
        int [] leftHigh = new int[height.length];
        int [] rightHigh = new int[height.length];
        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            maxHeight = Math.max(maxHeight, height[i]);
            leftHigh[i] = maxHeight;
        }
        maxHeight = Integer.MIN_VALUE;
        for (int i = height.length - 1; i >= 0; i--) {
            maxHeight = Math.max(maxHeight, height[i]);
            rightHigh[i] = maxHeight;
        }
        int totalLiters = 0;
        for (int i = 0; i < height.length; i++) {
            totalLiters += Math.min(leftHigh[i], rightHigh[i]) - height[i];
        }
        return totalLiters;
    }
//    public int trap(int[] height) {
//        int left = 0, right = height.length - 1, maxleft = 0, maxright = 0, res = 0;
//        while(left < right){
//            if(height[left] < height[right]){
//                if(height[left] > maxleft)
//                    maxleft = height[left];
//                else
//                    res += maxleft - height[left];
//                left++;
//            } else {
//                if(height[right] > maxright)
//                    maxright = height[right];
//                else
//                    res += maxright - height[right];
//                right--;
//            }
//        }
//        return res;
//    }

    public static void testFindMaxOnes() {
        System.out.println("Max ones = " + new GoogleLeetCode().findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1}));
    }
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxSequence = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxSequence = Math.max(maxSequence, count);
                count = 0;
            }
        }
        return Math.max(maxSequence, count);
    }

//    Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
//    Examples:
//    s = "leetcode"
//            return 0.
//    s = "loveleetcode",
//            return 2.
//    Note: You may assume the string contain only lowercase letters.
    public static void testNonRepeatingChar() {
//        System.out.println("Non repeated char index = " + new GoogleLeetCode().findNonRepeatingCharIndex("loveleetcode"));
        System.out.println("Non repeated char index = " + new GoogleLeetCode().firstUniqChar("lcleetcode"));
    }

    private  int findNonRepeatingCharIndex(String s) {
        HashMap<Character, Integer> charMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (charMap.containsKey(ch)) {
                charMap.put(ch, -1);
            } else {
                charMap.put(ch, i);
            }
        }
        int index = Integer.MAX_VALUE;
        for (int value: charMap.values()) {
            if (value >= 0) {
                index = Math.min(value, index);
            }
        }

        return index == Integer.MAX_VALUE ? -1: index;
    }

    private int firstUniqChar(String s) {
        int[] chars = new int[26];
        for (char ch : s.toCharArray()) {
            chars[ch - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return  -1;
    }

//    Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//    For example,
//    "A man, a plan, a canal: Panama" is a palindrome.
//    "race a car" is not a palindrome.
//
//    Note:
//    Have you consider that the string might be empty? This is a good question to ask during an interview.
//    For the purpose of this problem, we define empty string as valid palindrome.
    public static void testIsValidPalindrome() {
        System.out.println("Is valid palindrome = " + new GoogleLeetCode().isPalindrome(",."));
    }

    public boolean isPalindrome(String str) {
        if (str.isEmpty()) return true;
        String s = str.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return  false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

//    Intersection of Two Arrays
//    Given two arrays, write a function to compute their intersection.
//
//            Example:
//    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
//
//    Note:
//    Each element in the result must be unique.
//    The result can be in any order.
    public static void testIntersection() {
        int [] num1 = new int[] { 1, 2 };
        int [] num2 = new int[] { 2, 1, 3 };
        System.out.println("Is valid palindrome = " + Arrays.toString(new GoogleLeetCode().intersection(num1, num2)));
    }


    private int[] intersection(int[] nums1, int[] nums2) {
        int [] shortArray = nums1.length >= nums2.length ? nums2 : nums1;
        int [] longArray = nums1.length >= nums2.length ? nums1 : nums2;
        Arrays.sort(longArray);
        Set<Integer> points = new HashSet<>();
        for (int aShortArray : shortArray) {
            if (findValue(longArray, aShortArray, 0, longArray.length - 1) > 0) {
                points.add(aShortArray);
            }
        }
        int [] retArray = new int[points.size()];
        int i = 0;
        for (Object point : points) {
            retArray[i++] = (int) point;
        }
        return retArray;
    }
    private int findValue(int [] array, int value, int left, int right) {
        if (left > right) return  -1;
        int middle = (right + left) / 2;
        if (value == array[middle]) {
            return value;
        } else if(value < array[middle]) {
            return findValue(array, value, left, middle - 1);
        } else {
            return findValue(array, value, middle + 1, right);
        }
    }

    private int[] intersectionBruteForce(int[] nums1, int[] nums2) {
        int [] shortArray = nums1.length >= nums2.length ? nums2 : nums1;
        int [] longArray = nums1.length >= nums2.length ? nums1 : nums2;
        HashMap<Integer, Integer> points = new HashMap<>();
        for (int i = 0; i < shortArray.length; i++) {
            for (int aLongArray : longArray) {
                if (shortArray[i] == aLongArray) {
                    points.put(aLongArray, i);
                }
            }
        }
        int [] retArray = new int[points.keySet().size()];
        int i = 0;
        for (Integer value: points.keySet()) {
            retArray[i++] = value;
        }
        return retArray;
    }

//    Shortest Palindrome
//    Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
//    For example:
//    Given "aacecaaa", return "aaacecaaa".
//    Given "abcd", return "dcbabcd".
    public static void testShortPalindrome() {
        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("aba"));
        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("abcd"));
        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("aacecaaa"));
        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome(""));
        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("abb"));
//        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("abbacd"));
//        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("aabba"));
//        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("abb"));
        System.out.println("Shortest palindrome = " + new GoogleLeetCode().shortestPalindrome("abbacd"));
    }

    
    public String shortestPalindrome(String s) {
        if (s == null || s.isEmpty()) return s;

        int minLength = findMinPalindromeLength(s);
        int i = 0, j = s.length() - 1;
        StringBuilder sBuilder = new StringBuilder(minLength);
        while (i < minLength) {
            sBuilder.insert(i, s.charAt(j - i));
            i++;
        }
        sBuilder.insert(i, s);
        return sBuilder.toString();
    }

    private int findMinPalindromeLength(String s) {
       int i = 0, j = s.length() - 1;
       int minLength = 0;

        while (s.charAt(i) != s.charAt(j)) {
            j--;
            minLength++;
        }

       while (i < j) {
           if(s.charAt(i) == s.charAt(j)) {
               minLength++;
           } else {
               break;
           }
           i++;
       }


//           minLength = s.length() - j;
        if (minLength % 2 == 0) {
            minLength--;
        }

//       minLength = isPalindrome ? s.length() : s.length() - j - 1;
//       minLength = minLength > 0 ? minLength - 1 : minLength;
       return minLength;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
//    Closest Binary Search Tree Value
//    Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//    Note:
//    Given target value is a floating point.
//    You are guaranteed to have only one unique value in the BST that is closest to the target.
    public static void testClosestBinary() {
//        [1,2,3,5,8,6,9]
//        [4,2,5,1,3]
//        3.714286
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(9);
        System.out.println("Closest value = " + new GoogleLeetCode().findClosest(root, 3.1));
    }

    private int findClosest(TreeNode root, double target) {
        if (root == null) return -1;
        return (int) findClosest(root, round(target), root.val, Integer.MAX_VALUE);
//        return closest(root, target, root.val);
    }
    double round( double f) {
        if ((f * 10) % 10 >= 5) {
            return Math.ceil(f);
        } else {
            return Math.floor(f);
        }

    }

    private int findClosest(TreeNode root, double target, int value, double distance) {
        if (root == null) return value;
        double currentDistance = Math.abs(root.val - target);
        if (currentDistance < distance) {
            value = root.val;
        }
        if (target < root.val) {
            // left
            value = findClosest(root.left, target, root.val, currentDistance);
        } else if (target > root.val) {
            //right
            value = findClosest(root.right, target, root.val, currentDistance);
        }
        return value;
    }

//    private int closest(TreeNode node, double target, int val) {
//        if (node == null) return val;
//        if (Math.abs(node.val - target) < Math.abs(val - target)) val = node.val;
//        if (node.val < target) val = closest(node.right, target, val);
//        else if (node.val > target) val = closest(node.left, target, val);
//        return val;
//    }

//    Spiral Matrix
//    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//    For example,
//    Given the following matrix:
//            [
//            [ 1, 2, 3 ],
//            [ 4, 5, 6 ],
//            [ 7, 8, 9 ]
//            ]
//    You should return [1,2,3,6,9,8,7,4,5].
    public static void testSpiralOrder() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix1 = new int[][]{
                {7, 8, 9}
        };
        int[][] matrix2 = new int[][]{
                {7}, {8}, {9}
        };
        System.out.println("List = " + new GoogleLeetCode().spiralOrder(matrix));
        System.out.println("List = " + new GoogleLeetCode().spiralOrder(matrix1));
        System.out.println("List = " + new GoogleLeetCode().spiralOrder(matrix2));
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> resultList = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return resultList;
        }

        int colBegin = 0, colEnd = matrix[0].length - 1;
        int rowBegin = 0, rowEnd = matrix.length - 1;

        while ((rowBegin <= rowEnd) && (colBegin <= colEnd)) {
            for (int i = colBegin; i <= colEnd; i++) {
                resultList.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            for (int i = rowBegin; i <= rowEnd; i++) {
                resultList.add(matrix[i][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i--) {
                    resultList.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    resultList.add(matrix[i][colBegin]);
                }
            }
            colBegin++;

        }
        return resultList;
    }

//    4. Median of Two Sorted Arrays
//            DescriptionHintsSubmissionsDiscussSolution
//    There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//    Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//    Example 1:
//    nums1 = [1, 3]
//    nums2 = [2]
//
//    The median is 2.0
//
//    Example 2:
//    nums1 = [1, 2]
//    nums2 = [3, 4]
//    The median is (2 + 3)/2 = 2.5

    public static void testRunningMedian() {
        int[] nums1 = new int[] {2};
        int[] nums2 = new int[] {};
        System.out.println("Median = " + new GoogleLeetCode().findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
         if (nums1.length == 0 && nums2.length == 0) return 0;
         if (nums1.length == 0 && nums2.length > 0){
             return (double)(nums2[nums2.length - 1] + nums2[0]) / 2;
         }
         if (nums2.length == 0 && nums1.length > 0){
             return (double)(nums1[nums1.length - 1] + nums1[0]) / 2;
         }
        // if (nums2.length == 0) return nums1[0];
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1);
        int[] larger = nums1.length > nums2.length ? nums1 : nums2;
        int[] smaller = nums1.length > nums2.length ? nums2 : nums1;
        int i = 0;
        while(i < smaller.length) {
            if (smaller[i] < larger[i]) {
                minHeap.add(smaller[i]);
                maxHeap.add(larger[i]);
            } else {
                minHeap.add(larger[i]);
                maxHeap.add(smaller[i]);
            }
            i++;
        }
        while(i < larger.length) {
            if (minHeap.size() == 0 || larger[i] < minHeap.peek()) {
                minHeap.add(larger[i]);
            } else {
                maxHeap.add(larger[i]);
            }
            i++;
        }
        double median = maxHeap.size() > 0 ? maxHeap.peek() : minHeap.peek();
        if (nums1.length == nums2.length) {
            median = (double) (maxHeap.peek() + minHeap.peek()) / 2;
        }
        return median;
    }


//    312. Burst Balloons
//    Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
//    Find the maximum coins you can collect by bursting the balloons wisely.
//    Note:
//            (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
//            (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
//    Example:
//    Given [3, 1, 5, 8]
//    Return 167
//    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
    public static void testBurstBalloons() {
        int[] nums = new int[] { 3, 1, 5, 8 };
        System.out.println("Max coins = " + new GoogleLeetCode().maxCoins(nums));
    }
    public int maxCoins(int[] nums) {
        int[]iNums = new int[nums.length + 2];
        int n = 1;
        for (int x : nums) if (x > 0) iNums[n++] = x;
        iNums[0] = 1;
        iNums[n++] = 1;
        int[][] memo = new int[n][n];
        int sum = maxCoins(iNums, memo, 0, n - 1);
        return sum;
    }
    public int maxCoins(int[] nums, int[][] memo, int left, int right) {
        if (left + 1 == right)  {
            return 0;
        }
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        int sum = 0;

        for (int i = left + 1; i < right; i++ ) {
            int currentProd = nums[left] * nums[i] * nums[right];
            int leftProd = maxCoins(nums, memo, left, i);
            int rightProd = maxCoins(nums, memo, i, right);
            sum = Math.max(sum, currentProd +  leftProd + rightProd);
        }
        memo[left][right] = sum;
        return  sum;
    }

    public static void testMinimumTotal() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> angle = new ArrayList<>();
        angle.add(1);
        triangle.add(angle);
        List<Integer> angle2 = new ArrayList<>();
        angle2.add(2);
        angle2.add(3);
        triangle.add(angle2);
        List<Integer> angle3 = new ArrayList<>();
        angle3.add(4);
        angle3.add(5);
        angle3.add(6);
        triangle.add(angle3);

        List<Integer> angle4 = new ArrayList<>();
        angle4.add(10);
        angle4.add(9);
        angle4.add(7);
        angle4.add(8);
        triangle.add(angle4);
        System.out.println("Minimum sum = " + new GoogleLeetCode().minimumTotal(triangle));
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];
//        A[0] = 1;
        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> angle = triangle.get(i);
            for (int j = 0; j < angle.size(); j++) {
                System.out.println("A[" + j + "] = " + A[j] + " angle.get(" + j + ") = " + angle.get(j));
                System.out.println(("A[] = " + Arrays.toString(A)));
                A[j] = angle.get(j) + Math.min(A[j], A[j + 1]);
                System.out.println(("A[] = " + Arrays.toString(A)));
            }
        }
        return A[0];
    }
}
