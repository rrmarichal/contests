import java.io.*;
import java.math.BigInteger;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
    }

    private boolean ensureTokenizer() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = nextLine();
            if (line != null) {
                tokenizer = new StringTokenizer(line);
            }
        }
        return tokenizer != null && tokenizer.hasMoreElements();
    }

    public String nextLine() {
        try {
            tokenizer = null;
            return reader.readLine();
        }
        catch (IOException e) {
            return null;
        }
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

    /**
     * The Fraction constant zero.
     */
    public static final Fraction ZERO = new Fraction(0, 1);

    /**
     * The Fraction constant one.
     */
    public static final Fraction ONE = new Fraction(1, 1);

    /**
     * The Fraction constant infinity.
     */
    public static final Fraction INF = new Fraction(1, 0);

    private BigInteger n, d;
    private int sign;

    public static Fraction min(Fraction a, Fraction b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }
        return a.compareTo(b) < 0 ? a : b;
    }

    public static Fraction max(Fraction a, Fraction b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }
        return a.compareTo(b) > 0 ? a : b;
    }

    public static Fraction median(Fraction a, Fraction b) {
        return new Fraction(a.n.add(b.n), a.d.add(b.d));
    }

    public Fraction(BigInteger n, BigInteger d) {
        this.sign = _sign(n, d);
        BigInteger gcd = n.gcd(d);
        this.n = n.abs().divide(gcd);
        this.d = d.abs().divide(gcd);
    }

    public Fraction(long n, long d) {
        this(BigInteger.valueOf(n), BigInteger.valueOf(d));
    }

    private int _sign(BigInteger n, BigInteger d) {
        if (BigInteger.ZERO.equals(n)) {
            return 0;
        }
        if (BigInteger.ZERO.equals(d)) {
            return n.signum();
        }
        if (n.signum() == d.signum()) {
            return 1;
        }
        return -1;
    }
 
    public BigInteger getNumerator() {
        return n.multiply(BigInteger.valueOf(sign));
    }

    public BigInteger getDenominator() {
        return d;
    }

    public Fraction add(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        BigInteger dd = d.multiply(other.d);
        BigInteger nn = other.d.multiply(getNumerator()).add(d.multiply(other.getNumerator()));
        return new Fraction(nn, dd);
    }

    public Fraction add(Long x) {
        return add(new Fraction(x, 1L));
    }

    public Fraction add(BigInteger x) {
        return add(new Fraction(x, BigInteger.ONE));
    }

    public Fraction divide(Long q) {
        return new Fraction(getNumerator(), getDenominator().multiply(BigInteger.valueOf(q)));
    }

    public Fraction divide(BigInteger q) {
        return new Fraction(getNumerator(), getDenominator().multiply(q));
    }

    public Fraction multiply(Long x) {
        return new Fraction(getNumerator().multiply(BigInteger.valueOf(x)), getDenominator());
    }

    public Fraction multiply(BigInteger x) {
        return new Fraction(getNumerator().multiply(x), getDenominator());
    }

    public Fraction reciprocal() {
        return new Fraction(getDenominator(), getNumerator());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Fraction)) {
            throw new IllegalArgumentException();
        }
        return compareTo((Fraction) other) == 0;
    }

    @Override
    public int compareTo(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        if (sign != other.sign) {
            return Integer.compare(sign, other.sign);
        }
        BigInteger cp1 = other.d.multiply(n);
        BigInteger cp2 = other.n.multiply(d);
        return sign * cp1.compareTo(cp2);
    }

    @Override
    public String toString() {
        return String.format("%s/%s", getNumerator().toString(), getDenominator().toString());
    }

}

class ContinuedFraction implements Comparable<ContinuedFraction> {

    private List<BigInteger> terms;
    private Fraction fraction;

