import static org.junit.Assert.*;

import java.io.*;
import java.math.BigInteger;

import org.junit.*;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(1, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(2, result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(333333338, result);
    }

    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(500000006, result);
    }

    @Test
    public void test4() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/4.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(534174612, result);
    }

    @Test
    public void test5() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/5.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(318181823, result);
    }

    @Test
    public void test6() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/6.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(63822579, result);
    }

    @Test
    public void test7() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/7.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(135709439, result);
    }

    @Test
    public void modularInverseTest() {
        ExtendedEuclidResult r = Utils.extendedEuclid(BigInteger.valueOf(3), Solution.MODULO);
        assertEquals("1", r.gcd.toString());
        assertEquals("333333336", r.x.toString());
        assertEquals("-1", r.y.toString());
    }

    @Test
    public void fractionAdditionTest0() {
        Fraction f = new Fraction(1, 1);
        Fraction g = new Fraction(1, 1);
        Fraction s = f.add(g);
        assertEquals("2", s.p.toString());
        assertEquals("1", s.q.toString());
    }

    @Test
    public void fractionAdditionTest1() {
        Fraction f = new Fraction(4, 2);
        Fraction g = new Fraction(4, 3);
        Fraction h = new Fraction(-2, 1);
        Fraction s = f.add(g).add(h);
        assertEquals("4", s.p.toString());
        assertEquals("3", s.q.toString());
    }

}
