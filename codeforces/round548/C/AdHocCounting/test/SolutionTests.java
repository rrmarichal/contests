import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    /**
    4 4
    1 2 1
    2 3 1
    3 4 1
    */
    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(252, result);
    }

    /**
    4 6
    1 2 0
    1 3 0
    1 4 0
    */
    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(0, result);
    }

    /**
    3 5
    1 2 1
    2 3 0
    */
    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(210, result);
    }

    /**
    7 3
    1 2 0
    2 4 0
    2 6 0
    6 5 1
    6 3 1
    3 7 0
    */
    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        long result = solution.solve();
        assertEquals(270, result);
    }

}
