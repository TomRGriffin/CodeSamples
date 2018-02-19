package Samples;

import java.util.HashMap;
import java.util.Map;

public class Recursion {
    private static Map<Integer, Integer> hashMap = new HashMap<>();
    public static void fibonacciLoop(int number) {
        int num = 0, num2 = 1, fib = 0;
        for (int i = 0; i < number ; i++) {
            System.out.print(num + ", ");
            fib = num + num2;
            num = num2;
            num2 = fib;
        }
    }

    public static void fibonacci(int number) {
        for (int i = 0; i < number; i++) {
            System.out.print(fibonacciRecursive(i) + ", ");
        }
    }

    private static int fibonacciRecursive(int number) {
        if (hashMap.containsKey(number)) {
            return hashMap.get(number);
        }
        if (number == 0) {
            hashMap.put(number, number);
            return number;
        } else if (number == 1){
            hashMap.put(number, number);
            return number;
        } else {
            int value1 = fibonacciRecursive(number - 1);
            int value2 = fibonacciRecursive(number - 2);
            int sum = value1 + value2;
            hashMap.put(number, sum);
            return sum;
        }

    }
}
