class Solution {
    public int minimumSwap(String s1, String s2) {

        int ans = 0;

        int cnt1 = 0; // ..x/y
        int cnt2 = 0; // ..y/x

        for(int i  = 0; i<s1.length(); i++){
            if(s1.charAt(i) == 'x' && s2.charAt(i) == 'y') cnt1++;
            if(s1.charAt(i) == 'y' && s2.charAt(i) == 'x') cnt2++;
        }

        if(( (cnt1+cnt2)  & 1) == 1) return -1;
        
        ans += cnt1/2;
        ans += cnt2/2;

        if((cnt1 & 1) == 1) ans += 2;


        return ans;

    }
}


// hmpe sirf 3 case ho skte hain 
// 1. x/x , 2. y/y , 3. x/y , 4. y/x

// inme se 2 i hain jinhe solve krna hai baki 2 already solved hain 

// ab examples se clear hai also vhi true bhi hai 
// ki if ........x/y & x/y ... or y/x & y/x... hai to inhe solve krne me 1 swap lgta hai aur agar ye 
/// x/y & y/x hain to 2 swaps 

// so vhi kiya hmne... also agar dono strings me milkar count odd hai to matlab vo solve hi nhi ho skta dono me aadhe aadhe nhi distribute kiya jaa skta 


// thats it... to 2 se divide kiya ki jo same hain unke liye 1 -1 count 

// agar odd rha hoga to 2 aur lele ... ek hi bar usse jyada vo already even me aa chuka 

