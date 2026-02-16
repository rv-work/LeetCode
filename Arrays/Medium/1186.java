class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int pre[] = new int[n];
        int suff[] = new int[n];
        int max = Integer.MIN_VALUE;
        int curr = 0;
        
        for(int i = 0; i<n; i++){
            curr += arr[i];
            if(arr[i] > curr){
                curr = arr[i];
            }
            pre[i] = curr;
            max = Math.max(max ,curr);
        }

        curr = 0;
        for(int i = n-1; i>=0; i--){
            curr += arr[i];
            if(arr[i] > curr){
                curr = arr[i];
            }
            suff[i] = curr;
            max = Math.max(max ,curr);
        }

        for(int i = 1; i<n-1; i++){
             max = Math.max(max ,pre[i-1]+suff[i+1]);
        }

        return max;
    }
}






class Solution {
    public int maximumSum(int[] arr) {
        if(arr.length == 1) return arr[0];
        int max = Integer.MIN_VALUE;
        int pwd = 0;
        int pd = arr[0];

        for(int i = 1; i<arr.length; i++){
            pwd = Math.max(pwd + arr[i] , pd);
            pd = Math.max(pd+arr[i],arr[i]);
            max = Math.max(max , Math.max(pwd , pd));
        }

        return max;
    }
}