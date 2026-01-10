class Solution {
    public int[] rearrangeArray(int[] nums) {
    int n = nums.length;
    int ans[] = new int[n];
    int pi = 0, ni = 1;
    for(int i = 0 ; i < n; i++){
        if(nums[i] > 0) {
            ans[pi] = nums[i];
            pi+=2;
        } else {
            ans[ni] = nums[i];
            ni+=2;
        }
    } return ans;
    }
}







class Solution {
    public int[] rearrangeArray(int[] nums) {
        List<Integer> pos = new ArrayList();
        List<Integer> neg = new ArrayList();
        
        for(int x: nums) {
            if(x >= 0) pos.add(x);
            else neg.add(x);
        }

        for(int i=0;i<nums.length;i++) {
            if(i%2 == 0) {
                nums[i] = pos.get(i/2);
            } else {
                nums[i] = neg.get(i/2);
            }
        }

        return nums;
    }
}