package Samples;
import java.util.*;

import static org.junit.Assert.*;
public class ArraysStrings {

	// Question: "Implement an algorithm to determine if a string has all unique characters. 
	// What if you cannot use additional data structures?
	// Solution: Use integer as bit flag. Get the character value, shift 1 with char value.
	// Using shifted bits, set the bit ON in checker Flag. For next char check with checker flag
	// if the bit is ON, the string is not unique as character is repeated
	public static boolean isUniqueString(String inputStr) {
		if (inputStr.length() > 26) {
			return false;
		}
		int checkerFlag = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			int charBits = 1 << (inputStr.charAt(i) - 'a');
			if ((checkerFlag & charBits) > 0 ) {
				return false;
			}
			checkerFlag |= charBits;
			Utils.simulateTimeComplexity();
		}
		return true;
	}
	
	public static boolean isUniqueStringBruteForce(String inputStr) {
		if (inputStr.length() > 26) {
			return false;
		}
		for (int i = 0; i < inputStr.length(); i++) {
			char character = inputStr.charAt(i);
			for (int j = i + 1; j < inputStr.length(); j++) {
				if (character == (char)inputStr.charAt(j)){
					return false;
				}
				Utils.simulateTimeComplexity();	
			}
		}
		return true;
	}
	
	public static void testUniqueString() {
		String inputString = "cdefgh";
		boolean UNIQUE_STRING = true;
		long startTime = System.currentTimeMillis();
		assertEquals(UNIQUE_STRING, isUniqueStringBruteForce(inputString));
		long endTime = System.currentTimeMillis();
		System.out.println("Brute force Big O, (N*(N-1)/2) = " + 
				((endTime - startTime)/ Utils.TIME_INTERVAL));
		
		startTime = System.currentTimeMillis();
		assertEquals(UNIQUE_STRING, isUniqueString(inputString));
		endTime = System.currentTimeMillis();
		System.out.println("Big O = " + ((endTime - startTime)/Utils.TIME_INTERVAL));
		
		// TODO: should isUniqueString check for empty string
		inputString = "";
		assertEquals(true, isUniqueString(inputString));
		inputString = "-*(@)";
		assertEquals(true, isUniqueString(inputString));
	}
	
	// Question: "Given two strings, write a method to decide if one string is permutation of other
	private static void permutations(String str, String prefix, Map permutationsMap) {
		if (str.length() == 0) {
//			System.out.println(prefix);
			permutationsMap.put(prefix, "1");
			return;
		}
		
		for (int i = 0; i < str.length(); i++) {
//			System.out.print(", i = " + i);
//			System.out.print(", str.substring(0, i) = " + str.substring(0, i) + 
//					" , str.substring(i + 1) = " + str.substring(i + 1));
			String rem = str.substring(0, i) + str.substring(i + 1);
//			System.out.print(", rem = " + rem);
//			System.out.print(", permutations(" + rem + ", " + prefix + str.charAt(i) + ")");
//			System.out.println();
			permutations(rem, prefix + str.charAt(i), permutationsMap);
		}
	}
	public static void testPermutations() {
		permutations("abcd", "", new HashMap<String, String>());
	}
	
	private static boolean isPermutationWithMap(String firstString, String secondString) {
		Map<String, String> permutationsMap = new HashMap<String, String>();
		permutations(firstString, "", permutationsMap);
		if (permutationsMap.containsKey(secondString)) {
			return true;
		}
		return false;
	}

	private static boolean isPermutation(String firstString, String secondString) {
		Map<String, String> permutationsMap = new HashMap<String, String>();
		if (firstString.length() != secondString.length()) {
			return false;
		}
		for (int i = 0; i < firstString.length(); i++) {
			permutationsMap.put(String.valueOf(firstString.charAt(i)), "1");
		}
		for (int i = 0; i < secondString.length(); i++) {
			if (!permutationsMap.containsKey(String.valueOf(secondString.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static void testIsPermutations() {
		String firstString = "aebcdef";
		String secondString = "efedcba";
		long startTime = System.nanoTime();
		assertEquals(true, isPermutationWithMap(firstString, secondString));
		long endTime = System.nanoTime();
		System.out.println("Map permutations time = " + (endTime - startTime));
		
		startTime = System.nanoTime();
		assertEquals(true, isPermutation(firstString, secondString));
		endTime = System.nanoTime();
		System.out.println("String compare time = " + (endTime - startTime));
	}
	
	
//	 Write a method to replace all spaces in a string with '%20'. You may assume that
//	 the string has sufficient space at the end of the string to hold the additional
//	 characters, and that you are given the "true" length of the string. (Note: if
//	 implementing in Java, please use a character array so that you can perform this
//	 operation in place.)
//	 
//	 EXAMPLE
//	 
//	 Input:   "Mr John Smith    "
//	 Output:  "Mr%20John%20Smith"
	 
	private static void urlify(char[] inputStr, int length) {
		int numSpaces = countSpaces(inputStr, length);
		int endPtr = length - 1 + numSpaces * 2;
		for (int i = length - 1; i > 0; i--) {
			if (inputStr[i] == ' ') {
				inputStr[endPtr] = '0';
				inputStr[endPtr - 1] = '2';
				inputStr[endPtr - 2] = '%';
				endPtr -= 3;
			} else {
				inputStr[endPtr] = inputStr[i];
				endPtr--;
			}
		}
		
		System.out.println("Urlified = " + String.valueOf(inputStr));
	}
	
	private static int countSpaces(char[] inputStr, int length) {
		int numSpaces = 0;
		for (int i = 0; i < length; i++) {
			if (inputStr[i] == ' ') {
				numSpaces++;
			}
		}
		return numSpaces;
	}
	
	public static void testUrlify(){
		String inputStr = "Mr John Smith    ";
		urlify(inputStr.toCharArray(), 13);
	}
	
//	Palindrome permutation: Given a string, write a function to check if it is a permutation of a 
//	palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation
//	is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
//
//	Example
//
//	Input: Tact Coa
//	Output: True (permutations: "taco cat", "atco cta", "acto tca" etc.)
//	Input: Madam I m adam
//	Output: True (permutations: "adam I m madam")
	
	private static boolean isPalindromePermutation(String inputStr) {
		if (inputStr.isEmpty()) {
			return true;
		}
		
		if (inputStr.length() % 2 != 0) {
			return false;
		}
		
		int trimmedLength = 0;
		int bitVector = 0;
		int oddCharsCount = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			Character character = inputStr.charAt(i);
			int charBitValue = character - 'A';
			if (charBitValue >= 0) {
				charBitValue = 1 << (character - 'a');
				if ((bitVector & charBitValue) > 0) {
					oddCharsCount--;
					bitVector ^= charBitValue;
				} else {
					oddCharsCount++;
					bitVector |= charBitValue;
				}
				trimmedLength++;
			}
		}
		
		if (trimmedLength % 2 == 0) {
			return oddCharsCount == 0;
		} else {
			return oddCharsCount == 1;
		}		
	}
	
	public static void testIsPalindromePermutation() {
		String inputStr = "tact coa";
//		String inputStr = "madam I m adam";
		assertEquals(true, isPalindromePermutation(inputStr));
	}

	
// Matrix rotation	
	private static void initMatrix(int[][] matrix, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = (int) (Math.random() * 100);
			}
		}
	}
	private static void printArray(int[][] matrix, int row, int col) {
		System.out.println("{");
		for (int i = 0; i < row; i++) {
			System.out.print("   ");
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.print("}");
	}
	
	public static void testMatrixRotation() {
		int rows = 2, cols = 2;
		int[][] matrix = new int[rows][cols];
		initMatrix(matrix, rows, cols);
		rotateMatrix(matrix, rows, cols);
		printArray(matrix, rows, cols);
	}
	
	private static void rotateMatrix(int[][] matrix, int row, int col) {
		int[][] retMatrix = new int[row][col];
		for (int i = 0;  i < col; i++) {
			for (int j = 0; j < row; j++) {
				retMatrix[i][j] = matrix[row - j - 1][i];
			}
		}
		printArray(retMatrix, row, col);
	}
	
//	Implement a method to perform basic string compression using the counts 
//	of repeated characters. For example, the string aabcccccaaa would become 
//	a2blc5a3. If the "compressed" string would not become smaller than the orig­inal 
//	string, your method should return the original string.
	
	private static String compressString(String inputStr) {
		Integer compressedLength = computeCompressLength(inputStr);
		if (compressedLength >= inputStr.length()) {
			return inputStr;
		}
		StringBuilder outputStr = new StringBuilder(compressedLength);
		int counter = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			Character currentChar = inputStr.charAt(i);
			counter++;
			if ((i + 1 >= inputStr.length())  || 
					currentChar != inputStr.charAt(i + 1)) {
				outputStr.append(currentChar).append(Integer.toString(counter));
				counter = 0;
			}
		}
		return outputStr.toString();
	}
	private static Integer computeCompressLength(String inputStr) {
		int counter = 0;
		int compressedLength = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			Character currentChar = inputStr.charAt(i);
			counter++;
			if ((i + 1 >= inputStr.length())  || 
					currentChar != inputStr.charAt(i + 1)) {
				compressedLength += 1 + String.valueOf(counter).length();
				counter = 0;
			}
		}
		return compressedLength;
	}
	
	public static void testCompression() {
//		String inputStr = "aabcccccaaa";
		String inputStr = "a2b1c5a3";
		String expectedOutput = "a2b1c5a3";
		String actualOutput = compressString(inputStr);
		System.out.println("Output = " + actualOutput);
		assertEquals(expectedOutput, actualOutput);
	}

//	class Solution {
//		public List<Integer> findDuplicates(int[] nums) {
//			int[] x=new int[nums.length+1];
//			List<Integer> rs=new ArrayList<Integer>();
//			for(int i=0;i<nums.length;i++){
//				x[nums[i]]++;
//			}
//			for(int j=0;j<x.length;j++){
//				if(x[j]==2) rs.add(j);
//			}return  rs;
//		}
////	}
//int [] dups = new int [nums.length + 1];
//
//	List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++){
//		dups[nums[i]]++;
//	}
//        System.out.println("Dups = " + Arrays.toString(dups));
//        for (int i = 0; i < dups.length; i++){
//		if (dups[i] >= 2) {
//			list.add(i);
//		}
//	}

	public static void testMissingMininumInt() {
		int[] nums = new int[] { 3, 4, -1 , 1};
//		int[] nums1 = new int[] {-10,-3,-100,-1000,-239,1};
//		int[] nums1 = new int[] {1000, -1};
		int[] nums1 = new int[] {1, 1};
//		int[] nums1 = new int[] {1, 2, 0};
		System.out.println("Missing int = " + new ArraysStrings().findMissingInt(nums));
		System.out.println("Missing int = " + new ArraysStrings().findMissingInt(nums1));
	}


	int findMissingIntFailed(int[] nums) {
		if (nums.length == 0) return 1;
		if (nums.length == 1) return nums[0] == 1 ? 2 : 1;
		int start = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0) {
				Utils.swap(nums, i, start);
				start++;
			}
		}
