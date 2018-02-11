package Samples;

import java.awt.*;
import java.util.ArrayList;
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
        ArrayList<Point> upPath = dynamicProgramming.getUpPath(grid, sumPoints);
        ArrayList<Point> downPath = dynamicProgramming.getDownPath(grid, sumPoints);

        if (downPath != null) {
            System.out.println("Path exist for robot, path size = " + downPath.size() + upPath.size());
            Stream<Integer> sumStream = sumPoints.stream();
            System.out.println("Sum of points = " + sumStream.reduce(0, (a, b) -> a + b));
        } else {
            System.out.println("Path doesn't exist for robot");
        }
    }

    private ArrayList<Point> getUpPath(int [][] grid, ArrayList<Integer> sumPoints) {
        if (grid == null || grid.length == 0) return null;
        ArrayList<Point> path = new ArrayList<Point>();
        if (getUpPath(grid, grid.length - 1, grid[0].length - 1, path, sumPoints)) {
            return path;
        }
        return null;
    }

    private ArrayList<Point> getDownPath(int [][] grid, ArrayList<Integer> sumPoints) {
        if (grid == null || grid.length == 0) return null;
        ArrayList<Point> path = new ArrayList<Point>();
        if (getDownPath(grid, 0, 0, path, sumPoints)) {
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


}
