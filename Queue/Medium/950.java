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