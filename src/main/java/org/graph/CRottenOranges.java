package org.graph;

import java.util.LinkedList;
import java.util.Queue;

public class CRottenOranges {
    /*
    You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.
    Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

    Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

    Example 1:


    Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
    Output: 4
    Example 2:

    Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
    Output: -1
    Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
    Example 3:

    Input: grid = [[0,2]]
    Output: 0
    Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
     */

    /*
    Time Complexity: O(n × n × 4) In the worst case, every cell in the grid may contain an orange, and for each rotten orange we explore 4 directions (up, down, left, right). So we iterate through all cells (n × n) and perform 4 operations per orange. Hence, the complexity becomes O(n × n × 4), which simplifies to O(n²).

    Space Complexity: O(n × n) In the worst case, all the oranges might be rotten and will be stored in the queue simultaneously. The maximum size of the queue can be equal to the total number of oranges in the grid, i.e., n × n. Therefore, the space complexity is O(n²).
     */
    // Function to calculate the minimum minutes to rot all oranges

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
            int res = orangesRotting(grid);
            System.out.println("Total time= "+res+" units");
        }

}
