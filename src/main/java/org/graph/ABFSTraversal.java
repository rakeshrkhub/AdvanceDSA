package org.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ABFSTraversal {
    private static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj){
        int n= adj.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> bfsArray = new ArrayList<>();
        queue.add(0);
        visited[0]=true;
        while (!queue.isEmpty()){
            int current= queue.poll();
            bfsArray.add(current);
            for(int ele:adj.get(current)){
                if(!visited[ele]){
                    queue.add(ele);
                    visited[ele]=true;
                }
            }
        }
        return bfsArray;
    }
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // creating adjacency list
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        addEdge(adj, 1, 2);
        addEdge(adj, 1, 0);
        addEdge(adj, 2, 0);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 4);

        ArrayList<Integer> res = bfs(adj);

        for (int x : res)
            System.out.print(x + " ");
    }
}
