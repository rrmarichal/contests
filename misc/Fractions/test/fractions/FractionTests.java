package fractions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class FractionTests {

    @Test
    public void baseTest0() {
        Fraction f = new Fraction(0, 1);
        assertEquals(0, f.getNumerator().longValue());
        assertEquals(1, f.getDenominator().longValue());
    }

    @Test
    public void baseTest1() {
        Fraction f = new Fraction(0, 2);
        assertEquals(0, f.getNumerator().longValue());
        assertEquals(1, f.getDenominator().longValue());
    }

    @Test
    public void minMaxTest0() {
        Fraction f = new Fraction(1, 2);
        Fraction g = new Fraction(1, 4);
        assertEquals(f, Fraction.max(f, g));
        assertEquals(g, Fraction.min(f, g));
        assertTrue(g.compareTo(f) < 0);
    }

    @Test
    public void minMaxTest1() {
        Fraction f = new Fraction(-1, 2);
        Fraction g = new Fraction(1, 4);
        assertEquals(g, Fraction.max(f, g));
        assertEquals(f, Fraction.min(f, g));
        assertTrue(f.compareTo(g) < 0);
    }

    @Test
    public void minMaxTest2() {
        Fraction f = new Fraction(-1, 2);
        Fraction g = new Fraction(-1, 4);
        assertEquals(g, Fraction.max(f, g));
        assertEquals(f, Fraction.min(f, g));
        assertTrue(f.compareTo(g) < 0);
    }

    @Test
    public void minMaxTest3() {
        Fraction f = new Fraction(-1, -2);
        Fraction g = new Fraction(-1, 4);
        assertEquals(f, Fraction.max(f, g));
        assertEquals(g, Fraction.min(f, g));
        assertTrue(g.compareTo(f) < 0);
    }

    @Test
    public void minMaxTest4() {
        Fraction f = new Fraction(1, 2);
        Fraction g = new Fraction(-1, 4);
        assertEquals(f, Fraction.max(f, g));
        assertEquals(g, Fraction.min(f, g));
        assertTrue(g.compareTo(f) < 0);
    }

    @Test
    public void minMaxTest5() {
        Fraction f = null;
        Fraction g = new Fraction(1, 4);
        assertThrows(IllegalArgumentException.class, () -> Fraction.min(f, g));
    }

    @Test
    public void minMaxTest6() {
        Fraction f = new Fraction(1, 4);
        Fraction g = null;
        assertThrows(IllegalArgumentException.class, () -> Fraction.min(f, g));
    }

    @Test
    public void minMaxTest7() {
        Fraction f = null;
        Fraction g = new Fraction(1, 4);
        assertThrows(IllegalArgumentException.class, () -> Fraction.max(f, g));
    }

    @Test
    public void minMaxTest8() {
        Fraction f = new Fraction(1, 4);
        Fraction g = null;
        assertThrows(IllegalArgumentException.class, () -> Fraction.max(f, g));
    }

    @Test
    public void sumTest0() {
        Fraction f = new Fraction(0, 1);
        Fraction g = null;
        assertThrows(IllegalArgumentException.class, () -> f.add(g));
    }

    @Test
    public void sumTest1() {
        Random random = new Random();
        Fraction f = new Fraction(0, 1);
        Fraction g = new Fraction(random.nextInt(), random.nextInt());
        Fraction sum = f.add(g);
        assertTrue(g.equals(sum));
    }

    @Test
    public void sumTest2() {
        Fraction f = new Fraction(-1, 2);
        Fraction g = new Fraction(-1, 2);
        Fraction sum = f.add(g);
        assertEquals(new Fraction(-1, 1), sum);
    }

    @Test
    public void sumTest3() {
        Fraction f = new Fraction(-1, 2);
        Fraction g = new Fraction(1, 2);
        Fraction sum = f.add(g);
        assertEquals(new Fraction(0, 1), sum);
    }

    @Test
    public void sumTest4() {
        Fraction f = new Fraction(-Integer.MAX_VALUE, 1);
        Fraction g = new Fraction(10, 1);
        Fraction sum = f.add(g);
        assertEquals(new Fraction(-Integer.MAX_VALUE + 10, 1), sum);
    }

    @Test
    public void multiplyTest0() {
        Fraction f = new Fraction(4, 9);
        Fraction p = f.multiply(2L);
        assertEquals(new Fraction(8, 9), p);
    }

    @Test
    public void multiplyTest1() {
        Fraction f = new Fraction(-3, 29);
        Fraction p = f.multiply(11L);
        assertEquals(new Fraction(-33, 29), p);
    }

    @Test
    public void multiplyTest2() {
        Fraction f = new Fraction(-3, 29);
        Fraction p = f.multiply(-5L);
        assertEquals(new Fraction(15, 29), p);
    }

    @Test
    public void divideTest0() {
        Fraction f = new Fraction(4, 9);
        Fraction p = f.divide(2L);
        assertEquals(new Fraction(4, 18), p);
    }

    @Test
    public void divideTest1() {
        Fraction f = new Fraction(-3, 29);
        Fraction p = f.divide(11L);
        assertEquals(new Fraction(-3, 319), p);
    }

    @Test
    public void divideTest2() {
        Fraction f = new Fraction(1, 2);
        Fraction p = f.divide(-100L);
        assertEquals(new Fraction(-1, 200), p);
    }

    @Test
    public void fractionIsReducedTest() {
        Fraction f = new Fraction(3, 33);
        assertEquals(1, f.getNumerator().longValue());
        assertEquals(11, f.getDenominator().longValue());
    }

    @Test
    public void reciprocalTest() {
        Fraction f = new Fraction(3, 32).reciprocal();
        assertEquals(32, f.getNumerator().longValue());
        assertEquals(3, f.getDenominator().longValue());
    }

    @Test
    public void reciprocalTesta() {
        Fraction f = new Fraction(0, 1).reciprocal();
        assertEquals(1, f.getNumerator().longValue());
        assertEquals(0, f.getDenominator().longValue());
    }

}
