package org.graph;

public class BNumberOfIsland {
    /*
    Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    Input: grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    Output: 1
    Example 2:

    Input: grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    Output: 3
     */
    /*
    For particular index i,
    We have to move up(i-1,j),left(i,j-1),right(i,j+1),down(i-1,j)
    When we try to combine it: row is varying like -1,0,1,0 and column is varying like 0,-1,0,1
     */
    //Time Complexity: O(N*M), DFS traversal and marking visited cells dominate.
    //Space Complexity: O(N*M), or visited grid and set storing unique island shapes.
    static final int[] rowIndex={-1,0,1,0};
    static final int[] colIndex={0,-1,0,1};
    private static void dfs(String[][] grid, int[][] visited,int row, int col, int m, int n){
        if(row<0 || col <0 || row>m || col>n || visited[row][col]==1) return;
        visited[row][col]=1;
        for(int del=0;del<=3;del++){
            int nRow=row+rowIndex[del];
            int nCol=col+colIndex[del];
            if(nRow>=0 && nCol>=0 && nRow<m && nCol<n) {
                if (grid[nRow][nCol].equalsIgnoreCase("1") && visited[nRow][nCol] != 1) {
                    dfs(grid, visited, nRow, nCol, m, n);
                }
            }
        }
    }
    private static int callDFSForResult(String[][] grid){
        int ans=0;
        int m= grid.length;
        int n=grid[0].length;
        int[][] visited = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j].equalsIgnoreCase("1") && visited[i][j] !=1){
                    ans++;
                    dfs(grid,visited,i,j,m,n);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String[][] grid={{"1","1","0","0","0"},
                        {"1","1","0","0","0"},
                        {"0","0","1","0","0"},
                        {"0","0","0","1","1"}};
        System.out.println(callDFSForResult(grid));
    }
}
