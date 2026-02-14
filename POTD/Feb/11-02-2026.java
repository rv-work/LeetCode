// class Solution {
//     public int longestBalanced(int[] nums) {
//         int n = nums.length;
//         int max = 0;
//         Map<Integer, Integer> map = new HashMap<>();
//         Set<Integer> set = new HashSet<>();


//         map.put(0,-1);
//         int cnt = 0;

//         for(int i = 0; i<n; i++){

//             if(!set.contains(nums[i])){
//               if(nums[i] % 2 == 0) cnt++;
//               else cnt--;
//             }

//             if(map.containsKey(cnt)){
//                 max = Math.max(max , i-map.get(cnt));
//             } else {
//                 map.put(cnt , i);
//             }

//             set.add(nums[i]);
//         }


//         return max;
//     }
// }

// the only problem is that ..... yha aise global presum kaam nhi krega ..... kyunki .. duplicates ko add nhi kr skte hin apne prefixsum me to usse problem ye aayegi ki same sum kafi bar milega jabki vha uska ye matlab nhi hai ki unke bich ka sum 0 tha ..... .... to ye possible hi nhi ki ek global variable lekar chale aur ho jaye .... because of distinct only sum ....... to presum lena fir pichhe check krna ye kaam nhi krega .... ki just keep adding in presum and keep checking whether this sum exist or not ...... so now hme yha pr manually .... hr index pr jane ke baad vha jo sum milega vha ke liye store krke ... to hme hr bar dekhna pdega ki kya iss index ko include krke 0 se i tak me bnne vale sare subarray me se kisi ka sum 0 aa rha hai kya .......... if yes then only hm kh skte ki hain .... chije shi hain .... so at last n^2..... aur vo kuch aisa hai ......





// class Solution {
//     public int longestBalanced(int[] nums) {
//         int n = nums.length;
//         int maxLen = 0;

//         for (int i = 0; i < n; i++) {
           
//             Set<Integer> seen = new HashSet<>();
//             int cnt = 0;

           
//             for (int j = i; j >= 0; j--) {
                
//                 if (!seen.contains(nums[j])) {
//                     seen.add(nums[j]);
                    
//                     if (nums[j] % 2 == 0) {
//                         cnt++;
//                     } else {
//                         cnt--;
//                     }
//                 }
//                 // Note: Agar 'seen' me pehle se hai, to hum count update nahi karenge
//                 // kyunki Distinct Count chahiye. Par loop chalega aur length badhegi.

//                 // Ab check karo: Kya is point par (j se i tak) mamla balanced hai?
//                 if (cnt == 0) {
//                     maxLen = Math.max(maxLen, i - j + 1);
//                 }
//             }
//         }

//         return maxLen;
//     }
// }







// now lets optimise this code...usse phle.. isi ko thoda behtar soch skte ki ab aise krenge ki .... jab bhi mujhe koi seen milega to vha index tak sabme phle hi iska jo effect hai (+1/-1) uska oppositve vha pr jakar mark kr dunga taki fir mai pure hi aise me vo add kr sku to fir jab bhi mile phle 0 milega vhi se length nikal lenge 

// class Solution {
//     public int longestBalanced(int[] nums) {
//         int n = nums.length;
//         int maxLen = 0;
//         int sum [] = new int[n];
//         Map<Integer, Integer> map = new HashMap<>();
//         map.put(0,-1);


//         for (int i = 0; i < n; i++) {

//             int val = nums[i]  % 2 == 0? +1 : -1; 
//             int prev = -1;
//             if (map.containsKey(nums[i])) {
//                prev = map.get(nums[i]);
//             }
            
//             for (int j = 0; j<= prev; j++){
//                 sum[j] -= val;
//             }
//             for(int j = 0; j<=i; j++){
//                 sum[j] += val;
//             }
//             for(int j = 0; j<=i; j++){
//                 if(sum[j] == 0){
//                     maxLen = Math.max(maxLen, i-j+1);
//                     break;
//                 }
//             }
            
//             map.put(nums[i],i);

//         }        
//         return maxLen;
//     }
// }


// now lets optimise this .....................




class Solution {

    class SegmentTree {
        int n;
        int[] lazy, max, min;

        SegmentTree(int n) {
            this.n = n;
            lazy = new int[4 * n];
            max = new int[4 * n];
            min = new int[4 * n];
        }

        void propagate(int idx) {
            if (lazy[idx] != 0) {
                lazy[2 * idx + 1] += lazy[idx];
                max[2 * idx + 1] += lazy[idx];
                min[2 * idx + 1] += lazy[idx];

                lazy[2 * idx + 2] += lazy[idx];
                max[2 * idx + 2] += lazy[idx];
                min[2 * idx + 2] += lazy[idx];

                lazy[idx] = 0;
            }
        }

        void updateRange(int idx, int nodeLeft, int nodeRight, int start, int end, int val) {
            if (nodeLeft > end || nodeRight < start) {
                return;
            }

            if (nodeLeft >= start && nodeRight <= end) {
                lazy[idx] += val;
                max[idx] += val;
                min[idx] += val;
                return;
            }

            propagate(idx);
            int mid = (nodeLeft + nodeRight) / 2;

            updateRange(2 * idx + 1, nodeLeft, mid, start, end, val);
            updateRange(2 * idx + 2, mid + 1, nodeRight, start, end, val);

            max[idx] = Math.max(max[2 * idx + 1], max[2 * idx + 2]);
            min[idx] = Math.min(min[2 * idx + 1], min[2 * idx + 2]);
        }

        int findLeftMostZero(int idx, int nodeLeft, int nodeRight, int queryLimit) {
           
            if (min[idx] > 0 || max[idx] < 0) return -1;
            
            if (nodeLeft > queryLimit) return -1;

            if (nodeLeft == nodeRight) {
                return (min[idx] == 0) ? nodeLeft : -1;
            }

            propagate(idx);
            int mid = (nodeLeft + nodeRight) / 2;

            int res = findLeftMostZero(2 * idx + 1, nodeLeft, mid, queryLimit);
            
            if (res != -1) return res;
            
            return findLeftMostZero(2 * idx + 2, mid + 1, nodeRight, queryLimit);
        }
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        
        Map<Integer, Integer> map = new HashMap<>(); 
        SegmentTree seg = new SegmentTree(n);

        for (int i = 0; i < n; i++) {
            int val = (nums[i] % 2 == 0) ? 1 : -1;

            if (map.containsKey(nums[i])) {
                int prev = map.get(nums[i]);
                seg.updateRange(0, 0, n - 1, 0, prev, -val);
            }

            seg.updateRange(0, 0, n - 1, 0, i, val);

   
            int j = seg.findLeftMostZero(0, 0, n - 1, i);
            
            if (j != -1) {
                maxLen = Math.max(maxLen, i - j + 1);
            }

            map.put(nums[i], i);
        }
        
        return maxLen;
    }
}