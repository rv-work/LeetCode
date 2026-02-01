class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int ans[] = new int[nums.size()];
        for(int i = 0; i<nums.size(); i++){
            int num = 1;
            while(num <= nums.get(i) && (num | num+1) != nums.get(i) ){
                num++;
            }
            if(num > nums.get(i)) ans[i] = -1;
            else ans[i] = num;
        }

        return ans;
    }
}