package org.dynamicProgramming;

import java.util.Arrays;

public class GSubsetSumEqualToTarget {
    /*
    We are given an array ‘ARR’ with N positive integers. We need to find if there is a subset in “ARR” with a sum equal to K. If there is, return true else return false.

    A subset/subsequence is a contiguous or non-contiguous part of an array, where elements appear in the same order as the original array.
    For example, for the array: [2,3,1] , the subsequences will be [{2},{3},{1},{2,3},{2,1},{3,1},{2,3,1}} but {3,2} is not a subsequence because its elements are not in the same order as the original array.
     */
    /*
    Approach: Recursion
    TC: O(2^N)
    SC: O(N) recursion stack space
     */
    private static boolean getAllSubsequence(int[] arr, int index,int target){
        if(target==0) return true;
        if(index==0) return arr[0]==target;
        boolean notPick=getAllSubsequence(arr,index-1,target);

        boolean pick=false;
        if(arr[index]<=target)
            pick=getAllSubsequence(arr,index-1,target-arr[index]);

        return pick||notPick;
    }
    /*
    Approach: MEMOIZATION
    Steps to memoize a recursive solution:

    If we draw the recursion tree, we will see that there are overlapping subproblems. In order to convert a recursive solution the following steps will be taken:
    Create a dp array of size [n][k+1]. The size of the input array is ‘n’, so the index will always lie between ‘0’ and ‘n-1’. The target can take any value between ‘0’ and ‘k’. Therefore we take the dp array as dp[n][k+1]
    We initialize the dp array to -1.
    Whenever we want to find the answer of particular parameters (say f(ind,target)), we first check whether the answer is already calculated using the dp array(i.e dp[ind][target]!= -1 ). If yes, simply return the value from the dp array.
    If not, then we are finding the answer for the given value for the first time, we will use the recursive relation as usual but before returning from the function, we will set dp[ind][target] to the solution we get.

    Time Complexity: O(N*K),There are N*K states therefore at max ‘N*K’ new problems will be solved.

    Space Complexity: O(N*K) + O(N),We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
         */
    private static boolean getAllSubsequence(int[] arr, int index,int target,int[][] dp){
        if(target==0) return true;
        if(index==0) return arr[0]==target;
        if(dp[index][target] !=-1) return dp[index][target]==1;
        boolean notPick=getAllSubsequence(arr,index-1,target);
        boolean pick=false;
        if(arr[index]<=target)
            pick=getAllSubsequence(arr,index-1,target-arr[index]);
        dp[index][target]=pick||notPick?1:0;
        return pick|| notPick;
    }

    /*
    Approach: Tabulation
    To convert the memoization approach to a tabulation one, create a dp array with the same size as done in memoization. We can set its type as bool and initialize it as false. First, we need to initialize the base conditions of the recursive solution.
    If target == 0, ind can take any value from 0 to n-1, therefore we need to set the value of the first column as true.
    The first row dp[0][] indicates that only the first element of the array is considered, therefore for the target value equal to arr[0], only cell with that target will be true, so explicitly set dp[0][arr[0]] =true, (dp[0][arr[0]] means that we are considering the first element of the array with the target equal to the first element itself). Please note that it can happen that arr[0]>target, so we first check it: if(arr[0]<=target) then set dp[0][arr[0]] = true.
    After that , we will set our nested for loops to traverse the dp array and following the logic discussed in the recursive approach, we will set the value of each cell. Instead of recursive calls, we will use the dp array itself.
    At last we will return dp[n-1][k] as our answer.

    Time Complexity: O(N*K),There are two nested loops
    Space Complexity: O(N*K), We are using an external array of size ‘N*K’. Stack Space is eliminated.
     */

    private static boolean getAllSubsequence(int[] arr, int k){
        int n=arr.length;
        // Create DP table with n rows and k+1 columns, default false
        boolean[][] dp = new boolean[n][k + 1];
        // Base case: sum=0 can always be formed by empty subset
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // Base case: If first element <= k, mark dp[0][arr[0]] true
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }
        // Fill the DP table iteratively
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                // Option 1: Do not take the current element
                boolean notTaken = dp[ind - 1][target];
                // Option 2: Take current element if it does not exceed target
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[ind - 1][target - arr[ind]];
                }
                // Mark current cell as true if either option is true
                dp[ind][target] = notTaken || taken;
            }
        }

        // Return whether sum k can be formed using all elements
        return dp[n - 1][k];
    }

    /*
    If we closely look the relation,
dp[ind][target] =  dp[ind-1][target] || dp[ind-1][target-arr[ind]]

We see that to calculate a value of a cell of the dp array, we need only the previous row values (say prev). So, we don’t need to store an entire array. Hence we can space optimize it.
Note: Whenever we create a new row ( say cur), we need to explicitly set its first element is true according to our base condition.

    Time Complexity: O(N*K),There are three nested loops

    Space Complexity: O(K),We are using an external array of size ‘K+1’ to store only one row.
     */

    public static boolean subsetSumToK(int n, int k, int[] arr) {
        // Initialize previous row of DP table with false
        boolean[] prev = new boolean[k + 1];
        // Base case: sum 0 can always be formed with empty subset
        prev[0] = true;
        // Base case: if first element <= k, mark true
        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }
        // Iterate over elements starting from second
        for (int ind = 1; ind < n; ind++) {
            // Current row of DP table
            boolean[] cur = new boolean[k + 1];
            cur[0] = true; // sum 0 always possible
            for (int target = 1; target <= k; target++) {
                // Option 1: not take current element
                boolean notTaken = prev[target];

                // Option 2: take current element if possible
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = prev[target - arr[ind]];
                }

                // Store true if either option is true
                cur[target] = notTaken || taken;
            }
            // Move current row to previous for next iteration
            prev = cur;
        }
        // Return if sum k is possible using all elements
        return prev[k];
    }

    public static void main(String[] args) {
        int[] arr={1,2,2};
        int target=4;
        int n=arr.length;
        System.out.println(getAllSubsequence(arr,n-1,target));

        int[][] dp = new int[n][target+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(getAllSubsequence(arr,n-1,target,dp));
    }
}
