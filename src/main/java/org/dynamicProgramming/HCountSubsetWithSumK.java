package org.dynamicProgramming;

import java.util.Arrays;

public class HCountSubsetWithSumK {
    public static int getTotalSubsequence(int[] arr, int index, int target){
        if(target==0) return 1;
        if(index==0){
            if(arr[index]==target) return 1;
            return 0;
        }
        int notPick=getTotalSubsequence(arr,index-1,target);
        int pick=0;
        if(arr[index]<=target)
            pick=getTotalSubsequence(arr,index-1,target-arr[index]);
        return pick+notPick;
    }

    public static int getTotalSubsequence(int[] arr, int index, int target,int[][] dp){
        if(target==0) return 1;
        if(index==0){
            if(arr[index]==target) return 1;
            return 0;
        }
        if(dp[index][target] !=-1) return dp[index][target];
        int notPick=getTotalSubsequence(arr,index-1,target,dp);
        int pick=0;
        if(arr[index]<=target)
            pick=getTotalSubsequence(arr,index-1,target-arr[index],dp);
        return dp[index][target]=pick+notPick;
    }
    public static int getTotalSubsequenceTabulation(int[] arr, int target){
        int n=arr.length;
        int[][] dp= new int[n][target+1];
        for(int i=0;i<n;i++){
            dp[i][0]=1;
        }
        if(arr[0]<=target) dp[0][arr[0]]=1;
        for(int index=1;index<n;index++){
            for(int sum=0;sum<=target;sum++){
                int notPick=dp[index-1][sum];
                int pick=0;
                if(arr[index]<=sum)
                    pick=dp[index-1][sum-arr[index]];
                 dp[index][sum]=pick+notPick;
            }
        }
        return dp[n-1][target];
    }

    public static int getTotalSubsequenceSpaceOptimized(int[] arr, int target){
        int n=arr.length;
        int[] prev= new int[target+1];
        int[] curr= new int[target+1];
        prev[0]=1;
        curr[0]=1;
        if(arr[0]<=target) prev[arr[0]]=1;
        for(int index=1;index<n;index++){
            for(int sum=0;sum<=target;sum++){
                int notPick=prev[sum];
                int pick=0;
                if(arr[index]<=sum)
                    pick=prev[sum-arr[index]];
                curr[sum]=pick+notPick;
            }
            prev=curr.clone();
        }
        return prev[target];
    }
    /*
    NOTE:

    If array will be like [0,0,1] and target =1
    Then the base case in memoization will be change:
    if(target==0) return 1;
        if(index==0){
            if(arr[index]==target) return 1;
            return 0;
        }

        to

        if(index==0){
        if(target==0 && arr[index]==0) return 2;
         if(arr[0]==0 || arr[index]==target) return 1;
            return 0;
        }
     */
    public static void main(String[] args) {
        int[] arr={1,2,2,3};
        int n=arr.length;
        int target=3;
        System.out.println(getTotalSubsequence(arr,n-1,target));

        int[][] dp= new int[n][target+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getTotalSubsequence(arr,n-1,target,dp));
        System.out.println(getTotalSubsequenceTabulation(arr,target));
        System.out.println(getTotalSubsequenceSpaceOptimized(arr,target));

    }
}
