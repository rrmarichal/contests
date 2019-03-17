import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PalindromePairs2 {

	public static void main(String[] args) {
		PalindromePairs2 solution = new PalindromePairs2();
		for (List<Integer> pair : solution.palindromePairs(new String[] { "abcd","dcba","lls","s","sssll" })) {
			System.out.println(String.format("[%d, %d]", pair.get(0), pair.get(1)));
		}
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		TrieNode reverse = buildTrie(words, true);
		TrieNode forward = buildTrie(words, false);
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for (int j = 0; j < words.length; j++) {
    		// try concatenating words[j] + words[x] $ words[j].length >= words[x].length
    		TrieNode current = reverse;
    		for (int k = 0; k <= words[j].length(); k++) {
    			if (current.wordIndex() >= 0 && isPalindrome(words[j], k, words[j].length() - 1) && j != current.wordIndex())
				    result.add(Arrays.asList(j, current.wordIndex()));
    			if (k == words[j].length() || !current.children.containsKey(words[j].charAt(k)))
    				break;
    			current = current.children.get(words[j].charAt(k));
    		}
    		// try concatenating words[x] + words[j] $ words[j].length >= words[x].length
    		current = forward;
    		for (int k = words[j].length() - 1; k >= 0; k--) {
    			if (current.wordIndex() >= 0 && isPalindrome(words[j], 0, k) && j != current.wordIndex())
    				result.add(Arrays.asList(current.wordIndex(), j));
    			if (!current.children.containsKey(words[j].charAt(k)))
    				break;
    			current = current.children.get(words[j].charAt(k));
    		}
    	}
    	return result;
	}
	
	private boolean isPalindrome(String word, int start, int end) {
		while (start < end && word.charAt(start) == word.charAt(end)) {
			start++;
			end--;
		}
		return start >= end;
	}
	
	private TrieNode buildTrie(String[] words, boolean reverse) {
    	TrieNode result = new TrieNode();
    	for (int j = 0; j < words.length; j++)
    		if (reverse)
    			result.add(new StringBuilder(words[j]).reverse().toString(), j);
    		else
    			result.add(words[j], j);
    	return result;
    }
	
	static class TrieNode {

    	HashMap<Character, TrieNode> children;
    	static HashMap<String, Boolean> pals = new HashMap<String, Boolean>(); 
    	int index;

    	public TrieNode() {
    		children = new HashMap<Character, TrieNode>();
    		index = -1;
    	}

		public void add(String word, int wordIndex) {
    		TrieNode current = this;
    		for (int j = 0; j < word.length(); j++) {
    			TrieNode next;
    			if (!current.children.containsKey(word.charAt(j))) {
    				next = new TrieNode();
    				current.children.put(word.charAt(j), next);
    			}
    			else {
    				next = current.children.get(word.charAt(j));
    			}
    			current = next;
    		}
    		current.index = wordIndex;
    	}

    	public int wordIndex() { return index; }

    }

}
