
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int maxDist = 0;
        int m = nums1.length;
        int n = nums2.length;
        
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else {
                i++;
            }
        }
        
        return maxDist;
    }
}

