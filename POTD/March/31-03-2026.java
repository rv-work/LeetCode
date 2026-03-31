class Solution {
    boolean isValid(char[] ans, int idx, String str1, String str2, int m) {
        int start = Math.max(0, idx - m + 1);
        int end = Math.min(idx, str1.length() - 1);

        for (int i = start; i <= end; i++) {
            if (str1.charAt(i) == 'F') {
                boolean isPerfectMatch = true;

                // Check karo kya ye poori window exactly str2 ban gayi hai? // sirf idx check krna kafi nhi hai ....... 
                for (int k = 0; k < m; k++) {
                    if (ans[i + k] == '#' || ans[i + k] != str2.charAt(k)) {
                        isPerfectMatch = false;
                        break;
                    }
                }

                if (isPerfectMatch) {
                    return false;
                }
            }
        }
        return true;
    }

    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char ans[] = new char[n + m - 1];
        Arrays.fill(ans, '#');

        // for true cases ye nhi change ki jaaa skti hain....
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = i; j < i + m; j++) {
                    if (ans[j] == '#') {
                        ans[j] = str2.charAt(j - i);
                    } else {
                        if (str2.charAt(j - i) != ans[j])
                            return "";
                        else
                            continue;
                    }
                }
            }
        }

        // for false....
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean is = false;
                for (int j = i; j < i + m; j++) {
                    if (str2.charAt(j - i) != ans[j]) {
                        is = true;
                        break;
                    }
                }
                if (!is)
                    return "";
            }
        }

        // lets fill #..
        for (int i = 0; i < n + m - 1; i++) {
            if (ans[i] == '#') {
                for (char c = 'a'; c <= 'z'; c++) {
                    ans[i] = c;
                    if (isValid(ans, i, str1, str2, m))
                        break;
                }
            }
        }

        return new String(ans);
    }
}