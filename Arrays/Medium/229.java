class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer>ans = new ArrayList<>();

        HashMap<Integer,Integer>map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        for(int key:map.keySet()){
            if(map.get(key)>n/3){
                ans.add(key);
            }
        }

        return ans;
    }
}









class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int num1 = 0;
        int num2 = 0;
        int ele1 = Integer.MIN_VALUE;
        int ele2 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num == ele1) {
                num1++;
            } else if (num == ele2) {
                num2++;
            } else if (num1 == 0 && num != ele2) {
                ele1 = num;
                num1 = 1;
            } else if (num2 == 0 && num != ele1) {
                ele2 = num;
                num2 = 1;

            } else {
                num1--;
                num2--;
            }

        }

        num1 = 0;
        num2 = 0;
        for (int num : nums) {
            if (num == ele1)
                num1++;
            if (num == ele2)
                num2++;
        }

        List<Integer> ans = new ArrayList<>();
        if (num1 > nums.length / 3)
            ans.add(ele1);
        if (num2 > nums.length / 3)
            ans.add(ele2);

        return ans;
    }
}
