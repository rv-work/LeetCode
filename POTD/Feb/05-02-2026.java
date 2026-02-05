class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for(int i = 0; i<n; i++){
            if(nums[i] == 0) res[i] = nums[i];
            else if (nums[i] > 0 ){
                int k = nums[i] % n;
                if( i+k < n) res[i] = nums[i+k];
                else res[i] = nums[i-(n-k)];
            } else {
                int k = Math.abs(nums[i]) % n;
                if( i-k >= 0) res[i] = nums[i-k];
                else res[i] = nums[i+n-k];
            }
        }


        return res;
    }
}