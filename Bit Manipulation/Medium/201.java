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






// To get the result, you need to find the common prefix of left and right in their binary representation. Hint

// Example 1:
// 0000 0000 0000 0000 0000 0000 0000 0101 (5)
// 0000 0000 0000 0000 0000 0000 0000 0111 (7)

// prefix = 0000 0000 0000 0000 0000 0000 0000 0100 (4)

// Example 2:
// 0000 0000 0000 0000 0000 0000 0000 0110 (6)
// 0000 0000 0000 0000 0000 0000 0000 0111 (7)

// prefix = 0000 0000 0000 0000 0000 0000 0000 01100 (6)

// Example 3:
// 0000 0000 0000 0000 0000 0000 0000 0001 (1)
// 0111 1111 1111 1111 1111 1111 1111 1111 (2147483647)

// prefix = 0000 0000 0000 0000 0000 0000 0000 0000 (0)