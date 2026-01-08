package org.dynamicProgramming;

public class GPartitionEqualSubsetSum {
    /*
    Given an array arr of n integers, return true if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal else return false.
    Input: nums = [2, 3, 3, 3, 4, 5]
    Output: True
    Explanation: Nums can be partitioned into two subsets of sum 10.
    Input: nums = [1, 2, 3, 5]
    Output: False
    Explanation: The array cannot be partitioned into equal sum subsets.
     */
    /*

    This the same question As Subset Sum equal to K.
    Just calculate sum of elements of array and divide it by 2. This will be the target sum.
    int n=arr.length;
     int sum=0;
     for(inti=0;i<arr.length;i++){
     sum +=arr[i];
     }
     Point to keep in mind:
     if(sum%2==1) return false;
     else{
     call the method subsetSumToK(n,sum,arr);     //subsetSumToK(int n, int k, int[] arr)
     }

     */
    public static void main(String[] args) {

    }
}
