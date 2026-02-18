
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> {
                int diffA = Math.abs(a - x);
                int diffB = Math.abs(b - x);

                if (diffA == diffB) {
                    return b - a;     // larger element first
                }

                return diffB - diffA; // larger distance first
            }
        );

        for (int num : arr) {
            pq.offer(num);

            if (pq.size() > k) {
                pq.poll();   // remove worst element
            }
        }

        List<Integer> result = new ArrayList<>(pq);

        Collections.sort(result);  // must sort at end

        return result;
    }
}
