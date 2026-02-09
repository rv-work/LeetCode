class Solution {
    boolean solve(int i , int j , String s, Set<String> set,Boolean [][] dp){
        int n = s.length();
        if(i< n && j>= n) return false;
        if(i >= n && j>= n) return true;

        if(dp[i][j] != null) return dp[i][j];
        String sub = s.substring(i,j+1);
        
        boolean res = false;

        if(set.contains(sub)){
            res = solve(j+1,j+1,s,set,dp) || solve(i,j+1,s,set,dp);
        } else {
            res = solve(i,j+1,s,set,dp);
        }


        return dp[i][j] =  res;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for(String str  : wordDict){
            set.add(str);
        }
        int n = s.length();
        Boolean [][] dp = new Boolean[n][n];
        return solve(0,0,s,set,dp);
    }
}