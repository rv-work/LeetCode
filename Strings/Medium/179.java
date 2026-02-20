class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String [] arr = new String[n];
        for(int i = 0; i<n; i++){
            int val =nums[i];
            arr[i] = ""+val;
        }
        // Arrays.sort(arr , (a, b) -> b.compareTo(a)); why this fails is because fo these kind of cases....like a = "3" b = "30" .. now in this one .. 30 will come first ... so 303 but wait .. thr correct oen is 330.. thats why we need to compare the addition.....
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // if the largest element is "0", return "0"
        if (arr[0].equals("0")) return "0";



        StringBuilder ans = new StringBuilder();
        for(String ele : arr){
            ans.append(ele);
        }


        return ans.toString();
    }
}