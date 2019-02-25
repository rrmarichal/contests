package contests.ioi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void sieveTest0() {
        ArrayList<Integer> primes = app.sieve(10);
        assertEquals(4, primes.size());
    }

    @Test
    public void sieveTest1() {
        ArrayList<Integer> primes = app.sieve(489);
        assertEquals(93, primes.size());
    }

    @Test
    public void sieveTest2() {
        ArrayList<Integer> primes = app.sieve(1000000);
        assertEquals(78498, primes.size());
    }

    @Test
    public void factorize10() {
        int K = 10;
        ArrayList<Integer> primes = app.sieve(K);
        ArrayList<Long> pfactors = app.factorize(primes, K);
        assertEquals(pfactors.size(), 2);
        assertEquals((long) pfactors.get(0), 2);
        assertEquals((long) pfactors.get(1), 5);
    }

    @Test
    public void factorize12() {
        int K = 12;
        ArrayList<Integer> primes = app.sieve(K);
        ArrayList<Long> pfactors = app.factorize(primes, K);
        assertEquals(pfactors.size(), 2);
        assertEquals((long) pfactors.get(0), 2);
        assertEquals((long) pfactors.get(1), 3);
    }

    @Test
    public void factorize210() {
        int K = 210;
        ArrayList<Integer> primes = app.sieve(K);
        ArrayList<Long> pfactors = app.factorize(primes, K);
        assertEquals(pfactors.size(), 4);
        assertEquals((long) pfactors.get(0), 2);
        assertEquals((long) pfactors.get(1), 3);
        assertEquals((long) pfactors.get(2), 5);
        assertEquals((long) pfactors.get(3), 7);
    }

    @Test
    public void inclusionExclusionCountert1() {
        int K = 1;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(1, prel);
    }

    @Test
    public void inclusionExclusionCountert2() {
        int K = 2;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(1, prel);
    }

    @Test
    public void inclusionExclusionCountert3() {
        int K = 3;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(2, prel);
    }

    @Test
    public void inclusionExclusionCountert4() {
        int K = 4;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(2, prel);
    }


    @Test
    public void inclusionExclusionCountert5() {
        int K = 5;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(4, prel);
    }

    @Test
    public void inclusionExclusionCountert12() {
        int K = 12;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(4, prel);
    }

    @Test
    public void inclusionExclusionCounter30() {
        int K = 30;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(8, prel);
    }

    @Test
    public void inclusionExclusionCounter489() {
        int K = 489;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(324, prel);
    }

    @Test
    public void inclusionExclusionCounter10_12() {
        long K = 1000000000000L;
        ArrayList<Integer> primes = app.sieve(1000000);
        long prel = app.inclusionExclusionCounter(primes, K);
        assertEquals(400000000000L, prel);
    }

}
