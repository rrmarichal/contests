import static java.lang.System.out;

public class ReverseBits {

	public static void main(String[] args) {
		// out.println(Integer.toString(43261596, 2));
		// out.println(Integer.toString(999562752, 2));
		// out.println(Integer.toString(964176192, 2));
		Solution s = new Solution();
		out.println(s.reverseBits(43261596));
	}

	static class Solution {
	    // you need treat n as an unsigned value
	    public int reverseBits(int n) {
	    	int size = 32;
	    	int result = 0;
	        for (int p = 0; p < size / 2; p++) {
	            boolean bit1 = (n & (1 << p)) != 0;
	            boolean bit2 = (n & (1 << (size - 1 - p))) != 0;
                if (bit2) {
                    result = result | (1 << p);
                }
                if (bit1) {
                    result = result | (1 << (size - 1 - p));
                }
	            out.println(Integer.toString(result, 2));
	        }
	        return result;
	    }
	}
}
