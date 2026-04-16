
class Solution {

    int minDist(int a, int b, int n) {
        int d = Math.abs(a - b);
        return Math.min(d, n - d);
    }

    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        
        int[] minDist = new int[n];
        Arrays.fill(minDist, -1);
        
        for (List<Integer> list : map.values()) {
            
            int size = list.size();
            if (size <= 1) continue;
            
            for (int i = 0; i < size; i++) {
                int currIdx = list.get(i);
                
                int leftIdx = list.get((i - 1 + size) % size);
                int rightIdx = list.get((i + 1) % size);
                
                int distLeft = minDist(currIdx, leftIdx, n);
                int distRight = minDist(currIdx, rightIdx, n);
                
                minDist[currIdx] = Math.min(distLeft, distRight);
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            ans.add(minDist[queries[i]]);
        }
        
        return ans;
    }
}