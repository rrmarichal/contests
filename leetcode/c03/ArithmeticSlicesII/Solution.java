import java.util.*;

public class Solution {
    
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.numberOfArithmeticSlices(new int[] { 0,2000000000,-294967296 }));
		System.out.println(s.numberOfArithmeticSlices(new int[] { 2, 4, 6, 8, 10 }));
		System.out.println(s.numberOfArithmeticSlices(new int[] { 2, 0, 10, 18, -2, -6, -4 }));
	}

    public int numberOfArithmeticSlices(int[] A) {
        Map<Long, Integer>[] s = new Map[A.length];
        Map<Long, Integer>[] t = new Map[A.length];
        int result = 0;
        for (int j = 0; j < A.length; j++) {
        	s[j] = new HashMap<Long, Integer>();
        	t[j] = new HashMap<Long, Integer>();
        	for (int k = j - 1; k >= 0; k--) {
        		long d = (long) A[j] - (long) A[k];
        		if (!s[j].containsKey(d))
        			s[j].put(d, 0);
        		
        		int skd = s[k].containsKey(d) ? s[k].get(d) : 0;
        		int tkd = t[k].containsKey(d) ? t[k].get(d) : 0;

        		s[j].put(d, s[j].get(d) + skd + tkd);
        		if (!t[j].containsKey(d))
        			t[j].put(d, 1);
        		else
        			t[j].put(d, t[j].get(d) + 1);
        	}
        	for (Long d: s[j].keySet())
        		result += s[j].get(d);
        }
        return result;
    }

}
