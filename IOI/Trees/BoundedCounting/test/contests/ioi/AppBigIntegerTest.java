package contests.ioi;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.*;
import contests.ioi.AppBigInteger;

public class AppBigIntegerTest {

    private AppBigInteger app;

    @Before
    public void setup() {
        app = new AppBigInteger();
    }

    @Test
    @Ignore
    public void test0() {
        BigInteger result = app.perfectBalancedTressOfWeight(1);
        assertEquals(BigInteger.valueOf(1), result);
    }

    @Test
    @Ignore
    public void test1() {
        BigInteger result = app.perfectBalancedTressOfWeight(2);
        assertEquals(BigInteger.valueOf(1), result);
    }

    @Test
    @Ignore
    public void test2() {
        BigInteger result = app.perfectBalancedTressOfWeight(3);
        assertEquals(BigInteger.valueOf(2), result);
    }

    @Test
    @Ignore
    public void test3() {
        BigInteger result = app.perfectBalancedTressOfWeight(4);
        assertEquals(BigInteger.valueOf(3), result);
    }

    @Test
    @Ignore
    public void test4() {
        BigInteger result = app.perfectBalancedTressOfWeight(5);
        assertEquals(BigInteger.valueOf(4), result);
    }

    @Test
    @Ignore
    public void test5() {
        BigInteger result = app.perfectBalancedTressOfWeight(6);
        assertEquals(BigInteger.valueOf(6), result);
    }

    @Test
    @Ignore
    public void test6() {
        BigInteger result = app.perfectBalancedTressOfWeight(8);
        assertEquals(BigInteger.valueOf(9), result);
    }

    @Test
    @Ignore
    public void test7() {
        BigInteger result = app.perfectBalancedTressOfWeight(10);
        assertEquals(BigInteger.valueOf(13), result);
    }

    @Test
    @Ignore
    public void test8() {
        BigInteger result = app.perfectBalancedTressOfWeight(16);
        assertEquals(BigInteger.valueOf(29), result);
    }

    @Test
    @Ignore
    public void test10_2() {
        BigInteger result = app.perfectBalancedTressOfWeight(100);
        assertEquals(BigInteger.valueOf(658), result);
    }

    @Test
    @Ignore
    public void test10_6() {
        BigInteger result = app.perfectBalancedTressOfWeight(1000000);
        assertEquals(new BigInteger("5242627404"), result);
    }

    @Test
    @Ignore
    public void test10_7() {
        BigInteger result = app.perfectBalancedTressOfWeight(10000000);
        assertEquals(new BigInteger("280520110076"), result);
    }

    @Test
    @Ignore
    public void test10_8() {
        BigInteger result = app.perfectBalancedTressOfWeight(100000000);
        assertEquals(new BigInteger("15004522714920"), result);
    }

    @Test
    @Ignore
    public void test10_9() {
        BigInteger result = app.perfectBalancedTressOfWeight(1000000000);
        assertEquals(new BigInteger("802692214297940"), result);
    }

    @Test
    @Ignore
    public void testLong() {
        Long x = 802692214297940L;
        Long y = Long.MAX_VALUE;
        System.out.println(x);
        System.out.println(y);
        assertTrue(x <= y);
    }

    @Test
    @Ignore
    public void test9() {
        for (int j = 1; j <= 100; j++) {
            System.out.println(app.perfectBalancedTressOfWeight(j).toString());
        }
    }
    
}
