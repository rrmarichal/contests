import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: CJQUIZKNOWBEVYOFDPFLUXALGORITHMS",
            "Case #2: SUBDERMATOGLYPHICFJKNQVWXZ"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: BCJQUIZKNOWBEVYOFDPFLUXALGORITHMS",
            "Case #2: CBCJQUIZKNOWBEVYOFDPFLUXALGORITHMS",
            "Case #3: ACBCJQUIZKNOWBEVYOFDPFLUXALGORITHMS"
        }, result);
    }

}
