// class Solution {

//     class Pair {
//         char ch;
//         int freq;
//         Pair(char c , int f){
//             ch = c;
//             freq = f;
//         }
//     }

//     public int leastInterval(char[] tasks, int n) {
//         int size = tasks.length;

//         Map<Character , Integer> mp = new HashMap<>();

//         for(char ch : tasks){
//             mp.put(ch , mp.getOrDefault(ch , 0) + 1);
//         }
        
//         PriorityQueue<Pair> pq = new PriorityQueue<>(
//             (a,b) -> Integer.compare(b.freq , a.freq)   // max-heap
//         );
        
//         for (var entry : mp.entrySet()) {
//             pq.add(new Pair(entry.getKey(), entry.getValue()));
//         }


//         char arr[] = new char[((size-1)*(n+1))+1];
//         Arrays.fill(arr , '#');

//         while(!pq.isEmpty()){
//             Pair p = pq.poll();
//             int freq = p.freq;
//             int i = 0;
//             while(freq -- > 0){
//               while(arr[i] != '#')i++;
//               arr[i] = p.ch;
//               i += n + 1;
//             }
//         }


//         for(int i = arr.length-1; i>= 0; i--){
//             if(arr[i] != '#') return i+1;
//         }

//         return -1;
//     }
// }

// ["A","A","A","B","B","B", "C","C","C", "D", "D", "E"] & n = 2;


// our code : A B C A B C A B C D E # D = 13
// correct  : A B C A B D C A B C D E = 12.......

// so this is wrong

// abhi hm jo kr re vo ye hai ki max freq pick kro use rkhne ki koshish kro 

// but the right thing is..... ki max freq at each moment... not initial... so lets emplement the correct solution that hm whi phle leneg jiski us moment pr best hoga lene 

// in short... pick the task with max freq at that moment not initial......

// hmara motive ab bhi vhi rhega but dynamic

// we can not just pick and put and decrease and put inside and pick min again ... no kyunki sam efirse aa skta ... to iss chij ko ensure krne ke liye ki same element .. n ... ke baad hi aaya...hm n+1 ki cycle leke chalenge ..........




// class Solution {

//     class Pair {
//         char ch;
//         int freq;
//         Pair(char c , int f){
//             ch = c;
//             freq = f;
//         }
//     }

//     public int leastInterval(char[] tasks, int n) {
//         int size = tasks.length;
//         int ans = 0;

//         Map<Character , Integer> mp = new HashMap<>();

//         for(char ch : tasks){
//             mp.put(ch , mp.getOrDefault(ch , 0) + 1);
//         }
        
//         PriorityQueue<Pair> pq = new PriorityQueue<>(
//             (a,b) -> Integer.compare(b.freq , a.freq)   // max-heap
//         );
        
//         for (var entry : mp.entrySet()) {
//             pq.add(new Pair(entry.getKey(), entry.getValue()));
//         }


//         char arr[] = new char[((size-1)*(n+1))+1];
//         Arrays.fill(arr , '#');

//         int i = 0; // hm global le skte hain hr bar '#' pr hi hoga for sure ..
//         while(!pq.isEmpty()){
//           int cycle = n+1;
//           List<Pair> cycleEle = new ArrayList<>();

//           while(cycle > 0 && !pq.isEmpty()){
//              Pair p = pq.poll();
//              int freq = p.freq;
             
//              // hm ise rkhenge ..
//              arr[i] = p.ch;
//              i++;
//              cycle--;
//              freq--; // ab kyunki agar ye bacha huaa hai to rkna pdega again ... but abhi hi daal diya to ye ho skta fir se aa jaye isi cycle me..... to ise hm dubara daaal denge jab ye cycle puri ho jayegi .... so that next turn pr fir se aa skta hai ...to lets maintain a list.

//             if(freq  > 0){
//                 cycleEle.add(new Pair(p.ch , freq));
//             }

//           }

//           pq.addAll(cycleEle);// add all

//           // agar  pq empty ho gya matlab ab elements hi ni to yhi tak ke count addd kr lo jitne bhi huye to total the n+1... jisme se km krte krte cycle tak aa gye to utne o gye to 
//           if(pq.isEmpty()){
//             ans += n+1 - cycle;///
//           }else { // otherwise abhi baki hai krna to yha pura add krenge and next round me jayenge 
//             ans += n+1 ;
//           }
//         }


//         return ans;
//     }
// }

// optimization...... hm sirf count kr rhe to store krne ki jarurat nhi aur character bi useless hai 



class Solution {

    public int leastInterval(char[] tasks, int n) {
        int size = tasks.length;
        int ans = 0;

        Map<Character , Integer> mp = new HashMap<>();

        for(char ch : tasks){
            mp.put(ch , mp.getOrDefault(ch , 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(b , a) 
        );
        
        for (var entry : mp.entrySet()) {
            pq.add(entry.getValue());
        }



        while(!pq.isEmpty()){
          int cycle = n+1;
          List<Pair> cycleEle = new ArrayList<>();

          while(cycle > 0 && !pq.isEmpty()){
             Pair p = pq.poll();
             int freq = p.freq;

             cycle--;
             freq--; 

            if(freq  > 0){
                cycleEle.add(new Pair(p.ch , freq));
            }

          }

          pq.addAll(cycleEle);
 
          if(pq.isEmpty()){
            ans += n+1 - cycle;
          }else { 
            ans += n+1 ;
          }
        }


        return ans;
    }
}