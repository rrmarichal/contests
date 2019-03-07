package contests.ioi;

import java.util.Scanner;

public class App {

    private int inRange(int[] cummulative, int l, int h) {
        return cummulative[h] - cummulative[l-1];
    }

    public long count(int N, int[] A) {
        int[] cummulative = new int[N+1];
        for (int j = 0; j < N; j++) {
            cummulative[A[j]]++;
        }
        for (int j = 1; j <= N; j++) {
            cummulative[j] = cummulative[j] + cummulative[j-1];
        }
        int segments = 1;
        for (int j = 1; j < N; j++) {
            if (A[j] < A[j-1] || inRange(cummulative, A[j-1]+1, A[j]-1) > 0) {
                segments++;
            }
        }
        return segments;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App app = new App();
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int j = 0; j < N; j++) {
            A[j] = sc.nextInt();
        }
        System.out.println(app.count(N, A));
        sc.close();
    }

}
