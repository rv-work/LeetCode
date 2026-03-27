class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        k %= m;
 
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){

                if(i % 2 == 0 && mat[i][j] != mat[i][(j+k)%m] ){
                    return false;
                }
                 
                if(i % 2 == 1 && mat[i][j] != mat[i][(j+m-k)%m] ){
                    return false;
                }
                 
            }
        }

        return true;
    }
}