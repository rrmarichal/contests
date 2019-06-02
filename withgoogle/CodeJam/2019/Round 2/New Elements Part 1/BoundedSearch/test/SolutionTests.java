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

}
