public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int l = 0; int r = rows * cols - 1;
        while (l <= r) {
            int p = (l + r) / 2;
            int cr = p / cols;
            int cc = p % cols;
            if (target < matrix[cr][cc])
                r = p - 1;
            else
            if (target > matrix[cr][cc])
                l = p + 1;
            else
                return true;
        }
        return false;
    }

}
