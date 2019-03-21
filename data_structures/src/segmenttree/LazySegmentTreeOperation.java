package segmenttree;

public interface LazySegmentTreeOperation<T> {
    
    /**
     * Aggregate <code>left</code> and <code>right</code> elements.
     */
    T aggregate(T left, T right);

    /**
     * Transforms the element with value of <code>current</code> applying a new value of <code>value</code>.
     * @param current original value.
     * @param value value to apply for the transformation.
     */
    T transform(T current, T value);

    /**
     * Transforms the element with value of <code>current</code> applying a new value of <code>value</code>.
     * @param current original value.
     * @param value value to apply for the transformation.
     * @param size size of the subtree where the transforamtion is being applied.
     */
    T transform(T current, T value, int size);
    
    /**
     * Operation's null element.
     */
    T nil();
    
    /**
     * Lazy's null element.
     */
    T zero();
    
}
