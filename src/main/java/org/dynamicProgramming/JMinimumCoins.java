package org.dynamicProgramming;

import java.util.Arrays;

public class JMinimumCoins {
    /*
    Given an integer array of coins representing coins of different denominations and an integer amount representing a total amount of money.
    Return the fewest number of coins that are needed to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
     There are infinite numbers of coins of each type

     Input: coins = [1, 2, 5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1. We need 3 coins to make up the amount 11.
    Input : coins = [2, 5], amount = 3
    Output: -1
    Explanation :  It's not possible to make amount 3 with coins 2 and 5. Since we can't combine the coin 2 and 5 to make the amount 3, the output is -1.
         */

    /*
    Approach: RECURSION
    Time Complexity: Greater than O(2^N) as we are standing at same index. We acn say TC as EXPONENTIAL
    Space Complexity: Greater than O(N)
     */
    private static int getMinimumCoins(int[] coins, int index, int target){
        if(index==0){
            if(target%coins[0]==0) return target/coins[0];
            return 999999; //Return any big number keeping in mind it will not cause overflow
        }
        int notPick=getMinimumCoins(coins,index-1,target);
        int pick=Integer.MAX_VALUE;
        if(coins[index]<=target){
            pick=1+getMinimumCoins(coins,index,target-coins[index]);
        }
        return Math.min(notPick,pick);
    }
    /*
    Approach: MEMOIZATION
    Time Complexity: O(N*T), there are total of N*T states.
    Space Complexity: O(N*T) + O(N), additional space used to for memo table and recursion stack.
     */
    private static int getMinimumCoins(int[] coins, int index, int target,int[][] dp){
        if(index==0){
            if(target%coins[0]==0) return target/coins[0];
            return 999999; //Return any big number keeping in mind it will not cause overflow
        }
        if(dp[index][target] != -1) return dp[index][target];
        int notPick=getMinimumCoins(coins,index-1,target);
        int pick=Integer.MAX_VALUE;
        if(coins[index]<=target){
            pick=1+getMinimumCoins(coins,index,target-coins[index]);
        }
        return dp[index][target]=Math.min(notPick,pick);
    }

    /*
    Approach: TABULATION
    Time Complexity: O(N*T), there are total of N*T states.
    Space Complexity: O(N*T), additional space used to for memo table.
     */
    private static int getMinimumCoins(int[] coins, int k){
        int n=coins.length;
        int[][] dp = new int[n][k+1];
        for(int j=0;j<=k;j++){
            if(j%coins[0]==0)  dp[0][j]=j/coins[0];
            else dp[0][j]=999999; //Return any big number keeping in mind it will not cause overflow
        }
        for(int index=1;index<n;index++){
            for(int target=0;target<=k;target++){
                int notPick=dp[index-1][target];
                int pick=Integer.MAX_VALUE;
                if(coins[index]<=target){
                    pick=1+dp[index][target-coins[index]];
                }
                dp[index][target]=Math.min(notPick,pick);
            }
        }
        return dp[n-1][k];
    }
    /*
    Approach: SPACE OPTIMIZATION
    Time Complexity: O(N*T), there are total of N*T states.
    Space Complexity: O(T), additional space used to for storing rows.
     */

    private static int getMinimumCoinsOptimized(int[] coins, int k){
        int n=coins.length;
        int[] prev = new int[k+1];
        int[] curr = new int[k+1];
        for(int j=0;j<=k;j++){
            if(j%coins[0]==0)  prev[j]=j/coins[0];
            else prev[j]=999999; //Return any big number keeping in mind it will not cause overflow
        }
        for(int index=1;index<n;index++){
            for(int target=0;target<=k;target++){
                int notPick=prev[target];
                int pick=Integer.MAX_VALUE;
                if(coins[index]<=target){
                    pick=1+prev[target-coins[index]];
                }
                curr[target]=Math.min(notPick,pick);
            }
            prev=curr.clone();
        }
        return prev[k];
    }
    public static void main(String[] args) {
        int[] coins={2,5,10,1};
        int target=27;
        int n=coins.length;
        System.out.println(getMinimumCoins(coins,n-1,target));

        int[][] dp = new int[n][target+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getMinimumCoins(coins,n-1,target,dp));
        System.out.println(getMinimumCoins(coins,target));
        System.out.println(getMinimumCoinsOptimized(coins,target));
    }
}
