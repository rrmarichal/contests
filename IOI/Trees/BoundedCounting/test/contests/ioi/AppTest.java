package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;
import contests.ioi.App;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    @Ignore
    public void test0()
    {
        long result = app.perfectBalancedTressOfWeight(1);
        assertEquals(1, result);
    }

    @Test
    @Ignore
    public void test1()
    {
        long result = app.perfectBalancedTressOfWeight(2);
        assertEquals(1, result);
    }

    @Test
    @Ignore
    public void test2()
    {
        long result = app.perfectBalancedTressOfWeight(3);
        assertEquals(2, result);
    }

    @Test
    @Ignore
    public void test3()
    {
        long result = app.perfectBalancedTressOfWeight(4);
        assertEquals(3, result);
    }

    @Test
    @Ignore
    public void test4()
    {
        long result = app.perfectBalancedTressOfWeight(5);
        assertEquals(4, result);
    }

    @Test
    @Ignore
    public void test5()
    {
        long result = app.perfectBalancedTressOfWeight(6);
        assertEquals(6, result);
    }

    @Test
    @Ignore
    public void test6()
    {
        long result = app.perfectBalancedTressOfWeight(8);
        assertEquals(9, result);
    }

    @Test
    @Ignore
    public void test7()
    {
        long result = app.perfectBalancedTressOfWeight(10);
        assertEquals(13, result);
    }

    @Test
    @Ignore
    public void test8()
    {
        long result = app.perfectBalancedTressOfWeight(16);
        assertEquals(29, result);
    }

    @Test
    @Ignore
    public void test10_2()
    {
        long result = app.perfectBalancedTressOfWeight(100);
        assertEquals(658, result);
    }

    @Test
    @Ignore
    public void test10_6()
    {
        long result = app.perfectBalancedTressOfWeight(1000000);
        assertEquals(5242627404L, result);
    }

    @Test
    @Ignore
    public void test10_7()
    {
        long result = app.perfectBalancedTressOfWeight(10000000);
        assertEquals(280520110076L, result);
    }

    @Test
    @Ignore
    public void test10_8()
    {
        long result = app.perfectBalancedTressOfWeight(100000000);
        assertEquals(15004522714920L, result);
    }

    @Test
    @Ignore
    public void test10_9()
    {
        long result = app.perfectBalancedTressOfWeight(1000000000);
        assertEquals(802692214297940L, result);
    }

    @Test
    @Ignore
    public void test9()
    {
        for (int j = 1; j <= 100; j++) {
            System.out.println(app.perfectBalancedTressOfWeight(j));
        }
    }
}
