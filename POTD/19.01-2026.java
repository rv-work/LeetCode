class Solution {

    boolean check(int[][] mat, int threshold , int side){
        int m = mat.length;
        int n = mat[0].length;
        for(int i = 0; i<=m-side; i++){
            for(int j = 0; j<=n-side; j++){
                   
                   int firstCol = j;
                   int firstRow = i;
                   int lastCol = j+side-1;
                   int lastRow = i+side-1;
                   int sum = 0;
                   for(int row = firstRow; row <=lastRow; row++){
                      sum += mat[row][lastCol];
                      if(firstCol > 0) sum -= mat[row][firstCol-1];
                   }
                   if(sum <= threshold) return true;
            }
        }

        return false;
    }


    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        int ans = 0;

        for(int i = 0; i<m; i++){
            for(int j = 1; j<n; j++){
                 mat[i][j] += mat[i][j-1];
            }
        }

        int r = Math.min(m , n);
        int l = 1;
        while(l<=r){
          int mid = (l+r)/2;
          if(check(mat , threshold , mid)){
            ans = mid;
            l = mid+1;
          }else r = mid-1;
        }


        return ans;
    }
}