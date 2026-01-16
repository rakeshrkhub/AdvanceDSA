package org.dynamicProgramming;

import java.util.Arrays;

public class NMinOperationToMakeStringPalindrom {
    /*
    Given a string s. In one step you can insert any character at any index of the string.

    Return the minimum number of steps to make s palindrome.

    A Palindrome String is one that reads the same backward as well as forward.

    Example 1:

    Input: s = "zzazz"
    Output: 0
    Explanation: The string "zzazz" is already palindrome we do not need any insertions.
    Example 2:

    Input: s = "mbadm"
    Output: 2
    Explanation: String can be "mbdadbm" or "mdbabdm".
    Example 3:

    Input: s = "leetcode"
    Output: 5
    Explanation: Inserting 5 characters the string becomes "leetcodocteel".
     */
    private static int getLongestSubsequence(String s1, String s2, int index1, int index2,int[][] dp){
        if(index1<0 || index2<0) return 0;
        if(dp[index1][index2] !=-1) return dp[index1][index2];
        if(s1.charAt(index1)==s2.charAt(index2)){
            return dp[index1][index2]=1+getLongestSubsequence(s1,s2,index1-1,index2-1,dp);
        }
        return dp[index1][index2]=Math.max(getLongestSubsequence(s1,s2,index1-1,index2,dp),getLongestSubsequence(s1,s2,index1,index2-1,dp));
    }
    private static int callLongestSubsequence(String s1){
        StringBuilder s2=new StringBuilder(s1);
        String reverse=s2.reverse().toString();
        int n=s1.length();
        int[][] dp= new int[n+1][n+1];
        for(int i=0;i<=n;i++) Arrays.fill(dp[i],-1);
        return n-getLongestSubsequence(s1,reverse,n-1,n-1,dp);
    }
    public static void main(String[] args) {
        String s="leetcode";
        System.out.println(callLongestSubsequence(s));
    }
}
