package org.dynamicProgramming;

import java.util.Arrays;

public class LLongestCommonSubsequence {
    /*
    Given two strings str1 and str2, find the length of their longest common subsequence.
    A subsequence is a sequence that appears in the same relative order but not necessarily contiguous and a
    common subsequence of two strings is a subsequence that is common to both strings.
    Example 1:
    Input:
     str1 = "bdefg", str2 = "bfg"
    Output:
     3
    Explanation:
     The longest common subsequence is "bfg", which has a length of 3.

    Example 2:
    Input:
     str1 = "mnop", str2 = "mnq"
    Output:
     2
    Explanation:
     The longest common subsequence is "mn", which has a length of 2.
     */
    /*
    Approach: RECURSION

     */
    public static int getLongestSubsequence(String s1, String s2, int index1, int index2){
        if(index1<0 || index2<0){
            return 0;
        }
        if(s1.charAt(index1)==s2.charAt(index2)){
            return 1+getLongestSubsequence(s1,s2,index1-1,index2-1);
        }
        else{
            return Math.max(getLongestSubsequence(s1,s2,index1-1,index2),getLongestSubsequence(s1,s2,index1,index2-1));
        }
    }
    /*
    Approach: MEMOIZATION
    Time Complexity: O(n * m), where n is the length of str1 and m is the length of str2. This is because we are using a 2D DP array to store results for all pairs of indices.
    Space Complexity: O(N*M) + O(N+M), We are using an auxiliary recursion stack space(O(N+M)) and a 2D array (O(N*M)).
     */
    public static int getLongestSubsequence(String s1, String s2, int index1, int index2,int[][] dp){
        if(index1<0 || index2<0){
            return 0;
        }
        if(dp[index1][index2] !=-1) return dp[index1][index2];
        if(s1.charAt(index1)==s2.charAt(index2)){
            return dp[index1][index2]=1+getLongestSubsequence(s1,s2,index1-1,index2-1);
        }
        else{
            return dp[index1][index2]=Math.max(getLongestSubsequence(s1,s2,index1-1,index2),getLongestSubsequence(s1,s2,index1,index2-1));
        }
    }
    /*
    Approach: Tabulation
    Time Complexity: O(n * m), where n is the length of str1 and m is the length of str2. This is because we are filling a 2D DP array of size (n+1) x (m+1).
    Space Complexity: O(n * m), as we are using a 2D DP array to store results for all pairs of indices.
     */

    public static int longestCommonSub(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // DP table of size (n+1) x (m+1)
        int[][] dp = new int[n + 1][m + 1];

        // Initialize base cases (first row and column to 0)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        // Fill the DP table
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {

                // Characters match → take diagonal + 1
                if (str1.charAt(ind1 - 1) == str2.charAt(ind2 - 1))
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    // Characters don't match → take max from left or above
                else
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
            }
        }

        // Result is in dp[n][m]
        return dp[n][m];
    }

    /*
    Approach: Space Optimization
    Time Complexity: O(n * m), where n is the length of str1 and m is the length of str2. This is because we are still filling a 2D DP table, but now using only two rows.
    Space Complexity: O(m), where m is the length of str2. We are using only two arrays of size m+1 to store the current and previous rows of the DP table.
     */

    public int longestCommonSubsequenceOptimized(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // Arrays to store previous and current row of DP table
        int[] prev = new int[m + 1];
        int[] cur = new int[m + 1];

        // Base case already handled by arrays initialized to 0
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                // If characters match → diagonal + 1
                if (str1.charAt(ind1 - 1) == str2.charAt(ind2 - 1))
                    cur[ind2] = 1 + prev[ind2 - 1];
                    // Else → take max from top or left
                else
                    cur[ind2] = Math.max(prev[ind2], cur[ind2 - 1]);
            }
            // Copy current row to previous row for next iteration
            System.arraycopy(cur, 0, prev, 0, m + 1);
        }

        // Result is in prev[m]
        return prev[m];
    }

    //Print The resultant combination
    public static String printLongestCommonSub(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        // DP table of size (n+1) x (m+1)
        int[][] dp = new int[n + 1][m + 1];

        // Initialize base cases (first row and column to 0)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        // Fill the DP table
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {

                // Characters match → take diagonal + 1
                if (str1.charAt(ind1 - 1) == str2.charAt(ind2 - 1))
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    // Characters don't match → take max from left or above
                else
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
            }
        }

        // Resultant length is in dp[n][m]
        int length=dp[n][m];
        int i=n,j=m;
        StringBuilder ans= new StringBuilder();
        while(i>0 && j>0){
            if(str1.charAt(i-1)==str2.charAt(j-1)){
                ans.append(str1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j]>dp[i][j-1]) {
                i--;
            }else {
                j--;
            }
        }
        return ans.reverse().toString();
    }
    public static void main(String[] args) {
        String s1="adebc";
        String s2="dcadb";
        System.out.println(getLongestSubsequence(s1,s2,s1.length()-1,s2.length()-1));
        int n=s1.length();
        int m=s2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getLongestSubsequence(s1,s2,s1.length()-1,s2.length()-1,dp));
        System.out.println(printLongestCommonSub(s1,s2));
    }
}
