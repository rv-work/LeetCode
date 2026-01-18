class Solution {
  boolean check(int[][] gird , int k ,int m, int n){
         for(int i = 0; i<m-k; i++){
            for(int j = 0; j<n-k; j++){
                
             
             int lastRow = i+k-1;
             int firstRow = i;
             int firstCol = j;
             int lastCol = j+k-1;

            // rowSum
             for(int x = firstRow; x <= lastRow-1; x++){
                 int sum1 = grid[lastCol][x];
                 if(firstCol-1 >= 0) sum1 - grid[firstCol][x];
                 int sum2 = grid[lastCol][x+1];
                 if(firstCol-1 >= 0) sum2 - grid[firstCol][x+1];
                 if(sum1 != sum2) return false;
             } 


            //colSum
            
                


            }
         }
    }

  public int largestMagicSquare(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    for (int i = 0; i < m; i++) {
      int pre = 0;
      for (int j = 0; j < n; j++) {
        pre += grid[i][j];
        grid[i][j] = pre;
      }
    }

    int start = 1;
    int end = Math.min(m, n);

    while (start < end) {

    }
  }
}