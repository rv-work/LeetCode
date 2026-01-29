// class Solution {

//     class Pair {
//         int len;
//         int cnt;

//         Pair(int l, int c) {
//             this.len = l;
//             this.cnt = c;
//         }
//     }

//     Pair res(int idx, int last, int n, int[] nums, Pair[][] dp) {
//         if (idx == n) {
//             // EMPTY subsequence has length 0 and exactly 1 way
//             return new Pair(0, 1);
//         }

//         if (dp[idx][last + 1] != null)
//             return dp[idx][last + 1];

//         // ---- Option 1 : NOT TAKE ----
//         Pair notTake = res(idx + 1, last, n, nums, dp);

//         // ---- Option 2 : TAKE ----
//         Pair take = new Pair(0, 0);
//         if (last == -1 || nums[idx] > nums[last]) {
//             Pair val = res(idx + 1, idx, n, nums, dp);
//             take.len = val.len + 1;
//             take.cnt = val.cnt; // IMPORTANT FIX
//         }

//         // ---- Combine ----
//         if (take.len > notTake.len) {
//             return dp[idx][last + 1] = take;
//         } else if (take.len < notTake.len) {
//             return dp[idx][last + 1] = notTake;
//         } else {
//             // equal lengths → sum counts
//             return dp[idx][last + 1] = new Pair(take.len, take.cnt + notTake.cnt);
//         }
//     }

//     public int findNumberOfLIS(int[] nums) {
//         int n = nums.length;

//         // dp[idx][last+1]  -> because last starts at -1
//         Pair[][] dp = new Pair[n + 1][n + 1];

//         // BASE CASE fill: idx == n
//         // For any last: LIS is empty = (0 length, 1 way)
//         for (int last = -1; last < n; last++) {
//             dp[n][last + 1] = new Pair(0, 1);
//         }

//         // FILL TABLE from bottom to top
//         for (int idx = n - 1; idx >= 0; idx--) {
//             for (int last = idx - 1; last >= -1; last--) {

//                 // ---------- NOT TAKE ----------
//                 Pair notTake = dp[idx + 1][last + 1];

//                 // ---------- TAKE ----------
//                 Pair take = new Pair(0, 0);
//                 if (last == -1 || nums[idx] > nums[last]) {
//                     Pair next = dp[idx + 1][idx + 1];
//                     take.len = next.len + 1;
//                     take.cnt = next.cnt;
//                 }

//                 // ---------- COMBINE ----------
//                 if (take.len > notTake.len) {
//                     dp[idx][last + 1] = take;
//                 } else if (take.len < notTake.len) {
//                     dp[idx][last + 1] = notTake;
//                 } else {
//                     dp[idx][last + 1] = new Pair(
//                             take.len,
//                             take.cnt + notTake.cnt);
//                 }
//             }
//         }

//         // answer starts at (0, -1)
//         return dp[0][-1 + 1].cnt;
//     }
// }



// lets go for better.............



class Solution {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        int len[] = new int[n];
        int cnt[] = new int[n];

        Arrays.fill(len , 1);
        Arrays.fill(cnt , 1);

        int maxLen = 0;


        for(int idx = 0; idx<n; idx++){
            for(int last = 0; last < idx; last++){
                if(nums[last] < nums[idx]){

                    
                    if(len[last]+1 > len[idx]){ 

                        // index idx tak jo ab tak len thi.. ab agar last me ye vali idx ko include kre (isiliye +1) to bdi length .. to update krenge ..

                    len[idx] = len[last]+1;
                    cnt[idx] = cnt[last]; // take same count .. because length bdhi hai ..


                    } else if(len[last]+1 == len[idx]) { 
                        
                        //agar yha idx tak phuchne se ek aur same length mil rhi to matlab jitne cnt tha ab tak idx pr .... usme vo sare cnt add kr do jo last pr hain ..

                        cnt[idx] += cnt[last];// aur mil gye.. to vha se sare pick krlo...

                    }
                }
            }

            maxLen = Math.max(maxLen, len[idx]);
            // ye hme krna pdega kyunki len array sirf itna bta rha ki ith index tak maxLen kya hai... to ya to hme bad me nikalna pdega ya sath hi calculate krte jaye...
        }

        int ans = 0;

        for(int i = 0; i<n; i++){
            if(maxLen == len[i]) ans += cnt[i];
        }


        return ans;
        
    }
}