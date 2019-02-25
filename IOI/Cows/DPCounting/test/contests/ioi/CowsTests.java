package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;
import contests.ioi.Cows;

/**
 * Unit test for simple App.
 */
public class CowsTests {

    private Cows cows;

    @Before
    public void setup() {
        cows = new Cows();
    }

    @Test
    public void test0() {
        long result = cows.count(2, 2, 7, new String[] { "AB", "ab", "BA", "ba", "Aa", "Bb", "bB" });
        assertEquals(7, result);
    }

    @Test
    public void test1() {
        long result = cows.count(2, 3, 7, new String[] { "AB", "ab", "BA", "ba", "Aa", "Bb", "bB", "Bz" });
        assertEquals(10, result);
    }

}
