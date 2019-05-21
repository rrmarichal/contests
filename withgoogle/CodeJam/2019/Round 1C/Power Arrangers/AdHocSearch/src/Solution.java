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

public class Solution {

    private static final int[] FACTORIAL = new int[] { 1, 1, 2, 6, 24, 120 };
    private static final int LENGTH = 5;

    private InputReader in;
    private PrintWriter out;

    protected Solution(InputReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void solve() {
        long T = in.nextLong();
        in.nextLong();
        while (T-- > 0) {
            boolean[] active = new boolean[FACTORIAL[LENGTH] - 1];
            StringBuilder missing = new StringBuilder();
            boolean[] taken = new boolean[5];
            for (int j = 0; j < LENGTH; j++) {
                int[] count = new int[5];
                char[] sh = new char[FACTORIAL[LENGTH] - 1];
                for (int k = 0; k < FACTORIAL[LENGTH] - 1; k++) {
                    if (!active[k]) {
                        out.println(k * LENGTH + j + 1);
                        out.flush();
                        sh[k] = in.next().charAt(0);
                        count[sh[k] - 65]++;
                    }
                }
                // All but one in count is equal to (4 - j)!
                int m = 0;
                for (m = 0; m < LENGTH; m++) {
                    if (count[m] != FACTORIAL[LENGTH - 1 - j] && !taken[m]) {
                        break;
                    }
                }
                missing.append((char) (65 + m));
                taken[m] = true;

                // Deactivate non-matching combinations.
                for (int k = 0; k < FACTORIAL[LENGTH] - 1; k++) {
                    if (!active[k] && sh[k] != (char) (65 + m)) {
                        active[k] = true;
                    }
                }
            }
            out.println(missing.toString());
            out.flush();

            if ("N".equals(in.next())) {
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
