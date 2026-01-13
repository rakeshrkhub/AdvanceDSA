package org.dynamicProgramming;

import java.util.Arrays;

public class LRodCuttingProblem {
    /*
    Given a rod of length N inches and an array of prices, price[]. price[i] denotes the value of a piece of length i.
    Determine the maximum value you can obtain by cutting up the whole rod and selling the pieces.
    Note: Consider 1-based indexing.
    N = 8
    Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
    Output: 22
    Explanation: The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5+17=22.
     */
    /*
    Approach: RECURSION
    Time Complexity: Exponential greater than O(2^N) Since we are calling with same index again and again
    Space Complexity: O(N) Auxiliary stack space
     */

    private static int getMaxAmount(int[] price, int index, int length){
        if(index==0){
            return length*price[0];
        }
        int notPick= getMaxAmount(price,index-1,length);
        int pick=Integer.MIN_VALUE;
        if(length>=index+1){
            pick=price[index]+ getMaxAmount(price,index,length-(index+1));
        }
        return Math.max(notPick,pick);
    }

    /*
    Approach: MEMOIZATION
    Time Complexity: O(n × n) Each sub problem (i, length) is computed once.
    Space Complexity: O(n × n),We use a 2D DP table for memoization.
     */
    private static int getMaxAmount(int[] price, int index, int length,int[][] dp){
        if(index==0){
            return length*price[0];
        }
        if(dp[index][length] !=-1) return dp[index][length];
        int notPick= getMaxAmount(price,index-1,length);
        int pick=Integer.MIN_VALUE;
        if(length>=index+1){
            pick=price[index]+ getMaxAmount(price,index,length-(index+1));
        }
        return dp[index][length]=Math.max(notPick,pick);
    }

    /*
    Approach: TABULATION
    Time Complexity: O(N * W),Because we have a nested loop iterating through all `n` items and all `W` capacities.
    Space Complexity: O(N * W),We are using a 2D DP table of size `n` by `W+1` to store intermediate results, and we eliminated recursion stack space.
     */
    private static int getMaxAmount(int[] price){
        int length=price.length;
        int n=length;
        int[][] dp=new int[n][n+1];
        for(int j=1;j<=length;j++){
            dp[0][j]= j*price[0];
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<=length;j++){
                int notPick= dp[i-1][j];
                int pick=Integer.MIN_VALUE;
                if(j>=i+1){
                    pick=price[i]+ dp[i][j-(i+1)];
                }
                dp[i][j]=Math.max(notPick,pick);
            }
        }
        return dp[n-1][length];
    }
    /*
    Approach: SPACE OPTIMIZATION
    Time Complexity: O(N * W), We iterate through N items, and for each item we process W weight capacities.
    Space Complexity: O(W),We only use a 1D array of size W to store intermediate results, eliminating the need for 2D DP or recursion stack.
     */
    private static int getMaxAmountOptimized(int[] price){
        int length=price.length;
        int n=length;
        int[] prev=new int[n+1];
        int[] curr=new int[n+1];
        for(int j=1;j<=length;j++){
            prev[j]= j*price[0];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=length;j++){
                int notPick= prev[j];
                int pick=Integer.MIN_VALUE;
                if(j>=i+1){
                    pick=price[i]+ curr[j-(i+1)];
                }
                curr[j]=Math.max(notPick,pick);
            }
            prev=curr.clone();
        }
        return prev[length];
    }
    public static void main(String[] args) {
        int n=8;
        int[] price={1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(getMaxAmount(price,n-1,n));
        int[][] dp=new int[n][n+1];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);
        System.out.println(getMaxAmount(price,n-1,n,dp));
        System.out.println(getMaxAmount(price));
        System.out.println(getMaxAmountOptimized(price));

    }
}