//		start--;
		int positiveStart = start;
		for (int i = nums.length - 1; i >= positiveStart; i--) {
			if (nums[i] + start - 1 != i) {
				int to = nums[i] >= nums.length ? nums.length - 1 : nums[i];
				if (to != i) {
					Utils.swap(nums, to, i);
					start++;
				}
			}
		}
		if (nums.length - 1 == positiveStart) return nums[positiveStart] == 1 ? 2 : 1;
		for (int i = positiveStart; i < nums.length; i++) {
			if (i == 0 && nums[i] == 1 && i < nums.length - 1 && nums[i + 1] != 2) {
				return 2;
			} else {
				if (nums[0] == 0 || nums[0] == 1) {

				} else if (nums[i] != i) {
					return i;
				}
			}
		}
		return nums[nums.length - 1] + 1;
	}



	int findMissingInt(int[] A) {
		int n = A.length;
		int out_of_bound = n + 1;
		for (int i = 0; i < n; ++i)
			if (A[i] <= 0)
				A[i] = out_of_bound;

		/* second iteration: construct a hash map. map<int, int>, first argument is index
		 * second argument: if positive, it exist, else, it doesn't. e.g. A[0] = 4,
		 * A[0] (i.e. 1) exist */
		for (int i = 0; i < n; ++i) {
			int abs_i = Math.abs(A[i]);
			if (abs_i <= n)
				A[abs_i-1] = -Math.abs(A[abs_i-1]);
		}

		/* third iteration: check the first positive value in A[] */
		for (int i = 0; i < n; ++i) {
			if (A[i] > 0)
				return i + 1;
		}
		return n + 1;
	}
	public static  void testMissingPositive() {
//		int[] nums = new int[] { 3, 4, -1, 1 };
		int[] nums = new int[] { 1, 2, 0 };
		System.out.println("Missing Positive int = " + new ArraysStrings().firstMissingPositive(nums));
	}

	public int firstMissingPositive(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			while(nums[i] - 1 < nums.length && nums[i] - 1 >= 0 && nums[nums[i] - 1] != nums[i]) {
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return  i + 1;
			}
		}

		return nums.length + 1;
	}

