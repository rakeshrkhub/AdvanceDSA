package org.dynamicProgramming;

import java.util.Arrays;

public class DGridUniquePath2 {
    /*
    This problem is same as Grid Unique path but only difference is it has obstacle.
    So, will add if condition for obstacle. Rest every thing is same. Time and Space Complexity is also same.
     */
    private static int getAllUniquePath(int[][] grid,int row, int col){
        if(row==0 && col==0 && grid[0][0] != -1){
            return 1;
        }
        if(row<0 || col <0 || grid[row][col] == -1){
            return 0;
        }
        int left = getAllUniquePath(grid,row,col-1);
        int up = getAllUniquePath(grid,row-1,col);
        return left+up;
    }

    private static int getAllUniquePath(int[][] grid,int row, int col,int[][] dp){
        if(row==0 && col==0 && grid[0][0] != -1){
            return 1;
        }
        if(row<0 || col <0 || grid[row][col] == -1){
            return 0;
        }
        if(dp[row][col] != -2){
            return dp[row][col];
        }
        int left = getAllUniquePath(grid,row,col-1);
        int up = getAllUniquePath(grid,row-1,col);
        return dp[row][col]=left+up;
    }

    private static int getAllUniquePathTabulation(int[][] grid){
        int row= grid.length;
        int col=grid[0].length;
        int[][] dp = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i==0 && j==0 && grid[i][j] != -1){
                    dp[i][j]=1;
                    continue;
                }
                int up=0,left=0;
                if(i>0 && grid[i][j] !=-1){
                    up = dp[i-1][j];
                }
                if(j>0 && grid[i][j] !=-1){
                    left=dp[i][j-1];
                }
                dp[i][j]=up+left;
            }
        }
        return dp[row-1][col-1];
    }
    public static void main(String[] args) {
        int[][] arr={
                {0,0,0},
                {0,-1,0},
                {0,0,0}
                };
        int m=arr.length;
        int n=arr[0].length;
        System.out.println(getAllUniquePath(arr,m-1,n-1));

        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-2); //since -1 is present in array itself
        }
        System.out.println(getAllUniquePath(arr,m-1,n-1,dp));
        //Tabulation
        System.out.println(getAllUniquePathTabulation(arr));
    }
}
