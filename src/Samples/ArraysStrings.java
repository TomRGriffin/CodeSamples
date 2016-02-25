package Samples;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
	
	
	
	private static void initMatrix(int[][] matrix, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = (int) (Math.random() * 100);
			}
		}
	`}
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
}