//	Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

//			Note:
//	You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements
// from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

	public static void testMergeArray () {
		int [] nums1 = new int[] { 1, 3, 5, 0, 0, 0};
		int m = 3;
		int [] nums2 = new int[] { 2, 4, 6};
		int n = 3;
		new ArraysStrings().merge(nums1, m, nums2, n);
	}
	public void merge(int[] nums1, int m, int[] nums2, int n) {
	    int end = m  + n - 1;
		int i = m - 1;
		int j = n - 1;

		while (i >= 0 && j >= 0) {
			if (nums1[i] >= nums2[j]) {
				nums1[end--] = nums1[i];
				i--;
			} else {
				nums1[end--] = nums2[j];
				j--;
			}
		}
		System.out.println("Merged Array = " + Arrays.toString(nums1));
	}
//    https://leetcode.com/problems/remove-duplicate-letters/description/
//    Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and
//    only once. You must make sure your result is the smallest in lexicographical order among all possible results.
//    Example:
//    Given "bcabc"
//    Return "abc"
//
//    Given "cbacdcbc"
//    Return "acdb"
    public String removeDuplicateLetters(String s) {
        int[] charMap = new int [128];
        Arrays.fill(charMap, 0);
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            charMap[index]++;
            minIndex = Math.min(minIndex, index);
        }
        StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
		    char ch = s.charAt(i);
			int index = ch - 'a';

			if (charMap[index] == 1) {
                stringBuilder.append(ch);
            } else if (charMap[index] > 1) {
                if (minIndex >= index) {
                    stringBuilder.append(ch);
                    charMap[index] = 0;
                    minIndex = index + 1;
                } else if (minIndex < index) {
                    if (i + 1 < s.length()) {
                        int nextIndex = s.charAt(i + 1) - 'a';
                        if (index < nextIndex) {
                            stringBuilder.append(ch);
                            charMap[index] = 0;
                        }
                    }
                }
                charMap[index]--;
            }
		}
        return stringBuilder.toString();
    }
    public static void testRemoveDuplicatedLetters() {
	    System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicateLetters("cbacdcbc"));
	    System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicateLetters("bcabc"));
