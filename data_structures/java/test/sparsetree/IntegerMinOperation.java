package sparsetree;

public class IntegerMinOperation implements SparseTreeOperation<Integer> {

	@Override
	public NodeInfo<Integer> aggregate(NodeInfo<Integer> left, NodeInfo<Integer> right) {
		if (left.value < right.value) {
			return left;
		}
		return right;
	}

}
