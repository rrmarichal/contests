package fractions;

public class Fraction implements Comparable<Fraction> {

    private long n, d;
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
        return new Fraction(a.n + b.n, a.d + b.d);
    }

    public Fraction(long n, long d) {
        if (d == 0) {
            throw new IllegalArgumentException();
        }
        this.sign = (int) Math.signum(n * d);
        long gcd = Math.abs(_gcd(n, d));
        this.n = Math.abs(n) / gcd;
        this.d = Math.abs(d) / gcd;
    }

    private long _gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return _gcd(b, a % b);
    }
 
    public long getNumerator() {
        return n * sign;
    }

    public long getDenominator() {
        return d;
    }

    public Fraction add(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        long dd = d * other.d;
        long nn = other.d * getNumerator() + d * other.getNumerator();
        return new Fraction(nn, dd);
    }

    public Fraction add(long x) {
        return add(new Fraction(x, 1));
    }

    public Fraction divide(long q) {
        return new Fraction(getNumerator(), getDenominator() * q);
    }

    public Fraction multiply(long x) {
        return new Fraction(getNumerator() * x, getDenominator());
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
        long cp1 = other.d * n;
        long cp2 = other.n * d;
        return sign * Long.compare(cp1, cp2);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", getNumerator(), getDenominator());
    }

}

