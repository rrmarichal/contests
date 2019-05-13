import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * For a natural-value random variable R, E(R) = Sum P(R > i) for i = 0..infinity.
 * 
 * Let R = the expected length of the array as described.
 * 
 * For the computation of the value above, we just fix the value of the d = GCD(a1, a2, ..., ai),
 * and let d iterate over its possible values (2, 3, ..., m). Inclusion/exclusion principle is
 * applied to remove repeated sequences.
 */
public class Solution {

    static final BigInteger MODULO = BigInteger.valueOf((long)1e9 + 7);

    private int m;

    protected Solution(InputReader in) {
        m = in.nextInt();
    }

    public long solve() {
        boolean[] sieve = new boolean[m + 1];
        int p = 2;
        ArrayList<Integer> primes = new ArrayList<Integer>();
        int[] F = new int[m + 1];
        while (p <= m) {
            if (!sieve[p]) {
                primes.add(p);
                for (int j = 2; j*p <= m; j++) {
                    sieve[j*p] = true;
                }
                F[m/p]++;
            }
            else {
                // Get the prime factorization of p. Consider only factorizations with
                // unitary-exponent factors.
                int subtract = -1;
                int q = p;
                for (int j = 0; primes.get(j) * primes.get(j) <= p; j++) {
                    if (p % primes.get(j) == 0) {
                        if (p % (primes.get(j) * primes.get(j)) == 0) {
                            subtract = 0;
                            break;
                        }
                        subtract *= -1;
                        q /= primes.get(j);
                    }
                }
                if (q > 1) {
                    subtract *= -1;
                }
                F[m/p] += subtract;
            }
            p++;
        }
        Fraction result = new Fraction(1, 1);
        for (int j = 1; j <= m; j++) {
            if (F[j] != 0) {
                result = result.add(new Fraction(F[j] * j, m - j));
            }
        }
        BigInteger iq = Utils.extendedEuclid(result.q, MODULO).x;
        return result.p.multiply(iq).mod(MODULO).longValue();
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        out.println(solution.solve());
        out.close();
    }

}

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}

class ExtendedEuclidResult {
    BigInteger gcd, x, y;

    public ExtendedEuclidResult(BigInteger gcd, BigInteger x, BigInteger y) {
        this.gcd = gcd;
        this.x = x;
        this.y = y;
    }
}

class Utils {
    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }

    static ExtendedEuclidResult extendedEuclid(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.valueOf(0))) {
            return new ExtendedEuclidResult(a, BigInteger.valueOf(1), BigInteger.valueOf(0));
        }
        ExtendedEuclidResult r = extendedEuclid(b, a.mod(b));
        return new ExtendedEuclidResult(r.gcd, r.y, r.x.subtract(a.divide(b).multiply(r.y)));
    }
}

class Fraction {
    BigInteger p, q;

    public Fraction(long p, long q) {
        this.p = BigInteger.valueOf(p);
        this.q = BigInteger.valueOf(q);
    }

    public Fraction(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
    }

    public Fraction add(Fraction fraction) {
        BigInteger qq = Utils.lcm(q, fraction.q);
        BigInteger pp = qq.divide(q).multiply(p).add(qq.divide(fraction.q).multiply(fraction.p));
        BigInteger gcd = qq.gcd(pp);
        return new Fraction(pp.divide(gcd), qq.divide(gcd));
    }
}
