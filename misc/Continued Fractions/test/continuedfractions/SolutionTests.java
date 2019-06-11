package continuedfractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import continuedfractions.InputReader;
import continuedfractions.Solution;
import continuedfractions.ContinuedFraction;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        ContinuedFraction[] result = solution.solve();
        assertEquals("[3, 4, 12, 4]", result[0].toString());
        assertEquals("[0, 3, 4, 12, 4]", result[1].toString());
    }

    @Test
    public void test1() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/1.in"));
        Solution solution = new Solution(in);
        ContinuedFraction[] result = solution.solve();
        assertEquals("[0]", result[0].toString());
        assertEquals("[0]", result[1].toString());
        assertEquals("[0, 1000000007]", result[2].toString());
        assertEquals("[0, 84, 1, 2, 1, 7, 29]", result[3].toString());
    }

    @Test
    public void test2() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/2.in"));
        Solution solution = new Solution(in);
        ContinuedFraction[] result = solution.solve();
        assertEquals("[0, 1, 5, 2, 2]", result[0].toString());
        assertEquals("[3, 7, 15, 2, 7, 1, 4, 2]", result[1].toString());
        assertEquals("[3, 7, 16, 1, 3, 4, 2, 4]", result[2].toString());
    }

}
