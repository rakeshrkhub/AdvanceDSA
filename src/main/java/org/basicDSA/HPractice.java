package org.basicDSA;

import java.util.Arrays;

public class HPractice {
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

    public static boolean isSubsetSumPresent(int[] arr,int index,int target,int[][] dp){
        if(index==0){
            return arr[0]==target;
        }
        if(target==0) return true;
        if(dp[index][target] !=-1) return true;
        boolean pick=isSubsetSumPresent(arr,index-1,target,dp);
        boolean notPick=false;
        if(arr[index]<=target)
            notPick=isSubsetSumPresent(arr,index-1,target-arr[index],dp);
        dp[index][target]= pick||notPick ?1:0;
        return pick||notPick;
    }

    public static boolean isSubsetSumPresentTabulation(int[] arr,int target,int n){
        boolean[][] dp= new boolean[n][target+1];
        for(int i=0;i<n;i++){
            dp[i][0]=true;
        }
        if(arr[0]<=target){
            dp[0][0]= true;
        }
        for(int index=1;index<n;index++){
            for(int j=1;j<=target;j++){
                boolean pick=dp[index-1][j];
                boolean notPick=false;
                if(arr[index]<=j)
                    notPick=dp[index-1][j-arr[index]];

                dp[index][j]= pick||notPick;
            }
        }
        return dp[n-1][target];
    }
    public static void main(String[] args) {
        int[] arr= {1,2,3,4};
        int n=arr.length;
        int target=4;
        System.out.println(isSubsetSumPresent(arr,arr.length-1,target));
        int[][] dp= new int[n][target+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(isSubsetSumPresent(arr,arr.length-1,target,dp));
        System.out.println(isSubsetSumPresentTabulation(arr,n,target));
    }
}
