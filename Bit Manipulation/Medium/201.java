class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int ans = 0;

        for(int i = 31; i>=0; i--){
            int ith_left = ((1<<i) & left);
            int ith_right = ((1<<i) & right);
            if(ith_left == ith_right) ans |= ith_left;
            else return ans;
        }
        return ans;
    }
}