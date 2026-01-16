package org.dynamicProgramming;

public class MLongestCommonSubstring {
    public static int longestCommonSub(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Initialize base cases (first row and column to 0)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }
        int ans=0;
        // Fill the DP table
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {

                // Characters match → take diagonal + 1
                if (str1.charAt(ind1 - 1) == str2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    ans=Math.max(ans,dp[ind1][ind2]);
                }
                    // Characters don't match → take max from left or above
                else
                    dp[ind1][ind2] = 0;
            }
        }

        return ans;
    }

    public static int longestCommonSubSpaceOptimization(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[] prev = new int[m + 1]; //will consider the greater length between 2
        int[] curr = new int[m + 1];
        int ans=0;
        // Fill the DP table
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {

                // Characters match → take diagonal + 1
                if (str1.charAt(ind1 - 1) == str2.charAt(ind2 - 1)) {
                    curr[ind2] = 1 + prev[ind2 - 1];
                    ans=Math.max(ans,curr[ind2]);
                }
                // Characters don't match → take max from left or above
                else
                    curr[ind2] = 0;
            }
            prev=curr.clone();
        }

        return ans;
    }
    public static void main(String[] args) {
        String s1="abcd";
        String s2="abdd";
        System.out.println(longestCommonSub(s1,s2));
        System.out.println(longestCommonSubSpaceOptimization(s1,s2));

    }
}
