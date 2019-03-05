package contests.ioi;

import java.util.Scanner;

public class App {

    protected long naturalsSum(long k) {
        return k * (k + 1) / 2;
    }

    protected long squaresSum(long k) {
        return k * (k + 1) * (2*k + 1) / 6;
    }

    protected long cubesSum(long k) {
        return k * k * (k + 1) * (k + 1) / 4;
    }

    public long count(long N) {
        long nsquare = (long) Math.sqrt(N);
        long _2sum = 2 * squaresSum(nsquare) - naturalsSum(nsquare);
        if (nsquare * nsquare < N) {
            _2sum += (nsquare + 1) * (N - nsquare * nsquare);
        }
        long ncube = (long) Math.cbrt(N) - 1;
        long _3sum = 3 * cubesSum(ncube) + 3 * squaresSum(ncube) + naturalsSum(ncube);
        if (ncube * ncube * ncube <= N) {
            _3sum += (ncube + 1) * (N + 1 - (ncube + 1) * (ncube + 1) * (ncube + 1));
        }
        return _2sum + _3sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App app = new App();
        System.out.println(app.count(sc.nextLong()));
        sc.close();
    }

}
