class Fraction implements Comparable<Fraction> {
    long n, d, sign;

    public Fraction(long n, long d) {
        this.sign = ((long) n * d) < 0 ? -1 : 1;
        this.n = Math.abs(n);
        this.d = Math.abs(d);
    }

    public static Fraction max(Fraction x, Fraction y) {
        return x.compareTo(y) > 0 ? x : y;
    }

    public static Fraction min(Fraction x, Fraction y) {
        return max(x, y) == x ? y : x;
    }

    public Fraction add(Fraction ub) {
        long dd = d * ub.d;
        long nn = sign * ub.d * n + ub.sign * d * ub.n;
        return new Fraction(nn, dd);
    }

    public Fraction add(long x) {
        return add(new Fraction(x, 1));
    }

    public Fraction divide(long q) {
        return new Fraction(n, d * q);
    }

    public Fraction multiply(long x) {
        return new Fraction(n * x, d);
    }

    public Fraction reciprocal() {
        return new Fraction(d, n);
    }

    @Override
    public int compareTo(Fraction o) {
        if (sign != o.sign) {
            return (int) sign;
        }
        long cp1 = o.d * n;
        long cp2 = o.n * d;
        return (int) sign * Long.compare(cp1, cp2);
    }
}
