class Solution {
  public char findKthBit(int n, int k) {
    if (n == 1)
      return '0';

    int len = (1 << n) - 1;
    int mid = (len / 2) + 1;

    if (k < mid) {
      return findKthBit(n - 1, k);
    } else if (k == mid) {
      return '1';
    } else {
      char c = findKthBit(n - 1, len - k + 1);
      return c == '1' ? '0' : '1';
    }
  }
}