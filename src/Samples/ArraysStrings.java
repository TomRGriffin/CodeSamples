package Samples;
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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public static void testUniqueString() {
		String inputString = "cdefghijklm";
		boolean UNIQUE_STRING = true;
		long startTime = System.currentTimeMillis();
		assertEquals(UNIQUE_STRING, isUniqueStringBruteForce(inputString));
		long endTime = System.currentTimeMillis();
		System.out.println("Brute force Big O = (N*(N-1)/2)" + ((endTime - startTime)/ 1000));
		
		startTime = System.currentTimeMillis();
		assertEquals(UNIQUE_STRING, isUniqueString(inputString));
		endTime = System.currentTimeMillis();
		System.out.println("Big O = " + ((endTime - startTime)/ 1000));
		
		// TODO: should isUniqueString check for empty string
		inputString = "";
		assertEquals(true, isUniqueString(inputString));
		inputString = "-*(@)";
		assertEquals(true, isUniqueString(inputString));
	}
}
