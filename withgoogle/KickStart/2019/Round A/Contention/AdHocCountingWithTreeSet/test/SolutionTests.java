import static org.junit.Assert.*;

import java.io.*;
import java.util.Random;

import org.junit.*;

public class SolutionTests {

    /**
    3
    5 3
    1 2
    3 4
    2 5
    30 3
    10 11
    10 10
    11 11
    10 4
    1 8
    4 5
    3 6
    2 7
     */
    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 1",
            "Case #2: 0",
            "Case #3: 2"
        }, result);
    }

    /**
    2
    8 3
    4 5
    3 6
    2 7
    1 3
    1 1
    1 1
    1 1
     */
    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2",
            "Case #2: 0"
        }, result);
    }

    /**
    1
    20 3
    2 8
    4 8
    3 20
     */
    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2"
        }, result);
    }

    /**
    1
    10 3
    1 5
    6 10
    3 8
     */
    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2"
        }, result);
    }

    /**
    1
    6 2
    1 5
    4 6
     */
    @Test
    public void test4() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/4.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 3"
        }, result);
    }

    /**
    2
    20 4
    5 5
    10 11
    13 18
    19 19
    10 3
    4 5
    7 9
    9 9
     */
    @Test
    public void test5() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/5.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 1",
            "Case #2: 1"
        }, result); 
    }

    @Test
    public void testBig0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/big0.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 53"
        }, result);
    }

    @Test
    public void randomTest() throws FileNotFoundException {
        generate("test_data/small.in", 100, 40, 5);
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/small.in")));
        BruteForceSolution bfSolution = new BruteForceSolution(new InputReader(new FileInputStream("test_data/small.in")));
        String[] result = solution.solve();
        String[] bfResult = bfSolution.solve();
        assertArrayEquals(bfResult, result);
    }

    private void generate(String fileName, int T, int N, int Q) throws FileNotFoundException {
        Random r = new Random();
        PrintWriter pw = new PrintWriter(fileName);
        pw.println(T);
        for (; T > 0; T--) {
            pw.println(String.format("%d %d", N, Q));
            for (int j = 0; j < Q; j++) {
                int s = r.nextInt(N);
                pw.println(String.format("%d %d", s + 1, s + 1 + r.nextInt(N - s)));
            }
        }
        pw.close();
    }

}
