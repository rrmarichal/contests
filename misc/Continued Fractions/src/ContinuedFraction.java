import java.util.*;

class ContinuedFraction {

    private static final Fraction zero = new Fraction(0, 1);

    private List<Long> terms;

    protected ContinuedFraction(Fraction fraction) {
        _calc(fraction);
    }

    protected ContinuedFraction(List<Long> terms) {
        this.terms = terms;
    }

    public static ContinuedFraction fromFraction(Fraction fraction) {
        return new ContinuedFraction(fraction);
    }

    public static ContinuedFraction fromTerms(List<Long> terms) {
        return new ContinuedFraction(terms);
    }

    public static ContinuedFraction bestWithinInterval(ContinuedFraction l, ContinuedFraction u) {
        List<Long> terms = new ArrayList<Long>();
        int j = -1;
        if (l.terms.size() > u.terms.size()) {
            ContinuedFraction tmp = l;
            l = u;
            u = tmp;
        }
        while (++j < l.terms.size()) {
            if (l.terms.get(j) != u.terms.get(j)) {
                terms.add(1 + Math.min(l.terms.get(j), u.terms.get(j)));
                break;
            }
            else {
                terms.add(l.terms.get(j));
            }
        }
        if (j == l.terms.size()) {
            return l;
        }
        return ContinuedFraction.fromTerms(terms);
    }

    private void _calc(Fraction fraction) {
        terms = new ArrayList<Long>();
        while (true) {
            long i = fraction.n / fraction.d;
            terms.add(i);
            fraction = fraction.add(-i);
            if (fraction.compareTo(zero) == 0) {
                break;
            }
            fraction = fraction.reciprocal();
        }
    }

    private Fraction _eval(int index) {
        if (index == terms.size()) {
            return new Fraction(0, 1);
        }
        return _eval(index + 1).add(terms.get(index)).reciprocal();
    }

    public Fraction getFraction() {
        return _eval(1).add(terms.get(0));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int j = 0; j < terms.size(); j++) {
            sb.append(terms.get(j));
            if (j < terms.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

}
