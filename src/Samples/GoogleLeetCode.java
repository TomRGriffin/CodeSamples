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
        System.out.println("Is valid palindrome = " + new GoogleLeetCode().shortestPalindrome("abcd"));
    }

    
    public String shortestPalindrome(String s) {
        return "";
    }
}
