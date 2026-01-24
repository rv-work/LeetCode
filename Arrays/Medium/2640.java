class Solution {
    public long[] findPrefixScore(int[] nums) {
        int max = Integer.MIN_VALUE;
        long sum = 0;
        int n = nums.length;
        long ans [] = new long[n];

        for(int i = 0; i<n; i++){
            int num = nums[i];
            max = Math.max(max , num);
            int conver = max + num;
            sum += conver;
            ans[i] = sum;
        }


        return ans;
    }
}