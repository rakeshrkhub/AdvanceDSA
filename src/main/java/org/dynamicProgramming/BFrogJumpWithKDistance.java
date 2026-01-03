package org.dynamicProgramming;

import java.util.Arrays;

public class BFrogJumpWithKDistance {

    private static int getMinimumEnergyRequired(int[] energy,int k){
        int size = energy.length;
        int[] dp = new int[size+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for(int index=1;index<size;index++){
            int minEnergy=Integer.MAX_VALUE;
            for(int j=1;j<=k;j++) {
                if(index-j>=0){
                    int calculationForCurrIndex = dp[index - j] + Math.abs(energy[index] - energy[index - j]);
                    minEnergy = Math.min(calculationForCurrIndex, minEnergy);
                }
            }
            dp[index]=minEnergy;
        }

        return dp[size-1];
    }
    public static void main(String[] args) {
        int[] energy = {30,10,60,10,60,50};
        System.out.println(getMinimumEnergyRequired(energy,2));

    }
}
