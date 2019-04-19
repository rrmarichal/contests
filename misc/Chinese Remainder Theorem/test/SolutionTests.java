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
