package contests.ioi;

import java.util.Scanner;

public class App {

    public long count(long N) {
        long _2sum = 0, _3sum = 0;
        long p = 1;
        while ((p - 1) * (p - 1) < N) {
            if (p * p > N) {
                _2sum += p * (N - (p - 1) * (p - 1));
            }
            else {
                _2sum += p * (p * p - (p - 1) * (p - 1));
            }
            p++;
        }
        p = 1;
        while (p * p * p <= N) {
            if ((p + 1) * (p + 1) * (p + 1) > N) {
                _3sum += p * (N + 1 - p * p * p);
            }
            else {
                _3sum += p * ((p + 1) * (p + 1) * (p + 1) - p * p * p);
            }
            p++;
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
