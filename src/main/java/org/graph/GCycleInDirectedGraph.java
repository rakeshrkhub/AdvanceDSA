package org.graph;

import java.util.ArrayList;

public class GCycleInDirectedGraph {
    /*
    Problem Statement: Given a directed graph with V vertices and E edges, check whether it contains any cycle or not.

    Example 1:

    Input: N = 10, E = 11
     */
    public static boolean dfs(ArrayList<ArrayList<Integer>> adj, int[] visited, int[] pathVisited, int start){
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
    public static boolean isCyclePresent(ArrayList<ArrayList<Integer>> adj, int n){
        int[] visited = new int[n+1];
        int[] pathVisited = new int[n+1];
        for(int i=1;i<=n;i++){
            if(visited[i]==0){
                if(dfs(adj,visited,pathVisited,i)) return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int n=10;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());
        /*adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(7).add(5);
        adj.get(5).add(6);
        adj.get(8).add(2);
        adj.get(8).add(9);
        adj.get(9).add(10);*/

        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(7).add(5);
        adj.get(5).add(6);
        adj.get(8).add(2);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);

        System.out.println("Is this Cyclic directed graph: "+isCyclePresent(adj,n));


    }
}
