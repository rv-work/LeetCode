import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int prefix = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); 

        for (int x : nums) {
            prefix += x;
            int needed = prefix - k;
            if (map.containsKey(needed)) {
                ans += map.get(needed);
            }
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        return ans;
    }
}











class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int ans = 0;

        int ps = 0;
        mp.put(ps, 1);  

        for (int i = 0; i < n; i++) {
            ps += nums[i];

            int need = ps - k;

            ans += mp.getOrDefault(need, 0);

            mp.put(ps, mp.getOrDefault(ps, 0) + 1);
        }

        return ans;
    }
}