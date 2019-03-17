import java.util.HashSet;

import static java.lang.System.out;

public class RemoveDuplicateCharacters {

	public static void main(String[] args) {
		Solution s = new Solution();
		out.println(s.removeDuplicateLetters("baa"));
	}
	
	static class Solution {
        
	    public String removeDuplicateLetters(String s) {
	        HashSet<Character> chars = new HashSet<Character>();
	        for (int j = 0; j < s.length(); j++)
	            chars.add(s.charAt(j));
	        
	        HashSet<Character> added = new HashSet<Character>();
	        StringBuilder result = new StringBuilder(chars.size());
	        
	        for (int j = 0, lastAdded = -1; j < chars.size(); j++) {
	            // find the j-th character of solution
	            HashSet<Character> found = new HashSet<Character>();
	            int minIndex = -1;
	            for (int k = s.length() - 1; k > lastAdded; k--) {
	                if (!added.contains(s.charAt(k))) {
	                    if (found.size() == chars.size() - j - 1 && !found.contains(s.charAt(k)) || 
	                    	found.size() == chars.size() - j)
	                    {
	                    	if (minIndex == -1 || s.charAt(k) <= s.charAt(minIndex))
	                    		minIndex = k;
	                    }
	                    found.add(s.charAt(k));
	                }
	            }
	            added.add(s.charAt(minIndex));
	            result.append(s.charAt(minIndex));
	            lastAdded = minIndex;
	        }
	        
	        return result.toString();
	    }
    }
    
}
