class Solution {
  public boolean hasAlternatingBits(int n) {
    int curr = (n & 1);
    int mask = 1;

    int need = mask - curr;
    while (n != 0) {
      n >>= 1;
      curr = (n & 1);

      if (need != curr)
        return false;
      need = mask - curr;
    }

    return true;
  }
}