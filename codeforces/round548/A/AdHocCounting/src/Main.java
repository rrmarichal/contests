import java.io.*;
import java.util.*;

public class Main {

    private String S;

    protected Main(InputReader in) {
        in.next();
        S = in.next();
    }

    public int solve() {
        int count = 0;
        for (int j = 0; j < S.length(); j++) {
            if (Character.getNumericValue(S.charAt(j)) % 2 == 0) {
                count += j + 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Main solution = new Main(in);
        out.println(solution.solve());
        out.close();
    }

}

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
