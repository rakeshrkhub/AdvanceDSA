package org.basicDSA;

import java.util.Arrays;

public class InterviewQuestion {
    /*
    You are given an array of non-negative integers where each element represents the amount of money in a house.
     You cannot rob two adjacent houses because of security alarms. Find the maximum amount of money you can rob.
     Input: [2,7,9,3,1]
    Output: 12
    TC=2^N
    SC=O(N)
     */
    /*
    f(ind){
    if(ind<0){
    return 0;
    }
    if(ind==0){
    return arr[ind];
    }
     int notTake=fn(ind-1);
     int take=Integer.MIN_VALUE;
     if(ind>0){
        take=arr[ind]+fn(ind-2);
        }
        return Math.max(notTake,take);
    }
     */
    //TC=O(N)
    //SC:O(N)+O(N)=O(N)
    public static int getMaximum(int[] house, int index, int[] dp){
        if(index<0) return 0;
        if(index==0) return house[index];
        if(dp[index] !=-1) return dp[index];
        int notPick=getMaximum(house,index-1,dp);
        int pick=Integer.MIN_VALUE;
        if(index>0){
            pick=house[index]+getMaximum(house,index-2,dp);
        }
        return dp[index]=Math.max(notPick,pick);
    }

    public static void main(String[] args) {
        int[] arr={2,7,9,3,1};
        int n=arr.length;
        int[] dp= new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(getMaximum(arr,n-1,dp));
    }
}
