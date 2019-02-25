package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;

public class AppTest {

    private App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void relativelyPrimes1() {
        assertTrue(app.relativelyPrimes(1, 6));
    }

    @Test
    public void relativelyPrimes2() {
        assertTrue(app.relativelyPrimes(5, 22));
    }

    @Test
    public void relativelyPrimes3() {
        assertTrue(app.relativelyPrimes(11, 23));
    }

    @Test
    public void relativelyPrimes4() {
        assertFalse(app.relativelyPrimes(2, 14));
    }

    @Test
    public void relativelyPrimes5() {
        assertFalse(app.relativelyPrimes(6, 21));
    }

    @Test
    public void relativelyPrimes6() {
        assertFalse(app.relativelyPrimes(4, 16));
    }

    @Test
    public void relativelyPrimes7() {
        assertFalse(app.relativelyPrimes(10, 22));
    }

    @Test
    public void relativelyPrimes8() {
        assertFalse(app.relativelyPrimes(9, 21));
    }

    @Test
    public void relativelyPrimes9() {
        assertFalse(app.relativelyPrimes(8, 20));
    }

    @Test
    public void bruteForce0() {
        assertEquals(2, app.bruteForce(3));
    }

    @Test
    public void bruteForce1() {
        assertEquals(324, app.bruteForce(489));
    }

    @Test
    public void bruteForce2() {
        assertEquals(4, app.bruteForce(12));
    }

    @Test
    public void bruteForce3() {
        assertEquals(4, app.bruteForce(5));
    }

}
