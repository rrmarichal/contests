package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;

public class PairsTests {

    private Pairs app;

    @Before
    public void setup() {
        app = new Pairs();
    }

    @Test
    public void testSetsCount0() {
        long count = app.setsCount(1, 2, 4);
        assertEquals(0, count);
    }

    @Test
    public void testSetsCount1() {
        long count = app.setsCount(1, 5, 4);
        assertEquals(5, count);
    }

    @Test
    public void testSetsCount2() {
        long count = app.setsCount(1, 4, 4);
        assertEquals(3, count);
    }

    @Test
    public void testSetsCount3() {
        long count = app.setsCount(1, 21, 20);
        assertEquals(127, count);
    }

    @Test
    public void modularExpTest0() {
        long result = app.modularExp(0);
        assertEquals(1, result);
    }

    @Test
    public void modularExpTest1() {
        long result = app.modularExp(5);
        assertEquals(32, result);
    }

    @Test
    public void modularExpTest2() {
        long result = app.modularExp(32);
        assertEquals(294967296, result);
    }

    @Test
    public void modularExpTest3() {
        long result = app.modularExp(127);
        assertEquals(884105728, result);
    }

    @Test
    public void test0() {
        long result = app.count(2);
        assertEquals(1, result);
    }

    @Test
    public void test1() {
        long result = app.count(3);
        assertEquals(5, result);
    }

    @Test
    public void test2() {
        long result = app.count(4);
        assertEquals(21, result);
    }

    @Test
    public void test3() {
        long result = app.count(10);
        assertEquals(7064545, result);
    }

    @Test
    public void test4() {
        long result = app.count(20);
        assertEquals(239260453, result);
    }

    @Test
    public void test5() {
        long result = app.count(5);
        assertEquals(441, result);
    }

    @Test
    public void test6() {
        long result = app.count(15);
        assertEquals(273117677, result);
    }

    @Test
    public void test7() {
        long result = app.count(18);
        assertEquals(928361409, result);
    }

    @Test
    public void test8() {
        long result = app.count(19);
        assertEquals(134665269, result);
    }

}
