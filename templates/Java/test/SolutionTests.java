import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/test.in"));
        Solution<Integer> solution = new Solution<Integer>(in);
        Integer result = solution.solve();
        assertEquals(null, result);
    }

}
