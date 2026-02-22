class Solution {
    public int binaryGap(int n) {
        int max = 0;
        while((n & 1) != 1){
            n >>= 1;
        }

        int cnt = 0;
        while(n != 0){
            if((n & 1) == 1){
                max = Math.max(max , cnt);
                cnt = 1;
            }
            if((n & 1) == 0) cnt++;
            n >>=1;
        }

        return max;
    }
}