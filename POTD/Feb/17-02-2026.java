class Solution {
    int countBits(int n){
        int cnt = 0;
        while(n != 0){
            if((n & 1) == 1) cnt++;
            n >>= 1;
        }

        return cnt;
    }
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for(int h = 0; h<12; h++){
            for(int m = 0; m<60; m++){
                int cnt = countBits(h) + countBits(m);
                if(cnt == turnedOn){
                    String str = ""+h+":";
                    if(m >= 10){
                        str += m;
                    } else {
                        str += "0"+m;
                    }
                    ans.add(str);
                }
            }
        }

        return ans;
    }
}