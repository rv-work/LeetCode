// class Solution {

//     int res(List<Integer> price, List<List<Integer>> special, List<Integer> needs , int idx){

//         if (idx == special.size()) {
//             int sum = 0;
//             for (int i = 0; i < needs.size(); i++) {
//                 sum += needs.get(i) * price.get(i);
//             }
//             return sum;
//         }

//         int notTake = res(price, special, needs, idx+1);
//         int take = Integer.MAX_VALUE;

//         List<Integer> offer = special.get(idx);
//         boolean canTake = true;
//         for (int i = 0; i < offer.size()-1; i++) {
//             if (offer.get(i) > needs.get(i)) {
//                 canTake = false;
//                 break;
//             }
//         }

//         if (canTake) {
//             // Create a new needs list for the take case
//             List<Integer> newNeeds = new ArrayList<>();
//             for (int i = 0; i < offer.size()-1; i++) {
//                 newNeeds.add(needs.get(i) - offer.get(i));
//             }

//             take = offer.get(offer.size()-1) + 
//                    res(price, special, newNeeds, idx); // not idx+1.. because we can take this offer many times
//         }

//         return Math.min(take, notTake);
//     }

//     public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
//         return res(price, special, needs, 0);
//     }
// }


// here we are sending a updated copy each time...

// but also we can do with backtrack................down




// class Solution {

//     int res(List<Integer> price, List<List<Integer>> special, List<Integer> needs , int idx){

//         if(idx == special.size()){
//             int sum = 0;
//             for(int i = 0; i < needs.size(); i++){
//                 sum += needs.get(i) * price.get(i);
//             }
//             return sum;
//         }

//         int notTake = res(price, special, needs, idx+1);
//         int take = -1;

//         List<Integer> needsCopy = new ArrayList<>(needs);
//         List<Integer> offer = special.get(idx);

//         boolean canTake = true;
//         for(int i = 0; i < offer.size()-1; i++){
//             if(offer.get(i) > needs.get(i)){
//                 canTake = false;
//                 break;
//             }
//         }

//         // we are not chacking for the cost because it might possible that .. it can provide us better combination in future.... and we are checking all the possibilities so its ok..

//         if(canTake){
//             // apply offer
//             for(int i = 0; i < offer.size()-1; i++){
//                 needs.set(i, needs.get(i) - offer.get(i));
//             }

//             take = offer.get(offer.size()-1) + 
//                    res(price, special, needs, idx); // not idx+1.. because we can take this offer many
//         }

//         // restore needs
//         for(int i = 0; i < needs.size(); i++){
//             needs.set(i, needsCopy.get(i));
//         }

//         if(take == -1) return notTake;
//         return Math.min(take, notTake);
//     }

//     public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
//         return res(price, special, needs, 0);
//     }
// }



///  memo 



// class Solution {

//     int res(List<Integer> price, List<List<Integer>> special, 
//             List<Integer> needs, int idx, Map<String, Integer> dp) {

//         String key = idx + "-" + needs.toString();
//         if (dp.containsKey(key)) return dp.get(key);

//         // no more offers → normal cost
//         if (idx == special.size()) {
//             int sum = 0;
//             for (int i = 0; i < needs.size(); i++) {
//                 sum += needs.get(i) * price.get(i);
//             }
//             dp.put(key, sum);
//             return sum;
//         }

//         // OPTION 1: Do not take current offer
//         int notTake = res(price, special, needs, idx + 1, dp);

//         // OPTION 2: Take current offer (many times possible)
//         List<Integer> offer = special.get(idx);
//         boolean canTake = true;
//         List<Integer> newNeeds = new ArrayList<>();

//         for (int i = 0; i < needs.size(); i++) {
//             if (offer.get(i) > needs.get(i)) {
//                 canTake = false;
//                 break;
//             }
//             newNeeds.add(needs.get(i) - offer.get(i));
//         }

//         int take = Integer.MAX_VALUE;
//         if (canTake) {
//             take = offer.get(offer.size() - 1) +
//                     res(price, special, newNeeds, idx, dp);
//         }

//         int ans = Math.min(take, notTake);
//         dp.put(key, ans);
//         return ans;
//     }

//     public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
//         return res(price, special, needs, 0, new HashMap<>());
//     }
// }





// memo ....without idx... kyunki same index and same needs possible hi nhi ki repeat hoo aur hm same offer many times le skte to hr bar hm sare try krenge thats all..



class Solution {

    int solve(List<Integer> price, List<List<Integer>> special, 
              List<Integer> needs, Map<String, Integer> dp) {

        String key = needs.toString();
        if (dp.containsKey(key)) return dp.get(key);

        // Cost if no offers taken
        int normalCost = 0;
        for (int i = 0; i < needs.size(); i++) {
            normalCost += needs.get(i) * price.get(i);
        }

        int best = normalCost;

        // Try all offers
        for (List<Integer> offer : special) {
            boolean canTake = true;
            List<Integer> newNeeds = new ArrayList<>();

            for (int i = 0; i < needs.size(); i++) {
                if (offer.get(i) > needs.get(i)) {
                    canTake = false;
                    break;
                }
                newNeeds.add(needs.get(i) - offer.get(i));
            }

            if (canTake) {
                int offerPrice = offer.get(offer.size() - 1);
                best = Math.min(best, offerPrice + solve(price, special, newNeeds, dp));
            }
        }

        dp.put(key, best);
        return best;
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return solve(price, special, needs, new HashMap<>());
    }
}

/// tabulation... is not good idea here .... 6 states of needs....... we can but lets not ..



