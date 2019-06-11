package continuedfractions;

public class Utils {

    /**
     * Brute force approach implementation. Starting with an upper bound
     * defined by the median of l and u. Search for every possible fraction
     * better than that.
     */
    public static Fraction bestWithinInterval(Fraction l, Fraction u) {
        Fraction median = Fraction.median(l, u);
        for (long d = 1; d < median.getDenominator(); d++) {
            long k = l.getNumerator() * d / l.getDenominator();
            if (k * l.getDenominator() <= l.getNumerator() * d) {
                k++;
            }
            if (k * u.getDenominator() < d * u.getNumerator()) {
                return new Fraction(k, d);
            }
        }
        return median;
    }

}
