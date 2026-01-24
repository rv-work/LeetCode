class Solution {
    int check(int m , int n , int num){
           int cnt = 0;
           for(int i = 1; i<= m ; i++){
              if(i*n <= num) cnt += n;
              else {
                cnt += num/i;
              }
           }


           return cnt;
    }

    public int findKthNumber(int m, int n, int k) {
        int l = 1; int r = m*n;

        while(l<=r){
          int mid = (l+r)/2;
          int count = check(m,n,mid);
          if(count >= k) r = mid-1;
          else l = mid+1;
        }


        return l;
    }
}