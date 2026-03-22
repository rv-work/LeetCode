class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean is90 = true;
        boolean is180 = true;
        boolean is270 = true;
        boolean is0or360 = true;


        // 0/360
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){

                if(mat[i][j] != target[i][j] ) is0or360 =  false;
            }
        }


        // 90
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){

                int p = n-j-1;
                int q = i;

                if(mat[p][q] != target[i][j] ) is90 =  false;
            }
        }


        // 180
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){

                int p = n-i-1;
                int q = n-1-j;

                if(mat[p][q] != target[i][j] ) is180 =  false;
            }
        }



        // 270
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){

                int p = j;
                int q = n-1-i;

                if(mat[p][q] != target[i][j] ) is270 =  false;
            }
        }

        return is90 || is180 || is270 || is0or360;
    }
}