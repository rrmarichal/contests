import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2",
            "Case #2: 2",
            "Case #3: 1"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 4"
        }, result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2",
            "Case #2: 2",
            "Case #3: 12",
            "Case #4: 22"
        }, result);
    }

    @Test
    public void testRandom_1_8() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/random_1_8.in"));
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 21"
        }, result);
    }

    @Test
    public void testRandom_1_10() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/random_1_10.in"));
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 26"
        }, result);
    }

}
