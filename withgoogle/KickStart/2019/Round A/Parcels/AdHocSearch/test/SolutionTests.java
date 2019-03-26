import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class SolutionTests {

    /**
    3
    3 3
    101
    000
    101
    1 2
    11
    5 5
    10001
    00000
    00000
    00000
    10001
     */
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

    /**
    6
    5 6
    100000
    000000
    000000
    000000
    000000
    1 1
    1
    1 1
    0
    2 2
    00
    00
    2 2
    11
    11
    3 3
    111
    111
    110
     */
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

    /**
    2
    1 10
    0010000000
    1 11
    00100000000
     */
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
    
    /**
    6
    10 10
    1111100111
    0110101011
    0111100100
    1001111011
    1010011110
    0101000110
    0001101000
    0111100101
    0001001110
    0111011100
    9 14
    10010011001001
    00000000000000
    00000000000100
    00000000000100
    00001000000000
    00000000000000
    00000001000000
    00000001000000
    10000000000000
    9 7
    1001001
    0000000
    0011110
    0000000
    0000100
    0001100
    1111111
    0000000
    1111111
    12 56
    10010011001001100100110010011001001100100110010011001001
    00000000000000100100110010011001001100100110010011001001
    00000000000100000000000001000000000000010000000000000100
    00000000000100000011111000000101010000011111000000101010
    00001000000000000011111000000101010000011111000000101010
    00000000000000100100110010011001001100100110010011001001
    00000001000000000000010000001111100000010101010101010100
    10000000000000000000010000001111100000010101000000001000
    00000001000000111110000001010100000000100000011111000000
    10000000000000000000010000001111100000010101000000001000
    10000000000000000000010000001111100000010101000000001000
    00000001000000111110000001010100000000100000011111000000
    12 1
    1
    0
    0
    0
    0
    1
    1
    0
    1
    0
    0
    0
    10 10
    1111110111
    1111110111
    1101111111
    1111111111
    1111111111
    1111111111
    1111111110
    1111110111
    1111111110
    1111111110
    1111111110
     */
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
