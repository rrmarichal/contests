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
        assertEquals(4, app.count("ACGTA", 1));
    }

    @Test
    public void case1Test() {
        assertEquals(5, app.count("AACACGTA", 2));
    }

    @Test
    public void case2Test() {
        assertEquals(1, app.count("AAAAAAAAAAAAA", 3));
    }

    @Test
    public void case3Test() {
        assertEquals(1, app.count("AACACGTAAACACGTAAACACGTA", 24));
    }

}
