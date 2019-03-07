package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;

public class AppTests {

    private App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void case0Test() {
        assertEquals(4, app.count(6, new int[] { 5, 6, 4, 3, 1, 2 }));
    }

    @Test
    public void case1Test() {
        assertEquals(2, app.count(3, new int[] { 1, 2, 1 }));
    }

    @Test
    public void case2Test() {
        assertEquals(1, app.count(10, new int[] { 1, 2, 2, 2, 3, 4, 5, 10, 10, 10 }));
    }

    @Test
    public void case3Test() {
        assertEquals(3, app.count(4, new int[] { 1, 2, 1, 2 }));
    }

}
