package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;
import contests.ioi.App;

public class AppTests {

    private App app;

    @Test
    public void test0() {
        app = new App(new long[] { 1, 3, 2 }, 1);
        assertEquals(6, app.search());
    }

    @Test
    public void test1() {
        app = new App(new long[] { 4, 8, 1, 3, 6 }, 2);
        assertEquals(5, app.search());
    }

    @Test
    public void test2() {
        app = new App(new long[] { 4, 6, 12, 8, 4, 6, 1 }, 2);
        assertEquals(7, app.search());
    }

    @Test
    public void test3() {
        app = new App(new long[] { 18, 36, 6, 6, 9, 16, 27, 81, 6, 12, 24, 12, 12, 10 }, 5);
        assertEquals(4, app.search());
    }
    

    @Test
    public void test4() {
        app = new App(new long[] { 17, 17, 17, 34, 49, 7, 7, 7, 7, 1 }, 3);
        assertEquals(25, app.search());
    }

    @Test
    public void test5() {
        app = new App(new long[] { 17, 17, 17, 34, 49, 7, 7, 7, 7, 1, 6, 7, 121, 14, 24, 12, 24 }, 3);
        assertEquals(38, app.search());
    }

    @Test
    public void test6() {
        app = new App(new long[] { 17, 17, 17, 34, 49, 7, 7, 7, 7, 1, 6, 7, 121, 14, 24, 12, 24 }, 4);
        // 17, 17, 17, 34 => 17
        // 49, 7, 7, 7, 7 => 7
        // 1, 6, 7, 121 => 1
        // 14, 24, 12, 24 => 2
        assertEquals(27, app.search());
    }

    @Test
    public void test7() {
        app = new App(new long[] { 17, 17, 17, 34, 49, 7, 7, 7, 7, 1, 6, 7, 121, 14, 24, 12, 24 }, 5);
        // 17, 17, 17, 34, 49 => 1
        // 7, 7, 7, 7, 1 => 1
        // 6, 7, 121, 14, 24, 12, 24 => 1
        assertEquals(3, app.search());
    }

    @Test
    public void test8() {
        app = new App(new long[] { 24, 48, 6, 120, 100, 25, 50, 96, 64, 66, 8 }, 4);
        // 24, 48, 6, 120 => 6
        // 100, 25, 50, 96, 64, 66, 8 => 1
        assertEquals(7, app.search());
    }

}
