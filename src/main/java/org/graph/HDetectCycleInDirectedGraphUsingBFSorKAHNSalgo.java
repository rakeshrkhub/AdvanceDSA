package org.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HDetectCycleInDirectedGraphUsingBFSorKAHNSalgo {
    public static void makeAdjList(int[][] nodes, int n){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<n;i++){
            for(int j=0;j<nodes[i].length;j++){
                adj.get(i).add(nodes[i][j]);
            }
        }
        //Make array for in degree
        int[] inDegree = new int[n];
        for(int i=0;i<n;i++){
            for(int node: adj.get(i)){
                inDegree[node]++;
            }
        }
        //Add the node in the queue which has 0 indegree
        Queue<Integer> que = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(inDegree[i]==0) que.add(i);
        }

        int cnt=0;
        //remove the node from the queue and increase the count
        // decrease the count of indegree of all the adjacent nodes of it
        // And add in the que if indegree of any node becomes zero
        while (!que.isEmpty()){
            int curr = que.poll();
            cnt++;
            for(int node: adj.get(curr)){
                inDegree[node]--;
                if(inDegree[node]==0) que.add(node);
            }
        }
        //if we found topo sorted elements count==no of nodes then it has no cycle otherwise it has cycle.
        if(cnt==n) System.out.println("This is DAG");
        else System.out.println("This is Cyclic Directed graph");
    }
    public static void main(String[] args) {

    }
}
