import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2 1",
            "Case #2: IMPOSSIBLE",
            "Case #3: 1 1"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 1 25",
            "Case #2: 2 1"
        }, result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 8 29",
            "Case #2: 2 9",
            "Case #3: 83 100"
        }, result);
    }

    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 3 8",
            "Case #2: 2 5",
            "Case #3: 5 9",
            "Case #4: 50 1",
            "Case #5: 1 2",
            "Case #6: 3 2",
            "Case #7: 5 4",
            "Case #8: 14 9",
            "Case #9: 7 6",
            "Case #10: 1 1",
            "Case #11: 12 13",
            "Case #12: 2 3",
            "Case #13: 3 4",
            "Case #14: 3 5",
            "Case #15: 4 5",
            "Case #16: 4 7",
            "Case #17: 1 4",
            "Case #18: 8 29",
            "Case #19: 1 3",
            "Case #20: 1 5",
            "Case #21: 1 6",
            "Case #22: 1 10",
            "Case #23: 2 1",
            "Case #24: 18 7",
            "Case #25: 3 1",
            "Case #26: 11 1",
            "Case #27: 6 1",
            "Case #28: 4 1",
            "Case #29: 1 25",
            "Case #30: 1 112"
        }, result);
    }

    @Test
    public void test4() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/4.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 83 100",
            "Case #2: 1 22",
            "Case #3: 3 5",
            "Case #4: 2 3",
            "Case #5: 11 19",
            "Case #6: 3 4",
            "Case #7: 5 7",
            "Case #8: 4 5",
            "Case #9: 10 13",
            "Case #10: 35 61",
            "Case #11: 14 29",
            "Case #12: 1 2",
            "Case #13: 3 7",
            "Case #14: 7 17",
            "Case #15: 2 9",
            "Case #16: 1 3",
            "Case #17: 2 5",
            "Case #18: 1 5",
            "Case #19: 4 3",
            "Case #20: 1 1",
            "Case #21: 7 5",
            "Case #22: 3 2",
            "Case #23: 7 4",
            "Case #24: 5 2",
            "Case #25: 3 1",
            "Case #26: 11 6",
            "Case #27: 2 1",
            "Case #28: 17 8",
            "Case #29: 5 1",
            "Case #30: 6 1",
            "Case #31: 4 1",
            "Case #32: 16 5",
            "Case #33: 7 2",
            "Case #34: 11 3",
            "Case #35: 32 1",
            "Case #36: 3128 1"
        }, result);
    }

}
