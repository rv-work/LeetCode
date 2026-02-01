class Solution {
    int res(int i , int j , String w1, String w2, int n1, int n2){
        if(i>=n1) return n2-j;
        if(j>=n2) return n1-i;

        int ans = Integer.MAX_VALUE;
      
        if(w1.charAt(i) == w2.charAt(j)) {
            ans = Math.min(ans , 0 + res(i+1,j+1,w1,w2,n1,n2));
        }else {
            ans = Math.min(ans , 1 + res(i+1,j+1,w1,w2,n1,n2)); // update
            ans = Math.min(ans , 1 + res(i+1,j,w1,w2,n1,n2)); // delete
            ans = Math.min(ans , 1 + res(i,j+1,w1,w2,n1,n2)); // insert
        }


        return ans;
        
    }
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        return res(0,0,w1,w2,n1,n2);
    }
}