//	    System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicateLetters("cabc"));
//	    System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicateLetters("ccacbaba"));

    }

//    345. Reverse Vowels of a String
//			DescriptionHintsSubmissionsDiscussSolution
//	Write a function that takes a string as input and reverse only the vowels of a string.
//
//			Example 1:
//	Given s = "hello", return "holle".
//
//	Example 2:
//	Given s = "leetcode", return "leotcede".
//"heilluo" -> "houllie"
//	Note:
//	The vowels does not include the letter "y".

	public static void testReverseVowels() {
		String s = "aA";
		System.out.println("Reversed vowels = " + new ArraysStrings().reverseVowels(s));
	}

	public String reverseVowels(String s) {

		Map<Character, Boolean> vowelsMap = new HashMap<>();
		vowelsMap.put('a', true);
		vowelsMap.put('e', true);
		vowelsMap.put('i', true);
		vowelsMap.put('o', true);
		vowelsMap.put('u', true);
		vowelsMap.put('A', true);
		vowelsMap.put('E', true);
		vowelsMap.put('I', true);
		vowelsMap.put('O', true);
		vowelsMap.put('U', true);
		char [] charArray = s.toCharArray();
		int i = 0, j = s.length() - 1;
		while (i < j) {
			while(i < j && !vowelsMap.containsKey(charArray[i])) {
				i++;
			}
			while(i < j && !vowelsMap.containsKey(charArray[j])) {
				j--;
			}
			if (charArray[i] != charArray[j]) {
				char tmp = charArray[i];
				charArray[i] = charArray[j];
				charArray[j] = tmp;
			}
			i++;
			j--;
		}
		return String.valueOf(charArray);
	}

