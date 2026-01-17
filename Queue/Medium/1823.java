class Solution {
    public int findTheWinner(int n, int k) {
       Queue<Integer> q = new LinkedList<>();

       for(int i = 1; i<=n; i++){
         q.add(i);
       }

       while(q.size() > 1){
         for(int i = 1; i<k; i++){
              q.add(q.poll());
         }
         q.poll();
       }

       return q.peek();
    }
}






class Solution {
    public int findTheWinner(int n, int k) {
        ArrayList<Integer> circle = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            circle.add(i);
        }
        int cur_ind = 0;

        while (circle.size() > 1) {
            int next_to_remove = (cur_ind + k - 1) % circle.size();
            circle.remove(next_to_remove);
            cur_ind = next_to_remove;
        }

        return circle.get(0);
    }
}










class Solution {

    int ansIndex(int n , int k){
        if(n==1) return 0;

        int idx = ansIndex(n-1 , k);
        idx += k; // to get index in original array
        idx %= n; // to make circular move

        return idx;
    }
    public int findTheWinner(int n, int k) {
        return ansIndex(n , k) + 1;
    }
}



class Solution {

    int ansIndex(int n , int k){
        if(n==1) return 0;

        int idx = ansIndex(n-1 , k);
        idx += k; 
        
// to get index in original array... kyunki hm hr bari +k index se start krte
// kyunki see , n = 5 m k = 2
//    ind = 0,1,2,3,4 
//         [1,2,3,4,5]
// so ... after 1st .. we will delete 2 and start again from 3 so phle vale pichhhe aa jayenge
//      idx:0,1,2,3.... 
     //    [3,4,5,1]

// so for 3.. to get original indx .... curr  + k .. kyunki hm hr step me k minuse kr dete hain use aage lane ke liye isiliye... phle 2 the pichhe lane ke liye k minuse kiya so balance krne ke liye +k
   





        idx %= n; // to make circular move

        return idx;
    }
    public int findTheWinner(int n, int k) {
        return ansIndex(n , k) + 1;
    }
}







