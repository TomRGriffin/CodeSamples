package Samples;

import java.util.Arrays;

public class GeeksForGeeks {

//    A Product Array Puzzle
//    Given an array arr[] of n integers, construct a Product Array prod[] (of same size) such that prod[i] is equal to the product of all the elements of arr[] except arr[i]. Solve it without division operator and in O(n).
//
//    Example:
//    arr[] = {10, 3, 5, 6, 2}
//    prod[] = {180, 600, 360, 300, 900}
    public static void testProductArray(){
        int [] arr = new int[]{10, 3, 5, 6, 2};
        System.out.println("ProductArray = " + Arrays.toString(new GeeksForGeeks().productArray(arr)));
    }

    private int[] productArrayBruteForce(int[] arr) {
        int[] prodArr = new int[arr.length];
        int product = 1;
        for (int i : arr) {
            product *= i;
        }
        for (int i = 0; i < arr.length; i++) {
            prodArr[i] = product / arr[i];
        }
        return prodArr;
    }

    private int[] productArray(int[] arr) {
        int[] prodArr = new int[arr.length];
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        int i, j;

        left[0] = 1; 
        right[arr.length - 1] = 1;

        for (i = 1; i < arr.length; i++) {
            left[i] = arr[i - 1] * left[i - 1];
        }

        for (j = arr.length - 2; j >= 0; j--) {
            right[j] = arr[j + 1] * right[j + 1];
        }

        for (int k = 0; k < arr.length ; k++) {
            prodArr[i] = left[i] * right[i];
        }

        return prodArr;
    }
}
