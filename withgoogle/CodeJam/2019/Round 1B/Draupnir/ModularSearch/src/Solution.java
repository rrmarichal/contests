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

    public long nextLong() {
        return Long.parseLong(next());
    }
}

public class Solution {

    private InputReader in;
    private PrintWriter out;

    protected Solution(InputReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void solve() {
        long T = in.nextLong();
        in.nextLong();
        // d56 = r1*(2^56) + r2*(2^28) + r3*(2^18) + r4*(2^14) + r5*(2^11) + r6*(2^9)
        // d190 = r1*(2^190) + r2*(2^95) + r3*(2^63) + r4*(2^47) + r5*(2^38) + r6*(2^31)
        while (T-- > 0) {
            out.println(56);
            out.flush();
            long d56 = in.nextLong();
            out.println(190);
            out.flush();
            long d190 = in.nextLong();

            long r1 = d56 / (1L << 56);
            long r2 = (d56 - r1 * (1L << 56)) / (1L << 28);

            long r4 = d190 / (1L << 47);
            d190 -= r4 * (1L << 47);
            long r5 = d190 / (1L << 38);
            d190 -= r5 * (1L << 38);
            long r6 = d190 / (1L << 31);
            long r3 = (d56 - r1 * (1L << 56) - r2 * (1L << 28) - r4 * (1L << 14) - r5 * (1L << 11) - r6 * (1L << 9)) / (1L << 18);

            out.println(String.format("%d %d %d %d %d %d", r1, r2, r3, r4, r5, r6));
            out.flush();

            if (in.nextLong() != 1) {
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
