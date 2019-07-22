package sparsetree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class SparseTreeTests {

	@Test
	public void test0() {
		List<Integer> values = Arrays.asList(new Integer[] { 1, 2, 3 });
		SparseTree<Integer> tree = SparseTree.create(
			values,
			new IntegerMinOperation()
		);
		NodeInfo<Integer> min = tree.query(0, values.size() - 1);
		assertEquals(1, (int) min.value);
		assertEquals(0, min.index);
	}

	@Test
	public void test1() {
		List<Integer> values = Arrays.asList(new Integer[] { 1, 2, -3, 4, 10, -10, 11, 21 });
		SparseTree<Integer> tree = SparseTree.create(
			values,
			new IntegerMinOperation()
		);
		NodeInfo<Integer> min = tree.query(0, values.size() - 1);
		assertEquals(-10, (int) min.value);
		assertEquals(5, min.index);

		min = tree.query(0, 4);
		assertEquals(-3, (int) min.value);
		assertEquals(2, min.index);

		min = tree.query(6, values.size() - 1);
		assertEquals(11, (int) min.value);
		assertEquals(6, min.index);
	}

	@Test
	public void randomTest() {
		final int T = 100, L = 1000;
		final Random random = new Random();
		List<Integer> list = new ArrayList<Integer>(L);
		for (int j = 0; j < L; j++) {
			list.add(random.nextInt());
		}
		SparseTree<Integer> tree = SparseTree.create(list, new IntegerMaxOperation());
		for (int t = 0; t < T; t++) {
			int l = random.nextInt(L),
				r = random.nextInt(L);
			if (l > r) {
				int tmp = l;
				l = r;
				r = tmp;
			}
			assertEquals(_bruteForceRMQ(list, l, r), tree.query(l, r));
		}
	}

	private NodeInfo<Integer> _bruteForceRMQ(List<Integer> list, int l, int r) {
		int max = list.get(l), index = l;
		for (int j = l + 1; j <= r; j++) {
			if (max < list.get(j)) {
				max = list.get(j);
				index = j;
			}
		}
		return new NodeInfo<Integer>(max, index);
	}

}
