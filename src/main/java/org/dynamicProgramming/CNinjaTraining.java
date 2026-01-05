package org.dynamicProgramming;

import java.util.Arrays;

public class CNinjaTraining {
    /*
    Geek is going for a training program for n days. He can perform any of these activities: Running,
    Fighting, and Learning Practice. Each activity has some point on each day. As Geek wants to improve all his skills,
    he can't do the same activity on two consecutive days. Given a 2D array arr[][] of size n where arr[i][0], arr[i][1],
    and arr[i][2] represent the merit points for Running,
    Fighting, and Learning on the i-th day, determine the maximum total merit points Geek can achieve .
    Example 1:
Input: matrix = [[10, 40, 70], [20, 50, 80], [30, 60, 90]]
Output: 210
Explanation:
Day 1: fighting practice = 70
Day 2: stealth training = 50
Day 3: fighting practice = 90
Total = 70 + 50 + 90 = 210
This gives the optimal points.

Example 2:
Input: matrix = [[70, 40, 10], [180, 20, 5], [200, 60, 30]]
Output: 290
Explanation:
Day 1: running = 70
Day 2: stealth training = 20
Day 3: running = 200
Total = 70 + 20 + 200 = 290
This gives the optimal points.
     */

    /*
    Approach 1: Recursion, here we are solving overlapping sub problems;
    Time Complexity = O(2ⁿ)
    Space Complexity = O(N) Recursion stack space
     */

    private static int getMaxPoint(int[][] points, int day, int lastActivity){ //taking day variable for index or row only
        if(day==0){
            int maxReward=0;
            for(int task=0;task<3;task++){
                if(task !=lastActivity){
                    maxReward=Math.max(maxReward,points[day][task]);
                }
            }
            return maxReward;
        }

        int maxPoints=0;
        for(int task=0;task<3;task++){
            if(task !=lastActivity) {
                int currentReward = points[day][task] + getMaxPoint(points, day - 1, task);
                maxPoints = Math.max(maxPoints, currentReward);
            }
        }
        return maxPoints;
    }

    /*
    Approach 2: Memoization, Since we were solving overlapping sub problems in Approach 1 that's why we can think of DP.;
    Time Complexity = O(N)
    Space Complexity = O(N) + O(N) Recursion stack space
     */

    private static int getMaxPoint(int[][] points, int day, int lastActivity, int[][] dp){ //taking day variable for index or row only
        //check if already calculated
        if(dp[day][lastActivity] != -1){
            return dp[day][lastActivity];
        }

        if(day==0){
            int maxReward=0;
            for(int task=0;task<3;task++){
                if(task !=lastActivity){
                    maxReward=Math.max(maxReward,points[day][task]);
                }
            }
            //store the calculated value at particular index in dp array
            return dp[day][lastActivity]=maxReward;
        }

        int maxPoints=0;
        for(int task=0;task<3;task++){
            if(task !=lastActivity) {
                int currentReward = points[day][task] + getMaxPoint(points, day - 1, task);
                maxPoints = Math.max(maxPoints, currentReward);
            }
        }
        //store the calculated value at particular index in dp array
        return dp[day][lastActivity]=maxPoints;
    }

    /*
    Approach 3: Tabulation, Now we are optimizing space complexity using Tabulation.
    Time Complexity = O(N)
    Space Complexity = O(N)
     */

    private static int getMaxPoint(int[][] points){
        int n=points.length;
        int m=points[0].length;
        int[][] dp = new int[n][m+1];

        // Day 0
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(dp[0][0], Math.max(dp[0][1], dp[0][2]));

        for(int day = 1; day < n; day++){ //Iterating each row/day
            for(int lastActivity=0;lastActivity<=m;lastActivity++){ //Iterating on each task (0-3) 3 for none at very beginning Ninja didn't perform any task
                dp[day][lastActivity]=0;
                for(int task=0;task<m;task++){ // Checking each possibility if lastActivity is given
                    if(lastActivity != task){
                        int currentPoints=points[day][task]+dp[day-1][task];
                        dp[day][lastActivity]=Math.max(dp[day][lastActivity],currentPoints);
                    }
                }
            }
        }
        return dp[n-1][m];
    }

    /*
    Approach 4: Space Optimization.
    Time Complexity = O(N)
    Space Complexity = O(N)
     */

    private static int getMaxPointOptimized(int[][] points){
        int n=points.length;
        int m=points[0].length;
        int[] previous = new int[m+1];

        // Day 0
        previous[0] = Math.max(points[0][1], points[0][2]);
        previous[1] = Math.max(points[0][0], points[0][2]);
        previous[2] = Math.max(points[0][0], points[0][1]);
        previous[3] = Math.max(previous[0], Math.max(previous[1], previous[2]));

        for(int day = 1; day < n; day++){ //Iterating each row/day
            int[] current = new int[m+1];
            for(int lastActivity=0;lastActivity<=m;lastActivity++){ //Iterating on each task (0-3) 3 for none at very beginning Ninja didn't perform any task
                for(int task=0;task<m;task++){ // Checking each possibility if lastActivity is given
                    if(lastActivity != task){
                        int currentPoints=points[day][task]+previous[task];
                        current[lastActivity]=Math.max(current[lastActivity],currentPoints);
                    }
                }

            }
            previous=current.clone();
        }
        return previous[m];
    }
    public static void main(String[] args) {
        int[][] arr={{70, 40, 10}, {180, 20, 5}, {200, 60, 30}};
        System.out.println(getMaxPoint(arr,arr.length-1,3));

        int[][] dp = new int[arr.length][arr[0].length+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(getMaxPoint(arr,arr.length-1,3,dp));
        System.out.println(getMaxPoint(arr));
        System.out.println(getMaxPointOptimized(arr));
    }
}
