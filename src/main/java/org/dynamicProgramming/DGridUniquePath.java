package org.dynamicProgramming;

import java.util.Arrays;

public class DGridUniquePath {
    /*
    There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

    Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

    The test cases are generated so that the answer will be less than or equal to 2 * 109.

    Example 1:

    Input: m = 3, n = 7
    Output: 28

    Example 2:

    Input: m = 3, n = 2
    Output: 3
    Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    1. Right -> Down -> Down
    2. Down -> Down -> Right
    3. Down -> Right -> Down
     */

    /*
    Approach 1: RECURSION
    Since we can see the question is asking for all possibility so,
    Let's start the solution with Recursion. We'll start from the finish point and go till start or we can also start from start
     point and go till end. Both the way are fine/same.
    Time Complexity: O(2^(mxn)) since every cell has 2 options.
    Space Complexity: O(mxn) recursion stack space
     */

    private static int getAllUniquePaths(int row, int col){
        if(row==0 && col==0){ //Base case
            return 1;
        }
        if(row<0 || col <0){ //if we are going outside the boundary
            return 0;
        }
        int left = getAllUniquePaths(row,col-1);
        int up = getAllUniquePaths(row-1,col);
        return left+up;

    }

    /*
    Approach 2: MEMOIZATION
    Since we are solving overlapping sub problems in recursion solution so we can think of MEMOIZATION
    Time Complexity: O(mxn) since we are only calculating each cell exactly one time.
    Space Complexity: O(mxn) recursion stack  + O(mxn) for using DP array
     */

    private static int getAllUniquePaths(int row, int col, int[][] dp){
        if(row==0 && col==0){ //Base case
            return 1;
        }
        if(row<0 || col <0){ //if we are going outside the boundary
            return 0;
        }
        if(dp[row][col] !=-1){
            return dp[row][col];
        }

        int left = getAllUniquePaths(row,col-1,dp);
        int up = getAllUniquePaths(row-1,col,dp);
        return dp[row][col]=left+up;

    }

    /*
    Approach 3: TABULATION
    Since we have solved this problem using MEMOIZATION, so will try to optimize the space complexity and will
    reduce recursion stack space.
    Time Complexity: O(mxn)
    Space Complexity: O(mxn) for using DP array
     */

    private static int getAllUniquePathsTabulation(int row, int col){
        int[][] dp = new int[row][col];
        dp[0][0]=1;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i==0 && j==0) continue;
                int right=0;
                int down=0;
                if(j>0){
                    right  = dp[i][j-1];
                }
                if(i>0){
                    down = dp[i-1][j];
                }
                dp[i][j]=right+down;
            }

        }
        return dp[row-1][col-1];

    }

    /*
    Approach 4: SPACE OPTIMIZATION
    Since we can clearly see we are only caring about previous row and previous column so we can definitely optimise the space complexity
    Time Complexity: O(mxn)
    Space Complexity: O(n) for using prev array
     */

    private static int getAllUniquePathsOptimized(int row, int col){
        int[] prev = new int[col];
        for(int i=0;i<row;i++){
            int[] curr = new int[col];
            for(int j=0;j<col;j++){
                if(i==0 && j==0) {
                    curr[j]=1;
                    continue;
                }
                int right=0;
                int down=0;
                if(j>0){
                    right  = curr[j-1];
                }
                if(i>0){
                    down = prev[j];
                }
                curr[j]=right+down;
            }
            prev=curr;

        }
        return prev[col-1];

    }
    public static void main(String[] args) {
        int m=3,n=7;
        System.out.println(getAllUniquePaths(m-1,n-1));
        //Memoization
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getAllUniquePaths(m-1,n-1,dp));
        //Tabulation
        System.out.println(getAllUniquePathsTabulation(m,n));
        System.out.println(getAllUniquePathsOptimized(m,n));
    }
}
