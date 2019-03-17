import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Solution sol = new Solution();
        int[] g = new int[] { 11, 1, 3, 2, 2 };
        int[] s = new int[] { 3, 1, 1,10 };
		System.out.println(sol.findContentChildren(g, s));
	}

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int cc = g.length - 1;
        for (int j = s.length - 1; j >= 0; j--) {
            while (cc >= 0 && g[cc] > s[j])
            	cc--;
            if (cc >= 0) {
            	result++;
            	cc--;
            }
        }
        return result;
    }

}
