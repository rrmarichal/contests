import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(252, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(0, result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(210, result);
    }

    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(270, result);
    }

}
