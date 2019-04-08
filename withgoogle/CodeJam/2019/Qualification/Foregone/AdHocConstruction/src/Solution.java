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

    private int T;
    private String[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new String[T];
        for (int t = 0; t < T; t++) {
            tests[t] = in.next();
        }
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            StringBuilder A = new StringBuilder(), B = new StringBuilder();
            for (int j = 0; j < tests[t].length(); j++) {
                if (tests[t].charAt(j) == '4') {
                    A.append('1');
                    B.append('3');
                }
                else {
                    A.append(tests[t].charAt(j));
                    if (B.length() > 0) {
                        B.append('0');
                    }
                }
            }
            result[t] = String.format("Case #%d: %s %s", t + 1, A.toString(), B.toString());
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
