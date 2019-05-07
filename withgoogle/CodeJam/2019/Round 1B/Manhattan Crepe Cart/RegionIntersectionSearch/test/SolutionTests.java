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
            "Case #1: 0 6",
            "Case #2: 2 5",
            "Case #3: 0 4"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 6 6",
            "Case #2: 0 0",
            "Case #3: 6 5",
            "Case #3: 10 10"
        }, result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2 2"
        }, result);
    }

    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2 0",
            "Case #2: 8 6",
            "Case #3: 0 3",
            "Case #4: 0 5",
            "Case #5: 0 9",
            "Case #6: 0 4",
            "Case #7: 0 0",
            "Case #8: 10 0",
            "Case #9: 4 4",
            "Case #10: 9 10",
            "Case #11: 0 0",
            "Case #12: 6 8",
            "Case #13: 0 0",
            "Case #14: 7 0",
            "Case #15: 3 10",
            "Case #16: 0 9",
            "Case #17: 0 0",
            "Case #18: 7 6",
            "Case #19: 4 9",
            "Case #20: 0 0",
            "Case #21: 9 10",
            "Case #22: 5 0",
            "Case #23: 10 2",
            "Case #24: 10 3",
            "Case #25: 10 10",
            "Case #26: 7 9",
            "Case #27: 5 4",
            "Case #28: 2 10",
            "Case #29: 3 6",
            "Case #30: 0 0",
            "Case #31: 0 10",
            "Case #32: 10 2",
            "Case #33: 2 4",
            "Case #34: 0 0",
            "Case #35: 5 5",
            "Case #36: 3 7",
            "Case #37: 4 4",
            "Case #38: 3 0",
            "Case #39: 9 0",
            "Case #40: 0 0",
            "Case #41: 0 8",
            "Case #42: 10 0",
            "Case #43: 0 0",
            "Case #44: 10 10",
            "Case #45: 4 0",
            "Case #46: 0 0",
            "Case #47: 0 0",
            "Case #48: 4 0",
            "Case #49: 10 4",
            "Case #50: 0 5",
            "Case #51: 0 0",
            "Case #52: 6 0",
            "Case #53: 4 0",
            "Case #54: 5 0",
            "Case #55: 0 5",
            "Case #56: 10 7",
            "Case #57: 5 0",
            "Case #58: 0 3",
            "Case #59: 0 0",
            "Case #60: 0 4",
            "Case #61: 0 0",
            "Case #62: 8 0",
            "Case #63: 0 10",
            "Case #64: 4 10",
            "Case #65: 7 9",
            "Case #66: 10 2",
            "Case #67: 0 0",
            "Case #68: 4 3",
            "Case #69: 0 0",
            "Case #70: 10 0",
            "Case #71: 0 0",
            "Case #72: 0 0",
            "Case #73: 0 0",
            "Case #74: 7 10",
            "Case #75: 7 0",
            "Case #76: 8 10",
            "Case #77: 0 0",
            "Case #78: 0 10",
            "Case #79: 5 7",
            "Case #80: 0 0",
            "Case #81: 0 0",
            "Case #82: 0 7",
            "Case #83: 2 0",
            "Case #84: 2 5",
            "Case #85: 7 0",
            "Case #86: 0 9",
            "Case #87: 7 8",
            "Case #88: 0 0",
            "Case #89: 5 0",
            "Case #90: 0 4",
            "Case #91: 5 10",
            "Case #92: 10 8",
            "Case #93: 10 9",
            "Case #94: 0 9",
            "Case #95: 0 0",
            "Case #96: 0 3",
            "Case #97: 0 0",
            "Case #98: 0 5",
            "Case #99: 7 7",
            "Case #100: 0 0"
        }, result);
    }

}
