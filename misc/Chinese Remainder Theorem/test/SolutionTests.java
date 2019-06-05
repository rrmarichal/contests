import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

public class SolutionTests {

    @Test
    public void extendedEuclidTest0() {
        ExtendedEuclidResult r = Solution._extendedEuclid(0, 0);
        assertEquals(0, r.d);
        assertEquals(1, r.x);
        assertEquals(0, r.y);
    }

    @Test
    public void extendedEuclidTest1() {
        ExtendedEuclidResult r = Solution._extendedEuclid(1, 0);
        assertEquals(1, r.d);
        assertEquals(1, r.x);
        assertEquals(0, r.y);
    }

    @Test
    public void extendedEuclidTest2() {
        ExtendedEuclidResult r = Solution._extendedEuclid(1, 1);
        assertEquals(1, r.d);
        assertEquals(0, r.x);
        assertEquals(1, r.y);
    }

    @Test
    public void extendedEuclidTest3() {
        ExtendedEuclidResult r = Solution._extendedEuclid(13, 5);
        assertEquals(1, r.d);
        assertEquals(2, r.x);
        assertEquals(-5, r.y);
    }

    @Test
    public void extendedEuclidTest3a() {
        ExtendedEuclidResult r = Solution._extendedEuclid(23, 11);
        assertEquals(1, r.d);
        assertEquals(1, r.x);
        assertEquals(-2, r.y);
    }

    @Test
    public void extendedEuclidTest4() {
        ExtendedEuclidResult r = Solution._extendedEuclid(28, -11);
        assertEquals(1, r.d);
        assertEquals(2, r.x);
        assertEquals(5, r.y);
    }

    @Test
    public void extendedEuclidTest4a() {
        ExtendedEuclidResult r = Solution._extendedEuclid(62, -23);
        assertEquals(-1, r.d);
        assertEquals(10, r.x);
        assertEquals(27, r.y);
    }

    @Test
    public void extendedEuclidTest6() {
        ExtendedEuclidResult r = Solution._extendedEuclid(28*23 + 11*62, -2*11*23);
        assertEquals(-2, r.d);
        assertEquals(29, r.x);
        assertEquals(76, r.y);
    }

    @Test
    public void extendedEuclidTest7() {
        ExtendedEuclidResult r = Solution._extendedEuclid(28*23 + 11*62, -2*11*23);
        assertEquals(-2, r.d);
        assertEquals(29, r.x);
        assertEquals(76, r.y);
    }

    @Test
    public void extendedEuclidTest8() {
        ExtendedEuclidResult r = Solution._extendedEuclid(28 + 11, 62 + 23);
        assertEquals(1, r.d);
        assertEquals(24, r.x);
        assertEquals(-11, r.y);
    }

    @Test
    public void extendedEuclidTest9() {
        ExtendedEuclidResult r = Solution._extendedEuclid(28 + 62, 11 + 23);
        assertEquals(2, r.d);
        assertEquals(-3, r.x);
        assertEquals(8, r.y);
    }

    @Test
    public void extendedEuclidTesta() {
        ExtendedEuclidResult r = Solution._extendedEuclid(694166449 + 679334523, 402616793 + 389956898);
        assertEquals(1, r.d);
        assertEquals(-376220157, r.x);
        assertEquals(651975655, r.y);
    }

    @Test
    public void extendedEuclidTestb() {
        ExtendedEuclidResult r = Solution._extendedEuclid(743506299 + 694166449, 476597252 + 402616793);
        assertEquals(1, r.d);
        assertEquals(-141856188, r.x);
        assertEquals(231960325, r.y);
    }

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        long[] result = solution.solve();
        assertArrayEquals(new long[] {
            42,
            23,
            10
        }, result);
    }

}
