package Samples;

public class Matrix {

//    74. Search a 2D Matrix
//    Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//    Integers in each row are sorted from left to right.
//    The first integer of each row is greater than the last integer of the previous row.
//    For example,
//    Consider the following matrix:
//            [
//            [1,   3,  5,  7],
//            [10, 11, 16, 20],
//            [23, 30, 34, 50]
//            ]
//    Given target = 3, return true.
    public static void testSearchMatrix() {
//        int [][] matrix = new int[][]{ {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50} };
//        int [][] matrix = new int[][]{ {1,   4,  7, 11, 15}, {2,   5,  8, 12, 19}, {3,   6,  9, 16, 22},
//                {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30} };
        int[][] matrix = new int[][] { { 1, 1 }};
        System.out.println("Find target = " + new Matrix().searchMatrix(matrix, 2));
    }
    public boolean searchRowMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length ; i++) {
            if (matrix[i][0] < target && matrix[i][matrix[i].length - 1] > target) {
                int colIndex = findIndex(matrix[i], target);
                if (matrix[i][colIndex] == target) return true;
            }
        }
        return false;
    }
    private int findIndex(int[] rowArray, int target) {
        int start = 0, end = rowArray.length - 1;
        int mid = 0;
        while(start < end) {
            mid = start + ((end - start) / 2);
            if (rowArray[mid] <= target && rowArray[mid + 1] > target) {
                break;
            } else if (rowArray[mid] > target) {
                end--;
                mid = end;
            } else {
                start++;
                mid = start;
            }
        }
        return mid;
    }
//
//    240. Search a 2D Matrix II
//    Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//    Integers in each row are sorted in ascending from left to right.
//    Integers in each column are sorted in ascending from top to bottom.
//    For example,
//    Consider the following matrix:
//
//            [
//            [1,   4,  7, 11, 15],
//            [2,   5,  8, 12, 19],
//            [3,   6,  9, 16, 22],
//            [10, 13, 14, 17, 24],
//            [18, 21, 23, 26, 30]
//            ]
//    Given target = 5, return true.
//    Given target = 20, return false.

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int i = 0, j = matrix[0].length - 1;
        while(i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
    public boolean searchMatrixUber(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int rowEnd = matrix.length, colEnd = matrix[0].length;
        int row = 0, col = colEnd - 1;
        int midRow = rowEnd / 2, midCol = colEnd / 2;
        while(col >= 0) {
            if (matrix[0][col] > target) {
                col--;
            } else if (matrix[0][col] == target) {
                return true;
            } else {
                // search row
                if (findInColumn(matrix, target, col, 0, rowEnd)) return true;
                // search col
            }
        }
        return false;
    }

    private boolean findInColumn(int[][]matrix, int target, int col, int start,  int end) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][col] == target)  {
                return true;
            } else if (matrix[mid][col] < target)  {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
    private boolean findInRow(int[] rowArray, int target, int start, int end) {
        if (rowArray[start] == target) return true;
        while(start < end) {
            int mid = start + (end - start ) / 2;
            if(rowArray[mid] == target){
                return true;
            } else if(rowArray[mid] < target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
