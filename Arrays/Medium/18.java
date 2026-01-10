class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return ans;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {

            // skip duplicate i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {

                // skip duplicate j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int l = j + 1;
                int r = n - 1;

                while (l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];

                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));

                        l++;
                        r--;

                        // skip duplicate l
                        while (l < r && nums[l] == nums[l - 1]) l++;

                        // skip duplicate r
                        while (l < r && nums[r] == nums[r + 1]) r--;

                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return ans;
    }
}











class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();
        Map<Integer, List<int[]>> map = new HashMap<>();

        // store all pair sums
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                map.computeIfAbsent(sum, k -> new ArrayList<>())
                   .add(new int[]{i, j});
            }
        }

        // find complementary pairs
        for (int s : map.keySet()) {
            int need = target - s;
            if (!map.containsKey(need)) continue;

            for (int[] p1 : map.get(s)) {
                for (int[] p2 : map.get(need)) {
                    int a = p1[0], b = p1[1];
                    int c = p2[0], d = p2[1];

                    // indices must be different
                    if (a != c && a != d && b != c && b != d) {
                        List<Integer> quad =
                            Arrays.asList(nums[a], nums[b], nums[c], nums[d]);
                        Collections.sort(quad);
                        res.add(quad);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
}
