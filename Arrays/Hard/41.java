
class Solution {
    public int firstMissingPositive(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int x : nums) {
            set.add(x);
        }

        int n = nums.length;

        for (int i = 1; i <= n + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return -1; 
    }
}






class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean is1 = false;

        for(int i = 0; i<n; i++){
            if(nums[i] == 1) is1 = true;
            if(nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }

        if(!is1) return 1;

        for(int i = 0; i<n; i++){
            int num = Math.abs(nums[i]);
            if(nums[num-1] > 0) {   
                nums[num-1] *= -1;
            }
        }

        for(int i = 0; i<n; i++){
            if(nums[i] > 0) return i+1;
        }

        return n+1;

    }
}