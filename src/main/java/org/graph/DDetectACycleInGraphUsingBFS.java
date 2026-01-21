package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DDetectACycleInGraphUsingBFS {
    /*
    Problem Statement: Given an undirected graph with V vertices and E edges, check whether it contains any cycle or not.
    Example 1:
    Input:
    V = 8, E = 7
    Output:  0

    Explanation: No cycle in the given graph.
    Example 2:
    Input:
    V = 8, E = 6
    Output: 1
    Explanation: 4->5->6->4 is a cycle.
     */
    class Node {
        int first;
        int second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
/*
Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes. In the case of connected components of a graph, it will take another O(N) time.

Space Complexity: O(N) + O(N) ~ O(N), Space for queue data structure and visited array.
 */
    public boolean checkForCycle(int start, ArrayList<ArrayList<Integer>> adj,Node[] visited){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start,-1));
        visited[start]=new Node(1,-1);
        while(!que.isEmpty()){
            Node current=que.poll();
            int vertex=current.first;
            int parent = current.second;
            for(int ele:adj.get(vertex)){
                if(visited[ele] == null){
                    visited[ele]=new Node(1,vertex);
                    que.add(new Node(ele,vertex));
                }else if(ele !=parent){
                    return true;
                }
            }
        }
        return false;
    }


    // function to detect cycle in an undirected graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        Node[] visited=new Node[V];
        for(int i=0;i<V;i++)
            if(visited[i]==null)
                if(checkForCycle(i,adj,visited))
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

        DDetectACycleInGraphUsingBFS obj=new DDetectACycleInGraphUsingBFS();
        boolean ans =obj.isCycle(8, adj);
        if (ans)
            System.out.println("1");
        else
            System.out.println("0");
    }

}
