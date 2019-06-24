package continuedfractions;

import java.math.BigInteger;

import fractions.Fraction;

public class Utils {

    /**
     * Brute force approach implementation. Starting with an upper bound
     * defined by the median of l and u. Search for every possible fraction
     * better than that.
     */
    public static Fraction bestWithinInterval(Fraction l, Fraction u) {
        for (long d = 1; d <= l.getDenominator().add(u.getDenominator()).longValue(); d++) {
            BigInteger bd = BigInteger.valueOf(d);
            BigInteger k = l.getNumerator().multiply(bd).divide(l.getDenominator());
            if (k.multiply(l.getDenominator()).compareTo(l.getNumerator().multiply(bd)) <= 0) {
                k = k.add(BigInteger.ONE);
            }
            if (k.multiply(u.getDenominator()).compareTo(bd.multiply(u.getNumerator())) < 0) {
                return new Fraction(k, bd);
            }
        }
        return null;
    }

}
