class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int j : arr1) {
            if (hm.containsKey(j)) {
                hm.put(j, hm.get(j) + 1);
            } else {
                hm.put(j, 1);
            }
        }
        int[] ans = new int[arr1.length];
        int cnt=0;
        for (int j : arr2) {
            while (hm.get(j) > 0) {
                ans[cnt] = j;
                cnt++;
                hm.put(j, hm.get(j) - 1);
            }
            hm.remove(j);
        }
        int[] remaining = new int[arr1.length-cnt];
        int index = 0;
        for (int j : arr1) {
            if (hm.containsKey(j)) {
                while (hm.get(j) > 0) {
                    remaining[index] = j;
                    index++;
                    hm.put(j, hm.get(j) - 1);
                }
                hm.remove(j);
            }
        }
        Arrays.sort(remaining);
        for (int j : remaining) {
            ans[cnt] = j;
            cnt++;
        }
        return ans;
    }
}













class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int length = 0;
        for(int i=0;i<arr1.length;i++){
            length = Math.max(length,arr1[i]);
        }
        int[] count = new int[length+1];
        for(int i=0;i<arr1.length;i++){
            count[arr1[i]]++;
        }
        int[] ans = new int[arr1.length];
        int index=0;
        for(int i=0;i<arr2.length;i++){
            while(count[arr2[i]]>0){
                ans[index] = arr2[i];
                index++;
                count[arr2[i]]--;
            }
        }
        for(int i=0;i<count.length;i++){
            while(count[i]>0){
                ans[index] = i;
                index++;
                count[i]--;
            }
        }
        return ans;
    }
}