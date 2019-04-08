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
            "Case #1: 1 3",
            "Case #2: 910 30",
            "Case #3: 1111 3333"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 500010110921937183789127831728371251137651672300100 30000000000000000000000000000000300000000000000"
        }, result);
    }

}
