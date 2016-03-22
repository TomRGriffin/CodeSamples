package Samples;

public class BitManipulation {
	// Question: Do addition of two integers without using mathematical operators (+, -, \, *)
	private static int sum(int x, int y) {
		if (y == 0) {
			return x;
		}
		int carry = x & y;
		String binaryCarry = Integer.toBinaryString(carry);
		x = x ^ y;
		String binaryX = Integer.toBinaryString(x);
		y = carry << 1;
		String binaryY = Integer.toBinaryString(y);
		return sum (x, y);
	}
	
	public static void testSum() {
		int x = 2, y = 2;
		System.out.println("Sum of " + x + ", " + y + " = " + sum (x, y));
	}
}
