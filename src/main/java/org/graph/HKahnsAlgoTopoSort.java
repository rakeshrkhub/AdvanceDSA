package org.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class HKahnsAlgoTopoSort {
    //Time Complexity: O(V+ E)
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

        ArrayList<Integer> result = new ArrayList<>();
        //remove the node from the queue and add in result
        // decrease the count of indegree of all the adjacent nodes of it
        // And add in the que if indegree of any node becomes zero
        while (!que.isEmpty()){
            int curr = que.poll();
            result.add(curr);
            for(int node: adj.get(curr)){
                inDegree[node]--;
                if(inDegree[node]==0) que.add(node);
            }
        }
        System.out.println("Topological sorted array: "+result);
    }
    public static void main(String[] args) {
        int n=6;
        int[][] nodes= {{}, {}, {3}, {1}, {0, 1}, {0, 2}};
        makeAdjList(nodes,n);

    }
}
