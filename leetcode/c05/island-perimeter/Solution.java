public class Solution {

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.islandPerimeter(new int[][] { {1,1}, {1,0} }));
	}
	
	public int islandPerimeter(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0)
			return 0;
		int result = 0;
        for (int j = 0; j <= grid.length; j++) {
        	for (int k = 0; k <= grid[0].length; k++) {
        		int left = (k == 0 || j == grid.length) ? 0 : grid[j][k-1];
        		int up = (j == 0 || k == grid[0].length) ? 0 : grid[j-1][k];
        		int current = (j == grid.length || k == grid[0].length) ? 0 : grid[j][k];
        		if (left != current)
        			result++;
        		if (up != current)
        			result++;
        	}
        }
        return result;
    }

}
