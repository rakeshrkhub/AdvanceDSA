package org.graph;

import java.util.ArrayList;
import java.util.List;

public class BNumberOfProvinces {
    /*
    There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

    A province is a group of directly or indirectly connected cities and no other cities outside of the group.

    You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

    Return the total number of provinces.

    Example 1:
    Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
    Output: 2

    Example 2:
    Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
     */
    private static void dfs(List<List<Integer>> adj,boolean[] visited, int start) {
        visited[start]=true;
        for(int ele:adj.get(start)) {
            if(!visited[ele]) {
                visited[ele]=true;
                dfs(adj,visited,ele);
            }
        }
    }
    private static void addInList(List<List<Integer>> adj, int u, int v){
        adj.get(u).add(v);
        //adj.get(v).add(u);
    }
    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        int n= isConnected.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int u=0;u<n;u++){
            for(int v=0;v<n;v++){
                if(isConnected[u][v]==1 && u !=v){
                    addInList(adj,u,v);
                }
            }
        }
        int ans=0;
        boolean[] visited = new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                ans++;
                visited[i]=true;
                dfs(adj,visited,i);
            }
        }
        System.out.println(ans);

    }
}
