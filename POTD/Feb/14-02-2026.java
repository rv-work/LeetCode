class Solution {
    double res(int p , int r , int c,double dp[][]){
        if(r == 0 && c == 0) return p; // yha pr p hi rhne denge naki p-1... kyunki func bta rha ki uspe kitna hai naki vo kitna dega ..

        if(dp[r][c] != -1) return dp[r][c];
        double upperLeft = 0;
        double upperRight = 0;
        if(c < r){
            upperRight = res(p,r-1,c,dp); // kitna hai uske upperright me 
        }
        if(c > 0){
            upperLeft = res(p,r-1,c-1,dp);// kitna hai uske upperleft me 
        }

        double leftContri = upperLeft <= 1 ? 0 :( upperLeft - 1)/2.0;// kitna dega uska upperLeft  
        double rightContri = upperRight <= 1 ? 0 :( upperRight - 1)/2.0;// kitna dega uska upperRight

        return dp[r][c] =  leftContri + rightContri;
    }
    public double champagneTower(int p, int r, int c) {
        double dp [][] = new double[r+1][c+1];
        for(double arr[] : dp) Arrays.fill(arr,-1);
        return Math.min(1.0,res(p,r,c,dp)); // agar 1 se jyada hai to vo nichr gir jayega isliye
    }
}