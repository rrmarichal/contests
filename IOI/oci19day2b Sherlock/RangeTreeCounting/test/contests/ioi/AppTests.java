package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AppTests {

    private App app;

    @Before
    public void init() {
        app = new App();
    }
    
    @Test
    @Ignore
    public void case0Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        int[][] Q = new int[][] { {1, 4}, {3, 5}, {1, 2}, {1, 7} };
        int[] result = app.solve(A, Q);
        assertEquals(result[0], 4);
        assertEquals(result[1], 2);
        assertEquals(result[2], 0);
        assertEquals(result[3], 14);
    }

}
