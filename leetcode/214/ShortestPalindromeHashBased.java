import java.util.HashMap;

import static java.lang.System.out;

@SuppressWarnings("unused")
public class ShortestPalindromeHashBased {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		String shortest = s.shortestPalindrome("abcd");
		System.out.println(shortest);
	}
	
	/**
	  	INPUT_SIZE = 10^7
	  	ALPHABET_SIZE = 2
		HASHB Time Overall: 4840 ms. Missed 2 times.
		KNUMP Time Overall: 3283 ms.
		
		INPUT_SIZE = 5*10^6
		RANDOM STRING OVER ALPHABET_SIZE = 256
		NAIVE Time Overall: 520 ms.
		HASHB Time Overall: 3315 ms. Missed 0 times.
		KNUMP Time Overall: 1097 ms.
		
		INPUT_SIZE = 5*10^6
		RANDOM STRING OVER ALPHABET_SIZE = 2
		NAIVE Time Overall: 927 ms.
		HASHB Time Overall: 2585 ms. Missed 0 times.
		KNUMP Time Overall: 1620 ms.
	 */
	static class Solution {
        
		private int[] forwardHash, backwardHash;
		private final long MODULE = 1000000007;
		private long radix;
		int missed;
		private HashMap<Character, Long> map;

		public String shortestPalindrome(String s) {
			computeHashes(s);
			missed = 0;
			for (int j = s.length(); j > 0; j--) {
				if (forwardHash[j - 1] == backwardHash[j - 1]) {
					if (palindrome(s, j))
						return buildPalindrome(s, j);
					missed++;
				}
			}
			return null;
		}

		private void computeHashes(String s) {
			// forwardHash[i] is the hash of the substring s[0..i].
			// backwardHash[i] is the hash of the substring s[0..i] reversed.
			mapCharactersInString(s);
			
			// compute forwardHash table
			forwardHash = new int[s.length()];
			long rp = 1, prev = 0;
			for (int j = 0; j < s.length(); j++) {
				forwardHash[j] = (int) ((rp * map.get(s.charAt(j)) + prev) % MODULE);
				rp = rp * radix % MODULE;
				prev = forwardHash[j];
			}

			backwardHash = new int[s.length()];
			prev = 0;
			// compute backwardHash table
			for (int j = 0; j < s.length(); j++) {
				backwardHash[j] = (int) ((prev * radix + map.get(s.charAt(j))) % MODULE);
				prev = backwardHash[j];
			}
		}

		private void mapCharactersInString(String s) {
			map = new HashMap<Character, Long>();
			radix = 0;
			for (int j = 0; j < s.length(); j++)
				if (!map.containsKey(s.charAt(j)))
					map.put(s.charAt(j), radix++);
		}

		private boolean palindrome(String s, int length) {
	        int j = 0;
	        while (j < length / 2) {
	            if (s.charAt(j) != s.charAt(length - j - 1))
	                return false;
	            j++;
	        }
	        return true;
	    }
	    
	    private String buildPalindrome(String s, int length) {
	        if (length == s.length())
	            return s;
	        StringBuilder sb = new StringBuilder(s.substring(length));
	        return sb.reverse().append(s).toString();
	    }
    }
    
}
