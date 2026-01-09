package org.dynamicProgramming;

import java.util.Arrays;

public class HPartitionSetToMakeMinimalDiff {
    /*
    Problem Description: Given an array of n integers,
     partition the array into two subsets such that the absolute difference between their sums is minimized.
     Input: nums = [1, 2, 3, 4]
    Output: 0
    Explanation: Two subsets can be [1,4] and [2,3].
     */
    /*
    Approach: RECURSION

     */
    public static boolean isSubsetSumPresent(int[] arr,int index,int target){
        if(index==0){
            return arr[0]==target;
        }
        if(target==0) return true;
        boolean pick=isSubsetSumPresent(arr,index-1,target);
        boolean notPick=false;
        if(arr[index]<=target)
            notPick=isSubsetSumPresent(arr,index-1,target-arr[index]);
        return pick||notPick;
    }
    public static int getMinimalDiff(int[] arr){
        int n=arr.length;
        int sum=0;
        for(int i=0;i<n;i++){
            sum +=arr[i];
        }
        int result=Integer.MAX_VALUE;
        for(int s=1;s<=sum;s++){
            boolean isPossible=isSubsetSumPresent(arr,n-1,s);
            if(isPossible)
                result= Math.min(result,Math.abs((sum-s)-s));
        }
        return result;
    }

    /*
    Approach: MEMOIZATION
    Time Complexity: O(N*K), there are total N*K states, where N is the length of array and K is the total sum of the array.
    Space Complexity: O(N*K) + O(N), we use a memo table to avoid re-computation. Extra auxiliary stack space is used for recursion.
     */

    public static boolean isSubsetSumPresent(int[] arr,int index,int target,int[][] dp){
        if(index==0){
            return arr[0]==target;
        }
        if(target==0) return true;
        if(dp[index][target] !=-1) return dp[index][target] == 1;;
        boolean pick=isSubsetSumPresent(arr,index-1,target,dp);
        boolean notPick=false;
        if(arr[index]<=target)
            notPick=isSubsetSumPresent(arr,index-1,target-arr[index],dp);
        dp[index][target]=pick||notPick?1:0;
        return pick||notPick;
    }
    public static int getMinimalDiffByMemoization(int[] arr){
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

        // Compute subset sums for all targets from 0 to total sum
        for (int i = 0; i <= totSum; i++) {
            isSubsetSumPresent(arr,n - 1, i, dp);
        }

        // Initialize minimum difference to a large value
        int mini = Integer.MAX_VALUE;

        // Check all possible subset sums in last row of dp table
        for (int s1 = 0; s1 <= totSum; s1++) {
            if (dp[n - 1][s1] == 1) {
                // Calculate other subset sum and difference
                int s2 = totSum - s1;
                int diff = Math.abs(s1 - s2);

                // Update minimum difference
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    /*
    Approach: Tabulation
    Time Complexity: O(N*K), there are total N*K states, where N is the length of array and K is the total sum of the array.
    Space Complexity: O(N*K) , we use a 2D DP array to avoid recomputation.
     */

    // Function to find the minimum absolute difference between two subset sums
    public static int minSubsetSumDifference(int[] arr, int n) {
        int totSum = 0;

        // Calculate the total sum of the array
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }

        // Initialize a DP table to store the results of the subset sum problem
        boolean[][] dp = new boolean[n][totSum + 1];

        // Base case: If no elements are selected (sum is 0), it's a valid subset
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // Initialize the first row based on the first element of the array
        if (arr[0] <= totSum)
            dp[0][arr[0]] = true;

        // Fill in the DP table using a bottom-up approach
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= totSum; target++) {
                // Exclude the current element
                boolean notTaken = dp[ind - 1][target];

                // Include the current element if it doesn't exceed the target
                boolean taken = false;
                if (arr[ind] <= target)
                    taken = dp[ind - 1][target - arr[ind]];

                dp[ind][target] = notTaken || taken;
            }
        }

        int mini = Integer.MAX_VALUE;
        for (int i = 0; i <= totSum; i++) {
            if (dp[n - 1][i]) {
                // Calculate the absolute difference between two subset sums
                int diff = Math.abs(i - (totSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }
    /*
    Approach: Space Optimization
    Time Complexity: O(N*K), there are total N*K states, where N is the length of array and K is the total sum of the array.
    Space Complexity: O(N), we use two 1D arrays to store value of previous row and current row.
     */
    public static int minSubsetSumDifferenceOptimized(int[] arr, int n) {
        int totSum = 0;

        // Calculate the total sum of the array
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }

        // Initialize a boolean array 'prev' to represent the previous row of the DP table
        boolean[] prev = new boolean[totSum + 1];

        // Base case: If no elements are selected (sum is 0), it's a valid subset
        prev[0] = true;

        // Initialize the first row based on the first element of the array
        if (arr[0] <= totSum)
            prev[arr[0]] = true;

        // Fill in the DP table using a bottom-up approach
        for (int ind = 1; ind < n; ind++) {
            // Create a boolean array 'cur' to represent the current row of the DP table
            boolean[] cur = new boolean[totSum + 1];
            cur[0] = true;

            for (int target = 1; target <= totSum; target++) {
                // Exclude the current element
                boolean notTaken = prev[target];

                // Include the current element if it doesn't exceed the target
                boolean taken = false;
                if (arr[ind] <= target)
                    taken = prev[target - arr[ind]];

                cur[target] = notTaken || taken;
            }

            // Set 'cur' as the 'prev' for the next iteration
            prev = cur;
        }

        int mini = Integer.MAX_VALUE;
        for (int i = 0; i <= totSum; i++) {
            if (prev[i]) {
                // Calculate the absolute difference between two subset sums
                int diff = Math.abs(i - (totSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    public static void main(String[] args) {
        int[] arr={8,6,5};
        System.out.println(getMinimalDiff(arr));
        System.out.println(getMinimalDiffByMemoization(arr));
        System.out.println(minSubsetSumDifference(arr,arr.length));
        System.out.println(minSubsetSumDifferenceOptimized(arr,arr.length));
    }
}
