import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ContinuedFractionTests {

    @Test
    public void test0() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(3L);
        terms.add(7L);
        terms.add(16L);
        ContinuedFraction cf = ContinuedFraction.fromTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(355, fraction.n);
        assertEquals(113, fraction.d);
    }

    @Test
    public void test1() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(3L);
        terms.add(7L);
        terms.add(15L);
        terms.add(2L);
        ContinuedFraction cf = ContinuedFraction.fromTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(688, fraction.n);
        assertEquals(219, fraction.d);
    }

    @Test
    public void testInterval0() {
        Fraction l = new Fraction(28, 11),
            u = new Fraction(62, 23);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = bestcf.getFraction();
        assertEquals(8, best.n);
        assertEquals(3, best.d);
    }

    @Test
    public void testInterval1() {
        Fraction l = new Fraction(743506299, 476597252),
            u = new Fraction(694166449, 402616793);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = bestcf.getFraction();
        assertEquals(5, best.n);
        assertEquals(3, best.d);
    }

    @Test
    public void testInterval2() {
        Fraction l = new Fraction(694166449, 402616793),
            u = new Fraction(679334523, 389956898);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = bestcf.getFraction();
        assertEquals(19, best.n);
        assertEquals(11, best.d);
    }

    @Test
    public void testInterval3() {
        Fraction l = new Fraction(694166449, 402616793),
            u = new Fraction(679334523, 389956898);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = bestcf.getFraction();
        assertEquals(19, best.n);
        assertEquals(11, best.d);
    }

    @Test
    public void testInterval4() {
        Fraction l = new Fraction(1000000000, 999999999),
            u = new Fraction(1000000001, 999999999);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = bestcf.getFraction();
        assertEquals(500000001, best.n);
        assertEquals(500000000, best.d);
    }

    @Test
    public void testInterval5() {
        Fraction l = new Fraction(244304949, 202780255),
            u = new Fraction(832138971, 690620094);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = bestcf.getFraction();
        assertEquals(100, best.n);
        assertEquals(83, best.d);
    }
     
}
