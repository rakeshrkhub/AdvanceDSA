package org.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ETriangle {
    /*
    Given a triangle array, return the minimum path sum from top to bottom.

    For each step, you may move to an adjacent number of the row below. More formally,
     if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

    Example 1:

    Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    Output: 11
    Explanation: The triangle looks like:
       2
      3 4
     6 5 7
    4 1 8 3
    The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
    Example 2:

    Input: triangle = [[-10]]
    Output: -10


    Constraints:

    1 <= triangle.length <= 200
    triangle[0].length == 1
    triangle[i].length == triangle[i - 1].length + 1
    -104 <= triangle[i][j] <= 104
     */
    private static int getMinPathSum(List<List<Integer>> list, int i, int j, int n){
        if(i==n-1){
            return list.get(i).get(j);
        }
        int down=Integer.MAX_VALUE,rd=Integer.MAX_VALUE;
        if(i<n){
            down=list.get(i).get(j)+getMinPathSum(list,i+1,j,n);
        }
        if(i<n && j<list.get(i).size() ){
            rd=list.get(i).get(j)+getMinPathSum(list,i+1,j+1,n);
        }
        return Math.min(down,rd);
    }

    private static int getMinPathSum(List<List<Integer>> list, int i, int j, int n,int[][] dp){
        if(i==n-1){
            return list.get(i).get(j);
        }
        if(dp[i][j] !=99999){
            return dp[i][j];
        }
        int down=Integer.MAX_VALUE,rd=Integer.MAX_VALUE;
        if(i<n){
            down=list.get(i).get(j)+getMinPathSum(list,i+1,j,n);
        }
        if(i<n && j<list.get(i).size() ){
            rd=list.get(i).get(j)+getMinPathSum(list,i+1,j+1,n);
        }
        return dp[i][j]=Math.min(down,rd);
    }
/*
Always write Tabulation in opposite fashion of Recursion:
    TC:O(NXN)
    SC:O(NXN)
 */
    private static int getMinPathSum(List<List<Integer>> list){
        int n= list.size();
        int m=list.get(n-1).size();
        int[][] dp = new int[n][n];
        for(int j=0;j<n;j++) {
            dp[n-1][j] = list.get(n-1).get(j);
        }
        for(int i=n-2;i>=0;i--){
            for(int j=i;j>=0;j--){
               int down=list.get(i).get(j)+dp[i+1][j];
               int rd=list.get(i).get(j)+dp[i+1][j+1];
                dp[i][j]=Math.min(down,rd);
            }
        }

        return dp[0][0];
    }
    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();

        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3, 4));
        list.add(Arrays.asList(6, 5, 7));
        list.add(Arrays.asList(4, 1, 8, 3));

        int n=list.size();
        List<List<Integer>> list1 = new ArrayList<>();

        list1.add(Arrays.asList(-10));
        System.out.println(getMinPathSum(list1,0,0, list1.size()));

        int[][] dp= new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],99999);
        }
        System.out.println(getMinPathSum(list,0,0, list.size(),dp));

        System.out.println(getMinPathSum(list));

    }
}
