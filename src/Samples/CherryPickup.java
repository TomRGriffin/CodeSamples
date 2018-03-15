package Samples;
//
//In a N x N grid representing a field of cherries, each cell is one of three possible integers.
//
//        0 means the cell is empty, so you can pass through;
//        1 means the cell contains a cherry, that you can pick up and pass through;
//        -1 means the cell contains a thorn that blocks your way.
//        Your task is to collect maximum number of cherries possible by following the rules below:
//
//        Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
//        After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
//        When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
//        If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
//        Example 1:
//        Input: grid =
//        [[0, 1, -1],
//        [1, 0, -1],
//        [1, 1,  1]]
//        Output: 5
//        Explanation:
//        The player started at (0, 0) and went down, down, right right to reach (2, 2).
//        4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
//        Then, the player went left, up, up, left to return home, picking up one more cherry.
//        The total number of cherries picked up is 5, and this is the maximum possible.
//        Note:
//
//        grid is an N by N 2D array, with 1 <= N <= 50.
//        Each grid[i][j] is an integer in the set {-1, 0, 1}.
//        It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.

public class CherryPickup {
    public void testCherryPickup() {
        int [][] grid = {   {1, 1, -1},
                            {1, 1, -1},
                            {1, 1,  1}};
        System.out.println("[ ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(" [ ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + ", ");
            }
            System.out.print("] ");
            System.out.println();
        }
        System.out.println(" ] ");
        int cherriesPicked = cherryPickup(grid);
        System.out.println("Cherries piked = " + cherriesPicked);
    }
    public int cherryPickup(int[][] grid) {
        int cherriesPicked = 0;
        cherriesPicked += grid[0][0];
        grid[0][0] = 0;
        int col = 0;
        int row;
        for (row = 0; row < grid.length; row++) {
            int downValue = checkDownMove(grid, row, col);
            if (downValue != -1) {
                cherriesPicked += downValue;
            }
        }
        for (int c = 0; c < grid[0].length; c++) {
            int rightValue = checkRightMove(grid, row - 1, c);
            if (rightValue != -1) {
                cherriesPicked += rightValue;
            }
        }

        col = grid[0].length - 1;
        for (row = grid.length - 1; row >= 0; row--) {
            int upValue = checkUpMove(grid, row, col);
            if (upValue != -1) {
                cherriesPicked += upValue;
            } else {

                for (int c = col - 1; c >= 0; c--) {
                    int leftValue = checkLeftMove(grid, row, c);
                    if (leftValue == 1) {
                        cherriesPicked += leftValue;
                    } else {
                        row--;
                        break;
                    }
                }
            }
            col--;
        }
        return cherriesPicked;
    }


    public int checkDownMove(int [][]grid, int r, int c){
        int retVal = -1;
        int row = r;
        if (r < grid.length - 1) {
            retVal = grid[r + 1][c];
            row = r + 1;
        } else {
            retVal = grid[r][c];
        }
        if (retVal == 1) {
            grid[row][c] = 0;
        }
        return retVal;
    }

    public int checkRightMove(int [][]grid, int r, int c){
        int retVal = -1;
        int col = c;
        if (c < grid[r].length - 1) {
            retVal = grid[r][c + 1];
            col = c + 1;
        } else {
            retVal = grid[r][c];
        }

        if (retVal == 1) {
            grid[r][col] = 0;
        }

        return retVal;
    }

    public int checkUpMove(int [][]grid, int r, int c){
        int retVal = -1;
        int row = r;
        if (r > 0 && c >= 0) {
            retVal = grid[r - 1][c];
            row = r - 1;
        } else {
            retVal = grid[r][c];
        }

        if (retVal == 1) {
            grid[row][c] = 0;
        }
        return retVal;
    }

    public int checkLeftMove(int [][]grid, int r, int c){
        int retVal = -1;
        if (r >= 0 && c > 0) {
            retVal = grid[r][c - 1];
            if (retVal == 1) {
                grid[r][c - 1] = 0;
            }
        }
        return retVal;
    }

}
