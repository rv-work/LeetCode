class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int ans [] = new int[n];
        Arrays.sort(deck);
        int idx = 0;
        boolean skip =false;
        int i = 0;
        while(true){
            if(ans[idx] != 0){
                idx  = (idx + 1) % n;
                continue;
            }

            if(skip){
                idx  = (idx + 1) % n;
                skip = false;
                continue;
            } else {
                ans[idx] = deck[i++];
                idx  = (idx + 1) % n;
                skip = true;
            }

            if(i == n) break;
        }


        return ans;

    }
}










class Solution {

    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int ans [] = new int[n];
        Arrays.sort(deck);

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i< n; i++){
           q.add(i);
        }    
        
        int cnt = 0;
        while(cnt < n){
           ans[q.poll()] = deck[cnt];
           q.add(q.poll());
           cnt++;
        }   

        return ans;

    }
}