public class ReconstructOriginalDigits {
	
	public static void main(String args[]) {
		ReconstructOriginalDigits s = new ReconstructOriginalDigits();
		for (String test: args)
			System.out.println(s.originalDigits(test));
	}

    public String originalDigits(String s) {
        int[] count = new int[26];
        for (int j = 0; j < s.length(); j++)
            count[s.charAt(j) - 97]++;
        int[] digits = new int[10];
        // zero
        if (count['z' - 97] > 0) {
            digits[0] = count['z' - 97];
            count['e' - 97]-=count['z' - 97];
            count['r' - 97]-=count['z' - 97];
            count['o' - 97]-=count['z' - 97];
        }
        // two
        if (count['w' - 97] > 0) {
            digits[2] = count['w' - 97];
            count['t' - 97]-=count['w' - 97];
            count['o' - 97]-=count['w' - 97];
        }
        // four
        if (count['u' - 97] > 0) {
            digits[4] = count['u' - 97];
            count['f' - 97] -= count['u' - 97];
            count['o' - 97] -= count['u' - 97];
            count['r' - 97] -= count['u' - 97];
        }
        // six
        if (count['x' - 97] > 0) {
            digits[6] = count['x' - 97];
            count['s' - 97]-=count['x' - 97];
            count['i' - 97]-=count['x' - 97];
        }
        // eight
        if (count['g' - 97] > 0) {
            digits[8] = count['g' - 97];
            count['e' - 97] -= count['g' - 97];
            count['i' - 97] -= count['g' - 97];
            count['h' - 97] -= count['g' - 97];
            count['t' - 97] -= count['g' - 97];
        }
        // three
        if (count['h' - 97] > 0) {
            digits[3] = count['h' - 97];
            count['t' - 97] -= count['h' - 97];
            count['r' - 97] -= count['h' - 97];
            count['e' - 97] -= 2 * count['h' - 97];
        }
        // five
        if (count['f' - 97] > 0) {
            digits[5] = count['f' - 97];
            count['i' - 97] -= count['f' - 97];
            count['v' - 97] -= count['f' - 97];
            count['e' - 97] -= count['f' - 97];
        }
        // seven
        if (count['s' - 97] > 0) {
            digits[7] = count['s' - 97];
            count['e' - 97] -= 2 * count['s' - 97];
            count['v' - 97] -= count['s' - 97];
            count['n' - 97] -= count['s' - 97];
        }
        // nine
        if (count['i' - 97] > 0) {
            digits[9] = count['i' - 97];
            count['n' - 97] -= 2 * count['i' - 97];
            count['e' - 97] -= count['i' - 97];
        }
        // one
        if (count['o' - 97] > 0)
            digits[1] = count['o' - 97];
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++)
            if (digits[j] > 0)
                for (int k = 0; k < digits[j]; k++)
                    sb.append(j);
        return sb.toString();
    }
    
}
