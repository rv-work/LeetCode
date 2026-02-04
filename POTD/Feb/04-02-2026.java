class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return -1; 

        long[] pre = new long[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }

        long max = Long.MIN_VALUE;
        boolean found = false;

     
        for (int l = 0; l <= n - 4; l++) {

          
            if (nums[l + 1] <= nums[l]) continue;

        
            int p = l + 1;
            while (p < n - 1 && nums[p + 1] > nums[p]) {
                p++;
            }
            if (p == n - 1 || nums[p + 1] >= nums[p]) continue;

            int q = p + 1;
            while (q < n - 1 && nums[q + 1] < nums[q]) {
                q++;
            }
            if (q == n - 1 || nums[q + 1] <= nums[q]) continue;

            int r = q + 1;
            
            while (true) {
                long sum = pre[r] - (l > 0 ? pre[l - 1] : 0);
                max = Math.max(max, sum);
                found = true;

                if (r < n - 1 && nums[r + 1] > nums[r]) {
                    r++;
                } else {
                    break; 
                }
            }
        }

        return found ? max : -1; 
    }
}
















class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return -1; 

        long[] pre = new long[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }

        long max = Long.MIN_VALUE;

        int l = 0;
        while (l <= n - 4) {

            // 1. Skip flat/downward starts
            if(nums[l + 1] <= nums[l]) {
                l++;
                continue;
            }

            // 2. Find Peak (p)
            int p = l + 1;
            while (p < n - 1 && nums[p + 1] > nums[p]) p++;
            if (p == n - 1 || nums[p + 1] >= nums[p]) {
                l = p; // Optimization: Jump past this invalid segment
                continue;
            }

            // 3. Find Valley (q)
            int q = p + 1;
            while (q < n - 1 && nums[q + 1] < nums[q]) q++;
            if (q == n - 1 || nums[q + 1] <= nums[q]) {
                l = q; // Optimization: Jump past this invalid segment
                continue;
            }

            // --- REPLACEMENT FOR YOUR INNER WHILE(TRUE) ---
            
            // Step A: Find the BEST 'l' for this specific mountain (p, q).
            // We want the 'l' that has the Minimum Prefix Sum in range [current_l ... p-1]
            long minPreL = (l > 0) ? pre[l - 1] : 0;
            for (int k = l + 1; k < p; k++) {
                long currentPre = pre[k - 1];
                if (currentPre < minPreL) minPreL = currentPre;
            }

            // Step B: Scan 'r' ONCE and calculate max sum using the best 'l' we found.
            int r = q + 1;
            while (true) {
                // Calculate sum using the BEST l (minPreL) and CURRENT r
                long sum = pre[r] - minPreL;
                max = Math.max(max, sum);

                if (r < n - 1 && nums[r + 1] > nums[r]) {
                    r++;
                } else {
                    break;
                }
            }
            
            // --- JUMP ---
            // The logic is done for this mountain. 
            // The next potential mountain starts from 'q'.
            l = q; 
        }

        return  max ; 
    }
}




