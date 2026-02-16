class Solution {
    public int reverseBits(int n) {
        StringBuilder str = new StringBuilder();
        int mask = 1;
        for(int i = 0; i<32; i++){
            int bit = (n & (mask << i)) == 0 ? 0 : 1;
            str.append(bit);
        }

        int ans = 0;
        for(int i = 31; i>=0; i--){// 2^31 to 2^0 ( == 1)....
            ans += (1<<i)*(str.charAt(31-i)-'0');
        }

        return ans;

    }
}



// (n >> i) & 1	Always 0 or 1
// n & (1 << i)	0 or 2^i .. kyunki let say 1 << 3 .. now aba agar vo set hoga to ye dega 1000.. joki 1 nhi 8 hai 
// alternative is either check n & 1 << i ... is 0 or not if 0 then zero else 1 .. dont take direct value;;