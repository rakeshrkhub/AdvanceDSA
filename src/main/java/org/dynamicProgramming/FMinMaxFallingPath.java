package org.dynamicProgramming;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FMinMaxFallingPath {
    /*
    Given an m x n array of integers matrix, return the minimum sum of any falling path through matrix.
    A falling path starts at any element in the first row and chooses the element in the next row that is either
    directly below or diagonally left/right. Specifically,
    the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

    Example 1:

    Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
    Output: 13
    Explanation: There are two falling paths with a minimum sum as shown.

    Example 2:

    Input: matrix = [[-19,57],[-40,-5]]
    Output: -59
    Explanation: The falling path with a minimum sum is shown.

    Constraints:
    n == matrix.length == matrix[i].length
    1 <= n <= 100
    -100 <= matrix[i][j] <= 100
     */
    /*
    Approach 1: Recursion
    Since we have to check for all the possibilities that's why we will think about recursion.
    Time Complexity: O(3^N)
    Space Complexity: O(N)
     */
    private static int getMinimumFallingPathRec(int[][] path, int i, int j){
        if(i<0 || j<0 || j>=path[0].length){
            return 999999;
        }
        if(i==0 ){
            return path[i][j];
        }
        int ld=path[i][j]+getMinimumFallingPathRec(path,i-1,j-1);
        int d=path[i][j]+getMinimumFallingPathRec(path,i-1,j);
        int rd=path[i][j]+getMinimumFallingPathRec(path,i-1,j+1);

        return Math.min(ld,Math.min(d,rd));
    }

    /*
    Approach 2: Memoization
    Since we are solving overlapping sub problems in recursive solution so we can think of memoization to reduce time complexity
    from Exponential to square
    Time Complexity: O(MXN)
    Space Complexity: O(MXN) dp array +O(N) recursion stack space
     */
    private static int getMinimumFallingPathMemoization(int[][] path, int i, int j,int[][] dp){
        if(i<0 || j<0 || j>=path[0].length){
            return 999999;
        }
        if(i==0 ){
            return path[i][j];
        }
        if(dp[i][j] !=Integer.MAX_VALUE) return dp[i][j];
        int ld=path[i][j]+getMinimumFallingPathMemoization(path,i-1,j-1,dp);
        int d=path[i][j]+getMinimumFallingPathMemoization(path,i-1,j,dp);
        int rd=path[i][j]+getMinimumFallingPathMemoization(path,i-1,j+1,dp);

        return dp[i][j]=Math.min(ld,Math.min(d,rd));
    }

    /*
    Approach 3: Tabulation
    We can optimize the space complexity of above solution through tabulation
    Time Complexity: O(MXN) +O(N) to finding answer
    Space Complexity: O(MXN) dp array
     */
    private static int getMinimumFallingPathTabulation(int[][] path){
        int m=path.length;
        int n=path[0].length;
        int[][] dp = new int[m][n];
        for(int j=0;j<n;j++){
            dp[0][j]=path[0][j];
        }
        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                int ld=Integer.MAX_VALUE,rd=Integer.MAX_VALUE;
                if(j>0)
                    ld=path[i][j]+dp[i-1][j-1];
                int d=path[i][j]+dp[i-1][j];
                if(j<dp[i].length-1)
                    rd=path[i][j]+dp[i-1][j+1];
                dp[i][j]=Math.min(ld,Math.min(d,rd));
            }
        }

        int result=Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            result=Math.min(result,dp[m-1][j]);
        }


        return result;
    }

    /*
   Approach 3: Space Optimization
   As we can observe, we are only caring about just previous state nothing more, so we can optimise the space complexity
   Time Complexity: O(MXN) +O(N) to finding answer
   Space Complexity: O(N) prev array+O(N) curr array
    */
    private static int getMinimumFallingPathOptimized(int[][] path){
        int m=path.length;
        int n=path[0].length;
        int[] prev = new int[n];
        for(int j=0;j<n;j++){
            prev[j]=path[0][j];
        }
        for(int i=1;i<m;i++){
            int[] curr = new int[n];
            for(int j=0;j<n;j++){
                int ld=Integer.MAX_VALUE,rd=Integer.MAX_VALUE;
                if(j>0)
                    ld=path[i][j]+prev[j-1];
                int d=path[i][j]+prev[j];
                if(j<prev.length-1)
                    rd=path[i][j]+prev[j+1];
                curr[j]=Math.min(ld,Math.min(d,rd));
            }
            prev=curr.clone();
        }
        int result=Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            result=Math.min(result,prev[j]);
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] path={{2,1,3},{6,5,4},{7,8,9}};
        int m=path.length;
        int n=path[0].length;

        int[][] dp = new int[m][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        int result=Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            result =Math.min(result,getMinimumFallingPathMemoization(path,m-1,j,dp));
        }
        System.out.println(result);
        System.out.println(getMinimumFallingPathTabulation(path));
        System.out.println(getMinimumFallingPathOptimized(path));
    }
}
