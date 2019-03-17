public class MaxSumofRectangleNoLargerThanK {

	public static void main(String[] args) {
		Solution s = new Solution();
		int max = s.maxSumSubmatrix(new int[][] { {1, 0, 1}, {0, -2, 3} }, 11);
		System.out.println(max);
	}

	static class Solution {
	    public int maxSumSubmatrix(int[][] matrix, int k) {
	        if (matrix.length == 0 || matrix[0].length == 0)
	        	return 0;
	        // accumulate matrix
	        for (int j = 0; j < matrix.length; j++)
	        	for (int l = 0; l < matrix[0].length; l++)
	        		matrix[j][l] = value(matrix, j - 1, l) + value(matrix, j, l - 1) - value(matrix, j - 1, l - 1) + matrix[j][l];
	        // find best rectangle
	        int best = Integer.MAX_VALUE;
	        for (int j = 0; j < matrix.length; j++)
	        	for (int l = 0; l < matrix[0].length; l++)
	        		for (int down = 1; down <= matrix.length - j; down++)
	        			for (int right = 1; right <= matrix[0].length - l; right++) {
	        				int rect = sum(matrix, j, l, down, right);
	        				if (rect <= k && k - rect < best)
	        					best = k - rect;
	        			}
	        return k - best;
	    }

	    private int sum(int[][] matrix, int r, int c, int down, int right) {
	    	return value(matrix, r + down - 1, c + right - 1) - value(matrix, r - 1, c + right - 1)
	    			- value(matrix, r + down - 1, c - 1) + value(matrix, r - 1, c - 1);
	    }

	    private int value(int[][] matrix, int r, int c) {
	    	if (r < 0 || c < 0)
	    		return 0;
	    	return matrix[r][c];
	    }
	}

}
