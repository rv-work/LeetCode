class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxLen = Integer.MIN_VALUE;
        int i = 0; int j = 0;
        while(j<n){
            long max = nums[j];
            long min = nums[i];
            if(max <= min*k){
                maxLen = Math.max(maxLen , j-i+1);
            }
            while(i+1<=j && max > min*k){
                i++;
                min = nums[i];
            }
            j++;
        }
        return n - maxLen;
    }
}