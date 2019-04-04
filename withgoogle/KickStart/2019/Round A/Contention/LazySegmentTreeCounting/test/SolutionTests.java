import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/0.in")));
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 1",
            "Case #2: 2",
            "Case #3: 0"
        }, result);
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2",
            "Case #2: 0"
        }, result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2"
        }, result);
    }

    @Test
    public void test3() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/3.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2"
        }, result);
    }

    @Test
    public void test4() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/4.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 3"
        }, result);
    }

    @Test
    public void test5() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/5.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 1",
            "Case #2: 1"
        }, result); 
    }

    @Test
    public void test6() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/6.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 4",
            "Case #2: 1"
        }, result); 
    }

    @Test
    public void testBig0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/big0.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 53"
        }, result);
    }

    @Test
    public void testSmall0() throws FileNotFoundException {
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/small0.in")));
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 2",
            "Case #2: 2",
            "Case #3: 1",
            "Case #4: 0",
            "Case #5: 1",
            "Case #6: 1",
            "Case #7: 1",
            "Case #8: 2",
            "Case #9: 1",
            "Case #10: 4",
            "Case #11: 1",
            "Case #12: 1",
            "Case #13: 2",
            "Case #14: 3",
            "Case #15: 4",
            "Case #16: 2",
            "Case #17: 1",
            "Case #18: 2",
            "Case #19: 1",
            "Case #20: 1",
            "Case #21: 1",
            "Case #22: 1",
            "Case #23: 2",
            "Case #24: 2",
            "Case #25: 2",
            "Case #26: 2",
            "Case #27: 1",
            "Case #28: 1",
            "Case #29: 1",
            "Case #30: 1",
            "Case #31: 1",
            "Case #32: 2",
            "Case #33: 6",
            "Case #34: 1",
            "Case #35: 1",
            "Case #36: 3",
            "Case #37: 1",
            "Case #38: 5",
            "Case #39: 1",
            "Case #40: 3",
            "Case #41: 5",
            "Case #42: 2",
            "Case #43: 2",
            "Case #44: 4",
            "Case #45: 2",
            "Case #46: 4",
            "Case #47: 2",
            "Case #48: 1",
            "Case #49: 1",
            "Case #50: 1",
            "Case #51: 5",
            "Case #52: 1",
            "Case #53: 2",
            "Case #54: 2",
            "Case #55: 2",
            "Case #56: 1",
            "Case #57: 1",
            "Case #58: 1",
            "Case #59: 2",
            "Case #60: 3",
            "Case #61: 1",
            "Case #62: 1",
            "Case #63: 4",
            "Case #64: 1",
            "Case #65: 1",
            "Case #66: 1",
            "Case #67: 2",
            "Case #68: 4",
            "Case #69: 1",
            "Case #70: 1",
            "Case #71: 1",
            "Case #72: 2",
            "Case #73: 1",
            "Case #74: 3",
            "Case #75: 3",
            "Case #76: 1",
            "Case #77: 2",
            "Case #78: 0",
            "Case #79: 3",
            "Case #80: 1",
            "Case #81: 1",
            "Case #82: 1",
            "Case #83: 3",
            "Case #84: 2",
            "Case #85: 2",
            "Case #86: 0",
            "Case #87: 3",
            "Case #88: 2",
            "Case #89: 1",
            "Case #90: 3",
            "Case #91: 3",
            "Case #92: 2",
            "Case #93: 2",
            "Case #94: 3",
            "Case #95: 2",
            "Case #96: 3",
            "Case #97: 1",
            "Case #98: 1",
            "Case #99: 0",
            "Case #100: 1"
        }, result);
    }

    @Test
    public void testSmall1() throws FileNotFoundException {
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/small1.in")));
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 3",
            "Case #2: 4",
            "Case #3: 1",
            "Case #4: 4",
            "Case #5: 4",
            "Case #6: 2",
            "Case #7: 0",
            "Case #8: 3",
            "Case #9: 3",
            "Case #10: 1",
            "Case #11: 1",
            "Case #12: 2",
            "Case #13: 2",
            "Case #14: 1",
            "Case #15: 3",
            "Case #16: 2",
            "Case #17: 1",
            "Case #18: 1",
            "Case #19: 1",
            "Case #20: 4",
            "Case #21: 2",
            "Case #22: 1",
            "Case #23: 2",
            "Case #24: 2",
            "Case #25: 7",
            "Case #26: 2",
            "Case #27: 1",
            "Case #28: 0",
            "Case #29: 4",
            "Case #30: 1",
            "Case #31: 1",
            "Case #32: 2",
            "Case #33: 2",
            "Case #34: 0",
            "Case #35: 1",
            "Case #36: 6",
            "Case #37: 1",
            "Case #38: 3",
            "Case #39: 1",
            "Case #40: 2",
            "Case #41: 1",
            "Case #42: 1",
            "Case #43: 1",
            "Case #44: 1",
            "Case #45: 2",
            "Case #46: 2",
            "Case #47: 0",
            "Case #48: 1",
            "Case #49: 2",
            "Case #50: 1",
            "Case #51: 1",
            "Case #52: 1",
            "Case #53: 1",
            "Case #54: 1",
            "Case #55: 1",
            "Case #56: 2",
            "Case #57: 2",
            "Case #58: 2",
            "Case #59: 1",
            "Case #60: 2",
            "Case #61: 1",
            "Case #62: 0",
            "Case #63: 1",
            "Case #64: 0",
            "Case #65: 1",
            "Case #66: 3",
            "Case #67: 1",
            "Case #68: 3",
            "Case #69: 3",
            "Case #70: 6",
            "Case #71: 3",
            "Case #72: 1",
            "Case #73: 3",
            "Case #74: 1",
            "Case #75: 1",
            "Case #76: 5",
            "Case #77: 1",
            "Case #78: 1",
            "Case #79: 2",
            "Case #80: 1",
            "Case #81: 0",
            "Case #82: 3",
            "Case #83: 1",
            "Case #84: 1",
            "Case #85: 1",
            "Case #86: 1",
            "Case #87: 4",
            "Case #88: 1",
            "Case #89: 3",
            "Case #90: 1",
            "Case #91: 1",
            "Case #92: 3",
            "Case #93: 1",
            "Case #94: 1",
            "Case #95: 2",
            "Case #96: 3",
            "Case #97: 1",
            "Case #98: 1",
            "Case #99: 5",
            "Case #100: 1"
        }, result);
    }

    @Test
    public void testSmall2() throws FileNotFoundException {
        Solution solution = new Solution(new InputReader(new FileInputStream("test_data/small2.in")));
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: 9",
            "Case #2: 16",
            "Case #3: 3",
            "Case #4: 1",
            "Case #5: 11",
            "Case #6: 34",
            "Case #7: 2",
            "Case #8: 28",
            "Case #9: 4",
            "Case #10: 2",
            "Case #11: 12",
            "Case #12: 2",
            "Case #13: 2",
            "Case #14: 1",
            "Case #15: 10",
            "Case #16: 98",
            "Case #17: 6",
            "Case #18: 59",
            "Case #19: 1",
            "Case #20: 1",
            "Case #21: 3",
            "Case #22: 2",
            "Case #23: 2",
            "Case #24: 4",
            "Case #25: 2",
            "Case #26: 3",
            "Case #27: 16",
            "Case #28: 13",
            "Case #29: 3",
            "Case #30: 6",
            "Case #31: 21",
            "Case #32: 2",
            "Case #33: 23",
            "Case #34: 8",
            "Case #35: 1",
            "Case #36: 2",
            "Case #37: 62",
            "Case #38: 5",
            "Case #39: 1",
            "Case #40: 3",
            "Case #41: 5",
            "Case #42: 1",
            "Case #43: 2",
            "Case #44: 15",
            "Case #45: 1",
            "Case #46: 3",
            "Case #47: 7",
            "Case #48: 0",
            "Case #49: 1",
            "Case #50: 4"
        }, result);
    }

}
