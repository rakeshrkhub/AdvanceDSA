package org.dynamicProgramming;

import java.util.Arrays;

public class ICountSubsetWithDiffEqualD {
    /*
    Problem Statement : Given an array with N positive integers and an integer D, count the number of ways we can partition the given array into two subsets,
     S1 and S2 such that S1 - S2 = D and S1 is always greater than or equal to S2.
     Input: arr = [1, 1, 2, 3], diff = 1
    Output: 3
    Explanation: The subsets are [1, 2] and [1, 3], [1, 3] and [1, 2], [1, 1, 2] and [3].
    Input:  arr = [1, 2, 3, 4], diff = 2
    Output: 2
    Explanation: The subsets are [1, 3] and [2, 4], [1, 2, 3] and [4].
     */
    public static int getTotalSubsequence(int[] arr, int index, int target,int[][] dp){

        if(index==0){
            if(arr[index]==target && arr[index]==0) return 2;
            if(arr[index]==target || target==0) return 1;
            return 0;
        }
        if(dp[index][target] !=-1) return dp[index][target];
        int notPick=getTotalSubsequence(arr,index-1,target,dp);
        int pick=0;
        if(arr[index]<=target)
            pick=getTotalSubsequence(arr,index-1,target-arr[index],dp);
        return dp[index][target]=pick+notPick;
    }

    public static int getCountOfSubsetPartitionWithDiffD(int[] arr,int givenDiff){
        int n=arr.length;
        // Calculate total sum of array elements
        int totSum = 0;
        for (int val : arr) {
            totSum += val;
        }
        // Initialize dp table with -1 indicating uncalculated states
        int[][] dp = new int[n][totSum + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        if((totSum-givenDiff)%2==1 || (totSum-givenDiff)<0) return 0;
        return getTotalSubsequence(arr,n-1,(totSum-givenDiff)/2,dp);
        /*
        To find:
        s1-s2=D  where s1>s2
        =>TotalSum=s1+s2
        => s1=TotalSum-s2
        => s1-s2 =D
        => TotalSum-s2-s2=D
        => TotalSum-2s2=D
        => 2s2=TotalSum-D
        => s2=(TotalSum-D)/2

        So, edge case that need to handle are:
        (TotalSum-D) should be EVEN
        (TotalSum-D) >0 Since given in question that numbers are positive then sum will always be +ve and hence target will be +ve.
         */

    }
/*
Time Complexity: O(N * K), each state defined by index and target is computed once.
Space Complexity: O(K), extra space is used for storing the dp array.
 */
    public static int countPartitions(int[] arr, int d) {
        // Calculate total sum of array
        int totalSum = 0;
        for (int num : arr) totalSum += num;

        // Check if solution is possible
        if ((totalSum + d) % 2 != 0 || d > totalSum) return 0;

        // Calculate target sum
        int K = (totalSum + d) / 2;

        // Create dp array of size K+1
        int[] dp = new int[K + 1];
        dp[0] = 1;

        // If first element <= K, mark it
        if (arr[0] <= K) dp[arr[0]] += 1;

        // Process remaining elements
        for (int i = 1; i < arr.length; i++) {
            int[] curr = new int[K + 1];
            curr[0] = 1;

            for (int t = 0; t <= K; t++) {
                int notTake = dp[t];
                int take = 0;
                if (arr[i] <= t) {
                    take = dp[t - arr[i]];
                }
                curr[t] = take + notTake;
            }
            dp = curr;
        }
        return dp[K];
    }
    public static void main(String[] args) {
        int[] arr={1,0};
        int givenDiff=1;
        System.out.println(getCountOfSubsetPartitionWithDiffD(arr,givenDiff));
    }
}
