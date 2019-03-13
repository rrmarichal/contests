package contests.ioi;

import java.util.Scanner;

public class App {

    private int[] dist;
    private long[] A;
    private int D;
    private long max;

    protected App(long[] A, int D) {
        this.A = A;
        this.D = D;
    }

    private void printDist(int[] dist, long gsum) {
        for (int j = 0; j < dist.length; j++)
            System.out.print(String.format("%d ", dist[j]));
        System.out.println(String.format("=> %d", gsum));
    }

    private long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private long count(int chunks) {
        int shifSum = 0;
        long result = 0;
        for (int j = 0; j < chunks; j++) {
            long g = A[j * D + shifSum];
            for (int k = 1; k < D + dist[j]; k++) {
                g = gcd(g, A[j * D + shifSum + k]);
            }
            result += g;
            shifSum += dist[j];
        }
        return result;
    }

    private long search(int chunks, int shift, int current) {
        long best = 0;
        if (current == chunks-1) {
            dist[current] = shift;
            best = count(chunks);
        }
        else {
            for (int j = 0; j <= shift; j++) {
                dist[current] = j;
                long b = search(chunks, shift - j, current + 1);
                if (b > max) {
                    max = b;
                    printDist(dist, max);
                }
                best = Math.max(best, b);
            }
        }
        return best;
    }

    public long search() {
        long best = 0;
        for (int j = 1; j <= A.length / D; j++) {
            dist = new int[j];
            best = Math.max(best, search(j, A.length - j*D, 0));
        }
        return best;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt(), D = sc.nextInt();
        long[] A = new long[M];
        int c = 0;
        for (int j = 0; j < N; j++) {
            int k = sc.nextInt();
            for (; k > 0; k--) {
                A[c++] = sc.nextLong();
            }
        }
        App app = new App(A, D);
        System.out.println(app.search());
        sc.close();
    }

}
