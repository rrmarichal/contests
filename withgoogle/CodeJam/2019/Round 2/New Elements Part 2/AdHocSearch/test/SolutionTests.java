import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/0.in")));
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2 1",
            "Case #2: IMPOSSIBLE",
            "Case #3: 1 1"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/1.in")));
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 1 25",
            "Case #2: 2 1"
        }, result);
    }

}
