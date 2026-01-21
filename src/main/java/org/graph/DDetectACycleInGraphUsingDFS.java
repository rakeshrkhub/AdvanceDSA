package org.graph;

import java.util.ArrayList;

public class DDetectACycleInGraphUsingDFS {

    public boolean checkForCycle(int start,int parent,ArrayList<ArrayList<Integer>> adj, int[] visited ){
        visited[start]=1;
        for(int ele:adj.get(start)){
            if(visited[ele] != 1){
                if (checkForCycle(ele, start, adj, visited)) {
                    return true;
                }
            }else if(ele !=parent){
                return true;
            }
        }
        return false;
    }
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] visited=new int[V];
        for(int i=0;i<V;i++)
            if(visited[i] != 1)
                if(checkForCycle(i,-1,adj,visited))
                    return true;

        return false;
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(2).add(1);
        adj.get(3).add(1);
        adj.get(2).add(5);
        adj.get(5).add(2);
        adj.get(5).add(7);
        adj.get(7).add(5);
        adj.get(7).add(6);
        adj.get(6).add(7);
        adj.get(6).add(3);
        adj.get(3).add(6);
        adj.get(3).add(4);
        adj.get(4).add(3);

        DDetectACycleInGraphUsingDFS obj=new DDetectACycleInGraphUsingDFS();
        boolean ans =obj.isCycle(8, adj);
        if (ans)
            System.out.println("1");
        else
            System.out.println("0");
    }
}
