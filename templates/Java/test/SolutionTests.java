import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        int result = solution.solve();
        assertEquals(0, result);
    }

}
