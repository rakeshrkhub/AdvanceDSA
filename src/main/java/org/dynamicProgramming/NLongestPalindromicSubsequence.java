package org.dynamicProgramming;

import java.util.Arrays;

public class NLongestPalindromicSubsequence {
    /*
    Given a string s, find the longest palindromic subsequence's length in s.

    A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

    Example 1:

    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb".
    Example 2:

    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb".
     */
    private static int getLongestSubsequence(String s1, String s2, int index1, int index2){
        if(index1<0 || index2<0) return 0;
        if(s1.charAt(index1)==s2.charAt(index2)){
            return 1+getLongestSubsequence(s1,s2,index1-1,index2-1);
        }
        return Math.max(getLongestSubsequence(s1,s2,index1-1,index2),getLongestSubsequence(s1,s2,index1,index2-1));
    }

    private static int getLongestSubsequence(String s1, String s2, int index1, int index2,int[][] dp){
        if(index1<0 || index2<0) return 0;
        if(dp[index1][index2] !=-1) return dp[index1][index2];
        if(s1.charAt(index1)==s2.charAt(index2)){
            return dp[index1][index2]=1+getLongestSubsequence(s1,s2,index1-1,index2-1,dp);
        }
        return dp[index1][index2]=Math.max(getLongestSubsequence(s1,s2,index1-1,index2,dp),getLongestSubsequence(s1,s2,index1,index2-1,dp));
    }
    public static void main(String[] args) {
        String s1="bbbab";
        StringBuilder s2=new StringBuilder(s1);
        String reverse=s2.reverse().toString();
        int n=s1.length();
        int[][] dp= new int[n+1][n+1];
        for(int i=0;i<=n;i++) Arrays.fill(dp[i],-1);
        System.out.println(getLongestSubsequence(s1,reverse,n-1,n-1));
        System.out.println(getLongestSubsequence(s1,reverse,n-1,n-1,dp));
    }
}
