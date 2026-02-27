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