package Samples;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
}

