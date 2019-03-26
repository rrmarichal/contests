import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    /**
    3
    4 3
    3 1 9 100
    6 2
    5 5 1 2 3 4
    5 5
    7 7 1 7 7
    */
    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 14",
            "Case #2: 0",
            "Case #3: 6"
        }, result);
    }

}
