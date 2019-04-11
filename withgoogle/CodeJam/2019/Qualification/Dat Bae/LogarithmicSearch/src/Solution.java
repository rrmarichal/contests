import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1 << 16;

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

class Range {
    int left, right, broken;

    public Range(int left, int right, int broken) {
        this.left = left;
        this.right = right;
        this.broken = broken;
    }

    public int length() {
        return right - left + 1;
    }
}

public class Solution {

    private InputReader in;
    private PrintWriter out;

    protected Solution(InputReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    private int _chunkSize(Range range) {
        if (range.length() > 16) {
            return range.broken;
        }
        return (range.length() + 1) >> 1;
    }

    private String _buildTest(ArrayList<Range> ranges) {
        StringBuilder ts = new StringBuilder();
        for (Range range : ranges) {
            int cs = _chunkSize(range);
            for (int j = 0; j < range.length(); j++) {
                ts.append(j / cs % 2 == 0 ? "0" : "1");
            }
        }
        return ts.toString();
    }

    private ArrayList<Range> _expand(ArrayList<Range> ranges, String answer) {
        ArrayList<Range> expansion = new ArrayList<Range>();
        int index = 0;
        for (Range range : ranges) {
            int chunkSize = _chunkSize(range);

            int rangeChunks = (range.length() - 1) / chunkSize + 1;
            int chunkIndex = -1;

            int start = index;
            while (++chunkIndex < rangeChunks) {
                int chunkCount = chunkIndex < rangeChunks - 1
                    ? chunkSize
                    : range.length() - (chunkIndex * chunkSize);

                int k = index;
                while (index - k < chunkCount &&
                    index - start < range.length() - range.broken &&
                    answer.charAt(index) == (char) (chunkIndex % 2 + 48)) {
                    index++;
                }

                expansion.add(new Range(range.left + chunkIndex * chunkSize,
                    range.left + chunkIndex * chunkSize + chunkCount - 1,
                    chunkCount - (index - k)));
            }
        }
        return expansion;
    }

    public void solve() {
        int T = in.nextInt();
        while (T-- > 0) {
            int N = in.nextInt(), B = in.nextInt(), F = in.nextInt();
            ArrayList<Range> current = new ArrayList<Range>();
            current.add(new Range(0, N - 1, B));
            while (F-- > 0) {
                String ts = _buildTest(current);
                out.println(ts);
                out.flush();
                String answer = in.next();
                current = _expand(current, answer);
            }
            for (Range r : current) {
                if (r.broken > 0) {
                    for (int j = r.left; j <= r.right; j++) {
                        out.print(String.format("%d ", j));
                    }
                }
            }
            out.println();
            out.flush();
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
