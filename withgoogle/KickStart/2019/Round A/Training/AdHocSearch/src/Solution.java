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
    int N, P;
    int[] S;

    public TestInfo(int N, int P, int[] S) {
        this.N = N;
        this.P = P;
        this.S = S;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int j = 0; j < T; j++) {
            int N = in.nextInt(), P = in.nextInt();
            int[] S = new int[N];
            for (int k = 0; k < N; k++) {
                S[k] = in.nextInt();
            }
            tests[j] = new TestInfo(N, P, S);
        }
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int j = 0; j < T; j++) {
            int N = tests[j].N, P = tests[j].P;
            int[] S = tests[j].S;
            Arrays.sort(S);
            int current = 0;
            for (int k = 0; k < P - 1; k++) {
                current += S[P - 1] - S[k];
            }
            int best = current;
            for (int k = P; k < N; k++) {
                current += S[k - P] - S[k - 1] + (P - 1) * (S[k] - S[k - 1]);
                if (current < best) {
                    best = current;
                }
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
