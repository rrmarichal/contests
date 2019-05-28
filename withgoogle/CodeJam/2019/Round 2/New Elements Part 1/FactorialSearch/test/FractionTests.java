import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FractionTests {

    @Test
    public void test0() {
        Fraction f = new Fraction(1, 2);
        Fraction g = new Fraction(1, 4);
        assertEquals(f, Fraction.max(f, g));
        assertEquals(g, Fraction.min(f, g));
        assertTrue(g.compareTo(f) < 0);
    }

    @Test
    public void test1() {
        Fraction f = new Fraction(-1, 2);
        Fraction g = new Fraction(1, 4);
        assertEquals(g, Fraction.max(f, g));
        assertEquals(f, Fraction.min(f, g));
        assertTrue(f.compareTo(g) < 0);
    }

    @Test
    public void test2() {
        Fraction f = new Fraction(-1, 2);
        Fraction g = new Fraction(-1, 4);
        assertEquals(g, Fraction.max(f, g));
        assertEquals(f, Fraction.min(f, g));
        assertTrue(f.compareTo(g) < 0);
    }

    @Test
    public void test3() {
        Fraction f = new Fraction(-1, -2);
        Fraction g = new Fraction(-1, 4);
        assertEquals(f, Fraction.max(f, g));
        assertEquals(g, Fraction.min(f, g));
        assertTrue(g.compareTo(f) < 0);
    }

    @Test
    public void test4() {
        Fraction f = new Fraction(1, 2);
        Fraction g = new Fraction(-1, 4);
        assertEquals(f, Fraction.max(f, g));
        assertEquals(g, Fraction.min(f, g));
        assertTrue(g.compareTo(f) < 0);
    }

}
