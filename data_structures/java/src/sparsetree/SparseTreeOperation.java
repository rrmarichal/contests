package sparsetree;

public interface SparseTreeOperation<T> {

	/**
     * Aggregate <code>left</code> and <code>right</code> elements.
     */
	NodeInfo<T> aggregate(NodeInfo<T> left, NodeInfo<T> right);
	
}
