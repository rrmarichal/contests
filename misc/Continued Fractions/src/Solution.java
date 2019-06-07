import java.io.*;

public class Solution {

    private static final Fraction zero = new Fraction(0, 1);

    private int T;
    private Fraction[] fractions;

    /**
     * The first line contains T, the number of fractions.
     * The second line contains a list of T fractions, each defined as two integers, N and D.
     */
    protected Solution(InputReader in) {
        T = in.nextInt();
        fractions = new Fraction[T];
        for (int t = 0; t < T; t++) {
            fractions[t] = new Fraction(in.nextInt(), in.nextInt());
        }
    }

    private ContinuedFraction _calc(Fraction fraction) {
        ContinuedFraction result = new ContinuedFraction();
        while (true) {
            long i = fraction.n / fraction.d;
            result.add(i);
            fraction = fraction.add(-i);
            if (fraction.compareTo(zero) == 0) {
                break;
            }
            fraction = fraction.reciprocal();
        }
        return result;
    }

    /**
     * Return a ContinuedFraction object for each fraction in the input, corresponding to their
     * continued fraction representations.
     */
    public ContinuedFraction[] solve() {
        ContinuedFraction[] result = new ContinuedFraction[T];
        for (int t = 0; t < T; t++) {
            result[t] = _calc(fractions[t]);
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        ContinuedFraction[] result = solution.solve();
        for (ContinuedFraction cf: result) {
            out.println(cf.toString());
        }
        out.close();
    }

}