//	316. Remove Duplicate Letters
//			DescriptionHintsSubmissionsDiscussSolution
//	Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
//
//	Example:
//	Given "bcabc"
//	Return "abc"
//
//	Given "cbacdcbc"
//	Return "acdb"
	public static void testRemoveDuplicateLetters() {
		String s = "bcabc";
//		System.out.println("Reversed vowels = " + new ArraysStrings().removeDuplicates(s));
		System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicates("cbacdcbc"));
//		System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicates("bcabc"));
//	    System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicates("cabc"));
//	    System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicates("ccacbaba"));
//	    System.out.println("Removed duplicate letters = " + new ArraysStrings().removeDuplicates("baa"));
	}

	public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        char[] charArray = s.toCharArray();
        boolean[] visited = new boolean[26];
        for (char ch: charArray) {
            count[ch - 'a']++;
        }
        for (char ch: charArray) {
            count[ch - 'a']--;
            if (visited[ch - 'a']) {
                continue;
            }

            while(!stack.isEmpty() && stack.peek() > ch && count[stack.peek() - 'a'] > 0) {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(ch);
            visited[ch - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char ch: stack) {
            sb.append(ch);
        }
        return sb.toString();
	}
//
//    class Solution {
//        public static void main(String[] args) {
//            ArrayList<String> strings = new ArrayList<String>();
//            strings.add("Hello, World!");
//            strings.add("Welcome to CoderPad.");
//            strings.add("This pad is running Java 8.");
//
//            for (String string : strings) {
//                System.out.println(string);
//            }
//            String [] words = new String[] {"abcd", "abced", "abd","abc", "ababdes"}; //"ab"
//            String longestPrefix = findLongestPrefix(words);
//            System.out.println("longest common prefix: " + longestPrefix);
//        }
//
//        private static String findLongestPrefix(String[] words) {
//            if (words.length == 0) return "";
//            if (words.length == 1) return words[0];
//            String word1 = words[0];
//            String word2 = words[1];
//            String prefix = "";
//            int i = 0;
//            // while(word1.charAt(i) == word2.charAt(i)) {
//            //   prefix = prefix + word1.charAt(i);
//            //   i++;
//            // }
//            int j = prefix.length()
//            if (!prefix.isEmpty()) {
//                for (i = 0; i < words.length; i++){
//                    if(!words[i].startsWith(prefix)) {
//                        prefix = prefix.substring(0, length - j);
//                        j--;
//                    }
//                }
//            }
//
//            return prefix;
//        }
//    }
//    268. Missing Number
//    DescriptionHintsSubmissionsDiscussSolution
//    Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
//            Example 1
//
//    Input: [3,0,1]
//    Output: 2
//    Example 2
//
//    Input: [9,6,4,2,3,5,7,0,1]
//    Output: 8
//
//    Note:
//    Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
    public static void testMissingNumber() {
//	    int[] nums = new int[] { 9,6,4,2,3,5,7,0,1 };
//	    int[] nums = new int[] { 3,0,1 };
	    int[] nums = new int[] { 2 };
	    System.out.println("Missing number = " + new ArraysStrings().missingNumber(nums));
	    System.out.println("Missing number = " + new ArraysStrings().missingNumberGauss(nums));
	    System.out.println("Missing number = " + new ArraysStrings().missingNumberXOR(nums));
    }

    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums[nums.length - 1] + 1;
    }


    public int missingNumberGauss(int[] nums) {
//        int expectedSum = nums.length * (nums.length + 1) / 2;
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//        }
//        return expectedSum - sum;
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
    public int missingNumberXOR(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


//    Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
//
//    For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

    public static void testMissingRanges() {
//	    int[] nums = new int[] {0, 1, 3, 50, 75};
//	    int lower = 0, upper = 99;
	    int[] nums = new int[] { 2147483647};
	    int lower = 0, upper = 2147483647;
	    List<String> ranges = new ArraysStrings().findMissingRanges(nums, lower, upper);
	    System.out.println("Missing ranges = " + Arrays.toString(ranges.toArray()));
    }
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();
        int lb = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int ub = (i == nums.length) ? upper + 1 : nums[i];
            if (ub - lb >= 2) {
                ranges.add(addRange(lb + 1, ub - 1));
            }
            lb = ub;
        }
        return ranges;
    }
    private String addRange(int from, int to) {
	    return (from == to) ? String.valueOf(to) : String.valueOf(from) + "->" + String.valueOf(to);
    }

