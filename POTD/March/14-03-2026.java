class Solution {

    int count = 0;
    String result = "";

    void res(int n, int k, String s){

        if(s.length() == n){
            count++;
            if(count == k){
                result = s;
            }
            return;
        }

        for(char ch : new char[]{'a','b','c'}){

            if(s.length() > 0 && s.charAt(s.length()-1) == ch)
                continue;

            res(n, k, s + ch);

            if(result.length() > 0) return;
        }
    }

    public String getHappyString(int n, int k) {
        res(n, k, "");
        return result;
    }
}