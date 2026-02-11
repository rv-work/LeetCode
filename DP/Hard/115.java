class Solution {
    int res(String s, String t, int i, int j,int dp[][]){
        if(j >= t.length()) return 1;
        if(i >= s.length()) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int pick = 0;
        int notPick = res(s,t,i+1,j,dp);
        if(s.charAt(i) == t.charAt(j)){
            pick = res(s,t,i+1,j+1,dp); // yha bhi chhod skte hain but vo include ho jayega notpick me already..
        }

        return dp[i][j] = pick + notPick;
    }

    public int numDistinct(String s, String t) {
        int n = s.length(); int m = t.length();
        int dp[][] = new int[n][m];
        for(int arr[] : dp) Arrays.fill(arr , -1);
        return res(s,t,0,0,dp);
    }
}