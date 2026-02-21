class Solution {
    int countOnes(int x) {
        int cnt = 0;
        while (x > 0) {
            cnt += (x & 1); 
            x >>= 1;         
            }
        return cnt;
    }

    boolean isPrime(int n) {
        if (n <= 1) return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }

        return true;
    }
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;

        for (int num = left; num <= right; num++) {
            int bits = countOnes(num);
            if (isPrime(bits)) ans++;
        }

        return ans;
    }

   
}









class Solution {

    int countOnes(int x) {
        int cnt = 0;
        while (x > 0) {
            cnt += (x & 1);
            x >>= 1;
        }
        return cnt;
    }
    public int countPrimeSetBits(int left, int right) {

        // Precomputed small prime set kyunki max 32 bits hain to ye yhi tak ho skta acc to constraints
        Set<Integer> primes = Set.of(2,3,5,7,11,13,17,19);

        int ans = 0;

        for (int num = left; num <= right; num++) {
            int bits = countOnes(num);

            if (primes.contains(bits)) ans++;
        }

        return ans;
    }
}