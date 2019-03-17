public class ShortestPalindromeNaive {

	public static void main(String[] args) {
		Solution s = new Solution();
		String shortest = s.shortestPalindrome("abcd");
		System.out.println(shortest);
	}
	
	static class Solution {
        
	    public String shortestPalindrome(String s) {
	        for (int j = s.length(); j > 1; j--) {
	            if (palindrome(s, j))
	                return buildPalindrome(s, j);
	        }
	        return buildPalindrome(s, 1);
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
