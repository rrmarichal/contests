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
    private int _solve(TestInfo test) {
        return 0;
    }

    public TestInfo[] read() {
        InputReader in = new InputReader(System.in);
        int T = in.nextInt();
        TestInfo[] result = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            result[t] = new TestInfo();
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
