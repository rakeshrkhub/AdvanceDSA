package org.dynamicProgramming;

import java.util.Arrays;

public class CHouseRobberOrMaxSumOfNonAdjacent {
    /*
    There are n houses built in a line, each of which contains some money in it.
    A robber wants to steal money from these houses, but he can’t steal from two adjacent houses.
    The task is to find the maximum amount of money which can be stolen.

    Examples:

    Input: hval[] = {6, 7, 1, 3, 8, 2, 4}
    Output: 19
    Explanation: The thief will steal from house 1, 3, 5 and 7, total money = 6 + 1 + 8 + 4 = 19.

    Input: hval[] = {5, 3, 4, 11, 2}
    Output: 16
    Explanation: Thief will steal from house 1 and 4, total money = 5 + 11 = 16.
     */

    /*
    Here we are solving using recursion which is solving overlapping problem too. If we want to see the overlapping problem then
    we can observe by drawing recursion tree.
    TC: O(2^N)
    SC: O(N) recursion stack space
     */
    private static int getMaxMoney(int index, int[] house){
        if(index==0){
            return house[index];
        }
        if(index<0) return 0;
        int pick = getMaxMoney(index-2,house)+house[index];
        int notPick = getMaxMoney(index - 1, house)+0; //No need to write +0 but writing for revision purpose

        return Math.max(pick,notPick);

    }

    /*
    Now I will optimise it using dp MEMOIZATION.
    TC: O(N)
    SC:O(N) + O(N)
     */
    private static int getMaxMoney(int index, int[] house,int[] dp){
        if(index==0){
            return house[index];
        }
        if(index<0) return 0;
        if(dp[index] !=-1){
            return dp[index];
        }
        int pick = getMaxMoney(index-2,house)+house[index];
        int notPick = getMaxMoney(index - 1, house)+0; //No need to write +0 but writing for revision purpose

        return dp[index]=Math.max(pick,notPick);

    }

    /*
    Now I will optimise the space complexity using Tabulation.
    TC: O(N)
    SC:O(N)
     */
    private static int getMaxMoney(int[] house){
        int n= house.length;
        int[] dp = new int[n+1];
        dp[0] = house[0];
        for(int index=1;index<n;index++){
            int pick=house[index];
            if(index>1)
                pick += dp[index-2];
            int notPick = dp[index - 1];
            dp[index]=Math.max(pick,notPick);
        }

        return dp[n-1];

    }

    /*
    Now I will optimise the space complexity using variable instead of dp array.
    TC: O(N)
    SC:O(1)
     */
    private static int getMaxMoneyOptimized(int[] house){
        int n= house.length;
        int secondPrev=0;
        int prev = house[0];
        for(int index=1;index<n;index++){
            int pick= house[index];
            if(index>1) pick += secondPrev;
            int notPick = prev;
            int currentRobbed=Math.max(pick,notPick);
            secondPrev=prev;
            prev=currentRobbed;
        }
        return prev;
    }
    public static void main(String[] args) {
        int[] house = {5, 3, 4, 11, 2};
        int n = house.length;
        System.out.println(getMaxMoney(n-1,house));
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(getMaxMoney(n-1,house,dp));
        System.out.println(getMaxMoney(house));
        System.out.println(getMaxMoneyOptimized(house));
    }
}
