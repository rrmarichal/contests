import java.util.*;

public class PalindromePairs {

	public static void main(String[] args) {
		PalindromePairs solution = new PalindromePairs();
		for (List<Integer> pair : solution.palindromePairs(new String[] { "bat", "tab", "ab" })) {
			System.out.println(String.format("[%d, %d]", pair.get(0), pair.get(1)));
		}
	}

	public List<List<Integer>> palindromePairs(String[] words) {
    	TrieNode root = buildTrie(words);
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for (int j = 0; j < words.length; j++) {
    		TrieNode wordNode = root.find(words[j]);
    		if (wordNode != null) {
    			List<Integer> wi = wordNode.palindromeSuffixIndices("");
    			if (wi.size() > 0) {
    				for (Integer index: wi)
    					if (j != index)
    						result.add(Arrays.asList(j, index));
    			}
    		}
    	}
    	return result;
    }

    private TrieNode buildTrie(String[] words) {
    	TrieNode result = new TrieNode();
    	for (int j = 0; j < words.length; j++)
    		result.add(new StringBuilder(words[j]).reverse().toString(), j);
    	return result;
    }

    static class TrieNode {

    	HashMap<Character, TrieNode> children;
    	static HashMap<String, Boolean> pals = new HashMap<String, Boolean>(); 
    	List<Integer> indices;

    	public TrieNode() {
    		children = new HashMap<Character, TrieNode>();
    		indices = new ArrayList<Integer>();
    	}

    	public List<Integer> palindromeSuffixIndices(String current) {
    		ArrayList<Integer> result = new ArrayList<Integer>();
    		if (indices.size() > 0) {
				if (isPalindrome(current))
				    result.addAll(indices);
    		}
    		for (Character key: children.keySet())
				result.addAll(children.get(key).palindromeSuffixIndices(current + key));
    		return result;
		}

		private boolean isPalindrome(String word) {
			if (pals.containsKey(word))
				return pals.get(word);
	        for (int i = 0; i < word.length() / 2; i++)
	            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
	                pals.put(word, false);
	            	return false;
	            }
	        pals.put(word, true);
	        return true;
		}

		public void add(String word, int index) {
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
    		current.indices.add(index);
    	}

    	public TrieNode find(String word) {
    		TrieNode current = this;
    		for (int j = 0; j < word.length(); j++) {
    			if (current.children.containsKey(word.charAt(j)))
    				current = current.children.get(word.charAt(j));
    			else
    				return null;
    		}
    		return current;
    	}

    	public List<Integer> words() { return indices; }

    }
    
}
