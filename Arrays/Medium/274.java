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


