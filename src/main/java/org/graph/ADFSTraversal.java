package org.graph;

import java.util.ArrayList;

public class ADFSTraversal {
    private static void dfs(ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> result,boolean[] visited, int start) {
        visited[start]=true;
        result.add(start);
        for(int ele:adj.get(start)) {
            if(!visited[ele]) {
                visited[ele]=true;
                dfs(adj,result,visited,ele);
            }

        }
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

        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited= new boolean[V];
        dfs(adj,res,visited,0);

        for (int x : res)
            System.out.print(x + " ");
    }

}
