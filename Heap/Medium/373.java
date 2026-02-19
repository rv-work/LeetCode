
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        
        // Max Heap: Bada sum hamesha top par rahega
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);

        int n1 = nums1.length;
        int n2 = nums2.length;

        // Dono array me jyada se jyada 'k' tak hi check karna kafi hai
        // Kyunki k-th smallest element k index ke aage kabhi nahi ho sakta
        int limit1 = Math.min(n1, k);
        int limit2 = Math.min(n2, k);

        for (int i = 0; i < limit1; i++) {
            for (int j = 0; j < limit2; j++) {
                int sum = nums1[i] + nums2[j];
                
                if (pq.size() < k) {
                    // Agar jagah khali hai, to chupchaap daal do
                    pq.add(new int[]{nums1[i], nums2[j], sum});
                } else if (sum < pq.peek()[2]) {
                    // Agar naya sum Max-Heap ke top (sabse bade sum) se chhota hai
                    pq.poll(); // Sabse bade wale ko laat maro
                    pq.add(new int[]{nums1[i], nums2[j], sum}); // Chhote wale ko andar lo
                } else {
                    // YAHAN BREAK KA SAHI USE HAI:
                    // Kyunki nums2 sorted hai, agar current sum Heap ke top se bada ho gaya, 
                    // to aage (j+1, j+2...) ke sum aur bhi bade hi honge. 
                    // To aage j loop chalane ka fayda nahi.
                    break;
                }
            }
        }

        // PQ se nikal kar answer list me dalo
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            // Index 0 par add kar rahe hain, taaki Ascending order maintain rahe
            ans.add(0, Arrays.asList(curr[0], curr[1])); 
        }

        return ans;
    }
}








import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length;


        Set<String> visited = new HashSet<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[2] - b[2]   // sort by sum
        );

        // Start from (0,0)
        pq.add(new int[]{0, 0, nums1[0] + nums2[0]});
        visited.add("0,0");

        while (!pq.isEmpty() && k-- > 0) {
            int[] curr = pq.poll();
            int i = curr[0], j = curr[1];

            ans.add(Arrays.asList(nums1[i], nums2[j]));

            // Move to (i+1, j)
            // visited.add() true return karta hai agar element pehli baar add ho raha ho
            if (i + 1 < n1 && visited.add((i + 1) + "," + j)) {
                pq.add(new int[]{i + 1, j, nums1[i + 1] + nums2[j]});
            }

            // Move to (i, j+1)
            if (j + 1 < n2 && visited.add(i + "," + (j + 1))) {
                pq.add(new int[]{i, j + 1, nums1[i] + nums2[j + 1]});
            }
        }

        return ans;
    }
}