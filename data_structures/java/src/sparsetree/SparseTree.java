package sparsetree;

import java.util.ArrayList;
import java.util.List;

public class SparseTree<T> {

    private static final boolean CHECKED = true;

	public static <T> SparseTree<T> create(List<T> values, SparseTreeOperation<T> operation) {
		if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
        return new SparseTree<T>(values, operation);
	}

	private int size;
	private List<List<NodeInfo<T>>> tree;
	private SparseTreeOperation<T> operation;
	
	private SparseTree(List<T> values, SparseTreeOperation<T> operation) {
        this.operation = operation;
		this.size = values.size();
		_buildTree(values);
	}

	private void _buildTree(List<T> list) {
		tree = new ArrayList<List<NodeInfo<T>>>(size);
		for (int j = 0; j < size; j++) {
			tree.add(new ArrayList<NodeInfo<T>>());
			tree.get(j).add(new NodeInfo<T>(list.get(j), j));
		}
		for (int k = 1; (1 << k) <= size; k++) {
			tree.add(new ArrayList<NodeInfo<T>>());
			for (int j = 0; j + (1 << k) <= size; j++) {
				tree.get(j).add(operation.aggregate(
					tree.get(j).get(k - 1),
					tree.get(j + (1 << (k - 1))).get(k - 1)
				));
			}
		}
	}

	private NodeInfo<T> _query(int l, int r) {
		int k = (int) (Math.log(r - l + 1) / Math.log(2));
		NodeInfo<T> x = tree.get(l).get(k);
		NodeInfo<T> y = tree.get(r - (1 << k) + 1).get(k);
		return operation.aggregate(x, y);
	}
	
	public NodeInfo<T> query(int l, int r) {
		if (CHECKED && l > r) {
			throw new IllegalArgumentException("l > r.");
		}
		if (CHECKED && l < 0) {
			throw new IllegalArgumentException("l < 0.");
		}
		if (CHECKED && r >= size) {
			throw new IllegalArgumentException("r >= size.");
		}
		return _query(l, r);
	}
	
}
