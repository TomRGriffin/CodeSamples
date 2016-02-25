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
}
