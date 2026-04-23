

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        
        for (List<Integer> list : map.values()) {
            int m = list.size();
            if (m == 1) continue; 
            
            long sum = 0;
            int first = list.get(0);
            for (int i = 1; i < m; i++) {
                sum += list.get(i) - first;
            }
            ans[first] = sum;
            
            for (int i = 1; i < m; i++) {
                int prev = list.get(i - 1);
                int curr = list.get(i);
                
                long d = curr - prev; 
                long L = i;           
                long R = m - i;       
                
                ans[curr] = ans[prev] + (L * d) - (R * d);
            }
        }
        
        return ans;
    }
}
















