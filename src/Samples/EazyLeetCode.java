package Samples;

import java.util.Stack;

public class EazyLeetCode {
//    The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
//    Given two integers x and y, calculate the Hamming distance.
//            Note:
//            0 ≤ x, y < 231.
//
//    Example:
//    Input: x = 1, y = 4
//    Output: 2
//    Explanation:
//            1   (0 0 0 1)
//            4   (0 1 0 0)
//            ↑   ↑
//    The above arrows point to positions where the corresponding bits are different.

    public static int hammingWeight(int n) {
        long unsignedValue = n & 0xffffffffl;
        long count = 0;
        do {
            count += n & 1;
            n = n >> 1;
        } while (n != 0);

        return (int)count;
    }

    static int bitCount (int value) {
        int count = 0;
        while (value > 0) {           // until all bits are zero
         // add lower bit
            count += (value & 1);
            value >>= 1;              // shift bits, removing lower bit
        }
        return count;
    }

    public static int hammingDistance(int x, int y) {
        int value = x ^ y;
        return bitCount(value);
    }

    public static void testHammingDistance() {
//        System.out.println("Hamming distance = " + hammingDistance(1, 4));
//        System.out.println("Hamming weight = " + hammingWeight(  2147483648));
    }

//    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//    The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
    public static void testValidParentheses() {

        System.out.print("IsValidParentheses = " + isValid("()[{}])"));
    }

    public static boolean isValid(String s) {
        Stack<Character> bracketsStack = new Stack<>();
        for (char ch: s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                bracketsStack.push(ch);
            } else {
                if (!bracketsStack.isEmpty()) {
                    Character last = bracketsStack.peek();
                    if ((ch == ')' && last != '(') || (ch == '}' && last != '{') || (ch == ']' && last != '[')) {
                        return false;
                    } else {
                        bracketsStack.pop();
                    }
                } else {
                    return false;
                }
            }
        }
        return bracketsStack.isEmpty();
    }

//    An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
//    Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
//    To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
//    At the end, return the modified image.
//    Example 1:
//    Input:
//    image = [[1,1,1],[1,1,0],[1,0,1]]
//    sr = 1, sc = 1, newColor = 2
//    Output: [[2,2,2],[2,2,0],[2,0,1]]
//    Explanation:
//    From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
//    by a path of the same color as the starting pixel are colored with the new color.
//    Note the bottom corner is not colored 2, because it is not 4-directionally connected
//    to the starting pixel.
//    Note:
//
//    The length of image and image[0] will be in the range [1, 50].
//    The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
//    The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
    public static void testFloodFill() {
        int [][] image = new int[][] {{ 1,1,1 },{ 1,1,0 },{ 1,0,1 }};
        int sr = 1, sc = 1, newColor = 2;
        new EazyLeetCode().floodFill(image, sr, sc, newColor);
        for (int i = 0; i < image.length ; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j] + ", ");
            }
            System.out.println();
        }
    }
//
//    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
//        if (sr < 0 || sc < 0 || sr > image.length - 1 || sc > image[0].length - 1) {
//            return image;
//        }
//        int color = image[sr][sc];
//        if (color != newColor) {
//            image[sr][sc] = newColor;
//            image = floodFill(image, sr + 1, sc, newColor);
//            image = floodFill(image, sr - 1, sc, newColor);
//            image = floodFill(image, sr, sc + 1, newColor);
//            image = floodFill(image, sr, sc - 1, newColor);
//        }
//        return image;
//    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr == 2 && sc == 2) {
            System.out.println("Bottom corner");
        }
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }

}
