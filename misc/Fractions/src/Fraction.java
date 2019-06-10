class Fraction implements Comparable<Fraction> {

    private long n, d;
    private int sign;

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

    public Fraction add(Fraction ub) {
        if (ub == null) {
            throw new IllegalArgumentException();
        }
        long dd = d * ub.d;
        long nn = ub.d * getNumerator() + d * ub.getNumerator();
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Fraction)) {
            throw new IllegalArgumentException();
        }
        return compareTo((Fraction) obj) == 0;
    }

    @Override
    public int compareTo(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        if (sign != other.sign) {
            return sign;
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
