import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
    }

    private boolean ensureTokenizer() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = nextLine();
            if (line != null) {
                tokenizer = new StringTokenizer(line);
            }
        }
        return tokenizer != null && tokenizer.hasMoreElements();
    }

    public String nextLine() {
        try {
            tokenizer = null;
            return reader.readLine();
        }
        catch (IOException e) {
            return null;
        }
    }

    public String nextToken() {
        if (ensureTokenizer()) {
            return tokenizer.nextToken();
        }
        return null;
    }

    public Integer nextInt() {
        if (ensureTokenizer()) {
            return Integer.parseInt(tokenizer.nextToken());
        }
        return null;
    }

    public Long nextLong() {
        if (ensureTokenizer()) {
            return Long.parseLong(tokenizer.nextToken());
        }
        return null;
    }
}

class TestInfo {
    public int[] A;
    public ArrayList<Integer>[] G;

    public TestInfo(int[] A, ArrayList<Integer>[] G) {
        this.A = A;
        this.G = G;
    }
}

public class Solution {
    private int _dfs(TestInfo test, int current, int[] D) {
        if (D[current] > 0) {
            return D[current];
        }
        int result = 1;
        for (int next: test.G[current]) {
            if (test.A[current] > test.A[next]) {
                result += _dfs(test, next, D);
            }
        }
        return D[current] = result;
    }

    private int _solve(TestInfo test) {
        int[] D = new int[test.A.length];
        int max = 1;
        for (int i = 0; i < test.A.length; i++) {
            max = Math.max(max, _dfs(test, i, D));
        }
        return max;
    }

    public TestInfo[] read() {
        InputReader in = new InputReader(System.in);
        int T = in.nextInt();
        TestInfo[] result = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = in.nextInt();
            }
            ArrayList<Integer>[] G = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                G[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < N - 1; i++) {
                int x = in.nextInt(), y = in.nextInt();
                G[x - 1].add(y - 1);
                G[y - 1].add(x - 1);
            }
            result[t] = new TestInfo(A, G);
        }
        return result;
    }

    public void solve(TestInfo[] tests) {
        PrintWriter out = new PrintWriter(System.out);
        for (int t = 0; t < tests.length; t++) {
            out.println(String.format("Case #%d: %d", t + 1, _solve(tests[t])));
        }
        out.close();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TestInfo[] tests = solution.read();
        solution.solve(tests);
    }
}
