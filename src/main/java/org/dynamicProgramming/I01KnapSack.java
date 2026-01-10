package org.dynamicProgramming;

import java.util.Arrays;

public class I01KnapSack {
    /*
     A thief wants to rob a store. He is carrying a bag of capacity W. The store has ‘n’ items.
     Its weight is given by the ‘wt’ array and its value by the ‘val’ array. He can either include an item in its knapsack
     or exclude it but can’t partially have it as a fraction. We need to find the maximum value of items that the thief can steal.
    Example 1:
    Input: val = [60, 100, 120], wt = [10, 20, 30], W = 50
    Output: 220
    Explanation: Select items with weights 20 and 30 for a total value of 100 + 120 = 220.

    Example 2:
    Input: val = [10, 40, 30, 50], wt = [5, 4, 6, 3], W = 10
    Output: 90
    Explanation: Select items with weights 4 and 3 for a total value of 40 + 50 = 90.

     */

    /*
    Approach: RECURSION
    Time Complexity: O(2^N)
    Space Complexity: O(N)
     */
    public static int getMaxValue(int[] weight, int[] values,int capacity,int index){
        if(index==0){
            if(capacity>=weight[0]){
                return values[0];
            }
            return 0;
        }
        if(capacity<0){
            return Integer.MIN_VALUE;
        }
        int notPick=getMaxValue(weight,values,capacity,index-1);
        int pick=Integer.MIN_VALUE;
        if(weight[index]<=capacity){
            pick=values[index]+getMaxValue(weight,values,capacity-weight[index],index-1);
        }
        return Math.max(notPick,pick);
    }

    /*
    Approach: MEMOIZATION
    Time Complexity: O(N*W), There are N*W states therefore at max ‘N*W’ new problems will be solved.
    Space Complexity: O(N*W) + O(N), We are using a recursion stack space (O(N)) and a 2D array (O(N*W)).
     */
    public static int getMaxValue(int[] weight, int[] values,int capacity,int index,int[][] dp){
        if(index==0){
            if(capacity>=weight[0]){
                return values[0];
            }
            return 0;
        }
        if(capacity<0){
            return Integer.MIN_VALUE;
        }
        if(dp[index][capacity] !=-1) return dp[index][capacity];
        int notPick=getMaxValue(weight,values,capacity,index-1,dp);
        int pick=Integer.MIN_VALUE;
        if(weight[index]<=capacity){
            pick=values[index]+getMaxValue(weight,values,capacity-weight[index],index-1,dp);
        }
        return dp[index][capacity]=Math.max(notPick,pick);
    }

    /*
    Approach: TABULATION
    Time Complexity: O(N*W), There are two nested loops
    Space Complexity: O(N*W), We are using an external array of size ‘N*W’. Stack Space is eliminated.
     */
    public static int getMaxValueTabulation(int[] weight, int[] values,int capacity){
        int n=weight.length;
        int[][] dp = new int[n][capacity+1];
        for(int j=weight[0];j<=capacity;j++){
            dp[0][j]=values[0];
        }
        for(int index=1;index<n;index++){
            for(int j=0;j<=capacity;j++){
                int notPick=dp[index-1][j];
                int pick=Integer.MIN_VALUE;
                if(weight[index]<=j){
                    pick=values[index]+dp[index-1][j-weight[index]];
                }
                dp[index][j]=Math.max(notPick,pick);
            }
        }
        return dp[n-1][capacity];
    }

    /*
    Approach: SPACE OPTIMIZATION
    Time Complexity: O(N*W), There are two nested loops.
    Space Complexity: O(W), We are using an external array of size ‘W+1’ to store only one row.
     */
    public static int getMaxValueSpaceOptimization(int[] weight, int[] values,int capacity){
        int n=weight.length;
        int[] prev = new int[capacity+1];
        int[] curr = new int[capacity+1];
        for(int j=weight[0];j<=capacity;j++){
            prev[j]=values[0];
        }
        for(int index=1;index<n;index++){
            for(int j=0;j<=capacity;j++){
                int notPick=prev[j];
                int pick=Integer.MIN_VALUE;
                if(weight[index]<=j){
                    pick=values[index]+prev[j-weight[index]];
                }
                curr[j]=Math.max(notPick,pick);
            }
            prev=curr.clone();
        }
        return prev[capacity];
    }
    public static void main(String[] args) {
        int n=3;
        int[] weight={3,2,5};
        int[] values={30,50,60};
        int capacity=7;
        System.out.println(getMaxValue(weight,values,capacity,n-1));

        int[][] dp = new int[n][capacity+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getMaxValue(weight,values,capacity,n-1,dp));
        System.out.println(getMaxValueTabulation(weight,values,capacity));
        System.out.println(getMaxValueSpaceOptimization(weight,values,capacity));
    }
}
