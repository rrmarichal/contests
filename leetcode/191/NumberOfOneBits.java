public class NumberOfOneBits {

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.hammingWeight(-1));
	}

	static class Solution {
	    // you need to treat n as an unsigned value
	    public int hammingWeight(int n) {
	        int result = n < 0 ? 1 : 0;
	        for (int j = 0; j < 31; j++)
	        	result += (n & (1 << j)) != 0 ? 1 : 0;
	        return result;
	    }
    }
    
}
