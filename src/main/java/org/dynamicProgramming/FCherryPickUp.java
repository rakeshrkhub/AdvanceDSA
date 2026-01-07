package org.dynamicProgramming;

import java.util.Arrays;

public class FCherryPickUp {
    /*
    You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of cherries collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.


Example 1:


Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.
Example 2:


Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.


Constraints:

rows == grid.length
cols == grid[i].length
2 <= rows, cols <= 70
0 <= grid[i][j] <= 100
     */

    // Recursive function with memoization

    private static int solve(int i, int j1, int j2, int n, int m, int[][] grid) {
        // Out of boundary check
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m)
            return (int)(-1e9);

        // Base case: last row
        if (i == n - 1) {
            if (j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }

        // Take chocolates from current cell(s)
        int maxi = (int)(-1e9);
        int curr = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];

        // Try all 9 moves
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int ans = curr + solve(i + 1, j1 + dj1, j2 + dj2, n, m, grid);
                maxi = Math.max(maxi, ans);
            }
        }
        // Store result
        return maxi;
    }
    /*
    Alice and Bob start from fixed positions and move row by row until the last row. At each step, they need to pick the best moves to collect the highest number of chocolates.

    Why Greedy Fails?

    A greedy method picks the move that gives the most chocolates right away, but this can lead to fewer chocolates later because the matrix values are uneven. So, we must try all possible paths using recursion.

    Understanding the Problem

    We have a grid with rows and columns. Alice and Bob move row by row. Their current positions can be thought of as their current row and the two column positions they are standing on.

    Base Case:

    If they reach the last row: If both are in the same cell, count the chocolates once. If in different cells, add chocolates from both cells.
    If they move outside the grid, this is not allowed, so we return a very small value to mark it as an invalid path.
    Exploring All Moves

    At every step, both Alice and Bob can move straight down, down-right, or down-left. For every move Alice makes, Bob also has three choices, making nine possible move combinations at each step.

    Choosing the Best Option

    At every step, we check all nine possible moves and take the one that gives the highest total chocolates.

    Memoization

    Because the same moves will be calculated many times, we store the results to save time:

    Make a 3D table to store the best results for each row and column positions of Alice and Bob.
    Before calculating again, check if we already have the answer. If yes, use it.
    If not, calculate, store it, and then return it.

    Time Complexity: O(N*M*M) * 9, At max, there will be N*M*M calls of recursion to solve a new problem and in every call, two nested loops together run for 9 times.

    Space Complexity: O(N) + O(N*M*M), We are using a recursion stack space: O(N), where N is the path length and an external DP Array of size ‘N*M*M’.
     */
    private static int solve(int i, int j1, int j2, int n, int m, int[][] grid, int[][][] dp) {
        // Out of boundary check
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m)
            return (int)(-1e9);

        // Base case: last row
        if (i == n - 1) {
            if (j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }

        // If already computed return it
        if (dp[i][j1][j2] != -1) return dp[i][j1][j2];

        // Take chocolates from current cell(s)
        int maxi = (int)(-1e9);
        int curr = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];

        // Try all 9 moves
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int ans = curr + solve(i + 1, j1 + dj1, j2 + dj2,
                        n, m, grid, dp);
                maxi = Math.max(maxi, ans);
            }
        }
        // Store result
        return dp[i][j1][j2] = maxi;
    }

    /*
    Approach: Tabulation
    Steps to Convert Recursive Solution to Tabulation

    Tabulation uses the same 3D table as memoization. For example, one cell in the table shows the maximum chocolates collected when Alice and Bob are at specific positions in the same row.



    Base Setup

    Since the last row is the stopping point in recursion, fill the last row of the table first: If Alice and Bob are in the same cell, count chocolates once. If in different cells, add chocolates from both cells.
    Filling the Table

    Go row by row from the second last row up to the first row.
    For each pair of positions of Alice and Bob, check all 9 possible move combinations (down, down-left, down-right for each).
    For each option, look at the values already stored in the next row and add chocolates collected in the current move.
    Keep the highest value among all 9 options as the best result for that cell.
    Final Answer

    After filling the table, the top row’s starting positions give the maximum chocolates Alice and Bob can collect together.

    Time Complexity: O(N*M*M)*9, The outer nested loops run for (N*M*M) times and the inner two nested loops run for 9 times.

    Space Complexity: O(N*M*M), We are using an external array of size ‘N*M*M’. The stack space will be eliminated.
     */

    private static int maximumChocolatesTabulation(int n, int m, int[][] grid) {
        // 3D DP table
        int[][][] dp = new int[n][m][m];

        // Base case: last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) dp[n-1][j1][j2] = grid[n-1][j1];
                else dp[n-1][j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            }
        }

        // Fill DP table bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = (int)(-1e9);
                    int curr = (j1 == j2) ? grid[i][j1]
                            : grid[i][j1] + grid[i][j2];
                    // Try all 9 moves
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int newJ1 = j1 + dj1;
                            int newJ2 = j2 + dj2;
                            if (newJ1 >= 0 && newJ1 < m &&
                                    newJ2 >= 0 && newJ2 < m) {
                                maxi = Math.max(maxi, curr +
                                        dp[i+1][newJ1][newJ2]);
                            } else {
                                maxi = Math.max(maxi, (int)(-1e9));
                            }
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][m-1];
    }

    /*
    Approach: Space Optimization
    Space Optimization Idea

To fill each cell, we only need values from the next row. So, instead of keeping a full 3D table, we can use two 2D tables and update them as we move row by row.

Steps to Space Optimize the Tabulation Approach

First, create one 2D table (call it "front") and fill it like we did for the last row in the normal tabulation method.
Create another 2D table (call it "cur") that we will use while going through the rows.
Move row by row (starting from the second last row), just like in tabulation, checking all possible moves to find the highest chocolates for each cell. Use values from the "front" table for calculations.
Instead of filling a 3D table, store the best value directly in "cur".
After finishing one row, copy "cur" into "front", as "front" will now represent the next row for the upcoming calculation.
Final Answer

After completing all rows, the answer will be in front[0][m-1], which represents Alice starting at column 0 and Bob at the last column in the first row.

Time Complexity: O(N*M*M)*9, The outer nested loops run for (N*M*M) times and the inner two nested loops run for 9 times.

Space Complexity: O(M*M), We are using an external array of size ‘M*M’.
     */

    private static int maximumChocolatesOptimized(int n, int m, int[][] grid) {
        // Next row dp array
        int[][] next = new int[m][m];
        // Current row dp array
        int[][] curr = new int[m][m];

        // Base case: last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) next[j1][j2] = grid[n-1][j1];
                else next[j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            }
        }

        // Fill DP table bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = (int)(-1e9);
                    int currChoco = (j1 == j2) ? grid[i][j1]
                            : grid[i][j1] + grid[i][j2];
                    // Try all 9 moves
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int newJ1 = j1 + dj1;
                            int newJ2 = j2 + dj2;
                            if (newJ1 >= 0 && newJ1 < m &&
                                    newJ2 >= 0 && newJ2 < m) {
                                maxi = Math.max(maxi, currChoco +
                                        next[newJ1][newJ2]);
                            } else {
                                maxi = Math.max(maxi, (int)(-1e9));
                            }
                        }
                    }
                    curr[j1][j2] = maxi;
                }
            }
            // Move current row to next row
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    next[j1][j2] = curr[j1][j2];
                }
            }
        }
        // Answer is starting position
        return next[0][m-1];
    }







    // main function to call
    private static int maximumChocolates(int n, int m, int[][] grid) {
        int[][][] dp = new int[n][m][m];
        for (int[][] arr2d : dp) {
            for (int[] arr1d : arr2d) {
                Arrays.fill(arr1d, -1);
            }
        }
        return solve(0, 0, m - 1, n, m, grid, dp);
    }


    public static void main(String[] args) {
        int[][] grid = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };
        int n = grid.length, m = grid[0].length;
        System.out.println(maximumChocolates(n, m, grid));
        System.out.println(maximumChocolatesTabulation(n,m,grid));
        System.out.println(maximumChocolatesOptimized(n,m,grid));
    }
}
