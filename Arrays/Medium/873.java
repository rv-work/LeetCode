class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Set<Integer> map = new HashSet<>();
        for(int i = 0; i<n; i++){
            map.add(arr[i]);
        }
        
        int max = Integer.MIN_VALUE;
        

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                int num1 = arr[i];
                int len  = 2;
                int num2 = arr[j];
                int num3 = num1+num2;
                while(map.contains(num3)){
                    len++;
                    num1 = num2;
                    num2 = num3;
                    num3 = num1+num2;
                }

                max = Math.max(max , len);
            }
        }

        return max <= 2 ? 0 : max;
    }
}