public class Solution {

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.repeatedSubstringPattern(args[0]));
	}

    public boolean repeatedSubstringPattern(String str) {
        for (int l = 1; l <= str.length() / 2; l++)
        	if (str.length() % l == 0 && check(str, l))
        		return true;
        return false;
    }

    private boolean check(String str, int length) {
    	for (int j = 0; j < str.length() / length; j++) {
    		for (int k = 0; k < length; k++)
    			if (str.charAt(k) != str.charAt(j * length + k))
    				return false;
    	}
    	return true;
    }
    
}
