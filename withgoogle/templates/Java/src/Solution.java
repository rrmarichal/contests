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
    public TestInfo() {}
}

public class Solution {
    private PrintWriter out;
    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in, PrintWriter out) {
        this.out = out;
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            tests[t] = new TestInfo();
        }
    }

    private int _solve(TestInfo test) {
        return 0;
    }

    public void solve() {
        for (int t = 0; t < T; t++) {
            out.println(String.format("Case #%d: %d", t + 1, _solve(tests[t])));
        }
        out.close();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new InputReader(System.in), new PrintWriter(System.out));
        solution.solve();
    }
}
