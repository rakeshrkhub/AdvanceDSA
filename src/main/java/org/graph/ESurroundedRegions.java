package org.graph;

public class ESurroundedRegions {
    final int[] row={1,0,-1,0};
    final int[] col={0,1,0,-1};
    public boolean isValid(int r, int c, int m, int n){
        return r>=0 && c>=0 && r<m && c<n;
    }
    public void dfs(char[][] grid, int[][] visited, int r, int c, int m, int n){
        if(!isValid(r,c,m,n) || visited[r][c]==1) return;
        visited[r][c]=1;
        for(int i=0;i<4;i++){
            int nRow=r+row[i];
            int nCol=c+col[i];
            if(isValid(nRow,nCol,m,n) && visited[nRow][nCol]==0 && grid[nRow][nCol]=='O'){
                dfs(grid,visited,nRow,nCol,m,n);
            }
        }
    }
    public char[][] getResult(char[][] grid){
        int m= grid.length;
        int n=grid[0].length;
        int[][] visited = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]==0 && grid[i][j]=='O'){
                    dfs(grid,visited,i,j,m,n);
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]==0 && grid[i][j]=='O'){
                    grid[i][j]='X';
                }
            }
        }
        return grid;
    }
    public static void main(String[] args) {

    }
}
