package Samples;

public class AppleLeetCode {
    public static void testMaxSquare() {
        int[][] matrix = new int[][] {
                { 1, 1, 1, 0, 0},
                { 1, 1, 1, 1, 0},
                { 1, 1, 1, 1, 0},
                { 1, 0, 0, 0, 1},
        };
        int maxRectangle = new AppleLeetCode().findMaxSquare(matrix);
        System.out.println("Max Square = " + maxRectangle);
    }

    public int findMaxSquare(int [][] matrix) {
        int maxSum = Integer.MIN_VALUE;
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int [][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxSum = Math.max(maxSum, dp[i][j]);
                }
            }
        }
        Utils.printMatrix(dp);
        Utils.printMatrix(matrix);

        return maxSum * maxSum;
    }
    private boolean isValidSquare(int [][] matrix, int i, int j) {
        return matrix[i][j] != 0 &&
                matrix[i + 1][j] != 0 &&
                matrix[i][j + 1] != 0 &&
                matrix[i + 1][j + 1] != 0;
    }
}
