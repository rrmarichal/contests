package fractions;

import java.math.BigInteger;

public class Fraction implements Comparable<Fraction> {

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
