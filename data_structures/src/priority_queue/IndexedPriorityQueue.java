package priority_queue;

import java.util.Comparator;

/**
 * Fixed capacity indexed min-heap implementation of a priority queue.
 */
public class IndexedPriorityQueue<T> {
    
    private static final boolean CHECKED = true;

    private int size;
    private int[] map;
    private QueueItem<T>[] heap;
    private Comparator<T> comparator;
    private PriorityQueueOperation<T> operation;

    private static <R> void _checkSizeConstructor(int capacity,
        PriorityQueueOperation<R> operation) {
        if (CHECKED && capacity < 1) {
            throw new IllegalArgumentException("Invalid argument (capacity must be positive).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
    }

    /**
     * Create a new instance of an {@code IndexedPriorityQueue} with a specific capacity.
     * Initialize each element in the queue with the {@code operation}'s default value.
     * 
     * @param <R> The type of the queue's elements.
     * @param capacity Queue's initial capacity.
     * @param operation The queue's elements operation.
     */
    public static <R extends Comparable<R>> IndexedPriorityQueue<R> create(int capacity,
        PriorityQueueOperation<R> operation) {
        _checkSizeConstructor(capacity, operation);
        return new IndexedPriorityQueue<R>(capacity, operation, null);
    }

    /**
     * Create a new instance of {@code IndexedPriorityQueue} with a specific capacity. Initialize
     * each element in the queue with the {@code operation}'s default value. Use {@code comparator}
     * instance to compare elements in the queue.
     * 
     * @param <R> The type of the queue's elements.
     * @param capacity Queue's initial capacity.
     * @param operation The queue's elements operation.
     * @param comparator A {@linkplain Comparator} instance.
     */
    public static <R> IndexedPriorityQueue<R> create(int capacity,
        PriorityQueueOperation<R> operation, Comparator<R> comparator) {
        _checkSizeConstructor(capacity, operation);
        return new IndexedPriorityQueue<R>(capacity, operation, comparator);
    }

    private static <R> void _checkValuesConstructor(R[] values,
        PriorityQueueOperation<R> operation) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && values.length < 1) {
            throw new IllegalArgumentException("Invalid argument (empty values array not allowed).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
    }

    public static <R extends Comparable<R>> IndexedPriorityQueue<R> create(R[] values,
        PriorityQueueOperation<R> operation) {
        _checkValuesConstructor(values, operation);
        return new IndexedPriorityQueue<>(values, operation, null);
    }

    public static <R> IndexedPriorityQueue<R> create(R[] values,
        PriorityQueueOperation<R> operation, Comparator<R> comparator) {
        _checkValuesConstructor(values, operation);
        return new IndexedPriorityQueue<>(values, operation, comparator);
    }

    private IndexedPriorityQueue(int capacity, PriorityQueueOperation<T> operation, Comparator<T> comparator) {
        this.size = capacity;
        this.comparator = comparator;
        this.operation = operation;
        _init(capacity);
    }

    private IndexedPriorityQueue(T[] values, PriorityQueueOperation<T> operation, Comparator<T> comparator) {
        this.size = values.length;
        this.comparator = comparator;
        this.operation = operation;
        _init(values);
    }

    private void _init(int size) {
        heap = new QueueItem[size];
        map = new int[size];
        for (int j = 0; j < size; j++) {
            heap[j] = new QueueItem<T>(j, operation._default());
            map[j] = j;
        }
    }

    private void _init(T[] values) {
        heap = new QueueItem[values.length];
        map = new int[values.length];
        for (int j = 0; j < values.length; j++) {
            if (values[j] == null) {
                throw new IllegalArgumentException(String.format("Value at index %d is null.", j));
            }
            heap[j] = new QueueItem<T>(j, values[j]);
            map[j] = j;
        }
        _heapify();
    }

    @SuppressWarnings("unchecked")
    private int _compare(T x, T y) {
        if (comparator != null) {
            return comparator.compare(x, y);
        }
        return ((Comparable<T>) x).compareTo(y);
    }

    private void _push(int index) {
        QueueItem<T> pivot = heap[index];
        int half = size >> 1;
        while (index < half) {
            int left = (index << 1) + 1;
            int right = left + 1;
            int min = right >= size || _compare(heap[left].value, heap[right].value) < 0
                ? left
                : right;
            if (_compare(pivot.value, heap[min].value) <= 0) {
                break;
            }
            heap[index] = heap[min];
            map[heap[min].key] = index;
            index = min;
        }
        heap[index] = pivot;
        map[pivot.key] = index;
    }

    private void _pull(int index) {
        QueueItem<T> pivot = heap[index];
        while (index > 0) {
            int parent = (index - 1) >> 1;
            if (_compare(pivot.value, heap[parent].value) >= 0) {
                break;
            }
            heap[index] = heap[parent];
            map[heap[parent].key] = index;
            index = parent;
        }
        heap[index] = pivot;
        map[pivot.key] = index;
    }

    private void _heapify() {
        for (int j = (size >> 1) - 1; j >= 0; j--) {
            _push(j);
        }
    }

    private void _checkUpdate(int key, T value) {
        if (CHECKED && key < 0) {
            throw new IllegalArgumentException("Invalid argument (key is negative).");
        }
        if (CHECKED && key >= size) {
            throw new IllegalArgumentException("Invalid argument (key out of bound).");
        }
        if (CHECKED && value == null) {
            throw new IllegalArgumentException("Invalid argument (value is null).");
        }
    }

    private void _update(int key, T value) {
        T old = heap[map[key]].value;
        heap[map[key]].value = operation.transform(heap[map[key]].value, value);
        int cr = _compare(heap[map[key]].value, old);
        if (cr < 0) {
            _pull(map[key]);
        }
        else
        if (cr > 0) {
            _push(map[key]);
        }
    }

    private void _checkPeekPoll() {
        if (CHECKED && size < 1) {
            throw new RuntimeException("Cannot poll from an empty queue.");
        }
    }

    private QueueItem<T> _poll() {
        if (size < 1) {
            return null;
        }
        QueueItem<T> min = heap[0];
        heap[0] = heap[--size];
        _push(0);
        return min;
    }

    private QueueItem<T> _peek() {
        if (size < 1) {
            return null;
        }
        return heap[0];
    }

    /**
     * Update element at {@code index} with a specific value.
     */
    public void update(int key, T value) {
        _checkUpdate(key, value);
        _update(key, value);
    }

    /**
     * Extracts the minimum element from the queue.
     */
    public QueueItem<T> peek() {
        _checkPeekPoll();
        return _peek();
    }

    /**
     * Extracts the minimum element from the queue.
     */
    public QueueItem<T> poll() {
        _checkPeekPoll();
        return _poll();
    }

	public int size() {
		return size;
	}

}
