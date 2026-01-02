package org.dynamicProgramming;

import java.util.Arrays;

public class BFrogJump {
    /*
    Here we are solving every subProblems, So we can think of DP.
    TC: O(2ⁿ)
    SC: O(N) recursion stack
     */
    private static int getMinimumEnergyRequired(int[] energy, int index){
        if(index==0){
            return 0;
        }
        int calculationForOneIndex = getMinimumEnergyRequired(energy,index-1)+Math.abs(energy[index]-energy[index-1]);
        int calculationForTwoIndex=Integer.MAX_VALUE;
        if(index>1)
            calculationForTwoIndex = getMinimumEnergyRequired(energy,index-2)+Math.abs(energy[index]-energy[index-2]);
        return Math.min(calculationForOneIndex,calculationForTwoIndex);
    }

    /*
    Memoization:
    In the below solution we have optimized the solution by memoization to avoid solving already solved sub problems.
    TC: O(N)
    SC: O(N) (recursion stack) + O(N) DP array
     */
    private static int getMinimumEnergyRequired(int[] energy, int index, int[] dp){
        if(index==0){
            return 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        int calculationForOneIndex = getMinimumEnergyRequired(energy,index-1)+Math.abs(energy[index]-energy[index-1]);
        int calculationForTwoIndex=Integer.MAX_VALUE;
        if(index>1)
            calculationForTwoIndex = getMinimumEnergyRequired(energy,index-2)+Math.abs(energy[index]-energy[index-2]);
        return dp[index]=Math.min(calculationForOneIndex,calculationForTwoIndex);
    }
    /*
    Tabulation: Bottom up approach
    Here we have eliminated the recursion stack space, but we are still using DP array.
    TC: O(N) Since we are traversing exactly one time at every index.
    SC: O(N) Using DP array
     */

    private static int getMinimumEnergyRequired(int[] energy){
        int size = energy.length;
        int[] dp = new int[size+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for(int index=1;index<size;index++){
            int calculationForOneIndex = dp[index-1]+Math.abs(energy[index]-energy[index-1]);
            int calculationForTwoIndex=Integer.MAX_VALUE;
            if(index>1)
                calculationForTwoIndex = dp[index-2]+Math.abs(energy[index]-energy[index-2]);
            dp[index] = Math.min(calculationForOneIndex,calculationForTwoIndex);
        }

        return dp[size-1];
    }
/*
   Optimising Space Complexity : Wherever we see things like n-1,n-2 then there is scope of space optimization.
   For below solution:
   TC: O(N)
   SC: O(1)
 */
    private static int getMinimumEnergyRequiredAfterSpaceOptimization(int[] energy){
        int size = energy.length;
        int previous = 0;
        int beforePrevious=0;
        for(int index=1;index<size;index++){
            int calculationForOneIndex = previous+Math.abs(energy[index]-energy[index-1]);
            int calculationForTwoIndex=Integer.MAX_VALUE;
            if(index>1)
                calculationForTwoIndex = beforePrevious+Math.abs(energy[index]-energy[index-2]);
            int current = Math.min(calculationForOneIndex,calculationForTwoIndex);
            beforePrevious=previous;
            previous=current;

        }

        return previous;
    }

    public static void main(String[] args) {
        int[] energy = {30,10,60,10,60,50};
        int size = energy.length;
        System.out.println(getMinimumEnergyRequired(energy,size-1));

        int[] dp = new int[size+1];
        Arrays.fill(dp,-1);
        System.out.println(getMinimumEnergyRequired(energy,size-1,dp));
        System.out.println(getMinimumEnergyRequired(energy));
        System.out.println(getMinimumEnergyRequiredAfterSpaceOptimization(energy));
    }
}
