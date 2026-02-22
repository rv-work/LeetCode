class Solution {
    void res( List<List<Integer>> ans  ,  List<Integer> li , int idx  , int k , int n ){
        if(idx > n ) {
            if(k == 0) ans.add(new ArrayList<>(li));
            return ;
        }

        res(ans , li , idx+1 , k, n);
        li.add(idx);
        res(ans , li , idx+1 , k-1, n);
        li.remove(li.size()-1);;
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans  = new ArrayList<>();
        for(int i = 1; i<=n; i++){
            List<Integer> li = new ArrayList<>();
            li.add(i);
            res(ans , li , i+1 , k-1,n);
        }

        return ans;

    }
}








class Solution {

    void res(List<List<Integer>> ans, List<Integer> li, int idx, int k, int n) {

        if (k == 0) {
            ans.add(new ArrayList<>(li));
            return;
        }

        if (idx > n) return;

        // pruning: if remaining numbers are less than needed
        if ((n - idx + 1) < k) return;

        // OPTION 1 → skip idx
        res(ans, li, idx + 1, k, n);

        // OPTION 2 → take idx
        li.add(idx);
        res(ans, li, idx + 1, k - 1, n);
        li.remove(li.size() - 1);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        res(ans, new ArrayList<>(), 1, k, n);
        return ans;
    }
}