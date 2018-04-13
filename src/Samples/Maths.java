package Samples;

import java.util.*;

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

	public static void testUglyNumber(){
		System.out.println("Is Ugly Number = " + new Maths().isUgly(6));
	}
	public boolean isUgly(int num) {
		for (int i=2; i<6 && num>0; i++)
			while (num % i == 0)
				num /= i;
		return num == 1;
	}
	public static void testHappyNumber(){
		System.out.println("Is 6 Happy Number = " + new Maths().isHappy(6));
		System.out.println("Is 9 Happy Number = " + new Maths().isHappy(9));
		System.out.println("Is 7 Happy Number = " + new Maths().isHappy(7));
		System.out.println("Is 19 Happy Number = " + new Maths().isHappy(19));
//		System.out.println("Is 169 Happy Number = " + new Maths().isHappy(169));
	}
	public boolean isHappy(int num) {
        if (num == 1) return true;
        if (num < 7) return false;
		int sum = 0;
		while(num > 0) {
			int digit = num % 10;
			sum += digit * digit;
			num /= 10;
			if (sum == 4 && num == 0) {
				return false;
			} else if (sum == 1 && num == 0) {
				return true;
			} else if (num == 0){
				num = sum;
				sum = 0;
			}
		}
		return false;
	}

//	Two Sum
//	Difficulty:Easy
//
//	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//	You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//			Example:
//	Given nums = [2, 7, 11, 15], target = 9,
//
//	Because nums[0] + nums[1] = 2 + 7 = 9,
//			return [0, 1].
//	150,24,79,50,88,345,3]
//			200
	public static void testTwoSum() {
		int[] nums = new int[] {150,24,79,50,88,345,3};
		int[] indices = new Maths().twoSum(nums, 200);
		System.out.println("Sum pair = " + Arrays.toString(indices));
	}
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length ; i++) {
			if (map.containsKey(target - nums[i])) {
				int index = map.get(target - nums[i]);
				return new int [] {index, i};
			} else {
				map.put(nums[i], i);
			}
		}
		return new int[0];
	}

	public static void testLastRemaining() {
//		System.out.println("Last remaining = " + new Maths().lastRemaining(4));
//		System.out.println("Last remaining = " + new Maths().lastRemaining(6));
//		System.out.println("Last remaining = " + new Maths().lastRemaining(9));
		System.out.println("Last remaining = " + new Maths().lastRemaining(10));
	}
	class LinkedNode {
		int val;
		LinkedNode(int val) {
			this.val = val;
		}
		LinkedNode next;
	}
	public int lastRemaining(int n) {
		int[] nums = new int[n];
		int[] values = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = i + 1;
			values[i] = i + 1;
		}

		boolean oddLoop = true;
		int bound = values.length;
		while (bound > 1) {
			int i = 0;
			for (int j = 1; j < bound; j += oddLoop ? 2 : 1) {
				values[i++] = values[j];
			}
			oddLoop = !oddLoop;
			bound = i;
//			if (bound > 1 && (bound % 2 == 0)) bound++;
		}
		return values[0];
//		int[] dp = new int[n];
//		for (int i = 1; i <= n; i++) {
//			dp[i - 1] = i;
//		}
//		boolean oddLoop = true;
//		while(n > 1) {
//			int j = 0;
//			for (int i = 1; i < dp.length; i += 2) {
//				if (oddLoop) {
//					dp[j] = Math.max(dp[i - 1], dp[i]);
//				} else {
//					dp[j] = Math.min(dp[i - 1], dp[i]);
//				}
//				j++;
//			}
//			oddLoop = !oddLoop;
//			n /= 2;
//			if (n > 1 && (n % 2 != 0)) n++;
//		}
//		return dp[0];

//		boolean left = true;
//		int remaining = n;
//		int step = 1;
//		int head = 1;
//		while (remaining > 1) {
//			if (left || remaining % 2 ==1) {
//				head = head + step;
//			}
//			remaining = remaining / 2;
//			step = step * 2;
//			left = !left;
//		}
//		return head.val;
//		return  0;
	}
}

