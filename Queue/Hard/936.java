class Solution {

    int getIndex(String stamp, char[] arr) {
        int n = arr.length;
        int m = stamp.length();

        int idx = -1;
        int max = 0;

        for (int i = 0; i <= n - m; i++) {
            int j = i;
            boolean notStar = false;

            while (j < i + m && (arr[j] == '*' || arr[j] == stamp.charAt(j - i))) {
                if (arr[j] != '*') notStar = true;
                j++;
            }

            if (j == i + m && notStar && m > max) {
                max = m;
                idx = i;
            }
        }
        return idx;
    }

    public int[] movesToStamp(String stamp, String target) {
        int n = target.length();
        char[] arr = target.toCharArray();
        List<Integer> ans = new ArrayList<>();

        while (true) {
            int idx = getIndex(stamp, arr);

            if (idx == -1) break;

            for (int k = idx; k < idx + stamp.length(); k++) {
                arr[k] = '*';
            }

            ans.add(idx);
        }

        for (char c : arr) {
            if (c != '*') return new int[0];
        }

        Collections.reverse(ans);

       int[] res = new int[ans.size()];
       int idx = 0;
       for (int x : ans) {
           res[idx++] = x;
       }


        return res;
    }
}



// actually is qiestion me hm ulta game play kr rhe hain

// see what we are doing is ki ...find max possible lenth each time kyunki unhe override krne me problem nhi vo to kr hi skte so reverse game play hai .... matlab last steo hm phle calculate kr rhe ...


//  target:  a b a b a b c b c b a b a b c b c


// layer 1:          a b c           a b c    
// layer 2:      a b c   a b c   a b c   a b c
// layer 3:  a b c           a b c



//  pass 1:  a b a b a b c b c b a b a b c b c
//                   ^ ^ ^           ^ ^ ^

//  pass 2:  a b a b * * * b c b a b * * * b c
//               ^ ^ ^   ^ ^ ^   ^ ^ ^   ^ ^ ^

//  pass 3:  a b * * * * * * * b * * * * * * *
//           ^ ^ ^           ^ ^ ^

//  pass 4:  * * * * * * * * * * * * * * * * *