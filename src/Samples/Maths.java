package Samples;

import static org.junit.Assert.assertEquals;

public class Maths {
	public static int fibonacci(int number) {
		if (number == 0) {
			return 0;
		} else if (number == 1) {
			return 1;
		} else {
			return  fibonacci(number - 1) + fibonacci(number - 2);
		}
	}
	
	public static void testFibonacci() {
		int number = 7;
		for (int i = 0; i <= number; i++) {
			System.out.print(fibonacci(i) + " ");
	    }		
	}
	
	public static int factorial(int number) {
		Utils.simulateTimeComplexity();
		if (number == 0) {
			return 0;
		} else if (number == 1) {
			return 1;
		} else {
			return number * factorial(number - 1);
		}
	}
	
	public static void testFactorial() {
		int number = 9;
		long startTime = System.currentTimeMillis();
		System.out.println("Factorial(" + number + ") = " + factorial(number));
		long endTime = System.currentTimeMillis();
		long time = ((endTime - startTime)/ Utils.TIME_INTERVAL);
		long bigOTime = (long) (Math.pow(2, time));
		System.out.println("Big O = " + bigOTime);
	}
	
	private static double sqrt(double number) {
		double err = 1e-10, t = number, rem  = 0.0;
		int n = 0;
		while (Math.abs(t - rem) > (err * t)) {
			System.out.println("Math.abs(t - rem) = " + Math.abs(t - rem));
			rem = number/t;
			t = (rem + t) / 2.0;
			System.out.println("t = " + t);
			n++;
		}
		System.out.println("Iterations = " + n);
		return t;
	}
	public static void testSqrt() {
		int number = 64;
		System.out.println("Sqrt(" + number + ") = " + sqrt(number));
	}
	
	private static boolean isPrime(int number) {
		for (int i = 2; (i * i) <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void testIsPrime() {
		int number = 23;
		System.out.println(number + " is prime = " + isPrime(number));
		number = 16;
		System.out.println(number + " is prime = " + isPrime(number));
		number = 97;
		System.out.println(number + " is prime = " + isPrime(number));
	}

	public static void testMaxSubArray() {
		int [] nums = new int[] { -2,1,-3,4,-1,2,1,-5,4 };
		System.out.println("Max sum arrays = " + new Maths().maxSubArray(nums));
	}

	public int maxSubArray(int[] nums) {
		int[] sumArray = new int[nums.length];
		sumArray[0] = nums[0];
		int maxSum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sumArray[i] = nums[i] +
					(sumArray[i - 1] > 0 ? sumArray[i - 1] : 0);
			maxSum = Math.max(maxSum, sumArray[i]);
		}
		return maxSum;
	}
}
