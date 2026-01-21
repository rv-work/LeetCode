class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n1 = nums1.length, n2 = nums2.length;
        int half = (n1 + n2 + 1) / 2;

        int left = 0, right = n1;

        while (left <= right) {

            int mid1 = (left + right) / 2;      // cut in nums1
            int mid2 = half - mid1;            // cut in nums2

            // LEFT PART ENDS
            int l1 = (mid1 - 1 >= 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 - 1 >= 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;

            // RIGHT PART STARTS
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            // Perfect partition
            if (l1 <= r2 && l2 <= r1) {

                if (((n1 + n2) & 1) == 1) {
                    return Math.max(l1, l2);   // odd length
                }

                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0; // even length
            }

            // Move left in nums1
            else if (l1 > r2) {
                right = mid1 - 1;
            }

            // Move right in nums1
            else {
                left = mid1 + 1;
            }
        }

        return 0; // unreachable
    }
}
