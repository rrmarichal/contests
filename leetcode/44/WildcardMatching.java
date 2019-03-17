public class WildcardMatching {
	
	public static void main(String[] args) {
		WildcardMatching s = new WildcardMatching();
		/*System.out.println(s.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
"**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));*/
		System.out.println(s.isMatch("b", "?*?"));
	}

    public boolean isMatch(String s, String p) {
 		p = normalize(p);
 		//System.out.println(p);
 		byte[][] m = new byte[s.length() + 1][p.length() + 1];
 		return isMatch(m, s, p, 0, 0) == 1;
    }

    private byte isMatch(byte[][] m, String s, String p, int si, int pi) {
    	if (m[si][pi] != 0)
    		return m[si][pi];
    	byte result = 2;
    	if (si == s.length() && pi == p.length())
    		result = 1;
    	else
    	if (pi < p.length()) {
	    	if (p.charAt(pi) == '?')
	    		result = si < s.length() ? isMatch(m, s, p, si + 1, pi + 1) : 2;
	    	else
	    	if (p.charAt(pi) != '*')
	    		result = si < s.length() && p.charAt(pi) == s.charAt(si)
	    			? isMatch(m, s, p, si + 1, pi + 1) : 2;
	    	else {
		    	for (int l = 0; l <= s.length() - si; l++)
		    		if (isMatch(m, s, p, si + l, pi + 1) == 1) {
		    			result = 1;
		    			break;
		    		}
	    	}
	    }
    	return m[si][pi] = result;
    }

    private String normalize(String p) {
		int j = 0;
		boolean[] invalid = new boolean[p.length()];
		while (j < p.length()) {
			if (!invalid[j] && p.charAt(j) == '*') {
				int k = j + 1;
				while (k < p.length() && p.charAt(k) == '*') {
					invalid[k] = true;
					k++;
				}
				j = k;
			}
			else ++j;
		}
		StringBuilder sb = new StringBuilder(p.length());
		for (j = 0; j < p.length(); j++)
			if (!invalid[j])
				sb.append(p.charAt(j));
    	return sb.toString();
    }
}