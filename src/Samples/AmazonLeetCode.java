package Samples;

import java.util.Arrays;
import java.util.Stack;

public class AmazonLeetCode {
//    You are given an n x n 2D matrix representing an image.
//    Rotate the image by 90 degrees (clockwise).
//    Note:
//    You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
//
//    Example 1:
//    Given input matrix = [ [1,2,3],[4,5,6],[7,8,9] ],
//    rotate the input matrix in-place such that it becomes:
//            [ [7,4,1], [8,5,2], [9,6,3] ]
//    Example 2:
//    Given input matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
//    rotate the input matrix in-place such that it becomes:
//            [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11]]
    public static void testRotate() {
//        int[][] nums = new int [][] { {1,2,3}, {4,5,6}, {7,8,9} };
        int[][] nums = new int [][] { {5, 1, 9,11 }, { 2, 4, 8,10 }, { 13, 3, 6, 7 }, { 15,14,12,16 } };
        new AmazonLeetCode().rotate(nums);
    }

    public void rotate(int[][] matrix) {

        int cStart = 0, rStart = 0;
        int cEnd = matrix[0].length - 1, rEnd = matrix.length - 1;

        while (rEnd > rStart) {
            while (cEnd > cStart) {
                int temp = matrix[rEnd][cEnd];
                int rowIndex = rEnd - cEnd + rStart;
                matrix[rEnd][cEnd] = matrix[rowIndex][rEnd];
                matrix[rowIndex][rEnd] = matrix[rStart][rowIndex];
                matrix[rStart][rowIndex] = matrix[cEnd][cStart];
                matrix[cEnd][cStart] = temp;
                cEnd--;
            }
            rStart++;
            rEnd--;
            cEnd = rEnd;
            cStart++;
        }

        for (int[] aMatrix : matrix) {
            System.out.println("Rotated matrix = " + Arrays.toString(aMatrix));
        }
    }

//    746. Min Cost Climbing Stairs
//    DescriptionHintsSubmissionsDiscussSolution
//    On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
//    Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of
//    the floor, and you can either start from the step with index 0, or the step with index 1.
//    Example 1:
//    Input: cost = [10, 15, 20]
//    Output: 15
//    Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
//    Example 2:
//    Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
//    Output: 6
//    Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
    public static void testMinCost() {
        int [] cost = new int[] {15, 10, 12, 14, 20};
        System.out.println("Cost = " + new AmazonLeetCode().minCostClimbingStairs(cost));
    }

    public int minCostClimbingStairs(int[] cost) {
        int a = cost[0];
        int b = cost[1];
        int c = 0;
        for (int i = 2; i < cost.length; i++) {
            c = b;
            b = cost[i] + Math.min(a, b);
            a = c;
        }
        return Math.min(a, b);
    }

    public static void testMaxStack() {
//        ["MaxStack","push","push","popMax","push","push","top","push","push","push","pop","popMax","pop","push"]
//[[],[-95],[1],[],[-44],[16],[],[29],[65],[-18],[],[],[],[78]]
//        ["MaxStack","push","top","pop","push","push","popMax","popMax","push","popMax","push","push","pop","pop","push","popMax","push","push","push","push","peekMax"]
//[[],[-44],[],[],[63],[-44],[],[],[-35],[],[57],[-88],[],[],[-45],[],[-34],[2],[-71],[0],[]]
//        ["MaxStack","push","popMax","push","push","popMax","pop","push","push","peekMax","popMax","push","pop","push","push"]
//[[],[74],[],[89],[67],[],[],[61],[-77],[],[],[81],[],[-71],[32]]
        MaxStack maxStack = new MaxStack();
        maxStack.push(74);
        int max = maxStack.popMax();
        System.out.println("Max = " + max);
        maxStack.push(89);
        maxStack.push(67);
        max = maxStack.popMax();
        System.out.println("Max = " + max);
        int val = maxStack.pop();
        System.out.println("Val = " + val);
        maxStack.push(61);
        maxStack.push(-77);
        max = maxStack.peekMax();
        System.out.println("Max = " + max);
//        val = maxStack.pop();
//        System.out.println("Val = " + val);
//        maxStack.push(78);
//        [null,null,null,1,null,null,16,null,null,null,-18,65,29,null]
//
    }

    public static class MaxStack {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();
        /** initialize your data structure here. */
        public MaxStack() {

        }

        public void push(int x) {
            stack.push(x);
            if(maxStack.isEmpty()) {
                maxStack.push(x);
            } else {
                // maxStack.push(x);
                Stack<Integer> tempStack = new Stack<Integer>();
                while (!maxStack.isEmpty()) {
                    int max = maxStack.peek();
                    if(max <= x) {
                        // maxStack.push(max);
                        maxStack.push(x);
                        break;
                    } else {
                        tempStack.push(maxStack.pop());
                        if(maxStack.isEmpty()) {
                            maxStack.push(x);
                            break;
                        }
                    }

                }
                while(!tempStack.isEmpty()) {
                    maxStack.push(tempStack.pop());
                }
            }
        }

        public int pop() {
            if (stack.isEmpty()) return -1;
            int val = stack.pop();
            Stack<Integer> tempStack = new Stack<Integer>();
            while (!maxStack.isEmpty()) {
                int max = maxStack.pop();
                if(val != max) {
                    tempStack.push(max);
                } else {
                    break;
                }
            }
            while(!tempStack.isEmpty()) {
                maxStack.push(tempStack.pop());
            }
            // if(val == peekMax()) {
            //     maxStack.pop();
            // }
            return val;
        }

        public int top() {
            int x = stack.peek();
            return x;
        }

        public int peekMax() {
            if (maxStack.isEmpty()) return -1;
            return maxStack.peek();
        }

        public int popMax() {
            if (maxStack.isEmpty()) return -1;
            int max = maxStack.pop();
            Stack<Integer> tempStack = new Stack<Integer>();
            while (!stack.isEmpty()) {
                int val = stack.pop();
                if(val != max) {
                    tempStack.push(val);
                } else {
                    break;
                }
            }
            while(!tempStack.isEmpty()) {
                int val = tempStack.pop();
                stack.push(val);
//                if (val >= peekMax()) {
//                    maxStack.push(val);
//                }/

            }
            return max;
        }
    }

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */


}

