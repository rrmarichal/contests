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
    public TestInfo() {

    }
}

/**
 * Based on the Chinese Remainder Theorem, the set of linear modular equations:
 * 
 * x ~= a_i (mod n_i) has a unique solution modulo n_0 * n_1 * ... * n_K.
 */
public class Solution {

    private static final int[] P = new int[] { 4, 3, 5, 7, 11, 13, 17 };

    private InputReader in;
    private PrintWriter out;

    protected Solution(InputReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void solve() {
        int T = in.nextInt();
        in.nextInt();
        in.nextInt();
        int[] R = new int[7];
        while (T-- > 0) {
            for (int j = 0; j < 7; j++) {
                // Set P[j] blades on each windmill. 
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < 18; k++) {
                    sb.append(String.format("%d ", P[j]));
                }
                out.println(sb.toString());
                out.flush();
                R[j] = 0;
                for (int k = 0; k < 18; k++) {
                    R[j] = (R[j] + in.nextInt()) % P[j];
                }
            }
            for (int j = 1; ; j++) {
                boolean ok = true;
                for (int k = 0; k < 7; k++) {
                    if (j % P[k] != R[k]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    out.println(j);
                    out.flush();
                    break;
                }
            }
            if (in.nextInt() != 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        solution.solve();
        out.close();
    }

}
