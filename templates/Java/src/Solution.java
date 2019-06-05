import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
    }

    private String readLine() {
        try {
            return reader.readLine();
        }
        catch (IOException e) {
            return null;
        }
    }

    private boolean ensureTokenizer() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = readLine();
            if (line != null) {
                tokenizer = new StringTokenizer(line);
            }
        }
        return tokenizer != null && tokenizer.hasMoreElements();
    }

    public String nextLine() {
        if (ensureTokenizer()) {
            StringBuilder result = new StringBuilder();
            while (tokenizer.hasMoreTokens()) {
                result.append(tokenizer.nextToken());
                result.append(' ');
            }
            return result.toString();
        }
        return null;
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

public class Solution {

    protected Solution(InputReader in) {
        // TODO: Input
    }

    public int solve() {
        return 0;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        out.println(solution.solve());
        out.close();
    }

}
