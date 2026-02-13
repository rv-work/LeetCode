class Solution {

    int getForThree(String str, char f, char s, char t) {
        int n = str.length();
        int first = 0;
        int second = 0;
        int third = 0;
        int max = Integer.MIN_VALUE;
        Map<String, Integer> map = new HashMap<>();
        // isme hme seedhe abc store krne ki bjah a#b#c store krenge taki pta chale shi se (1#12#13) not (11213) now we know ki ye bahot bda ho skta so lets store (a-b)#(b-c).. ye bhi unique hi hoga..
        map.put("0#0", -1);

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (ch == f) {
                first++;
            } else if (ch == s) {
                second++;
            } else {
                third++;
            }

            String key = (first - second) + "#" + (second - third);
            if (map.containsKey(key)) {
                max = Math.max(max, i - map.get(key));
            } else {
                map.put(key, i); // kyunki hme max chahiye to if exits to better hai na vha bigger ans aayega
            }

        }

        return max;
    }

    int getForTwo(String str, char f, char s) {
        int n = str.length();
        int first = 0;
        int second = 0;
        int max = Integer.MIN_VALUE;

        // 1st way .........

        // for(int i = 0; i<n; i++){
        //     char ch = str.charAt(i);
        //     if(ch == f){
        //         first++;
        //     } else if(ch == s){
        //         second++;
        //     } else { // hits the wall third character
        //         int min = Math.min(first,second);
        //         int curr = 2*min;
        //         max = Math.max(curr , max);
        //         first = 0;
        //         second = 0;
        //     }
        // } // for last case... if string ends here .. without any wall...
        // int min = Math.min(first,second);
        // int curr = 2*min;
        // max = Math.max(curr , max);

        // abhi isme ye problem hai ki may be kisi ek ke bich me dusre ho to vo shi nhi hoga na jaise ki 
        // Input
        // s =
        // "cbbbc"

        // Output
        // 4
        // Expected
        // 3... kyuni cbbbc me se 4 nhi ho skta .. means sirf count se to possible nhi

        // so lets handle that ki last ki k sum in a window vale type se jayenge.... ( have seen )

        // 2nd way....
        // Map<Integer,Integer> map = new HashMap<>();
        // map.put(0,-1);

        //  for(int i = 0; i<n; i++){
        //     char ch = str.charAt(i);
        //     if(ch == f){
        //         first++;
        //     } else if(ch == s){
        //         second++;
        //     } else { // hits the wall third character
        //         // map.clear(); // yha se nya record bnayeinege ab ...
        //         map = new HashMap<>(); // kyunki clear krne me in worst case it takes O(n)... so 
        //         first = 0;
        //         second = 0;
        //     }

        //     int key = first - second;
        //     if(map.containsKey(key)){
        //         max = Math.max(max , i- map.get(key));
        //     } else {
        //         map.put(key,i); // kyunki hme max chahiye to if exits to better hai na vha bigger ans aayega
        //     }

        // } 

        // now if we want to avoid .. instanticiating new map each time so ..... lets override value .... so how i will get to know whether this is overrided or old.... so for that we will store a segment number ...

        Map<Integer, int[]> map = new HashMap<>();
        int segment = 0;

        map.put(0, new int[] { segment, -1 });

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (ch == f) {
                first++;
            } else if (ch == s) {
                second++;
            } else {
                segment++;
                first = 0;
                second = 0;
                map.put(0, new int[] { segment, i });
                continue;
            }

            int key = first - second;

            if (map.containsKey(key)
                    && map.get(key)[0] == segment) {

                max = Math.max(max, i - map.get(key)[1]);
            } else {
                map.put(key, new int[] { segment, i });
            }
        }

        return max;
    }




    

    public int longestBalanced(String s) {
        int n = s.length();
        int max = Integer.MIN_VALUE;

        // for 1 char only ( for combination of only 1 ....a/b/c)
        char c = '#';
        int curr = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == c) {
                curr++;
                max = Math.max(curr, max);
            } else {
                c = ch;
                curr = 1;
            }
        }
        max = Math.max(curr, max); // for last case .... string ends without any wall(other character at last)

        // for combinations of any 2 ...// string , have(first two), third is wall(ab-c/bd-a/ac-b)
        max = Math.max(max, getForTwo(s, 'a', 'b'));
        max = Math.max(max, getForTwo(s, 'b', 'c'));
        max = Math.max(max, getForTwo(s, 'a', 'c'));

        // for combination of all 3 (abc)
        max = Math.max(max, getForThree(s, 'a', 'b', 'c'));

        return max;
    }
}