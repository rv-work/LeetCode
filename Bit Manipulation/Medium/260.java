class Solution {
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        
        int n = nums.length;
        int i = 0;
        
        while (i < n) {
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                i += 2;
            } else {
                ans.add(nums[i]);
                i++;
            }
        }
        
        return new int[]{ans.get(0), ans.get(1)};
    }
}
