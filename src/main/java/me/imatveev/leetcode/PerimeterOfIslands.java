package me.imatveev.leetcode;

public class PerimeterOfIslands {
    private final static int LAND = 1;
    private final static int WATER = 0;

    public static void main(String[] args) {

        final int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        final int[][] grid0 = {{1, 0}};

        System.out.println(islandPerimeter(grid));
    }

    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        for (int i = 0; i < grid.length; ++i) {
            final int[] row = grid[i];

            for (int j = 0; j < row.length; ++j) {
                if (row[j] == LAND) {
                    perimeter += countWaterBounds(grid, i, j);
                }
            }

        }

        return perimeter;
    }

    private static int countWaterBounds(int[][] grid, int i, int j) {
        int count = 0;

        if (i == 0 || grid[i - 1][j] == WATER) {
            ++count;
        }
        if (i == grid.length - 1 || grid[i + 1][j] == WATER) {
            ++count;
        }
        if (j == 0 || grid[i][j - 1] == WATER) {
            ++count;
        }
        if (j == grid[0].length - 1 || grid[i][j + 1] == WATER) {
            ++count;
        }

        return count;
    }
}
