import static java.lang.System.out;

public class NumMatrix {

    private int[][] matrix;
    private int n, m;
    
    public static void main(String[] args) {
		NumMatrix nm = new NumMatrix(new int[][] {
			{3,0,1,4,2},
			{5,6,3,2,1},
			{1,2,0,1,5},
			{4,1,0,1,7},
			{1,0,3,0,5}
		});
		out.println(nm.sumRegion(2,1,4,3));
		out.println(nm.sumRegion(1,1,2,2));
		out.println(nm.sumRegion(1,2,2,4));
	}
    
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        accumulate();
    }
    
    private void accumulate() {
        n = matrix.length;
        m = n > 0 ? matrix[0].length : 0;
        for (int j = 1; j < m; j++) matrix[0][j] = matrix[0][j] + matrix[0][j - 1];
        for (int j = 1; j < n; j++) matrix[j][0] = matrix[j][0] + matrix[j - 1][0];
        for (int j = 1; j < n; j++)
            for (int k = 1; k < m; k++)
                matrix[j][k] = matrix[j][k] + matrix[j - 1][k] + matrix[j][k - 1] - matrix[j - 1][k - 1];
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (col1 == 0 && row1 == 0) return matrix[row2][col2];
        if (row1 == 0) return matrix[row2][col2] - matrix[row2][col1 - 1];
        if (col1 == 0) return matrix[row2][col2] - matrix[row1 - 1][col2];
        return matrix[row2][col2] - matrix[row2][col1 - 1] - matrix[row1 - 1][col2] + matrix[row1 - 1][col1 - 1];
    }

}
