class Solution {

    private boolean can(long[] base, int r, long k, long target) {
    int n = base.length;

    long[] diff = new long[n + 1];    // used for range increments
    long add = 0;                     // current active additions

    for (int i = 0; i < n; i++) {

        add += diff[i];               // apply ending additions
        long power = base[i] + add;   // effective power at city i

        if (power < target) {
            long need = target - power;

            if (k < need) return false;   // not enough stations left
            k -= need;

            add += need;                  // start increasing power now // condidering ki hm ise aisa point pr add kr rhe joki yha tak effect bhej ske aur aage bhi use ho ske.. meanse ye i .. uska left me last index ho to uske liye 

            // hm ise add krte hain i+r pr... to yha tak effect aa gya aur aage bhi jayega i+2*r tak jisse hmne uska maximum fayda utha liya.......


            int end = i + 2*r + 1;        // this is where effect stops
            if (end < n) diff[end] -= need; // ye hai ki vha pr ye khatam kr de effect 

            //******* We add need stations at the best place (i + r)
            //******* Which increases power of range [i ... i + 2r]
        }
    }

    return true;
}


    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;

        long pre[] = new long[n];
        long suff[] = new long[n];

        for(int i = 0; i<n; i++){
            if(i == 0) pre[i] += stations[i];
            else pre[i] += stations[i] + pre[i-1];
        }

        for(int i = n-1; i >= 0; i--){
            if(i == n-1) suff[i] += stations[i];
            else suff[i] += stations[i] + suff[i+1];
        }
        

        long power[] = new long[n];

        for(int i = 0; i<n; i++){
            power[i] = pre[i] + suff[i] - stations[i] ;
            if(i+r+1 <= n-1) power[i] -= suff[i+r+1];
            if(i-r-1 >=0 ) power[i] -= pre[i-r-1];
        }

        long low = 0L;
        long high = Arrays.stream(power).max().orElse(0L) + k; 
        long answer = 0L;


         while (low <= high) {
            long mid = low + (high - low) / 2;
            if (can(power, r, k, mid)) {
                answer = mid;     
                low = mid + 1;
            } else {
                high = mid - 1;   
            }
        }

        return answer;
    }
}