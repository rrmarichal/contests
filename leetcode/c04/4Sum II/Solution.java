import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] A = new int[] { 1, 2 };
		int[] B = new int[] { -2, -1 };
		int[] C = new int[] { -1, 2 };
		int[] D = new int[] { 0, 3 };
		System.out.println(sol.fourSumCount(A,B,C,D));
	}

   public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int[] ab = new int[A.length * A.length];
        int c = 0;
        for (int j = 0; j < A.length; j++)
        	for (int k = 0; k < A.length; k++)
        		ab[c++] = A[j] + B[k];
        HashMap<Integer, Integer> cd = new HashMap();
        for (int j = 0; j < A.length; j++)
        	for (int k = 0; k < A.length; k++)
        		if (!cd.containsKey(C[j] + D[k]))
        			cd.put(C[j] + D[k], 1);
        		else
        			cd.put(C[j] + D[k], cd.get(C[j] + D[k]) + 1);
        Integer result = 0;
        for (int j = 0; j < ab.length; j++)
        	if (cd.containsKey(-ab[j]))
        		result += cd.get(-ab[j]);
        return result;
    }

}
