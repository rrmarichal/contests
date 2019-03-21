import java.io.*;
import java.util.*;

public class Solution {

    private int N;
    private int[] A;

    protected Solution(InputReader in) {
        N = in.nextInt();
        A = new int[N];
        for (int j = 0; j < N; j++) {
            A[j] = in.nextInt();
        }
    }

    public long solve() {
        long max = 0;
        int last = Integer.MAX_VALUE;
        for (int j = N - 1; j >= 0; j--) {
            int current = Math.min(A[j], last - 1);
            max += current;
            last = current;
            if (last == 0) {
                break;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
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
