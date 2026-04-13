class Solution {
    public int getMinDistance(int[] nums, int t, int s) {
        int n = nums.length;
        int i = 0;
        while(true){
            if(s+i < n && nums[s+i] == t) return i;
            if(s-i >= 0 && nums[s-i] == t) return i;
            i++;
        }
    }
}