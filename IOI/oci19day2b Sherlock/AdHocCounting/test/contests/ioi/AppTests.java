package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class AppTests {

    private App app;

    @Test
    @Ignore
    public void case0TestBf() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        assertEquals(4, app.bruteForce(A, 0, 3));
        assertEquals(2, app.bruteForce(A, 2, 4));
        assertEquals(0, app.bruteForce(A, 0, 1));
        assertEquals(14, app.bruteForce(A, 0, 6));
    }

    @Test
    public void case0Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        app = new App(A);
        assertEquals(4, app.successiveMinCounter(0, 3));
        assertEquals(2, app.successiveMinCounter(2, 4));
        assertEquals(0, app.successiveMinCounter(0, 1));
        assertEquals(14, app.successiveMinCounter(0, 6));
    }

}