    public static ContinuedFraction fromFraction(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException();
        }
        return new ContinuedFraction(fraction);
    }

    public static ContinuedFraction fromBigIntegerTerms(List<BigInteger> terms) {
        if (terms == null || terms.size() == 0) {
            throw new IllegalArgumentException();
        }
        return new ContinuedFraction(terms);
    }

    public static ContinuedFraction fromLongTerms(List<Long> terms) {
        if (terms == null || terms.size() == 0) {
            throw new IllegalArgumentException();
        }
        List<BigInteger> bterms = new ArrayList<BigInteger>();
        for (Long term: terms) {
            bterms.add(BigInteger.valueOf(term));
        }
        return new ContinuedFraction(bterms);
    }

    protected ContinuedFraction(Fraction fraction) {
        this.fraction = fraction;
        this.terms = _eval(fraction);
    }

    protected ContinuedFraction(List<BigInteger> terms) {
        this.terms = terms;
        this.fraction = _eval(terms, 0).reciprocal();
    }

    public Fraction getFraction() {
        return fraction;
    }

    public List<BigInteger> getTerms() {
        return new ArrayList<BigInteger>(terms);
    }

    /**
     * Returns the extension representation of this Fraction. That is, the shortest
     * continued fraction that is equal to this.
     */
    protected ContinuedFraction getExtendedContinuedFraction() {
        List<BigInteger> exTerms = new ArrayList<BigInteger>(terms);
        exTerms.set(exTerms.size() - 1, exTerms.get(terms.size() - 1).subtract(BigInteger.ONE));
        exTerms.add(BigInteger.ONE);
        return ContinuedFraction.fromBigIntegerTerms(exTerms);
    }

    private static ContinuedFraction _bestWithinInterval(ContinuedFraction l, ContinuedFraction u) {
        List<BigInteger> terms = new ArrayList<BigInteger>();
        // Make l the shortest sequence.
        if (l.terms.size() > u.terms.size()) {
            ContinuedFraction tmp = l;
            l = u;
            u = tmp;
        }
        int k = -1;
        while (++k < l.terms.size()) {
            if (l.terms.get(k).equals(u.terms.get(k))) {
                terms.add(l.terms.get(k));
            }
            else {
                terms.add(BigInteger.ONE.add( l.terms.get(k).min(u.terms.get(k)) ));
                return ContinuedFraction.fromBigIntegerTerms(terms);
            }
        }
        // All terms up to k are equal, and k equals length(l).
        terms.add(u.terms.get(k).add(BigInteger.ONE));
        return ContinuedFraction.fromBigIntegerTerms(terms);
    }

    private static ContinuedFraction _best(ContinuedFraction l, ContinuedFraction u, ContinuedFraction[] list) {
        for (ContinuedFraction f: list) {
            if (l.compareTo(f) < 0 && f.compareTo(u) < 0) {
                return f;
            }
        }
        return null;
    }

    public static ContinuedFraction bestWithinInterval(ContinuedFraction l, ContinuedFraction u) {
        if (l == null) {
            throw new IllegalArgumentException("Null argument l.");
        }
        if (u == null) {
            throw new IllegalArgumentException("Null argument u.");
        }
        // Check l is strictly less that u.
        if (l.compareTo(u) >= 0) {
            throw new IllegalArgumentException("l >= u.");
        }
        ContinuedFraction le = l.getExtendedContinuedFraction();
        ContinuedFraction ue = u.getExtendedContinuedFraction();
        ContinuedFraction z0 = _bestWithinInterval(l, u);
        ContinuedFraction z1 = _bestWithinInterval(l, ue);
        ContinuedFraction z2 = _bestWithinInterval(le, u);
        ContinuedFraction z3 = _bestWithinInterval(le, ue);
        return _best(l, u, new ContinuedFraction[] { z0, z1, z2, z3 });
    }

    private static List<BigInteger> _eval(Fraction fraction) {
        List<BigInteger> terms = new ArrayList<BigInteger>();
        while (true) {
            BigInteger i = fraction.getNumerator().divide(fraction.getDenominator());
            terms.add(i);
            fraction = fraction.add(i.negate());
            if (fraction.compareTo(Fraction.ZERO) == 0) {
                break;
            }
            fraction = fraction.reciprocal();
        }
        return terms;
    }

    private static Fraction _eval(List<BigInteger> terms, int index) {
        if (index == terms.size()) {
            return new Fraction(0, 1);
        }
        return _eval(terms, index + 1).add(terms.get(index)).reciprocal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int j = 0; j < terms.size(); j++) {
            sb.append(terms.get(j).toString());
            if (j < terms.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public int compareTo(ContinuedFraction o) {
        if (o == null) {
            throw new IllegalArgumentException("Null argument.");
        }
        return getFraction().compareTo(o.getFraction());
    }

}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
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

    private Fraction[] _getBounds(TestInfo testInfo) {
        Fraction lb = new Fraction(0, 1);
        Fraction ub = new Fraction((long) 1e10, 1);
        MoleculeInfo curr = testInfo.molecules[0];
        for (int j = 1; j < testInfo.N; j++) {
            MoleculeInfo next = testInfo.molecules[j];
            if (curr.J >= next.J && curr.C >= next.C) {
                return null;
            }
            long dc = curr.C - next.C;
            long dj = next.J - curr.J;
            if (curr.J < next.J) {
                lb = Fraction.max(lb, new Fraction(dc, dj));
            }
            else
            if (curr.J > next.J) {
                ub = Fraction.min(ub, new Fraction(dc, dj));
            }
            curr = next;
        }
        return new Fraction[] { lb, ub };
    }

    private Fraction _solve(TestInfo testInfo) {
        Fraction[] bounds = _getBounds(testInfo);
        if (bounds == null) {
            return null;
        }
        if (bounds[0].compareTo(bounds[1]) >= 0) {
            return null;
        }
        if (bounds[1].add(bounds[0].multiply(-1L)).compareTo(new Fraction(1, 1)) > 0) {
            return new Fraction(BigInteger.ONE.add(bounds[0].getNumerator().divide( bounds[0].getDenominator())), BigInteger.ONE);
        }
        return ContinuedFraction.bestWithinInterval(
            ContinuedFraction.fromFraction(bounds[0]),
            ContinuedFraction.fromFraction(bounds[1])
        ).getFraction();
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            Fraction b = _solve(tests[t]);
            if (b == null) {
                result[t] = String.format("Case #%d: IMPOSSIBLE", t + 1);
            }
            else {
                result[t] = String.format("Case #%d: %s %s",
                    t + 1, b.getDenominator().toString(), b.getNumerator().toString());
            }
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
