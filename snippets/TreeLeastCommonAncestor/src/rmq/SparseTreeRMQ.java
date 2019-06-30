package rmq;

import java.util.ArrayList;
import java.util.List;

public class SparseTreeRMQ<T extends Comparable<T>> {

	private int size;
	private List<List<T>> tree;

	public SparseTreeRMQ(List<T> list) {
		if (list == null) {
			throw new IllegalArgumentException("null list.");
		}
		_buildTree(list);
	}

	private T _min(T x, T y) {
		if (x.compareTo(y) < 0) {
			return x;
		}
		return y;
	}

	private void _buildTree(List<T> list) {
		size = list.size();
		tree = new ArrayList<List<T>>(size);
		for (int j = 0; j < size; j++) {
			tree.add(new ArrayList<T>());
			tree.get(j).add(list.get(j));
		}
		for (int k = 1; (1 << k) <= size; k++) {
			tree.add(new ArrayList<T>());
			for (int j = 0; j + (1 << k) <= size; j++) {
				tree.get(j).add(_min(
					tree.get(j).get(k - 1),
					tree.get(j + (1 << (k - 1))).get(k - 1)
				));
			}
		}
	}

	public T query(int l, int r) {
		if (l > r) {
			throw new IllegalArgumentException("l > r.");
		}
		if (l < 0) {
			throw new IllegalArgumentException("l < 0.");
		}
		if (r >= size) {
			throw new IllegalArgumentException("r >= size.");
		}
		return _query(l, r);
	}

	private int _lg2(int x) {
		return (int) (Math.log(x) / Math.log(2));
	}

	private T _query(int l, int r) {
		int k = _lg2(r - l + 1);
		T x = tree.get(l).get(k);
		T y = tree.get(r - (1 << k) + 1).get(k);
		return _min(x, y);
	}

}
