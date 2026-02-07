
class Solution {

    class DSU {
        int[] parent;
        DSU(int n){
            parent = new int[n+1];
            for(int i = 0; i <= n; i++) parent[i] = i;
        }
        int find(int x){
            if(parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b){
            a = find(a);
            b = find(b);
            if(a != b) parent[a] = b;
        }
    }

    int[] buildSPF(int max){
        int[] spf = new int[max+1];
        for(int i = 2; i <= max; i++){
            if(spf[i] == 0){ // i is prime
                for(int j = i; j <= max; j += i){
                    if(spf[j] == 0) spf[j] = i;
                }
            }
        }
        return spf;
    }

    public boolean gcdSort(int[] nums) {

        int max = 0;
        for(int x : nums) max = Math.max(max, x);

        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        DSU dsu = new DSU(max);

        int[] spf = buildSPF(max);

        // factorize using SPF array
        for (int num : nums) {

            int x = num;
            int prevPrime = -1;

            while (x > 1) {
                int p = spf[x];
                dsu.union(num, p);    // connect number with prime

                if (prevPrime != -1)
                    dsu.union(prevPrime, p); // connect primes together

                prevPrime = p;

                while (x % p == 0) x /= p;
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(dsu.find(nums[i]) != dsu.find(sorted[i])) return false;
        }
        return true;
    }
}


// SPF hme help krna hai log.n me chije krne me ... hme check nhi krna pdta random... ki 2,3,5,7...spf hme us number sabse chhota prime factor de deta hai jisse ki ... hme seedhe use add krte hain usi ke aage divide krte krte aage ke bhi mil jate .. manual check nhi krna pdta...