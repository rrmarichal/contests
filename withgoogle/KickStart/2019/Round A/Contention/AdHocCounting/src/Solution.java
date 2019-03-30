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

class SegmentInfo {
    int L, R;

    public SegmentInfo(int L, int R) {
        this.L = L;
        this.R = R;
    }
}

class EndpointInfo implements Comparable<EndpointInfo> {
    int offset, index;
    boolean open;

    public EndpointInfo(int offset, int index, boolean open) {
        this.offset = offset;
        this.index = index;
        this.open = open;
    }

    @Override
    public int compareTo(EndpointInfo o) {
        return Integer.compare(offset, o.offset);
    }
}

class TestInfo {
    int N, Q;
    SegmentInfo[] S;

    public TestInfo(int N, int Q, SegmentInfo[] S) {
        this.N = N;
        this.Q = Q;
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
            int N = in.nextInt(), Q = in.nextInt();
            SegmentInfo[] S = new SegmentInfo[Q];
            for (int k = 0; k < Q; k++) {
                S[k] = new SegmentInfo(in.nextInt() - 1, in.nextInt());
            }
            tests[j] = new TestInfo(N, Q, S);
        }
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            int Q = tests[t].Q;
            SegmentInfo[] S = tests[t].S;
            EndpointInfo[] endpoints = new EndpointInfo[Q << 1];
            for (int j = 0; j < Q; j++) {
                endpoints[j << 1] = new EndpointInfo(S[j].L, j, true);
                endpoints[(j << 1) + 1] = new EndpointInfo(S[j].R, j, false);
            }
            Arrays.sort(endpoints);
            int min = Integer.MAX_VALUE;
            boolean[] sold = new boolean[Q];
            for (int j = 0; j < Q; j++) {
                int maxIndex = -1, last = 0;
                int[] available = new int[Q];
                TreeSet<Integer> open = new TreeSet<Integer>();
                for (int k = 0; k < endpoints.length; k++) {
                    if (sold[endpoints[k].index]) {
                        continue;
                    }
                    if (open.size() == 1) {
                        available[open.first()] += endpoints[k].offset - endpoints[last].offset;
                        if (maxIndex == -1 || available[open.first()] > available[maxIndex]) {
                            maxIndex = open.first();
                        }
                    }
                    if (endpoints[k].open) {
                        open.add(endpoints[k].index);
                    }
                    else {
                        open.remove(endpoints[k].index);
                    }
                    last = k;
                }
                if (available[maxIndex] < min) {
                    min = available[maxIndex];
                }
                sold[maxIndex] = true;
            }
            result[t] = String.format("Case #%d: %d", t + 1, min);
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
