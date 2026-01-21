class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = 1, right = x / 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;        
    }
}










class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;

        int count = 0;
        int temp = x;
        while (temp > 0) {
            count++;
            temp >>= 1;
        }

        int result = 0;
        for (int i = (count + 1) / 2; i >= 0; i--) {
            result |= (1 << i);
            if ((long) result * result > x) {
                result ^= (1 << i);
            }
        }

        return result;        
    }
}