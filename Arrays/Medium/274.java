class Solution {
    boolean check(int cnt , int arr[]){
        int lim = cnt;

        for(int num : arr){
            if(num >= lim){
                cnt--;
                if(cnt == 0) return true;
            }
        }

        return false;
    }
    public int hIndex(int[] c) {
        int n = c.length;
        int low = 1; int high = n;
        int ans = 0;
        while(low <= high){
            int mid = (low+high)>>1;
            if(check(mid , c)){
                ans = mid;
                low = mid+1;
            }else {
                high = mid-1;
            }
        }

        return ans;
    }
}







class Solution {
    public int hIndex(int[] c) {
        int n = c.length;
        int[] freq = new int[n + 1];

        // Count frequencies, cap values > n
        for (int x : c) {
            if (x >= n) freq[n]++;
            else freq[x]++;
        }

        int papers = 0;
        for (int i = n; i >= 0; i--) {
            papers += freq[i];
            if (papers >= i) return i;
        }
        return 0;
    }
}





class Solution {
    public int hIndex(int[] c) {
        Arrays.sort(c); 
        int n = c.length;
        for (int i = 0; i < n; i++) {
            int h = n - i;      
            if (c[i] >= h) {
                return h;
            }
        }
        return 0;
    }
}