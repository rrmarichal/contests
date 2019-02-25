package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private App app;

    @Before
    public void init() {
        app = new App();
    }

    @Test
    public void case0Test() {
        assertEquals("a", app.solve("aaaa"));
    }

    @Test
    public void case1Test() {
        assertEquals("-1", app.solve("ab"));
    }

    @Test
    public void case2Test() {
        assertEquals("bba", app.solve("bbabab"));
    }

}
