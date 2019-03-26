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

class OrderedParcel implements Comparable<OrderedParcel> {
    int closest;
    Parcel parcel;

    public OrderedParcel(Parcel parcel, int closest) {
        this.parcel = parcel;
        this.closest = closest;
    }

    @Override
    public int compareTo(OrderedParcel o) {
        return closest - o.closest;
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

    private int[][] search(String[] grid) {
        int[][] distances = new int[grid.length][grid[0].length()];
        for (int j = 0; j < grid.length; j++) {
            Arrays.fill(distances[j], Integer.MAX_VALUE);
        }
        LinkedList<Parcel> queue = new LinkedList<Parcel>();
        for (int j = 0; j < grid.length; j++) {
            for (int k = 0; k < grid[0].length(); k++) {
                if (grid[j].charAt(k) == '1') {
                    queue.add(new Parcel(j, k));
                    distances[j][k] = 0;
                }
            }
        }
        Parcel next;
        while ((next = queue.pollFirst()) != null) {
            // Up.
            int nr = next.row - 1, nc = next.column;
            if (nr >= 0 && distances[nr][nc] == Integer.MAX_VALUE) {
                queue.add(new Parcel(nr, nc));
                distances[nr][nc] = distances[next.row][next.column] + 1;
            }
            // Right.
            nr = next.row; nc = next.column + 1;
            if (nc < grid[0].length() && distances[nr][nc] == Integer.MAX_VALUE) {
                queue.add(new Parcel(nr, nc));
                distances[nr][nc] = distances[next.row][next.column] + 1;
            }
            // Down.
            nr = next.row + 1; nc = next.column;
            if (nr < grid.length && distances[nr][nc] == Integer.MAX_VALUE) {
                queue.add(new Parcel(nr, nc));
                distances[nr][nc] = distances[next.row][next.column] + 1;
            }
            // Left.
            nr = next.row; nc = next.column - 1;
            if (nc >= 0 && distances[nr][nc] == Integer.MAX_VALUE) {
                queue.add(new Parcel(nr, nc));
                distances[nr][nc] = distances[next.row][next.column] + 1;
            }
        }
        return distances;
    }

    private int distance(Parcel p, int row, int column) {
        return Math.abs(p.row - row) + Math.abs(p.column - column);
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            int R = tests[t].R, C = tests[t].C;
            String[] grid = tests[t].grid;
            int[][] distances = search(grid);
            OrderedParcel[] parcels = new OrderedParcel[R * C];
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    parcels[j*C + k] = new OrderedParcel(new Parcel(j, k), distances[j][k]);
                }
            }
            Arrays.sort(parcels);
            int best = Integer.MAX_VALUE;
            for (int i = R * C - 1; i >= 0; i--) {
                int row = parcels[i].parcel.row, column = parcels[i].parcel.column;
                if (grid[row].charAt(column) == '0') {
                    // Walk the map array trying to reduce the distances using this new parcel.
                    int index = R * C, maxr = Integer.MIN_VALUE;
                    while (--index >= 0 && maxr < parcels[index].closest) {
                        int d = distance(parcels[index].parcel, row, column);
                        if (d > maxr) {
                            maxr = d;
                        }
                        if (d >= parcels[index].closest) {
                            break;
                        }
                        if (maxr >= best) {
                            break;
                        }
                    }
                    if (maxr > parcels[index].closest) {
                        maxr = parcels[index].closest;
                    }
                    if (maxr < best) {
                        best = maxr;
                    }
                }
            }
            if (best == Integer.MAX_VALUE) {
                best = 0;
            }
            result[t] = String.format("Case #%d: %d", t + 1, best);
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
