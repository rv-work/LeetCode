class Solution {
    int reverse(int n){
        int num = 0;
        while(n != 0){

            num = num * 10 ;
            int dig = n%10;
            num = num +  dig;
            n = n / 10;

        }
        return num;
    }

    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }
}