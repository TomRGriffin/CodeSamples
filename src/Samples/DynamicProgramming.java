package Samples;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class DynamicProgramming {
    // Robot in a Grid: Imagine a robot sitting on the upper left corner of  grid with r rows and c columns. The robot
    // can only move in two directions right and down, but certain cells are "off limits" such that robot cannot step
    // on them. Design an algorithm to find a path for the robot from the top left to the bottom right.

    public static void testPath() {
        int [][] grid = { { 1, 0, -1 },
                          { 0, 1, -1 },
                          { 1, 0, 1 } };
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        ArrayList<Integer> sumPoints = new ArrayList<>();
        ArrayList<Point> path = dynamicProgramming.getPath(grid, sumPoints);

        if (path != null) {
            System.out.println("Path exist for robot, path size = " + path.size());
            Stream<Integer> sumStream = sumPoints.stream();
            System.out.println("Sum of points = " + sumStream.reduce(0, (a, b) -> a + b));
        } else {
            System.out.println("Path doesn't exist for robot");
        }
    }

    private ArrayList<Point> getPath(int [][] grid, ArrayList<Integer> sumPoints) {
        if (grid == null || grid.length == 0) return null;
        ArrayList<Point> path = new ArrayList<Point>();
        if (getUpPath(grid, grid.length - 1, grid[0].length - 1, path, sumPoints) &&
                getDownPath(grid, 0, 0, path, sumPoints)) {
            return path;
        }
        return null;
    }

    private boolean getUpPath(int [][]grid, int row, int col, ArrayList<Point> path, ArrayList<Integer> sumPoints) {
        if (col < 0 || row < 0 || grid[row][col] == -1) {
            return false;
        }

        boolean isAtOrigin = (row == 0) && (col == 0);

        if (isAtOrigin || getUpPath(grid, row, col - 1, path, sumPoints) ||
                getUpPath(grid, row - 1, col, path, sumPoints)) {
            Point p = new Point(row, col);
            path.add(p);
            sumPoints.add(grid[row][col]);
            grid[row][col] = 0;
            return true;
        }

        return false;
    }

    private boolean getDownPath(int [][]grid, int row, int col, ArrayList<Point> path, ArrayList<Integer> sumPoints) {
        if (col >= grid[0].length || row >= grid.length || grid[row][col] == -1) {
            return false;
        }

        boolean isAtEnd = (row == grid.length - 1) && (col == grid[0].length - 1);

        if (isAtEnd || getDownPath(grid, row, col + 1, path, sumPoints) ||
                getDownPath(grid, row + 1, col, path, sumPoints)) {
            Point p = new Point(row, col);
            path.add(p);
            sumPoints.add(grid[row][col]);
            grid[row][col] = 0;
            return true;
        }

        return false;
    }


    public static void testCountPaths() {
        int[][] grid = {{1, 0, -1},
                        {1, 1, -1},
                        {1, 1, 1}};
        int [][] pathsGrid = new int[grid.length][grid[0].length];
        System.out.println("Total paths = " + countPaths(grid, 0, 0, pathsGrid));
        for (int i = 0; i < pathsGrid.length ; i++) {
            for (int j = 0; j < pathsGrid[0].length ; j++) {
                System.out.print(pathsGrid[i][j] + ", ");
            }
            System.out.println();
        }
    }
    private static int countPaths(int[][] grid, int row, int col, int[][] pathsgrid) {
        if (col >= grid[0].length || row >= grid.length || grid[row][col] == -1) {
            return 0;
        }
        if ((row == grid.length - 1) && (col == grid[0].length - 1)) {
            return 1;
        }

        int paths = countPaths(grid, row + 1, col, pathsgrid) + countPaths(grid, row, col + 1, pathsgrid);
//        System.out.println("row = " + row + ", col = " + col + ", paths = " + paths);
        pathsgrid[row][col] = paths;
        return paths;
    }

//  675. Cut Off Trees for Golf Event
//    You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
//            0 represents the obstacle can't be reached.
//            1 represents the ground can be walked through.
//    The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
//    You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
//
//    You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
//    You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
//            Example 1:
//    Input:
//            [
//            [1,2,3],
//            [0,0,4],
//            [7,6,5]
//            ]
//    Output: 6
//    Example 2:
//    Input:
//            [
//            [1,2,3],
//            [0,0,0],
//            [7,6,5]
//            ]
//    Output: -1
//    Example 3:
//    Input:
//            [
//            [2,3,4],
//            [0,0,5],
//            [8,7,6]
//            ]
//    Output: 6
//    Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
//            Hint: size of the given matrix will not exceed 50x50.

    public static void testCutOffTrees() {
        List<List<Integer>> forest = new ArrayList<>();
        List<Integer> lane1 = new ArrayList<Integer>() {{
            add(1); add(2); add(3);
        }};
        forest.add(lane1);
        List<Integer> lane2 = new ArrayList<Integer>() {{
            add(0); add(0); add(4);
        }};
        forest.add(lane2);
        List<Integer> lane3 = new ArrayList<Integer>() {{
            add(7); add(6); add(5);
        }};
        forest.add(lane3);
        System.out.println("No. of trees to cut = " + new DynamicProgramming().cutOffTree(forest));
    }

    public int cutOffTree(List<List<Integer>> forest) {
        int rowEnd = forest.size();
        int colEnd = forest.get(0).size();
        for (int i = 0; i < rowEnd; i++) {
            List lane = forest.get(i);
            for (int j = 0; j < colEnd; j++) {
                System.out.print(lane.get(j) + " ");
            }
            System.out.println();
        }
        if (forest.isEmpty()) {
            return -1;
        } else if(forest.get(0).get(0) == 0) {
            return  -1;
        } else {
            ArrayList<Integer> treesCount = new ArrayList<>();
            return countTreesToCut(forest, treesCount,0, 0);
        }
    }

    private int countTreesToCut(List<List<Integer>> forest, List<Integer> treesCount, int row, int col) {
        int colEnd = forest.get(0).size() - 1, rowEnd = forest.size() - 1;
        Integer left = 0, right = 0, top = 0, bottom = 0;

        int step = 0;
        while (col < colEnd) {
            List<Integer> currentList = forest.get(row);

            if (col > 0) {
                left = currentList.get(col - 1);
            }
            if (col < colEnd - 1) {
                right = currentList.get(col + 1);
            }
            if (row < rowEnd - 1) {
                List<Integer> nextList = forest.get(row + 1);
                bottom = nextList.get(col);
                if (row > 0) {
                    List<Integer> prevList = forest.get(row - 1);
                    top = prevList.get(col);
                }

                if (bottom > top && bottom < right) {
                    step++;
                    row++;
                }
            }
            if(right > left) {
                step++;
            }
            col++;
        }


        return step;
    }

    public static void testNumDecoding() {
        System.out.println("Num of decodings = " + new DynamicProgramming().numDecodings("12"));
    }
    public int numDecodings(String s) {
        if (s.isEmpty()) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            Integer first = Integer.parseInt(s.substring(i - 1, i));
            Integer second = Integer.parseInt(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