//    260. Single Number III
//	Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
//	For example:
//	Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
//	Note:
//	The order of the result is not important. So in the above example, [5, 3] is also correct.
//	Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
	public static void testSingleNumber() {
		int[] nums = new int[] { 1, 2, 1, 3, 2, 5 };

		int[] singleNums = new ArraysStrings().singleNumber(nums);
		System.out.println("Single numbers = " + Arrays.toString(singleNums));
		int[] nums1 = new int[] { 0, 0, 1, 2 };
		int[] singleNumBits = new ArraysStrings().singleNumberBits(nums1);
		System.out.println("Single numbers = " + Arrays.toString(singleNumBits));
	}
	public int[] singleNumber(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				set.remove(nums[i]);
			} else {
				set.add(nums[i]);
			}
		}
		int[]retArry = new int[set.size()];
		for (int i = 0; i < set.size(); i++) {
			retArry[i] = (int)set.toArray()[i];
		}
		return retArry;
	}
	public int[] singleNumberBits(int[] nums) {
		int firstSingle = 0;
		int secondSingle = 0;
		for (int i = 0; i < nums.length; i++) {
			secondSingle |= firstSingle & nums[i];
			firstSingle ^= nums[i];
		}

		return new int[] { secondSingle, firstSingle ^ secondSingle };
	}
//	38. Count and Say
//    The count-and-say sequence is the sequence of integers with the first five terms as following:
//            1.     1
//            2.     11
//            3.     21
//            4.     1211
//            5.     111221
//            1 is read off as "one 1" or 11.
//            11 is read off as "two 1s" or 21.
//            21 is read off as "one 2, then one 1" or 1211.
//    Given an integer n, generate the nth term of the count-and-say sequence.
    public static void testCountAndSay() {
	    System.out.println(" Count and say = " + new ArraysStrings().countAndSay(3));
    }
    public String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            String prev = result;
            result = "";
            char temp = prev.charAt(0);
            int count = 1;
            for (int j = 1; j < prev.length(); j++) {
                if(prev.charAt(j) == temp) {
                    count++;
                } else {
                    result = result + count + temp;
                    temp = prev.charAt(j);
                    count = 1;
                }
            }
            result = result + count + temp;
        }
        return result;
    }

    public static void testMaxProfit() {
	    int[] prices = new int[] { 1,4,5,4,2 };
	    int profit = new ArraysStrings().maxProfit(prices);
	    System.out.println("Max profit = " + profit);
    }
    public int maxProfit(int[] prices) {
	    if (prices.length < 2) return 0;
        int minProfit = prices[0];
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int currentProfit = prices[i] - minProfit;
            maxProfit = Math.max(maxProfit, currentProfit);
            minProfit = Math.min(minProfit, prices[i]);
        }
        return maxProfit;
    }

    public static void testSubsets() {
        char[] input = new char[] { 'a', 'b', 'c' };
        ArrayList<ArrayList<Character>>  allSubsets = new ArraysStrings().subsets(input);
        System.out.println("Subsets = " + allSubsets.toString());

        int[] inputInts = new int[] { 1, 2, 3 };
        List<List<Integer>>  allIntSubsets = new ArraysStrings().subsets(inputInts);
        System.out.println("Subsets = " + allIntSubsets.toString());
	}

//    public List<List<Integer>> subsets(int[] nums) {
    public ArrayList<ArrayList<Character>>  subsets(char[] input) {
        return getAllSubsets(input, 0);
    }
    public ArrayList<ArrayList<Character>> getAllSubsets(char[] input, int index) {
	    ArrayList<ArrayList<Character>> allSubsets;
	    if (input.length == index) {
	        allSubsets = new ArrayList<>();
	        allSubsets.add(new ArrayList<>());
        } else {
	        allSubsets = getAllSubsets(input, index + 1);
	        Character ch = input[index];
	        ArrayList<ArrayList<Character>> moreSubsets = new ArrayList<>();
	        for(ArrayList list : allSubsets) {
	            ArrayList<Character> newList = new ArrayList<>();
	            newList.addAll(list);
	            newList.add(ch);
	            moreSubsets.add(newList);
            }
            allSubsets.addAll(moreSubsets);
        }
	    return allSubsets;
    }

    public List<List<Integer>>  subsets(int[] input) {
        return getAllSubsets(input, 0);
    }
    public List<List<Integer>> getAllSubsets(int[] input, int index) {
        List<List<Integer>> allSubsets;
        if (input.length == index) {
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<>());
        } else {
            allSubsets = getAllSubsets(input, index + 1);
            Integer item = input[index];
            ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<>();
            for(List list : allSubsets) {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.addAll(list);
                newList.add(item);
                moreSubsets.add(newList);
            }
            allSubsets.addAll(moreSubsets);
        }
        return allSubsets;
    }

