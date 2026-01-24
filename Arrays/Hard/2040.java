// class Solution {
//     long check(int[] nums1, int[] nums2, long num){
//         int n1 = nums1.length;
//         int n2 = nums2.length;
//         if(n1 > n2) return check(nums2 , nums1 , num);
        
//         long cnt = 0;
//         for(int i = 0; i<n1; i++){
//             if(nums1[i]*nums2[n2-1] <= num) cnt += n2;
//             else {
//                 for(int j = 0; j<n2; j++){
//                       if(nums1[i]*nums2[j] <= num) cnt += 1;
//                       else return cnt;
//                 }
//             }
//         }

//         return -1;// never reached
//     }

//     public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
//         long l = Math.min(nums1[0] , nums2[0]);
//         int n1 = nums1.length;
//         int n2 = nums2.length;
//         long r = nums1[n1-1]*nums2[n2-1];

//         while(l<=r){
//             long mid = (l+r)/2;
//             long cnt = check(nums1 , nums2 , mid);
//             if(cnt >= k ) r = mid-1;
//             else l = mid+1;
//         }

//         return l;
//     }
// }

// this is wrong because numbers can be -ve............................................................


// 🔥 Example that breaks your logic

// Agar:

// nums1 = [-4]
// nums2 = [-5, -1, 2, 6]


// Row banega:

// -4 * [-5, -1, 2, 6] = [20, 4, -8, -24]


// Ye row sorted nahi hai.

// But tum assume kar rahe ho ki:

// if(nums1[i]*nums2[n2-1] <= num) → poora row <= num
// else traverse left-to-right and break


// BUT this only works when row is sorted.



// Neg X Neg = Pos (Order reverses: smaller absolute value -> smaller product)
// Pos X Pos = Pos (Order stays same)
// Neg X Pos = Neg (Order stays same: more negative -> more negative product)
// Pos X Neg = Neg (Order reverses)

// class Solution {
//     public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {

//         // Split nums into negative, positive and zero groups
//         List<Integer> neg1 = new ArrayList<>();
//         List<Integer> pos1 = new ArrayList<>();
//         List<Integer> neg2 = new ArrayList<>();
//         List<Integer> pos2 = new ArrayList<>();
//         long z1 = 0, z2 = 0;

//         for (int x : nums1) {
//             if (x < 0) neg1.add(x);
//             else if (x > 0) pos1.add(x);
//             else z1++;
//         }
//         for (int x : nums2) {
//             if (x < 0) neg2.add(x);
//             else if (x > 0) pos2.add(x);
//             else z2++;
//         }

//         Collections.sort(neg1);               // negative sorted ascending e.g. [-10,-3,-1]
//         Collections.sort(neg2);
//         Collections.sort(pos1);               // positive sorted ascending e.g. [1,3,7]
//         Collections.sort(pos2);

//         long lo = -10000000000L, hi = 10000000000L, ans = 0;

//         // Binary search on product value
//         while (lo <= hi) {
//             long mid = lo + (hi - lo) / 2;
//             if (count(mid, neg1, pos1, z1, neg2, pos2, z2) >= k) {
//                 ans = mid;
//                 hi = mid - 1;
//             } else {
//                 lo = mid + 1;
//             }
//         }
//         return ans;
//     }

//     // Count products <= target value
//     private long count(long val,
//                        List<Integer> neg1, List<Integer> pos1, long z1,
//                        List<Integer> neg2, List<Integer> pos2, long z2) {

//         long cnt = 0;

//         // Zero products -> contribute only when val >= 0
//         if (val >= 0) {
//             long s1 = neg1.size() + pos1.size() + z1;
//             long s2 = neg2.size() + pos2.size() + z2;
//             cnt += z1 * s2 + z2 * s1 - z1 * z2;
//         }

//         // Negative * Negative → positive
//         if (val >= 0) {
//             int j = neg2.size() - 1;
//             for (int x : neg1) {
//                 while (j >= 0 && (long) x * neg2.get(j) <= val) j--;
//                 cnt += (neg2.size() - 1 - j);
//             }
//         }

//         // Positive * Positive → positive
//         if (val >= 0) {
//             int j = pos2.size() - 1;
//             for (int x : pos1) {
//                 while (j >= 0 && (long) x * pos2.get(j) > val) j--;
//                 cnt += (j + 1);
//             }
//         }

//         // Negative * Positive → negative
//         if (val >= 0) {
//             cnt += (long) neg1.size() * pos2.size();
//         } else {
//             int j = 0;
//             for (int x : neg1) {
//                 while (j < pos2.size() && (long) x * pos2.get(j) > val) j++;
//                 cnt += (pos2.size() - j);
//             }
//         }

//         // Positive * Negative → negative
//         if (val >= 0) {
//             cnt += (long) pos1.size() * neg2.size();
//         } else {
//             int j = 0;
//             for (int x : pos1) {
//                 while (j < neg2.size() && (long) x * neg2.get(j) <= val) j++;
//                 cnt += j;
//             }
//         }

//         return cnt;
//     }
// }


// not better but more clean...............



class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {

        // Always iterate on smaller array
        if (nums1.length > nums2.length)
            return kthSmallestProduct(nums2, nums1, k);

        long lo = -10000000000L, hi = 10000000000L, ans = 0;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if (countLessEqual(nums1, nums2, mid) >= k) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    // Count pairs with product <= val
    private long countLessEqual(int[] A, int[] B, long val) {
        long cnt = 0;
        int n = B.length;

        for (int x : A) {
            if (x > 0) {
                cnt += upperBound(B, val, x);   // count y <= val/x
            } else if (x < 0) {
                cnt += (n - lowerBound(B, val, x)); // count y >= val/x
            } else {
                if (val >= 0) cnt += n;        // 0 <= val
            }
        }
        return cnt;
    }

    // Count elements where x * B[mid] <= val (x > 0)
    private int upperBound(int[] B, long val, int x) {
        int l = 0, r = B.length - 1, res = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if ((long)x * B[m] <= val) {
                res = m + 1;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }

    // First index where x * B[mid] <= val (x < 0)
    private int lowerBound(int[] B, long val, int x) {
        int l = 0, r = B.length - 1, res = B.length;
        while (l <= r) {
            int m = (l + r) / 2;
            if ((long)x * B[m] <= val) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return res;
    }
}
