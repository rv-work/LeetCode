class Solution {
    int MOD = 1000000007;
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;

        for(int [] q : queries){
            for(int i = q[0]; i<= q[1]; i+=q[2]){
                nums[i] = (int)(((long) nums[i] * q[3]) % MOD);
            }
        }


        int xor = 0;

        for(int num : nums){
            xor ^= num;
        }


        return xor;
    }
}