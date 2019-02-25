package contests.ioi;

import java.util.Scanner;

public class App {

    private int best;

    private int distributeWithMinPaths(int N, int K, int[][] C, int[][] P, int current) {
        int dist = 0;
        for (int j = 0; j < N; j++) {
            if ((current & (1<<j)) == 0) {
                int jd = Integer.MAX_VALUE;
                for (int k = 0; k < N; k++) {
                    if ((current & (1<<k)) != 0) {
                        int x = C[j][k];
                        if (P[j][k] != 0) {
                            int l = k;
                            while (P[j][l] != 0) {
                                l = P[j][l]-1;
                            }
                            x = C[j][l];
                        }
                        if (x < jd) {
                            jd = x;
                        }
                    }
                }
                dist += jd;
            }
        }
        return dist;
    }

    private int distribute(int N, int K, int[][] C, int current) {
        int dist = 0;
        int all = (1<<N)-1;
        while (current < all) {
            // Find closest empty glass to any filling glass
            int closest = Integer.MAX_VALUE, next = 0;
            for (int j = 0; j < N; j++) {
                int dj = Integer.MAX_VALUE;
                if ((current & (1<<j)) == 0) {
                    for (int k = 0; k < N; k++) {
                        if ((current & (1<<k)) != 0) {
                            if (C[j][k] < dj) {
                                dj = C[j][k];
                            }
                        }
                    }
                }
                if (dj < closest) {
                    closest = dj;
                    next = j;
                }
            }
            current |= (1<<next);
            dist += closest;
        }
        return dist;
    }

    private void subsetsCounterWithMinPaths(int N, int K, int[][] C, int[][] P, int current, int offset, int size) {
        if (size == K) {
            int dist = distributeWithMinPaths(N, K, C, P, current);
            if (dist < best) {
                best = dist;
            }
        }
        else
        if (offset < N) {
            subsetsCounterWithMinPaths(N, K, C, P, current + (1<<offset), offset + 1, size + 1);
            subsetsCounterWithMinPaths(N, K, C, P, current, offset + 1, size);
        }
    }

    private void subsetsCounter(int N, int K, int[][] C, int current, int offset, int size) {
        if (size == K) {
            int dist = distribute(N, K, C, current);
            if (dist < best) {
                best = dist;
            }
        }
        else
        if (offset < N) {
            subsetsCounter(N, K, C, current + (1<<offset), offset + 1, size + 1);
            subsetsCounter(N, K, C, current, offset + 1, size);
        }
    }

    private void floydWarshall(int N, int[][] C, int[][] P) {
        for (int l = 0; l < N; l++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if (C[j][k] > C[j][l] + C[l][k]) {
                        C[j][k] = C[j][l] + C[l][k];
                        P[j][k] = l+1;
                    }
    }

    public int solveWithMinPaths(int N, int K, int[][] C) {
        if (N == K) {
            return 0;
        }
        int[][] P = new int[N][N];
        floydWarshall(N, C, P);
        best = Integer.MAX_VALUE;
        subsetsCounterWithMinPaths(N, K, C, P, 0, 0, 0);
        return best;
    }

    public int solve(int N, int K, int[][] C) {
        if (N == K) {
            return 0;
        }
        best = Integer.MAX_VALUE;
        subsetsCounter(N, K, C, 0, 0, 0);
        return best;
    }

    public static void main( String[] args ) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), K = sc.nextInt();
        int[][] C = new int[N][N];
        for (int j = 0; j < N; j++)
            for (int k = 0; k < N; k++)
                C[j][k] = sc.nextInt();
        System.out.println(app.solve(N, K, C));
        // System.out.println(app.solveWithMinPaths(N, K, C));
        sc.close();
    }

}
