class Solution {
    public int longestAwesome(String s) {
        int n = s.length() ; 
        int rs = 0 ; 
        int len = (1<<10) ; 
        char[] chars = s.toCharArray() ; 
        int[] mask = new int[len] ;
        for( int i=0 ; i<len ; ++i ){
            mask[i] = n ; 
        }
        int curr = 0 ; 
        for( int i=0 ; i<n ; ++i ){
            int num = chars[i] - '0' ; 
            curr ^= ( 1 << num ) ; 
            if( curr == 0 || (curr&(curr-1)) == 0 ){
                rs = i+1 ; 
            }else {
                if( mask[curr] != -1 ){
                    rs = Math.max( rs , i - mask[curr] ) ; 
                }
                for( int j=0 ; j<=9 ; ++j ){
                    rs = Math.max( rs , i - mask[curr^(1<<j)] ) ; 
                }
            }
            if( mask[curr] == n ){
                mask[curr] = i ; 
            }
        }
        return rs ; 
    }
}








// mine..............................................................



class Solution {
    public int longestAwesome(String s) {
        int max = Integer.MIN_VALUE;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);

        int xor = 0; // isme hm hr number ke liye set/unset ka game khelenge unke place pr jake....agar vo aage kahi bhi effect 0 huaa menas bech me jo bhi tha vo cancel out huaa hai matlab even length also... hm check kr rhe honge ki 0 to 9 tak kya koi bhi agar ek bar hm maan le to bhi kya possible hain

        for(int i = 0; i<s.length(); i++){
            xor ^= ( 1 <<  s.charAt(i)-'0');

            if(map.containsKey(xor)){
                max = Math.max(max , i-map.get(xor)); // even lenght palindrome..
            }


                for(int j = 0; j<10; j++){
                    int odd = (xor ^ (1 << j)); // trying to change each bit of xor ( flip ).. for odd..hm aisa maante hain ki let say koi even time nhi aaya to bhi agar vo odd time hota to uske place ( 0 -9 ) pr unset hota..
    
                    if(map.containsKey(odd)){
                    max = Math.max(max , i-map.get(odd)); // odd lenght palindrome..
                }
            }


            if(!map.containsKey(xor)){
                map.put(xor, i);
            }
        }

        return max;

    }
}


/* 
Har prefix ka digit-parity (even/odd) bitmask rakhte hain.
isse ye hai ki jab hm xor krte hain same number hota to ... sare bits 0 ho jayenge ..to no effect .. 
so vhi krne ki koshish hai ki lete jao xor aur check krte jao..
Agar do prefix ka bitmask same hai → beech ka substring palindrome ban sakta hai (saare even counts).
Aur agar sirf 1 bit differ ho → ek odd allowed, fir bhi palindrome ban sakta hai.
*/

