import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class WordPattern {

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.wordPattern("abba", "dog dog dog dog"));
	}

}

class Solution {

    public boolean wordPattern(String pattern, String str) {
        StringTokenizer st = new StringTokenizer(str);
        if (pattern.length() != st.countTokens())
        	return false;
        HashMap<Character, String> mapping = new HashMap<Character, String>();
        HashSet<String> words = new HashSet<String>();
        for (int j = 0; j < pattern.length(); j++) {
        	char c = pattern.charAt(j);
        	String word = st.nextToken();
        	if (!mapping.containsKey(c)) {
        		if (words.contains(word))
        			return false;
        		words.add(word);
        		mapping.put(c, word);
        	}
        	else
        	if (word.compareTo(mapping.get(c)) != 0)
        		return false;
        }
        return true;
    }

}
