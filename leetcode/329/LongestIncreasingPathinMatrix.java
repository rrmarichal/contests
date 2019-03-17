public class LongestIncreasingPathinMatrix {

	private static final int DIRECTIONS = 4;
	private static final int[][] MOVES = new int[][] { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

	public static void main(String[] args) {
		LongestIncreasingPathinMatrix solution = new LongestIncreasingPathinMatrix();
		System.out.println(solution.longestIncreasingPath(new int[][]
					{{3,4,5},
			  {3,2,6},
			  {2,2,1}}));
	}
	
	public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length > 0 ? matrix[0].length : 0;
        int[][][] state = new int[2][rows * cols][2];
        int cs = 0, steps = 0;
        int[] csc = new int[2];
        for (int j = 0; j < rows; j++)
            for (int k = 0; k < cols; k++) {
                state[cs][csc[cs]][0] = j;
                state[cs][csc[cs]++][1] = k;
            }
        boolean[][] stateMatrix = new boolean[rows][cols];
        while (csc[cs] > 0) {
        	steps++;
            for (int j = 0; j < csc[cs]; j++) {
                int sr = state[cs][j][0];
                int sc = state[cs][j][1];
                for (int k = 0; k < DIRECTIONS; k++) {
                    int srk = sr + MOVES[k][0];
                    int sck = sc + MOVES[k][1];
                    if (valid(sr, sc, srk, sck, rows, cols, matrix) && !stateMatrix[srk][sck]) {
                        state[1 - cs][csc[1 - cs]][0] = srk;
                        state[1 - cs][csc[1 - cs]++][1] = sck;
                        stateMatrix[srk][sck] = true;
                    }
                }
            }
            csc[cs] = 0;
            cs = 1 - cs;
            for (int j = 0; j < rows; j++)
                java.util.Arrays.fill(stateMatrix[j], false);
        }
        return steps;
    }
    
    private boolean valid(int ro, int co, int r, int c, int rows, int cols, int[][] matrix) {
        return r >= 0 && r < rows && c >= 0 && c < cols && matrix[ro][co] < matrix[r][c];
    }

}
