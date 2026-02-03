class Solution {
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        
        int n = nums.length;
        int i = 0;
        
        while (i < n) {
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                i += 2;
            } else {
                ans.add(nums[i]);
                i++;
            }
        }
        
        return new int[]{ans.get(0), ans.get(1)};
    }
}










class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int x : nums) xor ^= x;

        int diff = xor & -xor;

        int a = 0, b = 0;
        for (int x : nums) {
            if ((x & diff) == 0) a ^= x;
            else b ^= x;
        }
        return new int[]{a, b};
    }
}





class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) xor ^= num;

        // find rightmost set bit in xor
        // int k = 0;
        // for (int i = 0; i < 32; i++) {
        //     if (((xor >> i) & 1) == 1) {
        //         k = i;
        //         break;
        //     }
        // }


        // int k = xor & -xor;..........alternative

        // eg/..
        // xor =  10110000
        // -xor = 01010000 (2’s complement)
        // -------------------------
        // &    = 00010000   ← rightmost set bit



        int a = 0, b = 0; // 2 grps....  set and unset
        for (int num : nums) {
            if (((num >> k) & 1) == 1) a ^= num;
            else b ^= num;
        }


//alternative.../... k be like .. 0010.... so jiska ye vala 1 hoga vo ek me jo nhi hoga to 0 hi aayega vo dusre me 
        // for (int x : nums) {
        //    if ((x & diff) == 0) a ^= x;
        //    else b ^= x;
        // }


        return new int[]{a, b};
    }
}

