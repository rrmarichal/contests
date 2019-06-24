package continuedfractions;

import java.util.List;
import java.math.BigInteger;
import java.util.ArrayList;

import fractions.Fraction;

public class ContinuedFraction implements Comparable<ContinuedFraction> {

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
