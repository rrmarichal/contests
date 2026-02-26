import java.util.*;

class Solution {
    private void backtrack(int row, int n, int[] queens, boolean[] cols,
            boolean[] diag1, boolean[] diag2, List<List<String>> result) {
        if (row == n) {
            result.add(buildBoard(queens, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            queens[row] = col;
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            backtrack(row + 1, n, queens, cols, diag1, diag2, result);

            // Remove queen (backtrack)
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    private List<String> buildBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.');
            rowChars[queens[row]] = 'Q';
            board.add(new String(rowChars));
        }
        return board;
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n]; // queens[row] = column where queen is placed
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // row - col + (n-1)
        boolean[] diag2 = new boolean[2 * n - 1]; // row + col

        backtrack(0, n, queens, cols, diag1, diag2, result);
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int N = Integer.parseInt(args[0]);
        List<List<String>> result = sol.solveNQueens(N);
        System.out.println("n = " + N + ": " + result.size() + " solutions");
        for (List<String> board : result) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
