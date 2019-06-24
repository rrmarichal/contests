package continuedfractions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import continuedfractions.ContinuedFraction;
import fractions.Fraction;

public class ContinuedFractionTests {

    @Test
    public void termsToFractionTest0() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(3L);
        terms.add(7L);
        terms.add(16L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(355, fraction.getNumerator().longValue());
        assertEquals(113, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest1() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(3L);
        terms.add(7L);
        terms.add(15L);
        terms.add(2L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(688, fraction.getNumerator().longValue());
        assertEquals(219, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest2() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(3L);
        terms.add(7L);
        terms.add(15L);
        terms.add(2L);
        terms.add(5L);
        terms.add(2L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(8234, fraction.getNumerator().longValue());
        assertEquals(2621, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest3() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(3L);
        terms.add(7L);
        terms.add(15L);
        terms.add(2L);
        terms.add(5L);
        terms.add(1L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(4461, fraction.getNumerator().longValue());
        assertEquals(1420, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest4() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(2L);
        terms.add(3L);
        terms.add(1L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(9, fraction.getNumerator().longValue());
        assertEquals(4, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest5() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(1L);
        terms.add(3L);
        terms.add(1L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(5, fraction.getNumerator().longValue());
        assertEquals(4, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest6() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(1L);
        terms.add(3L);
        terms.add(0L);
        terms.add(1L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(5, fraction.getNumerator().longValue());
        assertEquals(4, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest7() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(-1L);
        terms.add(-4L);
        terms.add(-6L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(-31, fraction.getNumerator().longValue());
        assertEquals(25, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest8() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(0L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(0, fraction.getNumerator().longValue());
        assertEquals(1, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest8a() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(0L);
        terms.add(0L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(1, fraction.getNumerator().longValue());
        assertEquals(0, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest8b() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(1L);
        terms.add(0L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(1, fraction.getNumerator().longValue());
        assertEquals(0, fraction.getDenominator().longValue());
    }

    @Test
    public void termsToFractionTest8c() {
        List<Long> terms = new ArrayList<Long>();
        terms.add(0L);
        terms.add(1L);
        ContinuedFraction cf = ContinuedFraction.fromLongTerms(terms);
        Fraction fraction = cf.getFraction();
        assertEquals(1, fraction.getNumerator().longValue());
        assertEquals(1, fraction.getDenominator().longValue());
    }

    @Test
    public void fractionToTermsTest0() {
        Fraction fraction = new Fraction(4461, 1420);
        ContinuedFraction cf = ContinuedFraction.fromFraction(fraction);
        assertEquals("[3, 7, 15, 2, 6]", cf.toString());
    }

    @Test
    public void fractionToTermsTest1() {
        Fraction fraction = new Fraction(8234, 2621);
        ContinuedFraction cf = ContinuedFraction.fromFraction(fraction);
        assertEquals("[3, 7, 15, 2, 5, 2]", cf.toString());
    }

    @Test
    public void fractionToTermsTest2() {
        Fraction fraction = new Fraction(-31, 25);
        ContinuedFraction cf = ContinuedFraction.fromFraction(fraction);
        assertEquals("[-1, -4, -6]", cf.toString());
    }

    @Test
    public void testInterval0() {
        Fraction l = new Fraction(28, 11),
            u = new Fraction(62, 23);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testInterval1() {
        Fraction l = new Fraction(743506299, 476597252),
            u = new Fraction(694166449, 402616793);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testInterval2() {
        Fraction l = new Fraction(694166449, 402616793),
            u = new Fraction(679334523, 389956898);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testInterval4() {
        Fraction l = new Fraction(1000000000, 999999999),
            u = new Fraction(1000000001, 999999999);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = bestcf.getFraction();
        assertEquals(500000001, best.getNumerator().longValue());
        assertEquals(500000000, best.getDenominator().longValue());
    }

    @Test
    public void testInterval5() {
        Fraction l = new Fraction(244304949, 202780255),
            u = new Fraction(832138971, 690620094);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testInterval6() {
        ContinuedFraction cfl = ContinuedFraction.fromFraction(new Fraction(0, 1)),
            cfu = null;
        assertThrows(IllegalArgumentException.class,
            () -> ContinuedFraction.bestWithinInterval(cfl, cfu));
    }

    @Test
    public void testInterval7() {
        ContinuedFraction cfl = ContinuedFraction.fromFraction(new Fraction(0, 1)),
            cfu = ContinuedFraction.fromFraction(new Fraction(0, 1));
        assertThrows(IllegalArgumentException.class,
            () -> ContinuedFraction.bestWithinInterval(cfl, cfu));
    }

    @Test
    public void testInterval8() {
        Fraction l = new Fraction(28, 11);
        Fraction u = new Fraction(62, 23);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testInterval9() {
        Fraction l = new Fraction(314155, 100000);
        Fraction u = new Fraction(314165, 100000);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervala() {
        Fraction l = new Fraction(55832999, 12709292);
        Fraction u = new Fraction(292647351, 60742699);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalb() {
        Fraction l = new Fraction(41, 726);
        Fraction u = new Fraction(1365591, 24037376);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalc() {
        Fraction l = new Fraction(29, 82);
        Fraction u = new Fraction(17106703, 48365568);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(l, u);
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervald() {
        Fraction l = new Fraction(297, 148);
        Fraction u = new Fraction(136249929, 67895296);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = Utils.bestWithinInterval(l, u);
        Fraction bestf = bestcf.getFraction();
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervale() {
        Fraction l = new Fraction(1, 2);
        Fraction u = new Fraction(184, 337);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = Utils.bestWithinInterval(l, u);
        Fraction bestf = bestcf.getFraction();
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalf() {
        Fraction l = new Fraction(35, 3);
        Fraction u = new Fraction(415, 22);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction best = Utils.bestWithinInterval(l, u);
        Fraction bestf = bestcf.getFraction();
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalg() {
        Fraction l = new Fraction(355, 179);
        Fraction u = new Fraction(33860834966898755L, 17073491434012672L);
        ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
            cfu = ContinuedFraction.fromFraction(u);
        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        assertEquals(89440508844L, bestf.getNumerator().longValue());
        assertEquals(45098172065L, bestf.getDenominator().longValue());
    }

    /**
     * Generate two random fractions and compute the best fraction between them.
     */
    @Test
    public void bestWithinIntervalRandomTest() {
        Random random = new Random();
        final int MAX = 1000, T = 100;
        for (int t = 0; t < T; t++) {
            Fraction l = new Fraction(1 + random.nextInt(MAX), 1 + random.nextInt(MAX)), u;
            do {
                u = new Fraction(1 + random.nextInt(MAX), 1 + random.nextInt(MAX));
            }
            while (l.compareTo(u) == 0);
            if (l.compareTo(u) > 0) {
                Fraction tmp = l;
                l = u;
                u = tmp;
            }
            // Run a few iterations to make the fractions close to each other.
            for (int k = 0; k < 15; k++) {
                u = l.add(u).divide(BigInteger.valueOf(2));
            }
            ContinuedFraction cfl = ContinuedFraction.fromFraction(l),
                cfu = ContinuedFraction.fromFraction(u);
            ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
            Fraction bestf = bestcf.getFraction();
            Fraction best = Utils.bestWithinInterval(l, u);
            System.err.println(String.format("%s%s - %s%s: %s%s",
                l.toString(), cfl.toString(), u.toString(), cfu.toString(), bestf.toString(), bestcf.toString()));
            assertEquals(best.getNumerator(), bestf.getNumerator());
            assertEquals(best.getDenominator(), bestf.getDenominator());
        }
    }
    
    @Test
    public void testIntervalFromTerms0() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(3L);
        cflTerms.add(7L);
        cflTerms.add(15L);
        cflTerms.add(2L);
        cflTerms.add(5L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(3L);
        cfuTerms.add(7L);
        cfuTerms.add(15L);
        cfuTerms.add(2L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms1() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(3L);
        cflTerms.add(7L);
        cflTerms.add(15L);
        cflTerms.add(2L);
        cflTerms.add(5L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(3L);
        cfuTerms.add(7L);
        cfuTerms.add(15L);
        cfuTerms.add(2L);
        cfuTerms.add(6L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [3, 7, 15, 2, 5, 2] = 8234/2621
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms2() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(3L);
        cflTerms.add(4L);
        cflTerms.add(4L);
        cflTerms.add(1L<<20);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(3L);
        cfuTerms.add(4L);
        cfuTerms.add(5L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [3, 4, 4, 2]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms2a() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(3L);
        cflTerms.add(4L);
        cflTerms.add(4L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(3L);
        cfuTerms.add(4L);
        cfuTerms.add(5L);
        cfuTerms.add(500L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [3, 4, 5]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms2b() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(3L);
        cflTerms.add(5L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(3L);
        cfuTerms.add(4L);
        cfuTerms.add(500L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [3, 4, 2]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms3() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(3L);
        cflTerms.add(5L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(3L);
        cfuTerms.add(4L);
        cfuTerms.add(1L<<20);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [3, 4, 2]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms3a() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(2L);
        cflTerms.add(2L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 2, 3]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms3b() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(2L);
        cflTerms.add(500L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 2, 501]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms3c() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(2L);
        cflTerms.add(3L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        cfuTerms.add(3L);
        cfuTerms.add(500L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 2, 3, 501]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms3d() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(2L);
        cflTerms.add(3L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        cfuTerms.add(3L);
        cfuTerms.add(500L);
        cfuTerms.add(1500L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 2, 3, 501]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms4() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(5L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 3]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms4a() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(3L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 2, 2]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms5() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(2L);
        cflTerms.add(2L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        cfuTerms.add(5L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 2, 3]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testIntervalFromTerms6() {
        List<Long> cflTerms = new ArrayList<Long>();
        cflTerms.add(1L);
        cflTerms.add(2L);
        cflTerms.add(2L);
        ContinuedFraction cfl = ContinuedFraction.fromLongTerms(cflTerms);

        List<Long> cfuTerms = new ArrayList<Long>();
        cfuTerms.add(1L);
        cfuTerms.add(2L);
        cfuTerms.add(3L);
        ContinuedFraction cfu = ContinuedFraction.fromLongTerms(cfuTerms);

        ContinuedFraction bestcf = ContinuedFraction.bestWithinInterval(cfl, cfu);
        Fraction bestf = bestcf.getFraction();
        // [1, 2, 2, 2]
        Fraction best = Utils.bestWithinInterval(cfl.getFraction(), cfu.getFraction());
        assertEquals(best.getNumerator(), bestf.getNumerator());
        assertEquals(best.getDenominator(), bestf.getDenominator());
    }

    @Test
    public void testExtension0() {
        ContinuedFraction cf = ContinuedFraction.fromFraction(new Fraction(31, 25));
        ContinuedFraction ex = cf.getExtendedContinuedFraction();
        assertEquals(cf.getFraction(), ex.getFraction());
        assertEquals("[1, 4, 5, 1]", ex.toString());
    }

    @Test
    public void testExtension1() {
        ContinuedFraction cf = ContinuedFraction.fromFraction(new Fraction(-31, 25));
        ContinuedFraction ex = cf.getExtendedContinuedFraction();
        assertEquals(cf.getFraction(), ex.getFraction());
        assertEquals("[-1, -4, -7, 1]", ex.toString());
    }

}
