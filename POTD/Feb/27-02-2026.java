"Is problem me array ko modify nahi karna hai, ye pure Math aur Parity (Sam/Visham) ka game hai! Logic ye hai ki hum sirf total zeros (z) count karenge aur operations (m) ka ek loop 0 se lekar n + 2 tak chalayenge. Har m operations ke liye humare total flips m * k honge, aur is m ko valid hone ke liye 3 strict conditions pass karni hongi: (1) Parity Match: Total flips aur z ka Even/Odd nature same hona chahiye ((m * k) % 2 == z % 2), kyunki even/odd flips se hi decide hota hai ki final character kya banega. (2) Minimum Flips: Total flips kam se kam z ke barabar hone chahiye (m * k >= z). (3) Maximum Capacity: Ek bit ko m operations me hum max m baar hi flip kar sakte hain, jiska math formula m ke even ya odd hone par depend karta hai (yahan se maxFlipsAllowed nikal kar check karenge ki m * k <= maxFlipsAllowed hai ya nahi). Hum loop ko n + 2 tak isliye chalate hain kyunki n worst-case flips hain aur + 2 parity set karne ka 'safety buffer' hai. Jo bhi m sabse pehle in teeno conditions ko pass kar lega, wahi hamara answer hoga, warna loop ke baad -1 return kar denge. Ekdum O(N) time aur O(1) space ka solid solution!"class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int z = 0; // Total zeros count
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                z++;
            }
        }
        
        // Hum m (number of operations) ko 0 se n+2 tak check karenge
        for (long m = 0; m <= n + 2; m++) {
            long totalFlips = m * k;
            
            // Condition 1: Parity check (Sabse important!)
            if (totalFlips % 2 != z % 2) {
                continue;
            }
            
            // Condition 2: Minimum flips
            if (totalFlips < z) {
                continue;
            }
            
            // Condition 3: Maximum flips capacity
            long maxFlipsAllowed;
            
            if (m % 2 == 0) {
                // Agar m even hai: '0's ko m-1 flips, '1's ko m flips milenge
                maxFlipsAllowed = (long) z * (m - 1) + (long) (n - z) * m;
            } else {
                // Agar m odd hai: '0's ko m flips, '1's ko m-1 flips milenge
                maxFlipsAllowed = (long) z * m + (long) (n - z) * (m - 1);
            }
            
            // Agar teeno conditions pass ho gayi, to yehi hamara minimum 'm' hai!
            if (totalFlips <= maxFlipsAllowed) {
                return (int) m;
            }
        }
        
        // Agar loop khatam ho gaya aur kuch nahi mila
        return -1;
    }
}

// Is problem me array ko modify nahi karna hai, ye pure Math aur Parity (Sam/Visham) ka game hai! Logic ye hai ki hum sirf total zeros (z) count karenge aur operations (m) ka ek loop 0 se lekar n + 2 tak chalayenge. Har m operations ke liye humare total flips m * k honge, aur is m ko valid hone ke liye 3 strict conditions pass karni hongi: (1) Parity Match: Total flips aur z ka Even/Odd nature same hona chahiye ((m * k) % 2 == z % 2), kyunki even/odd flips se hi decide hota hai ki final character kya banega. (2) Minimum Flips: Total flips kam se kam z ke barabar hone chahiye (m * k >= z). (3) Maximum Capacity: Ek bit ko m operations me hum max m baar hi flip kar sakte hain, jiska math formula m ke even ya odd hone par depend karta hai (yahan se maxFlipsAllowed nikal kar check karenge ki m * k <= maxFlipsAllowed hai ya nahi). Hum loop ko n + 2 tak isliye chalate hain kyunki n worst-case flips hain aur + 2 parity set karne ka 'safety buffer' hai. Jo bhi m sabse pehle in teeno conditions ko pass kar lega, wahi hamara answer hoga, warna loop ke baad -1 return kar denge. Ekdum O(N) time aur O(1) space ka solid solution!"











