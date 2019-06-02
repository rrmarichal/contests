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

class MoleculeInfo {
    int C, J;

    public MoleculeInfo(int C, int J) {
        this.C = C;
        this.J = J;
    }
}

class TestInfo {
    int N;
    MoleculeInfo[] molecules;

    public TestInfo(int N, MoleculeInfo[] molecules) {
        this.N = N;
        this.molecules = molecules;
    }
}

class Fraction implements Comparable<Fraction> {
    int n, d, sign;

    public Fraction(int n, int d) {
        this.sign = ((long) n * d) < 0 ? -1 : 1;
        this.n = Math.abs(n);
        this.d = Math.abs(d);
    }

    public static Fraction max(Fraction x, Fraction y) {
        return x.compareTo(y) > 0 ? x : y;
    }

    public static Fraction min(Fraction x, Fraction y) {
        return max(x, y) == x ? y : x;
    }

    public int getSign() {
        return sign;
    }

    @Override
    public int compareTo(Fraction o) {
        if (sign != o.getSign()) {
            return sign;
        }
        long cp1 = (long) o.d * n;
        long cp2 = (long) o.n * d;
        return sign * Long.compare(cp1, cp2);
    }

}

public class Solution {

    private int T;
    private TestInfo[] tests;
    private PrintWriter out;

    protected Solution(InputReader in, PrintWriter out) {
        this.out = out;
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            MoleculeInfo[] molecules = new MoleculeInfo[N];
            for (int j = 0; j < N; j++) {
                molecules[j] = new MoleculeInfo(in.nextInt(), in.nextInt());
            }
            tests[t] = new TestInfo(N, molecules);
        }
    }

    private int _valid(MoleculeInfo[] molecules, int length, boolean[] used, Fraction lb, Fraction ub) {
        if (length == molecules.length) {
            return 1;
        }
        int result = 0;
        for (int j = 0; j < molecules.length; j++) {
            if (!used[j]) {
                Fraction _lb = lb, _ub = ub;
                MoleculeInfo prev = molecules[j];
                boolean ok = true;
                for (int k = 0; k < molecules.length; k++) {
                    if (k != j && !used[k]) {
                        MoleculeInfo next = molecules[k];
                        if (prev.J >= next.J && prev.C >= next.C) {
                            ok = false;
                            break;
                        }
                        int dc = prev.C - next.C;
                        int dj = next.J - prev.J;
                        if (prev.J < next.J) {
                            _lb = Fraction.max(_lb, new Fraction(dc, dj));
                        }
                        else
                        if (prev.J > next.J) {
                            _ub = Fraction.min(_ub, new Fraction(dc, dj));
                        }
                    }
                }
                if (ok && _lb.compareTo(ub) < 0) {
                    used[j] = true;
                    result += _valid(molecules, length + 1, used, _lb, _ub);
                    used[j] = false;
                }
            }
        }
        return result;
    }

    private int _solve(TestInfo testInfo) {
        Fraction lb = new Fraction(0, 1), ub = new Fraction(Integer.MAX_VALUE, 1);
        return _valid(testInfo.molecules, 0, new boolean[testInfo.N], lb, ub);
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            result[t] = String.format("Case #%d: %d", t + 1, _solve(tests[t]));
            out.println(result[t]);
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        solution.solve();
        out.close();
    }

}
