package contests.ioi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void case0Test() {
        int N = 3, K = 3;
        int[][] C = new int[][] { {0,1,1}, {1,0,1}, {1,1,0} };
        assertEquals(0, app.solve(N, K, C));
    }

    @Test
    public void case1Test() {
        int N = 3, K = 2;
        int[][] C = new int[][] { {0,1,1}, {1,0,1}, {1,1,0} };
        assertEquals(1, app.solve(N, K, C));
    }

    @Test
    public void case2Test() {
        int N = 5, K = 2;
        int[][] C = new int[][] {
            { 0, 5, 4, 3, 2 },
            { 7, 0, 4, 4, 4 },
            { 3, 3, 0, 1, 2 },
            { 4, 3, 1, 0, 5 },
            { 4, 5, 5, 5, 0 }
        };
        assertEquals(5, app.solve(N, K, C));
    }

    @Test
    public void case3Test() {
        int N = 5, K = 1;
        int[][] C = new int[][] {
            { 0, 0, 5, 5, 5 },
            { 5, 0, 0, 5, 5 },
            { 5, 5, 0, 0, 5 },
            { 5, 5, 5, 0, 0 },
            { 5, 5, 5, 5, 0 }
        };
        assertEquals(0, app.solve(N, K, C));
    }

    @Test
    public void case4Test() {
        int N = 5, K = 1;
        int[][] C = new int[][] {
            { 0, 1, 5, 5, 5 },
            { 5, 0, 2, 5, 5 },
            { 5, 5, 0, 3, 5 },
            { 5, 5, 5, 0, 4 },
            { 5, 5, 5, 5, 0 }
        };
        assertEquals(10, app.solve(N, K, C));
    }

    @Test
    public void case5Test() {
        int N = 5, K = 1;
        int[][] C = new int[][] {
            { 0, 1, 5, 5, 5 },
            { 5, 0, 1, 5, 5 },
            { 5, 5, 0, 1, 5 },
            { 5, 5, 5, 0, 1 },
            { 5, 5, 5, 5, 0 }
        };
        assertEquals(4, app.solve(N, K, C));
    }

    @Test
    public void case6Test() {
        int N = 3, K = 1;
        int[][] C = new int[][] {
            { 0, 4, 1 },
            { 100, 0, 100 },
            { 2, 4, 0 }
        };
        assertEquals(5, app.solve(N, K, C));
    }

}
