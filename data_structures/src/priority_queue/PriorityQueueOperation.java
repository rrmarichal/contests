package priority_queue;

public interface PriorityQueueOperation<T> {

    /**
     * Transforms the element with value of <code>current</code> applying a new value of <code>value</code>.
     */
    T transform(T current, T value);
    
    /**
     * Queue items default element.
     */
    T _default();

}
