class Solution {

    class Pair {
        int val;
        int idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] can = new int[n];
        
        // Use a List and sort it (faster than TreeSet overhead)
        // or keep TreeSet if you prefer, but logic remains the same.
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair(ratings[i], i));
        }

        // Sort by value (asc), then by index
        Collections.sort(list, (a, b) -> {
            if (a.val != b.val) return Integer.compare(a.val, b.val);
            return Integer.compare(a.idx, b.idx);
        });

        for (Pair p : list) {
            int idx = p.idx;
            int count = 1; // Every child gets at least 1

            // Check Left Neighbor
            // If my rating is higher, I must have more candies than the left neighbor
            if (idx > 0 && ratings[idx] > ratings[idx - 1]) {
                count = Math.max(count, can[idx - 1] + 1);
            }

            // Check Right Neighbor
            // If my rating is higher, I must have more candies than the right neighbor
            if (idx < n - 1 && ratings[idx] > ratings[idx + 1]) {
                count = Math.max(count, can[idx + 1] + 1);
            }

            can[idx] = count;
        }

        int sum = 0;
        for (int c : can) sum += c;
        return sum;
    }
}





class Solution {

    class Pair {
        int val;
        int idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] can = new int[n];
        
        // Use a List and sort it (faster than TreeSet overhead)
        // or keep TreeSet if you prefer, but logic remains the same.
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair(ratings[i], i));
        }

        // Sort by value (asc), then by index
        Collections.sort(list, (a, b) -> {
            if (a.val != b.val) return Integer.compare(a.val, b.val);
            return Integer.compare(a.idx, b.idx);
        });

        for (Pair p : list) {
            int idx = p.idx;
            int count = 1; // Every child gets at least 1

            // Check Left Neighbor
            // If my rating is higher, I must have more candies than the left neighbor
            if (idx > 0 && ratings[idx] > ratings[idx - 1]) {
                count = Math.max(count, can[idx - 1] + 1);
            }

            // Check Right Neighbor
            // If my rating is higher, I must have more candies than the right neighbor
            if (idx < n - 1 && ratings[idx] > ratings[idx + 1]) {
                count = Math.max(count, can[idx + 1] + 1);
            }

            can[idx] = count;
        }

        return Arrays.stream(can).sum();
    }
}