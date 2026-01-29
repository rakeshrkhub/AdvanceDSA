package org.graph;

import java.util.ArrayList;

public class GCourseSchedule {
    /*
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return true if you can finish all courses. Otherwise, return false.


    Example 1:

    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0. So it is possible.
    Example 2:

    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
     */
    public boolean dfs(ArrayList<ArrayList<Integer>> adj, int[] visited, int[] pathVisited, int start){
        visited[start]=1;
        pathVisited[start]=1;
        for(int adjacent: adj.get(start)){
            if(visited[adjacent]==0){
                if(dfs(adj,visited,pathVisited,adjacent)) return true;
            }else {
                if(pathVisited[adjacent]==1) return true;
            }
        }
        pathVisited[start]=0;
        return false;
    }
    public boolean isCyclePresent(int[][] nodes, int n){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<nodes.length;i++){
            adj.get(nodes[i][0]).add(nodes[i][1]);
        }
        int[] visited = new int[n+1];
        int[] pathVisited = new int[n+1];
        for(int i=0;i<n;i++){
            if(visited[i]==0){
                if(dfs(adj,visited,pathVisited,i)) return true;
            }
        }
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return isCyclePresent(prerequisites, numCourses);
    }
    public static void main(String[] args) {
        int n=2;
        int[][] nodes= {{1,0}};
        GCourseSchedule obj = new GCourseSchedule();
        System.out.println(!obj.canFinish(n,nodes));
    }
}
