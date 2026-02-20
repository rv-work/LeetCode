class Solution {
    public String makeLargestSpecial(String s) {
        int n = s.length();
        if(n <= 2) return s;
        List<String> li = new ArrayList<>();
        boolean is = false;
        

        int i = 0;
        int cnt = 0;
        int last = -1;
        while(i<n){
            if(s.charAt(i) == '1'){
                cnt++;
            } else cnt--;

            if(cnt == 0){
                String res = "";
                if(last == -1 && i == n-1){
                    is = true;
                    res = makeLargestSpecial(s.substring(last+1+1,i+1-1));
                }else {
                    res = makeLargestSpecial(s.substring(last+1,i+1));
                }
                li.add(res);
                last = i;
            }
            i++;
        }


        Collections.sort(li, (a, b) -> b.compareTo(a));

        StringBuilder ans = new StringBuilder();

        for(String str : li){
            ans.append(str);
        }
        if(is){
            return "1"+ans.toString()+"0";
        }

        return ans.toString();
    }
}





// First the problem can be converted to rearrange valid parentheses.

// 101100 => ()(())

// First we try to split the string into as many sub valid parentheses as possible.

// Case 1 ()(()())(()) => () | (()()) | (())

// in this example we simply sort 3 parts and will get the answer.

// The question is can we always split any string? The answer is no, for example:

// Case 2 (()(())) => ((())())

// Let's think about why we cannot split the string:
// First '(' is matching with the last ')' not any ')' in between, otherwise the string can be split into at least two parts.

// With above we can see the relationship between Case1 and Case2:
// 1 Split the string into sub valid parentheses.
// 2 Recursion(rearrange) on every sub valid parentheses.
// 3 When the result comes back, we sort each part and put together.