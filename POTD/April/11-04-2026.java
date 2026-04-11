
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

       
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new TreeSet<>());
            map.get(nums[i]).add(i);
        }

        int ans = Integer.MAX_VALUE;

        for (int key : map.keySet()) {
            TreeSet<Integer> set = map.get(key);

            if (set.size() < 3) continue;

            List<Integer> list = new ArrayList<>(set);

            for (int i = 0; i + 2 < list.size(); i++) {
                int i1 = list.get(i);
                int i2 = list.get(i + 1);
                int i3 = list.get(i + 2);

                int dist = 2 * (i3 - i1);
                ans = Math.min(ans, dist);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}