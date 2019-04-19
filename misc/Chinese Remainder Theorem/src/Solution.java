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
    int K;
    int[] a, n;  

    public TestInfo(int K, int[] a, int[] n) {
        this.K = K;
        this.a = a;
        this.n = n;
    }
}

class ExtendedEuclidResult {
    long d, x, y;

    public ExtendedEuclidResult(long d, long x, long y) {
        this.d = d;
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            int K = in.nextInt();
            int[] a = new int[K], n = new int[K];
            for (int k = 0; k < K; k++) {
                a[k] = in.nextInt();
            }
            for (int k = 0; k < K; k++) {
                n[k] = in.nextInt();
            }
            tests[t] = new TestInfo(K, a, n);
        }
    }

    protected static ExtendedEuclidResult _extendedEuclid(long a, long b) {
        if (b == 0) {
            return new ExtendedEuclidResult(a, 1, 0);
        }
        ExtendedEuclidResult r = _extendedEuclid(b, a % b);
        return new ExtendedEuclidResult(r.d, r.y, r.x - a / b * r.y);
    }

    private static long _modularInverse(long m, int n) {
        ExtendedEuclidResult r = _extendedEuclid(m, n);
        return r.x;
    }

    private long _solve(TestInfo testInfo) {
        long n = 1;
        for (int j = 0; j < testInfo.K; j++) {
            n *= testInfo.n[j];
        }
        long[] m = new long[testInfo.K];
        for (int j = 0; j < testInfo.K; j++) {
            m[j] = n / testInfo.n[j];
        }
        long[] invm = new long[testInfo.K];
        for (int j = 0; j < testInfo.K; j++) {
            invm[j] = _modularInverse(m[j], testInfo.n[j]);
        }
        long[] c = new long[testInfo.K];
        for (int j = 0; j < testInfo.K; j++) {
            c[j] = m[j] * (invm[j] % testInfo.n[j]);
        }
        long a = 0;
        for (int j = 0; j < testInfo.K; j++) {
            a = (a + testInfo.a[j] * c[j]) % n;
        }
        return (a + n) % n;
    }

    public long[] solve() {
        long[] result = new long[T];
        for (int t = 0; t < T; t++) {
            result[t] = _solve(tests[t]);
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        long[] result = solution.solve();
        for (long c: result) {
            out.println(c);
        }
        out.close();
    }

}
