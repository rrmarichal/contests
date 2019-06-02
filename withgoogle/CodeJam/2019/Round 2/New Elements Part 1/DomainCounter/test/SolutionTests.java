import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Random;

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
            "Case #3: 10",
            "Case #4: 20"
        }, result);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Only for debugging purposes")
    public void randomTest0() throws FileNotFoundException {
        final int T = 100, N = 42, MAX = 42;
        generate(T, N, MAX);
        InputReader in = new InputReader(new FileInputStream(getInputFileName(T, N, MAX)));
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in, out);
        solution.solve();
    }

    private void generate(int T, int N, int MAX) throws FileNotFoundException {
        Random r = new Random();
        PrintWriter pw = new PrintWriter(getInputFileName(T, N, MAX));
        pw.println(T);
        for (int t = 0; t < T; t++) {
            pw.println(N);
            for (int j = 0; j < N; j++) {
                pw.println(String.format("%d %d", 1 + r.nextInt(MAX), 1 + r.nextInt(MAX)));
            }
        }
        pw.close();
    }

    private String getInputFileName(int T, int N, int MAX) {
        return String.format("test_data/random_%d_%d_%d.in", T, N, MAX);
    }

}
