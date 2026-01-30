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









class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int x : nums) xor ^= x;

        int diff = xor & -xor;

        int a = 0, b = 0;
        for (int x : nums) {
            if ((x & diff) == 0) a ^= x;
            else b ^= x;
        }
        return new int[]{a, b};
    }
}
