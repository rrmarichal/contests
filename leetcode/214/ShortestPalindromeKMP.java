public class ShortestPalindromeKMP {

	public static void main(String[] args) {
		Solution s = new Solution();
		String shortest = s.shortestPalindrome("abcd");
		System.out.println(shortest);
	}
	
	static class Solution {

		public String shortestPalindrome(String input) {
			if (input.length() <= 1)
				return input;
			StringBuilder patternBuilder = new StringBuilder(input);
			int[] PI = computeKmpTable(patternBuilder.toString() + patternBuilder.reverse());
			int k = PI[PI.length - 1];
			while (k > input.length())
				k = PI[k - 1];
			return buildPalindrome(input, k);
		}

		private int[] computeKmpTable(String pattern) {
			int[] PI = new int[pattern.length()];
			int k = 0;
			for (int j = 1; j < pattern.length(); j++) {
				while (k > 0 && pattern.charAt(k) != pattern.charAt(j))
					k = PI[k - 1];
				if (pattern.charAt(k) == pattern.charAt(j))
					k++;
				PI[j] = k;
			}
			return PI;
		}
		
		private String buildPalindrome(String s, int length) {
	        if (length == s.length())
	            return s;
	        StringBuilder sb = new StringBuilder(s.substring(length));
	        return sb.reverse().append(s).toString();
	    }
    }
    
}
