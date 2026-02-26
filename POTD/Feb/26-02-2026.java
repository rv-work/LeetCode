class Solution {
    public int numSteps(String s) {
        StringBuilder sb = new StringBuilder(s);
        int steps = 0;

        while (sb.length() > 1) {
            int n = sb.length();

            if (sb.charAt(n - 1) == '0') {
                sb.deleteCharAt(n - 1);
            } else {
                int i = n - 1;
                while (i >= 0 && sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                    i--;
                }

                if (i >= 0) {
                    sb.setCharAt(i, '1');
                } else {
                    sb.insert(0, '1'); 
                }
            }

            steps++;
        }

        return steps;
    }
}









class Solution {
    public int numSteps(String s) {
        // Step 1: Pehle se saare zeros ke index store karke rakh lo
        Stack<Integer> zeroIndices = new Stack<>();
        char[] arr = s.toCharArray();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                zeroIndices.push(i);
            }
        }
        
        int steps = 0;
        int i = arr.length - 1;
        
        while (i > 0) {
            if (arr[i] == '0') {
                // Agar simply 0 hai, to divide by 2 hoga (pointer 1 step peeche)
                steps++;
                // Stack me se is current 0 ko hata do kyunki ye process ho gaya
                if (!zeroIndices.isEmpty() && zeroIndices.peek() == i) {
                    zeroIndices.pop();
                }
                i--;
            } else {
                // Agar 1 hai, to humein +1 karna padega
                if (!zeroIndices.isEmpty()) {
                    // Sabse nazdeek wala '0' nikalo (jo left side me hai)
                    int idx = zeroIndices.pop();
                    
                    // 1 step lag gaya Addition karne me
                    // Aur (i - idx) steps lag gaye un naye zeros ko Divide karke khatam karne me!
                    steps += 1 + (i - idx);
                    
                    // Carry ki wajah se wo nazdeek wala '0' ab '1' ban gaya hai
                    arr[idx] = '1';
                    
                    // Direct chhalaang (Jump)! Naye zeros pe time waste nahi karenge
                    i = idx; 
                } else {
                    // Agar left me koi '0' bacha hi nahi hai (e.g., "111")
                    // To +1 karne par wo "1000" ban jayega
                    // 1 step for Addition, and (i + 1) steps array ke saare elements ko divide karne ke liye
                    steps += 1 + (i + 1);
                    break;
                }
            }
        }
        
        return steps;
    }
}