package Samples;

import java.util.ArrayList;
import java.util.List;

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

	public static void testFactorCombinations() {
	    System.out.println("Factors = " + new Maths().getFactors(12));
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> factorsLists = new ArrayList<List<Integer>>();
        getFactors(factorsLists, new ArrayList<Integer>(), n , 2);
        return factorsLists;
    }
    public void getFactors(List<List<Integer>> result, List<Integer> list, int n, int start) {
	    if (n <= 1) {
	        if (list.size() > 1) {
	            result.add(new ArrayList<>(list));
            }
        }
        for (int i = start; i <= n; i++){
	        if (n % i == 0) {
	            list.add(i);
	            getFactors(result, list, n/i, i);
	            list.remove(list.size() - 1);
            }
        }
    }

    public static void testMyPow() {
	    System.out.println("Power = " + new Maths().myPow(2, 6));
    }
    public double myPow(double x, int n) {
        double result = 1;
        long pow = Math.abs((long)n);
        while(pow > 0) {
            if(pow % 2 == 1)  {
                result *= x;
            }
            pow /= 2;
            x *= x;
        }
        return n < 0 ?  1 / result : result;
    }

    public static void testRandomWithSameProb() {
		System.out.println("Probality " + new Maths().rand(1, 4));
//		System.out.println("Probality " + new Maths().rand(1, 4));
//		System.out.println("Probality " + new Maths().rand(1, 4));
//		System.out.println("Probality " + new Maths().rand(1, 4));

	}
	int rand(int lower, int higher) {
		return lower + (int) (Math.random() * (higher - lower + 1));
	}
}
