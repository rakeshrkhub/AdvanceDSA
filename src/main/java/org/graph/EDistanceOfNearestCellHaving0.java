package org.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EDistanceOfNearestCellHaving0 {
    /*
    Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
    The distance between two cells sharing a common edge is 1.
    Example 1:
    Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
    Output: [[0,0,0],[0,1,0],[0,0,0]]

    Example 2:
    Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
    Output: [[0,0,0],[0,1,0],[1,2,1]]
     */
    //Time Complexity: O(MXN)+O(MXNX4) which is nearly about O(MXN)
    //Space Complexity: O(MXN)
    class Triplet{
        int row;
        int col;
        int steps;
        public Triplet(int row, int col, int steps){
            this.row=row;
            this.col=col;
            this.steps=steps;
        }
    }
    private final int[] rows={-1,0,1,0};
    private final int[] cols={0,1,0,-1};
    private boolean isValidCoordinate(int row, int col, int m, int n){
        if(row>=0 && col>=0 && row<m && col <n) return true;
        return false;
    }

    public int[][] getDistanceHavingZero(int[][] mat, int[][] visited){
        int m= mat.length;
        int n=mat[0].length;
        int[][] distance = new int[m][n];
        Queue<Triplet> que = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==0){
                    que.add(new Triplet(i,j,0));
                    visited[i][j]=1;
                    distance[i][j]=0;
                }
            }
        }
        while (!que.isEmpty()){
            Triplet out=que.poll();
            int row=out.row;
            int col=out.col;
            int steps= out.steps;
            for(int i=0;i<4;i++){
                int nRow=row+rows[i];
                int nCol=col+cols[i];
                if(isValidCoordinate(nRow, nCol,m,n) && visited[nRow][nCol] != 1){
                    distance[nRow][nCol]=steps+1;
                    que.add(new Triplet(nRow,nCol,steps+1));
                    visited[nRow][nCol]=1;
                }
            }
        }
        return distance;
    }
    public static void main(String[] args) {
        int[][] mat={{0,0,0},{0,1,0},{1,1,1}};
        EDistanceOfNearestCellHaving0 obj = new EDistanceOfNearestCellHaving0();
        int[][] visited = new int[mat.length][mat[0].length];
        int[][] result= obj.getDistanceHavingZero(mat,visited);
        System.out.println("Resultant matrix is: ");
        for(int i=0; i< result.length;i++){
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
