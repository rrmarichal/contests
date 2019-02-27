package contests.ioi;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class AppTests {

    private App app;

    @Before
    public void init() {
        app = new App();
    }
    
    @Test
    public void case0Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        QueryInfo[] Q = new QueryInfo[] {
            new QueryInfo(0, 3, 0),
            new QueryInfo(2, 4, 1),
            new QueryInfo(0, 1, 2),
            new QueryInfo(0, 6, 3)
        };
        long[] result = app.solve(A, Q);
        assertEquals(4, result[0]);
        assertEquals(2, result[1]);
        assertEquals(0, result[2]);
        assertEquals(14, result[3]);
    }

    @Test
    public void case1Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4, 5, 7, 7, 1, 1 };
        QueryInfo[] Q = new QueryInfo[] {
            new QueryInfo(0, 3, 0),
            new QueryInfo(2, 4, 1),
            new QueryInfo(0, 1, 2),
            new QueryInfo(0, A.length - 1, 3),
            new QueryInfo(0, 6, 4)
        };
        long[] result = app.solve(A, Q);
        assertEquals(bruteForce(A, Q[0].L, Q[0].R), result[Q[0].index]);
        assertEquals(bruteForce(A, Q[1].L, Q[1].R), result[Q[1].index]);
        assertEquals(bruteForce(A, Q[2].L, Q[2].R), result[Q[2].index]);
        assertEquals(bruteForce(A, Q[3].L, Q[3].R), result[Q[3].index]);
        assertEquals(bruteForce(A, Q[4].L, Q[4].R), result[Q[4].index]);
    }

    @Test
    public void randomCaseTest() {
        int maxN = 1000, maxQ = 10;
        int[] A = new int[maxN];
        QueryInfo[] Q = new QueryInfo[maxQ];
        Random random = new Random();
        for (int j = 0; j < maxN; j++) {
            A[j] = random.nextInt(1000000);
        }
        for (int j = 0; j < maxQ; j++) {
            int L = random.nextInt(maxN);
            int R = random.nextInt(maxN);
            Q[j] = new QueryInfo(Math.min(L, R), Math.max(L, R), j);
        }
        long[] result = app.solve(A, Q);
        for (int j = 0; j < maxQ; j++) {
            long inv = bruteForce(A, Q[j].L, Q[j].R);
            assertEquals(inv, result[Q[j].index]);
        }
    }

    private long bruteForce(int[] A, int L, int R) {
        long count = 0;
        for (int j = L; j < R; j++)
            for (int k = j+1; k <= R; k++)
                if (A[j] > A[k]) {
                    count++;
                }
        return count;
    }

}
