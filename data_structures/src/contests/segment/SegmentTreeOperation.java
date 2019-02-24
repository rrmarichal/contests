package contests.segment;

public interface SegmentTreeOperation<T> {
    
    /**
     * Aggregate <code>left</code> and <code>right</code> elements.
     */
    T aggregate(T left, T right);

    /**
     * Update element with value <code>current</code> to <code>update</code>.
     */
    T update(T current, T update);
    
	T nil();
    
}
