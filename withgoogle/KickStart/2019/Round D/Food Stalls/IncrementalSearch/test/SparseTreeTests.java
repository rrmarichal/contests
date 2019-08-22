import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SparseTreeTests {

    @Test
	public void test0() {
		long[] values = new long[] { 10, -2, 3, 10, 0, -15, 8, 4 };
		SparseTree tree = new SparseTree(values);
		assertEquals(5, tree.minIndex(0, values.length - 1));
		assertEquals(1, tree.minIndex(0, 4));
		assertEquals(7, tree.minIndex(6, 7));
		assertEquals(4, tree.minIndex(2, 4));
	}

	@Test
	public void test1() {
		long[] values = new long[] { 10, -2, 3, 10, 0, -15, 8, 4, 10, 0, -13, 8, 4 };
		SparseTree tree = new SparseTree(values);
		assertEquals(5, tree.minIndex(0, values.length - 1));
		assertEquals(10, tree.minIndex(6, values.length - 1));
	}

}