//    153. Find Minimum in Rotated Sorted Array
//    DescriptionHintsSubmissionsDiscussSolution
//    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//            (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//    Find the minimum element.
//    You may assume no duplicate exists in the array.
    public static void testFindMinInRotatedArray() {
        int[] nums = new int[] {4, 5, 6, 7, 8, 0, 1, 2, 3};
//        int[] nums = new int[] { 0, 1, 2, 4, 5, 6, 7 };
        System.out.println("Min in sorted array = " + new ArraysStrings().findMin(nums));
    }

    public int findMin(int[] nums) {
	    int left = 0, right = nums.length -1;
	    while (left < right && nums[left] >= nums[right]) {
	        int middle = (left + right) / 2;
	        if (nums[middle] > nums[right]) {
	            left = middle + 1;
            } else {
	            right = middle;
            }
        }
        return nums[left];
    }
    public int findMin(int[] nums, int left, int right) {
	    if (left > right) return -1;
	    int middle = left + (right - left) / 2;
	    if (nums[middle] > nums[right]) {
	        return findMin(nums, middle, right);
        } else {
            return findMin(nums, left, middle - 1);

        }
    }

//    17. Letter Combinations of a Phone Number
//	DescriptionHintsSubmissionsDiscussSolution
//	Given a digit string, return all possible letter combinations that the number could represent.
//	A mapping of digit to letters (just like on the telephone buttons) is given below.
//	Input:Digit string "23"
//	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

	public static void testLetterCombinations() {
		System.out.println("Test letter combinations = " + new ArraysStrings().letterCombinations("23"));
	}
	private static final String[] t9Chars = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> letterCombinations(String digits) {
		List<String> results = new ArrayList<>();
		combinations("", digits, 0, results);
		return results;
	}
	private void combinations(String prefix, String digits, int index, List<String> results) {
		if (index == digits.length()) {
			results.add(prefix);
			return;
		}
		String digitLetters = t9Chars[digits.charAt(index) - '0'];
		for (int i = 0; i < digitLetters.length(); i++) {
			combinations(prefix + digitLetters.charAt(i), digits, index + 1, results);
		}
	}

//	Intersection of Two Arrays II
//	Given two arrays, write a function to compute their intersection.
//
//			Example:
//	Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
//
//	Note:
//	Each element in the result should appear as many times as it shows in both arrays.
//	The result can be in any order.
//	Follow up:
//	What if the given array is already sorted? How would you optimize your algorithm?
//	What if nums1's size is small compared to nums2's size? Which algorithm is better?
//	What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
	public static void testIntersectionArray() {
//		int[] nums1 = new int[] {1,2,2,1};
//		int[] nums2 = new int[] {3,2,2};
		int[] nums1 = new int[] {3,1,2};
		int[] nums2 = new int[] {1,3};
		int[] array = new ArraysStrings().intersect(nums1, nums2);
		System.out.println("Intersection array = " + Arrays.toString(array));
	}
	public int[] intersect(int[] nums1, int[] nums2) {
		if(nums1.length < nums2.length) {
			return intersect(nums2, nums1);
		}
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int p = 0, q = 0;
		ArrayList<Integer> list = new ArrayList<>();
		while(p < nums1.length && q < nums2.length) {
			if (nums1[p] < nums2[q]) {
				p++;
			} else {
				if (nums1[p] > nums2[q]) {
					q++;
				} else {
					list.add(nums1[p]);
					p++;
					q++;
				}
			}
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size() ; i++) {
			result[i] = list.get(i);
		}
		return result;
	}
}

