package segmenttree;

public interface SegmentTreeOperation<T> {
    
    /**
     * Aggregate <code>left</code> and <code>right</code> elements.
     */
    T aggregate(T left, T right);

    /**
     * Transforms the element with value of <code>current</code> applying a new value of <code>value</code>.
     */
    T transform(T current, T value);
    
    /**
     * Operation's null element.
     */
    T nil();
    
}
