package me.imatveev.leetcode;

public class NumberOfIslands {
    public static void main(String[] args) {

        final char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };


        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        final char LAND = '1';

        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            final char[] row = grid[i];

            for (int j = 0; j < row.length; ++j) {
                if (row[j] == LAND) {
                    count++;
                    dfsEraseIsland(grid, i, j);
                }
            }
        }

        return count;
    }

    private static void dfsEraseIsland(char[][] grid, int i, int j) {
        final boolean outOfBounds = i < 0 || i >= grid.length
                || j < 0 || j >= grid[0].length;

        if (outOfBounds || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        dfsEraseIsland(grid, i - 1, j);
        dfsEraseIsland(grid, i + 1, j);
        dfsEraseIsland(grid, i, j - 1);
        dfsEraseIsland(grid, i, j + 1);
    }
}
