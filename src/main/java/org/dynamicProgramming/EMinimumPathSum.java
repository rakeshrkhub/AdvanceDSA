package org.dynamicProgramming;

import java.util.Arrays;

public class EMinimumPathSum {
    /*
    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
    which minimizes the sum of all numbers along its path.
    Note: You can only move either down or right at any point in time.

    Example 1:

    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
    Output: 7
    Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

    Example 2:

    Input: grid = [[1,2,3],[4,5,6]]
    Output: 12
     */

    /*
    Approach 1: Recursion
    Since we know we have to try all the possible path and choose the path which has the minimal path sum.
    Time Complexity: O(2^(mxn)) Since we have 2 choice for each cell
    Space Complexity: O(mxn) for recursion stack space
     */
    private static int getMimPathSum(int[][] path, int i, int j){
        if(i==0 && j==0){
            return path[0][0];
        }
        if(i<0 || j<0) return 9999; //Any bigger number but not Integer.MAX_VALUE at it is causing overflow and converting in -ve value and taking part in ans
        int left= path[i][j]+getMimPathSum(path, i, j - 1);
        int up = path[i][j]+getMimPathSum(path, i - 1, j);

        return Math.min(left,up);
    }

    /*
    Approach 2: MEMOIZATION
    Since we were solving overlapping sub problems, we can see in recursion tree. So, will think of memoization.
    Time Complexity: O(mxn) Since we are going to each grid exactly one time.
    Space Complexity: O(mxn) for recursion stack space + O(mxn) for dp array
     */
    private static int getMimPathSum(int[][] path, int i, int j,int[][] dp){
        if(i==0 && j==0){
            return path[0][0];
        }
        if(i<0 || j<0) return 9999;
        if(dp[i][j] !=-1) return dp[i][j];
        int left= path[i][j]+getMimPathSum(path, i, j - 1);
        int up = path[i][j]+getMimPathSum(path, i - 1, j);

        return dp[i][j]=Math.min(left,up);
    }

    /*
    Approach 3: TABULATION
    Since we are using recursion that's why space complexity has been increased so we can think of tabulation.
    Time Complexity: O(mxn)
    Space Complexity: O(mxn) for dp array
     */
    private static int getMimPathSumTabulation(int[][] path){
        int m=path.length;
        int n=path[0].length;
        int[][] dp = new int[m][n];
        dp[0][0]=path[0][0];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0)continue;
                int right=Integer.MAX_VALUE,down=Integer.MAX_VALUE;
                if(j>0)
                    right= path[i][j]+dp[i][j-1];
                if(i>0)
                    down = path[i][j]+dp[i-1][j];
                dp[i][j]=Math.min(right,down);
            }
        }
        return dp[m-1][n-1];
    }

    /*
    Approach 3: Space Optimization
    Since we only care about previous one grid only. So we can optimise the space complexity by using array of length of column only
    to store the previous grid.
    Time Complexity: O(mxn)
    Space Complexity: O(n) for previous and current array
     */

    private static int getMimPathSumOptimized(int[][] path){
        int m=path.length;
        int n=path[0].length;
        int[] prev = new int[n];
        Arrays.fill(prev,9999);

        for(int i=0;i<m;i++){
            int[] curr = new int[n];
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    curr[j]=path[i][j];
                    continue;
                }
                int right=Integer.MAX_VALUE,down=Integer.MAX_VALUE;
                if(j>0)
                    right= path[i][j]+curr[j-1];
                if(i>0)
                    down = path[i][j]+prev[j];
                curr[j]=Math.min(right,down);
            }
            prev=curr.clone();
        }
        return prev[n-1];
    }
    public static void main(String[] args) {
        int[][] path ={{1,2,3},{4,5,6}};
        int m=path.length;
        int n=path[0].length;
        System.out.println(getMimPathSum(path,m-1,n-1));

        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getMimPathSum(path,m-1,n-1,dp));
        System.out.println(getMimPathSumTabulation(path));
        System.out.println(getMimPathSumOptimized(path));
    }
}
