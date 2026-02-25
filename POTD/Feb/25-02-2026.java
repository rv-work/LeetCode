
class Solution {
    public int[] sortByBits(int[] arr) {
      
        Integer[] temp = new Integer[arr.length];

        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        Arrays.sort(temp, (a, b) -> {
            int bc1 = Integer.bitCount(a);
            int bc2 = Integer.bitCount(b);

            if (bc1 == bc2) return Integer.compare(a, b);
            return Integer.compare(bc1, bc2);
        });

        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }

        return arr;
    }
}