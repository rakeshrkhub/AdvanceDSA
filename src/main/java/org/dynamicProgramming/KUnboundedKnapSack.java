package org.dynamicProgramming;

import java.util.Arrays;

public class KUnboundedKnapSack {
    /*
        Problem Statement: A thief wants to rob a store. He is carrying a bag of capacity W. The store has ‘n’ items of infinite supply. Its weight is given by the ‘wt’ array and its value by the ‘val’ array. He can either include an item in its knapsack or exclude it but can’t partially have it as a fraction. We need to find the maximum value of items that the thief can steal. He can take a single item any number of times he wants and put it in his knapsack .
        Input: n = 3, W = 8, wt = [2, 4, 6], val = [5, 11, 13]
        Output: 22
        Explanation:We can take item with weight 2 (value 5) four times to fill capacity 8,total value = 5 × 4 = 20.
        But a better choice: take item with weight 2 (value 5) twice and item with weight 4 (value 11) once → total weight = 2 + 2 + 4 = 8, total value = 5 + 5 + 11 = 21.
        Even better: take two items with weight 4 (value 11 each), total value = 22, which is maximum.

        Input: n = 2, W = 3, wt = [2, 1], val = [4, 2]
        Output: 6
        Explanation:We can take item with weight 1 (value 2) three times , total value = 6.
        Taking weight 2 (value 4) plus weight 1 (value 2) also gives 6. No combination yields more than 6.
     */
    /*
    Approach: RECURSION
    Time Complexity: Exponential -> in worst case weight[1,1,1,1,1,1] capacity=5 it will be O(Capacity ^N) all have choice=capacity
    Space Complexity= Auxiliary Stack space = O(Capacity)
     */
    private static int getMaxVolume(int[] weight, int[] volume, int capacity, int index){
        if(index==0){
            return (capacity / weight[0]) * volume[0];
        }
        int notPick=getMaxVolume(weight,volume,capacity,index-1);
        int pick=Integer.MIN_VALUE;
        if(weight[index]<=capacity){
            pick=volume[index]+getMaxVolume(weight,volume,capacity-weight[index],index);
        }
        return Math.max(notPick,pick);
    }

    /*
    Approach: MEMOIZATION
    Time Complexity: O(NXK) where K=Capacity
    Space Complexity=O(Capacity)+O(NXK) -> Auxiliary Stack space+ DP array
     */
    private static int getMaxVolume(int[] weight, int[] volume, int capacity, int index,int[][] dp){
        if(index==0){
            return (capacity / weight[0]) * volume[0];
        }
        if(dp[index][capacity] !=-1) return dp[index][capacity];
        int notPick=getMaxVolume(weight,volume,capacity,index-1,dp);
        int pick=Integer.MIN_VALUE;
        if(weight[index]<=capacity){
            pick=volume[index]+getMaxVolume(weight,volume,capacity-weight[index],index,dp);
        }
        return dp[index][capacity]=Math.max(notPick,pick);
    }

    /*
    Approach: TABULATION
    Time Complexity: O(NXK) where K=Capacity
    Space Complexity=O(NXK) -> DP array
     */
    private static int getMaxVolume(int[] weight, int[] volume, int capacity){
        int n=weight.length;
        int[][] dp = new int[n][capacity+1];
        for(int j=0;j<=capacity;j++){
            if(j>=weight[0]) dp[0][j]=(j / weight[0]) * volume[0];
        }
        for(int index=1;index<n;index++){
            for(int j=0;j<=capacity;j++){
                int notPick=dp[index-1][j];
                int pick=Integer.MIN_VALUE;
                if(weight[index]<=j){
                    pick=volume[index]+dp[index][j-weight[index]];
                }
                dp[index][j]=Math.max(notPick,pick);
            }
        }
        return dp[n-1][capacity];
    }
    /*
    Approach: SPACE OPTIMIZATION
    Time Complexity: O(NXK) where K=Capacity
    Space Complexity=O(K)+O(K) -> Prev array+Curr Array
     */

    private static int getMaxVolumeOptimized(int[] weight, int[] volume, int capacity){
        int n=weight.length;
        int[] prev = new int[capacity+1];
        int[] curr = new int[capacity+1];
        for(int j=0;j<=capacity;j++){
            if(j>=weight[0]) prev[j]=(j / weight[0]) * volume[0];
        }
        for(int index=1;index<n;index++){
            for(int j=0;j<=capacity;j++){
                int notPick=prev[j];
                int pick=Integer.MIN_VALUE;
                if(weight[index]<=j){
                    pick=volume[index]+curr[j-weight[index]];
                }
                curr[j]=Math.max(notPick,pick);
            }
            prev=curr.clone();
        }
        return prev[capacity];
    }
    public static void main(String[] args) {
        int[] weight={2,4,6};
        int[] volume={5,11,13};
        int capacity=10;
        int n=weight.length;
        System.out.println(getMaxVolume(weight,volume,capacity,n-1));
        int[][] dp = new int[n][capacity+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getMaxVolume(weight,volume,capacity,n-1,dp));
        System.out.println(getMaxVolume(weight,volume,capacity));
        System.out.println(getMaxVolumeOptimized(weight,volume,capacity));
    }
}