class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int z = 0;
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == '0') z++;
        }

        int operations[] = new int[n+1];
        Arrays.fill(operations, -1);
        operations[z] = 0;

        // Tumhara Hack: Ek Odd aur Ek Even ka set (Jo sirf UNVISITED states ko store karega)
        TreeSet<Integer> evenUnvisited = new TreeSet<>();
        TreeSet<Integer> oddUnvisited = new TreeSet<>();

        for (int i = 0; i <= n; i++) {
            if (i == z) continue; // Z pehle hi visit ho chuka hai
            
            if (i % 2 == 0) {
                evenUnvisited.add(i);
            } else {
                oddUnvisited.add(i);
            }
        }


        if(z == 0) return 0;
 

        Queue<Integer> q = new LinkedList<>();
        q.add(z);

        while(!q.isEmpty()){
            int rem0 = q.poll();
            int minF = Math.max(0 , k - (n - rem0)); // iska matlab hai ki maan lo hmne sare zeros ko 1 bne diya ..done very good ,,, but agar ye krte huye hmne kuch 1s ko zero bna diyo (majboori me) to ..unhe firse 1 bnane ke liye hme kam se kam itne flip fir se krne pdenge na to vohi likha hai ki " k flip kiye jisme se 1st kitne the .... to total - jitne zeros the ..... k - 1s menas k - ( total - zeros )"

            int maxF = Math.min(k , rem0); // ye bta rha ki max kitne flip kr skte hm 

            // for(int f = minF ; f <= maxF; f++){
            //     int newRem0 = rem0 - f + (k - f);
            //     if( operations[newRem0] == -1){
            //         operations[newRem0] = operations[rem0] + 1;
            //         if(newRem0 == 0) return  operations[newRem0];
            //         q.add(newRem0);
            //     }
            // }

            // ab isme problem kya hai ki newRem0 ( matlab zeros ) ki value repeat ho rhi hain ...to vo hme ignore krna hoga ... dhyan se... agar lg rha ho ki ye line .......if( operations[newRem0] == -1){...... aisa hone se rokegi to tham jao socho fir se .... ye iss point pr check kr rhi ek level pr matlab agar isse upar vale level me vo aa chuka hoga tab to bach jayega .. but same level me left ya right me hoga to kuch nhi ho skta 

            // so ise correct krne ke liye 

            // Dhyan do: f jab badhta hai to naya zero count ghtata hai
            // Isliye minF se MAX zeros milenge, aur maxF se MIN zeros milenge
            int maxNextZeros = rem0 + k - 2 * minF; 
            int minNextZeros = rem0 + k - 2 * maxF; 

            // Range ki parity check karo (minNextZeros aur maxNextZeros dono ki parity same hogi)
            TreeSet<Integer> targetSet = (minNextZeros % 2 == 0) ? evenUnvisited : oddUnvisited;

            // Target set me 'minNextZeros' ke barabar ya usse bada pehla UNVISITED number dhoondo
            while (true) {
                Integer nextUnvisited = targetSet.ceiling(minNextZeros);
                
                // Agar koi unvisited bacha hi nahi, ya range se bahar nikal gaya, to break kar do
                if (nextUnvisited == null || nextUnvisited > maxNextZeros) {
                    break;
                }

                // Bingo! Ek unvisited state mil gayi jo hamari range me hai
                operations[nextUnvisited] = operations[rem0] + 1;
                
                if (nextUnvisited == 0) {
                    return operations[nextUnvisited];
                }
                
                q.add(nextUnvisited);
                
                // Ye state visit ho gayi, ise set se nikaal fenko taaki koi aur ise dobara check na kare!
                targetSet.remove(nextUnvisited);
            }

            // apply here


        }

        return -1;
    }
}



// Is problem ko humne Graph (Shortest Path) ki tarah solve kiya hai using BFS (Breadth-First Search). Yahan hamara 'State' puri string nahi, balki 'string me current zeros ki ginti (rem0)' hai. Hamara target is rem0 ko 0 par lana hai.

// Core Logic (Transition Formula):
// Jab hum exactly k characters flip karte hain, toh man lo humne usme se f zeros choose kiye. Agar f zeros flip honge, to bache hue (k - f) ones flip honge.
// Toh naya zero count kya hoga?
//  newRem0 = rem0 - f + (k - f)
//  newRem0 = rem0 + k - 2*f

// The Boundaries (f ki range kya hogi?):
// Hum apni marzi se koi bhi f nahi chun sakte, iski do strict limits hain:

// maxF (Zyada se zyada kitne 0s flip kar sakte hain?): Hum k se zyada flips nahi kar sakte, aur jitne zeros available (rem0) hain usse zyada 0s flip nahi kar sakte.
//  maxF = Math.min(k, rem0)

// minF (Kam se kam kitne 0s flip karne hi padenge?): Agar k ki value hamare paas maujood total '1's (n - rem0) se badi ho gayi, toh majboori me bache hue extra flips humein '0's par hi karne padenge.
//  minF = Math.max(0, k - (n - rem0)) yaani Math.max(0, k - n + rem0)

// BFS Execution:
// Humne ek operations[] array banaya jo Visited Array aur Distance Array dono ka kaam kar raha hai (size n + 1). Queue me initial zeros z daalkar BFS start kiya. Har state rem0 ke liye, hum minF se maxF tak loop chalate hain, naya state newRem0 nikalte hain. Agar wo state pehle visit nahi hui hai (operations == -1), to hum use queue me daal dete hain. Kyunki BFS hamesha shortest path explore karta hai, jaise hi newRem0 == 0 milega, wahi hamara Minimum Operations answer hoga! Agar Queue khali ho gayi aur 0 nahi mila, to seedha -1 return kar do.





