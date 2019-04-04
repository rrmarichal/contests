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
            "Case #1: 1",
            "Case #2: 0",
            "Case #3: 2"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 4",
            "Case #2: 0",
            "Case #3: 0",
            "Case #4: 2",
            "Case #5: 0",
            "Case #6: 0"
        }, result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2",
            "Case #2: 3"
        }, result);
    }
    
    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2",
            "Case #2: 4",
            "Case #3: 2",
            "Case #4: 4",
            "Case #5: 2",
            "Case #6: 1"
        }, result);
    }

}
