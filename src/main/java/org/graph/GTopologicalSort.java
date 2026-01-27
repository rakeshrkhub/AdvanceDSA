package org.graph;

import java.util.ArrayList;
import java.util.Stack;

public class GTopologicalSort {
    /*
    Problem Statement: Given a Directed Acyclic Graph (DAG) with V vertices labeled from 0 to V-1.
    The graph is represented using an adjacency list where adj[i] lists all nodes connected to node.
    Find any Topological Sorting of that Graph.

    In topological sorting, node u will always appear before node v if there is a directed edge from node u towards node v(u -> v).
    The Output will be True if your topological sort is correct otherwise it will be False.

    Input: V = 6, adj = [[], [], [3], [1], [0, 1], [0, 2]]
    Output: [5, 4, 2, 3, 1, 0]
    Explanation: A graph may have multiple topological sortings. The result is one of them. The necessary conditions for the ordering are:
    According to edge 5 -> 0, node 5 must appear before node 0 in the ordering.
    According to edge 4 -> 0, node 4 must appear before node 0 in the ordering.
    According to edge 5 -> 2, node 5 must appear before node 2 in the ordering.
    According to edge 2 -> 3, node 2 must appear before node 3 in the ordering.
    According to edge 3 -> 1, node 3 must appear before node 1 in the ordering.
    According to edge 4 -> 1, node 4 must appear before node 1 in the ordering.
    The above result satisfies all the necessary conditions. [4, 5, 2, 3, 1, 0] is also one such topological sorting that satisfies all the conditions.
         */
    public static void dfs(ArrayList<ArrayList<Integer>> adj, int[] visited, Stack<Integer> stack, int start){
        visited[start]=1;
        for(int adjacent : adj.get(start)){
            if(visited[adjacent]==0) dfs(adj,visited,stack,adjacent);
        }
        stack.add(start);
    }

    public static void makeAdjList(int[][] nodes, int n){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<n;i++){
            for(int j=0;j<nodes[i].length;j++){
                adj.get(i).add(nodes[i][j]);
            }
        }
        int[] visited = new int[n];
        Stack<Integer> stack= new Stack<>();
        for(int i=0;i<n;i++){
            if(visited[i]==0) dfs(adj,visited,stack,i);
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        System.out.println("Topological sorted array: "+result);
    }
    public static void main(String[] args) {
        int n=6;
        int[][] nodes= {{}, {}, {3}, {1}, {0, 1}, {0, 2}};
        makeAdjList(nodes,n);

    }
}
