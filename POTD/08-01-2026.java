class Solution {
    int rec(int i , int j , int[] nums1, int[] nums2){
        int n1 = nums1.length;
        int n2 = nums2.length;

        if(i >= n1 || j >= n2) return Integer.MIN_VALUE;

        int mul = nums1[i]*nums2[j] ;
        int next = rec(i+1, j+1, nums1 , nums2);
        if(next != Integer.MIN_VALUE){
            mul = Math.max(mul , mul + next);
        }

        mul = Math.max(mul , next);

        int notMul = Math.max( rec(i, j+1, nums1 , nums2)  , rec(i+1, j, nums1 , nums2) );
        

        return Math.max(mul , notMul);
        
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        return rec(0, 0 , nums1 , nums2);
    }
}