class Solution {
    public int[][] reverseSubmatrix(int[][] g, int x, int y, int k) {
        for(int j = y ; j < y+k; j++){
             int a = x; int b = x+k-1;
             while(a < b){
                int temp = g[a][j];
                g[a][j] = g[b][j];
                g[b][j] = temp;
                a++;
                b--;
             }
        }

        return g;
        
    }
}