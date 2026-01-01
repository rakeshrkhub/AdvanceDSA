package org.dynamicProgramming;

import java.util.Arrays;

public class AFibboNumber {
    /*
    Fibonacci Number:
    0 1 1 2 3 5 8:
    0th: 0,
    1st: 1
    2nd: 1
    3rd: 2
    4th: 3
    fn(n)= fn(n-1)+fn(n-2)
     */

    private static int fiboNacci(int n,int[] dp){
        if(n<=1){
            return dp[n]=n;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        return dp[n]=fiboNacci(n-1,dp)+fiboNacci(n-2,dp);
    }
    //TC: O(N)
    //SC: O(N)(dp array)+O(N) (Recursion stack)

    private static int fiboNacciTabular(int n){
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    //Removed the stack space complexity

    private static int fiboNacciWithVariable(int n){
        int secondPrev=0;
        int prev=1;
        for(int i=2;i<=n;i++){
            int curr=prev+secondPrev;
            secondPrev=prev;
            prev=curr;
        }
        return prev;
    }
    public static void main(String[] args) {
        int size=6;
        int[] dp = new int[7];
        Arrays.fill(dp,-1);
        System.out.println(fiboNacci(size,dp));
        System.out.println(fiboNacciTabular(6));
        System.out.println(fiboNacciWithVariable(6));
        //TC: O(N), SC: O(N) -> O(N) -> O(1)
    }
}
