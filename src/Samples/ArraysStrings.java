package Samples;
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

}

