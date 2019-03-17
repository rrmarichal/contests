import java.util.*;

public class Solution {
    
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.numberOfBoomerangs(new int[][] { {0, 0}, {1, 0}, {2, 0} }));
	}

    public int numberOfBoomerangs(int[][] points) {
        int N = points.length, result = 0;
        int[] d = new int[N - 1];
        for (int j = 0; j < N; j++) {
        	int idx = 0;
        	for (int k = 0; k < N; k++)
        		if (j != k)
        			d[idx++] = distance(points[j], points[k]);
        	Arrays.sort(d);
        	int k = 0;
        	while (k < N - 1) {
        		int l = k + 1;
        		while (l < N - 1 && d[k] == d[l])
        			l++;
        		result += (l - k - 1) * (l - k);
        		k = l;
        	}
        }
        return result;
    }

    private int distance(int[] p0, int[] p1) {
    	return (p0[0] - p1[0]) * (p0[0] - p1[0]) + (p0[1] - p1[1]) * (p0[1] - p1[1]);
    }

}
