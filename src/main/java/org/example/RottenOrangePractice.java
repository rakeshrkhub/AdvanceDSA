package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOrangePractice {
    private static final int[] row={-1,0,1,0};
    private static final int[] col={0,1,0,-1};

    private static boolean isValidCoordinate(int row, int col,int m, int n){
        if(row>=0 && col >=0 && row<m && col <n) return true;
        return false;
    }
    public static int orangesRotting(int[][] grid){
        int m=grid.length;
        if(m==0) return 0;
        int n=grid[0].length;
        int orange=0;
        int time=0;
        int[][] visited = new int[m][n];
        Queue<int[]> que= new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] !=0) orange++;
                if(grid[i][j]==2){
                    que.add(new int[]{i,j});
                }
            }
        }
        int rottenOrange=0;
        while (!que.isEmpty()){
            int queSize= que.size();
            rottenOrange +=queSize;
            System.out.println("Initial Size="+queSize);
            for(int i=0;i<queSize;i++){
                int[] curr = que.poll();
                for(int j=0;j<4;j++){
                    int nRow=curr[0]+row[j];
                    int nCol=curr[1]+col[j];
                    if(isValidCoordinate(nRow,nCol,m,n) && grid[nRow][nCol]==1 && visited[nRow][nCol] !=2){
                        visited[nRow][nCol] =2;
                        grid[nRow][nCol]=2;
                        que.add(new int[]{nRow,nCol});
                    }

                }
            }
            System.out.println("Exit Size="+que.size());
            if(!que.isEmpty()){
                time++;
            }
        }
        System.out.println("Rotten orange= "+rottenOrange+" Total Orange= "+ orange);
        return orange==rottenOrange?time:-1;
    }
    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        // Create object and call function
        int res = orangesRotting(grid);
        System.out.println("Total time= "+res+" units");
    }
}
