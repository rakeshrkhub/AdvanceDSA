package org.dynamicProgramming;

public class JTargetSum {
    /*
    You are given an integer array nums and an integer target.

    You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

    For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
    Return the number of different expressions that you can build, which evaluates to target.

    Example 1:

    Input: nums = [1,1,1,1,1], target = 3
    Output: 5
    Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
    -1 + 1 + 1 + 1 + 1 = 3
    +1 - 1 + 1 + 1 + 1 = 3
    +1 + 1 - 1 + 1 + 1 = 3
    +1 + 1 + 1 - 1 + 1 = 3
    +1 + 1 + 1 + 1 - 1 = 3
    Example 2:

    Input: nums = [1], target = 1
    Output: 1
     */
    /*
    This question is same as "Partition array into 2 parts such that their difference equal to target"
    Here we can think like we are doing partition of array into 2 part
    One partition with +ve sign and Another Partition with -ve Sign
    When we add both of them it will be like (Sum of +ve signed element) - (Sum of -ve Signed elements) this should be equal to
    given target.
    So, it is same as This question is same as "Partition array into 2 parts such that their difference equal to target".
    Solved in ICountSubsetWithDiffEqualD class.
    We can call the method of ICountSubsetWithDiffEqualD without any modification.
     */
}
