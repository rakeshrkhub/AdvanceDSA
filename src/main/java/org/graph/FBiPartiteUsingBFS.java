package org.graph;

import java.util.*;

public class FBiPartiteUsingBFS {
    // Helper method to check if a connected component is bipartite
    private boolean bfs(int start, int V, List<Integer>[] adj, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start); // Start BFS from this node
        color[start] = 0; // Assign first color (0)

        while (!q.isEmpty()) {
            int node = q.poll(); // Get current node

            for (int neighbor : adj[node]) {
                // If the adjacent node is not yet colored
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[node]; // Assign opposite color
                    q.offer(neighbor);
                }
                // If the neighbor already has the same color → not bipartite
                else if (color[neighbor] == color[node]) {
                    return false;
                }
            }
        }
        return true; // This component is bipartite
    }

    // Main method to check bipartiteness of the entire graph
    public boolean isBipartite(int V, List<Integer>[] adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1); // Initially, all nodes are uncolored

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) { // If the node is not colored
                if (!bfs(i, V, adj, color)) {
                    return false; // If any component is not bipartite
                }
            }
        }
        return true;
    }

    // Utility function to add edges (undirected graph)
    public static void addEdge(List<Integer>[] adj, int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    // Driver code
    public static void main(String[] args) {
        int V = 4;
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 0, 3);
        addEdge(adj, 2, 3);
        addEdge(adj, 1, 2);

        FBiPartiteUsingBFS obj= new FBiPartiteUsingBFS();
        boolean ans = obj.isBipartite(V, adj);

        if (ans) System.out.println("1");
        else System.out.println("0");
    }
}
