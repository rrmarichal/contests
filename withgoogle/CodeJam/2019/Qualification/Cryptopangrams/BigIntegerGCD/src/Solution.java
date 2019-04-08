import java.io.*;
import java.math.BigInteger;
import java.util.*;

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

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class TestInfo {
    int L;
    String[] list;

    public TestInfo(int L, String[] list) {
        this.L = L;
        this.list = list;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            in.next();
            int L = in.nextInt();
            String[] list = new String[L];
            for (int j = 0; j < L; j++) {
                list[j] = in.next();
            } 
            tests[t] = new TestInfo(L, list);
        }
    }

    private Character charIndexOf(BigInteger[] primes, BigInteger p) {
        for (int j = 0; j < primes.length; j++) {
            if (p.equals(primes[j])) {
                return (char) (j + 65);
            }
        }
        return null;
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            HashSet<BigInteger> primes = new HashSet<BigInteger>();
            TestInfo test = tests[t];
            int index = 0;
            while (test.list[index].equals(test.list[index + 1])) {
                index++;
            }
            BigInteger x = new BigInteger(test.list[index]);
            BigInteger y = new BigInteger(test.list[index + 1]);
            BigInteger p = x.gcd(y);
            BigInteger p0 = x.divide(p);
            primes.add(p0);
            primes.add(p);
            // Recover the prime for the first character in the text.
            for (int j = index - 1; j >= 0; j--) {
                BigInteger current = new BigInteger(test.list[j]);
                p0 = current.divide(p0);
            }
            // Find all primes used.
            for (int j = index + 1; j < test.L; j++) {
                BigInteger current = new BigInteger(test.list[j]);
                p = current.divide(p);
                primes.add(p);
            }
            BigInteger[] parray = primes.toArray(new BigInteger[] {});
            Arrays.sort(parray);
            StringBuilder text = new StringBuilder();
            text.append(charIndexOf(parray, p0));
            for (int j = 0; j < test.L; j++) {
                BigInteger current = new BigInteger(test.list[j]);
                p0 = current.divide(p0);
                text.append(charIndexOf(parray, p0));
            }
            result[t] = String.format("Case #%d: %s", t + 1, text.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        for (String c: result) {
            out.println(c);
        }
        out.close();
    }

}
