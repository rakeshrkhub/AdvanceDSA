package org.dynamicProgramming;

import java.util.Arrays;

public class KCoinChange2 {
    /*
    We are given an array Arr with N distinct coins and a target. We have an infinite supply of each coin denomination. We need to find the number of ways we sum up the coin values to give us the target.

    Example 1:
    Input: coins = [2, 4,10], amount = 10
    Output: 4
    Explanation: The four combinations are:
    10 = 10
    10 = 4 + 4 + 2
    10 = 4 + 2 + 2 + 2
    10 = 2 + 2 + 2 + 2 + 2

    Example 2:
    Input: coins = [5], amount = 5
    Output: 1
    Explanation: There is one combination: 5 = 5.
     */

    /*
    Approach: RECURSION
    Time Complexity: Exponential meaning greater than O(2^N)
    Space Complexity: O(target) since in worst case [1,1,1,1,1] target=5 we have to go till 5.
     */
    private static int getAllWays(int[] coins, int index, int target){
        if(index==0){
            if(target%coins[0]==0) return 1;
            return 0;
        }
        int notPick=getAllWays(coins,index-1,target);
        int pick=0;
        if(coins[index]<=target){
            pick=getAllWays(coins,index,target-coins[index]);
        }
        return pick+notPick;
    }
    /*
    Approach: MEMOIZATION
    Time Complexity: O(N × Target), since each state (ind, T) is computed once.
    Space Complexity: O(N × Target) for the DP table, plus O(Target) recursion stack in the worst case.
     */
    private static int getAllWays(int[] coins, int index, int target,int[][] dp){
        if(index==0){
            if(target%coins[0]==0) return 1;
            return 0;
        }
        if(dp[index][target] !=-1) return dp[index][target];
        int notPick=getAllWays(coins,index-1,target,dp);
        int pick=0;
        if(coins[index]<=target){
            pick=getAllWays(coins,index,target-coins[index],dp);
        }
        return dp[index][target]=pick+notPick;
    }
    /*
    Approach: TABULATION
    Time Complexity: O(N*T), as There are two nested loops.
    Space Complexity: O(N*T), as We are using an external array of size ‘N*T’. Stack Space is eliminated.
     */
    private static int getAllWays(int[] coins, int target){
        int n=coins.length;
        int[][] dp = new int[n][target+1];
        // Initialize base condition for the first element of the array
        for (int i = 0; i <= target; i++) {
            if (i % coins[0] == 0)
                dp[0][i] = 1;
            // Else condition is automatically fulfilled, as dp array is initialized to zero
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=target;j++){
                int notPick=dp[i-1][j];
                int pick=0;
                if(coins[i]<=j){
                    pick=dp[i][j-coins[i]];
                }
                dp[i][j]=pick+notPick;
            }
        }
        return dp[n-1][target];
    }
    /*
    Approach: SPACE OPTIMIZATION
    Time Complexity: O(N*T), as There are two nested loops.
    Space Complexity: O(T), as We are using an external array of size ‘T+1’ to store two rows only.
     */

    private static int getAllWaysOptimized(int[] coins, int target){
        int n=coins.length;
        int[] prev = new int[target+1];
        int[] curr = new int[target+1];
        // Initialize base condition for the first element of the array
        for (int i = 0; i <= target; i++) {
            if (i % coins[0] == 0)
                prev[i] = 1;
            // Else condition is automatically fulfilled, as prev array is initialized to zero
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=target;j++){
                int notPick=prev[j];
                int pick=0;
                if(coins[i]<=j){
                    pick=curr[j-coins[i]];
                }
                curr[j]=pick+notPick;
            }
            prev=curr.clone();
        }
        return prev[target];
    }
    public static void main(String[] args) {
        int[] coins={1,2,3};
        int target=4;
        int n=coins.length;
        System.out.println(getAllWays(coins,n-1,target));
        int[][] dp = new int[n][target+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getAllWays(coins,n-1,target,dp));
        System.out.println(getAllWays(coins,target));
        System.out.println(getAllWaysOptimized(coins,target));
    }
}
