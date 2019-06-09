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
    long C, J;

    public MoleculeInfo(long C, long J) {
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
    long n, d, sign;
    double r;

    public Fraction(long n, long d) {
        this.sign = ((long) n * d) < 0 ? -1 : 1;
        this.n = Math.abs(n);
        this.d = Math.abs(d);
        this.r = (double) n/d;
    }

    public static Fraction max(Fraction x, Fraction y) {
        return x.compareTo(y) > 0 ? x : y;
    }

    public static Fraction min(Fraction x, Fraction y) {
        return max(x, y) == x ? y : x;
    }

    public long getSign() {
        return sign;
    }

    public Fraction add(Fraction ub) {
        long dd = d * ub.d;
        long nn = ub.d * n + d * ub.n;
        return new Fraction(nn, dd);
    }

    public Fraction divide(long q) {
        return new Fraction(n, d * q);
    }

    public Fraction multiply(long x) {
        return new Fraction(n * x, d);
    }

    @Override
    public int compareTo(Fraction o) {
        if (sign != o.getSign()) {
            return (int) sign;
        }
        long cp1 = (long) o.d * n;
        long cp2 = (long) o.n * d;
        return (int) sign * Long.compare(cp1, cp2);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", n, d);
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

	public Solution(InputReader in) {
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

	private boolean _increasing(MoleculeInfo[] molecules, long c, long j) {
        long curr = molecules[0].C * c + molecules[0].J * j;
        for (int k = 1; k < molecules.length; k++) {
            long next = molecules[k].C * c + molecules[k].J * j;
            if (curr >= next) {
                return false;
            }
            curr = next;
        }
        return true;
    }

    private Fraction _solve(TestInfo testInfo) {
        for (int c = 1; c < 199; c++) {
            for (int j = 1; j < 199; j++) {
                if (_increasing(testInfo.molecules, c, j)) {
                    return new Fraction(c, j);
                }
            }
        }
        return null;
    }

    public String[] solve() {
		String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            Fraction b = _solve(tests[t]);
            if (b == null) {
                result[t] = String.format("Case #%d: IMPOSSIBLE", t + 1);
            }
            else {
                result[t] = String.format("Case #%d: %d %d", t + 1, b.n, b.d);
            }
        }
        return result;
	}

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        for (String s: result) {
            out.println(s);
        }
        out.close();
    }
    
}
