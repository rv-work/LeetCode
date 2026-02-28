class Solution {
    int MOD = 1000000007;
    public int concatenatedBinary(int n) {
        long ans = 0;
        for(int i = 1; i<= n; i++){
            
            // hme left shift krna hai ans ko . jitne isme digits honge utne times so .....
            int num = i;
            while(num != 0){
                num >>= 1;
                ans <<= 1;
            }
            ans = (ans | i)%MOD;

            // // alternative 
            // int bitLength = 32 - Integer.numberOfLeadingZeros(i);
            // ans = ((ans << bitLength) % MOD + i) % MOD;
        }

        return (int)ans ;
    }
}