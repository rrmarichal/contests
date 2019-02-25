package contests.ioi;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private long kcompounds;

    public ArrayList<Integer> sieve(int N) {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        boolean[] sieve = new boolean[N+1];
        for (int j = 2; j <= N; j++) {
            if (!sieve[j]) {
                primes.add(j);
                for (int k = 2; k*j <= N; k++) {
                    sieve[k*j] = true;
                }
            }
        }
        return primes;
    }

    public ArrayList<Long> factorize(ArrayList<Integer> primes, long K) {
        ArrayList<Long> pfactors = new ArrayList<Long>();
        for (int p: primes) {
            if (K % p == 0) {
                pfactors.add((long) p);
                while (K % p == 0) {
                    K /= p;
                }
            }
            if (K == 1) {
                break;
            }
        }
        // K is a prime larger that 10^6
        if (K > 1) {
            pfactors.add(K);
        }
        return pfactors;
    }

    public void subsetsCounting(ArrayList<Long> pfactors, long K, long current, long subsetSize, int offset) {
        if (offset == pfactors.size()) {
            if (subsetSize > 0) {
                int f = subsetSize % 2 == 0 ? -1 : 1;
                kcompounds += f * K / current;
            }
        }
        else {
            subsetsCounting(pfactors, K, current * pfactors.get(offset), subsetSize + 1, offset + 1);
            subsetsCounting(pfactors, K, current, subsetSize, offset + 1);
        }
    }

    public long inclusionExclusionCounter(ArrayList<Integer> primes, long K) {
        ArrayList<Long> pfactors = factorize(primes, K);
        kcompounds = 0;
        subsetsCounting(pfactors, K, 1, 0, 0);
        return K - kcompounds;
    }

    public static void main(String[] args) {
        App app = new App();
        ArrayList<Integer> primes = app.sieve(1000000);
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (; T>0; T--) {
            long K = sc.nextLong();
            System.out.println(app.inclusionExclusionCounter(primes, K));
        }
        sc.close();
    }

}
