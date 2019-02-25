package contests.ioi;

import java.util.Scanner;

public class App {

    private boolean isPrime(long n) {
        for (long j=2; j<=Math.sqrt(n); j++) {
            if (n % j == 0) return false;
        }
        return true;
    }

    public long primesUpTo(long n) {
        long count = 0;
        for (long j=2; j<=n; j++) {
            if (isPrime(j)) {
                count++;
            }
        }
        return count;
    }

    public boolean relativelyPrimes(long a, long b) {
        if (a > b) {
            long tmp = a;
            a = b;
            b = tmp;
        }
        if (a == 1) {
            return true;
        }
        if (b % a == 0) {
            return false;
        }
        for (long j=2; j<=Math.sqrt(a); j++) {
            if (a%j == 0) {
                if (b%j == 0) {
                    return false;
                }
                if (b%(a/j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public long bruteForce(long K) {
        long count = 1;
        for (int j=2; j<K; j++) {
            if (relativelyPrimes(j, j+K)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (; T>0; T--) {
            long K = sc.nextLong();
            System.out.println(app.bruteForce(K));
        }
        sc.close();
    }

}
