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
            "Case #1: 14",
            "Case #2: 0",
            "Case #3: 6"
        }, result);
    }

}
