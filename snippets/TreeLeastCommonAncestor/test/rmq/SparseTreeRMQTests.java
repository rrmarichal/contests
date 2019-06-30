package rmq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SparseTreeRMQTests {

	@Test
	public void test0() {
		Integer[] items = new Integer[] { 5 };
		SparseTreeRMQ<Integer> tree = new SparseTreeRMQ<Integer>(Arrays.asList(items));
		Assertions.assertEquals(5, tree.query(0, items.length - 1));
	}

	@Test
	public void test1() {
		Integer[] items = new Integer[] { 5, 1, 10, 2, 9, 19, -7, 10, 21, 4 };
		SparseTreeRMQ<Integer> tree = new SparseTreeRMQ<Integer>(Arrays.asList(items));
		Assertions.assertEquals(-7, tree.query(0, items.length - 1));
	}

	@Test
	public void randomTest() {
		final int T = 100, L = 1000;
		final Random random = new Random();
		List<Integer> list = new ArrayList<Integer>(L);
		for (int j = 0; j < L; j++) {
			list.add(random.nextInt());
		}
		SparseTreeRMQ<Integer> tree = new SparseTreeRMQ<Integer>(list);
		for (int t = 0; t < T; t++) {
			int l = random.nextInt(L),
				r = random.nextInt(L);
			if (l > r) {
				int tmp = l;
				l = r;
				r = tmp;
			}
			Assertions.assertEquals(_bruteForceRMQ(list, l, r), tree.query(l, r));
		}
	}

	private int _bruteForceRMQ(List<Integer> list, int l, int r) {
		int min = list.get(l);
		for (int j = l + 1; j <= r; j++) {
			if (min > list.get(j)) {
				min = list.get(j);
			}
		}
		return min;
	}

}
