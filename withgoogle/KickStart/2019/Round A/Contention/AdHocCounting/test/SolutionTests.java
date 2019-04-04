import static org.junit.Assert.*;

import java.io.*;
import java.util.Random;

import org.junit.*;

public class SolutionTests {

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

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2"
        }, result);
    }

    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2"
        }, result);
    }

    @Test
    public void test4() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/4.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 3"
        }, result);
    }

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
        // generate("test_data/small.in", 50, 150);
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/small.in")));
        BruteForceSolution bfSolution = new BruteForceSolution(new InputReader(new FileInputStream("test_data/small.in")));
        String[] result = solution.solve();
        String[] bfResult = bfSolution.solve();
        // printResult(result);
        assertArrayEquals(bfResult, result);
    }

    @SuppressWarnings("unused")
    private void printResult(String[] result) {
        for (String s: result) {
            System.out.println(String.format("\"%s\",", s));
        }
    }

    @SuppressWarnings("unused")
    private void generate(String fileName, int T, int N) throws FileNotFoundException {
        Random r = new Random();
        PrintWriter pw = new PrintWriter(fileName);
        pw.println(T);
        for (; T > 0; T--) {
            int Q = 1 + r.nextInt(Math.min(N / 10, 9));
            pw.println(String.format("%d %d", N, Q));
            for (int j = 0; j < Q; j++) {
                int s = r.nextInt(N);
                pw.println(String.format("%d %d", s + 1, s + 1 + r.nextInt(N - s)));
            }
        }
        pw.close();
    }

}
