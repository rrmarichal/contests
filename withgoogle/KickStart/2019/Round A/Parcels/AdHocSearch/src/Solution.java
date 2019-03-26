import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class TestInfo {
    int R, C;
    String[] grid;

    public TestInfo(int R, int C, String[] grid) {
        this.R = R;
        this.C = C;
        this.grid = grid;
    }
}

class Parcel {
    int row, column;

    public Parcel(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int j = 0; j < T; j++) {
            int R = in.nextInt(), C = in.nextInt();
            String[] grid = new String[R];
            for (int k = 0; k < R; k++) {
                grid[k] = in.next();
            }
            tests[j] = new TestInfo(R, C, grid);
        }
    }

    private int search(String[] grid, int r, int c) {
        int[][] visited = new int[grid.length][grid[0].length()];
        LinkedList<Parcel> parcels = new LinkedList<Parcel>();
        for (int j = 0; j < grid.length; j++) {
            for (int k = 0; k < grid[0].length(); k++) {
                if (grid[j].charAt(k) == '1') {
                    parcels.add(new Parcel(j, k));
                    visited[j][k] = 1;
                }
            }
        }
        parcels.add(new Parcel(r, c));
        visited[r][c] = 1;
        int md = 1;
        while (true) {
            Parcel next = parcels.pollFirst();
            if (next == null) {
                break;
            }
            // Expand from this cell on.
            // Up.
            int nr = next.row - 1, nc = next.column;
            if (nr >= 0 && visited[nr][nc] == 0) {
                parcels.add(new Parcel(nr, nc));
                visited[nr][nc] = visited[next.row][next.column] + 1;
                if (visited[nr][nc] > md) {
                    md = visited[nr][nc];
                }
            }
            // Right.
            nr = next.row; nc = next.column + 1;
            if (nc < grid[0].length() && visited[nr][nc] == 0) {
                parcels.add(new Parcel(nr, nc));
                visited[nr][nc] = visited[next.row][next.column] + 1;
                if (visited[nr][nc] > md) {
                    md = visited[nr][nc];
                }
            }
            // Down.
            nr = next.row + 1; nc = next.column;
            if (nr < grid.length && visited[nr][nc] == 0) {
                parcels.add(new Parcel(nr, nc));
                visited[nr][nc] = visited[next.row][next.column] + 1;
                if (visited[nr][nc] > md) {
                    md = visited[nr][nc];
                }
            }
            // Left.
            nr = next.row; nc = next.column - 1;
            if (nc >= 0 && visited[nr][nc] == 0) {
                parcels.add(new Parcel(nr, nc));
                visited[nr][nc] = visited[next.row][next.column] + 1;
                if (visited[nr][nc] > md) {
                    md = visited[nr][nc];
                }
            }
        }
        return md - 1;
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int j = 0; j < T; j++) {
            int R = tests[j].R, C = tests[j].C;
            String[] grid = tests[j].grid;
            int best = Integer.MAX_VALUE;
            // Add a new parcel at (row=k, column=l).
            for (int k = 0; k < R; k++) {
                for (int l = 0; l < C; l++) {
                    if (grid[k].charAt(l) == '0') {
                        int md = search(grid, k, l);
                        if (md < best) {
                            best = md;
                        }
                    }
                }
            }
            if (best == Integer.MAX_VALUE) {
                best = 0;
            }
            result[j] = String.format("Case #%d: %d", j + 1, best);
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        for (String c: result) {
            out.println(c);
        }
        out.close();
    }

}
