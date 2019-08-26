import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class SolutionTests {

    @Test
    public void test0() throws FileNotFoundException {
        InputReader in = new InputReader(new FileInputStream("test_data/0.in"));
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        assertArrayEquals(new String[] {
            "Case #1: YYNY",
            "Case #2: Y"
        }, result);
	}
	
	@Test
    public void testBinarySearch()  {
		int[] A = new int[] { 1, 4, 10, 55, 100 };
		int index = Arrays.binarySearch(A, 101);
		index = -index - 1;
		assertEquals(5, index);

		index = Arrays.binarySearch(A, -1);
		index = -index - 1;
		assertEquals(0, index);

		index = Arrays.binarySearch(A, 10);
		assertEquals(2, index);

		index = Arrays.binarySearch(A, 20);
		index = -index - 1;
		assertEquals(3, index);
    }

}
