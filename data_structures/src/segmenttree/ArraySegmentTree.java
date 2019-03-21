package segmenttree;

import java.lang.reflect.Array;

public class ArraySegmentTree<T> {

    private static final boolean CHECKED = true;
    
    public static <T> ArraySegmentTree<T> create(T[] values, SegmentTreeOperation<T> operation) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
        return new ArraySegmentTree<T>(values, operation);
    }

    private T[] tree;
    private SegmentTreeOperation<T> operation;
    private int size;

    private ArraySegmentTree(T[] values, SegmentTreeOperation<T> operation) {
        this.operation = operation;
        size = values.length;
        tree = (T[]) Array.newInstance(values.getClass().getComponentType(),
            2*nextPowerOfTwo(values.length - 1) - 1);
        _buildTree(values, tree, 0, values.length - 1, 0);
    }

    protected int nextPowerOfTwo(int value) {
        return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(value));
    }

    private void _buildTree(T[] values, T[] tree, int low, int high, int index) {
        if (low == high) {
            tree[index] = values[low];
        }
        else {
            int mid = (low + high) / 2;
            _buildTree(values, tree, low, mid, 2*index + 1);
            _buildTree(values, tree, mid + 1, high, 2*(index + 1));
            tree[index] = operation.aggregate(tree[2*index + 1], tree[2*(index + 1)]);
        }
    }

    private T _value(int low, int high, int key, int nodeLow, int nodeHigh) {
        // If no intersection, return default value.
		if (low > nodeHigh || high < nodeLow) {
            return operation.nil();
        }
        // Check for full overlap.
        if (nodeLow >= low && nodeHigh <= high) {
            return tree[key];
        }
        // Partial overlap.
        int mid = (nodeLow + nodeHigh)/2;
        T lv = _value(low, high, 2*key + 1, nodeLow, mid);
        T rv = _value(low, high, 2*(key + 1), mid + 1, nodeHigh);
        return operation.aggregate(lv, rv);
    }

    private void _increment(int low, int high, int key, int index, T value) {
        if (low == high) {
            tree[key] = operation.transform(tree[key], value);
        }
        else {
            int mid = (low + high)/2;
            if (index <= mid) {
                _increment(low, mid, 2*key + 1, index, value);
            }
            else {
                _increment(mid + 1, high, 2*(key + 1), index, value);
            }
            tree[key] = operation.aggregate(tree[2*key + 1], tree[2*(key + 1)]);
        }
    }

    /**
     * Return the "aggregation" of items from low to high.
     */
    public T value(int low, int high) {
        if (CHECKED && low > high) {
            throw new IllegalArgumentException("Invalid range (low > high).");
        }
        if (CHECKED && low < 0) {
            throw new IllegalArgumentException("Invalid range (low < 0).");
        }
        if (CHECKED && high >= size) {
            throw new IllegalArgumentException("Invalid range (high > length).");
        }
        return _value(low, high, 0, 0, size - 1);
    }

    /**
     * Increment item at <code>index</code> by <code>value</code>.
     */
    public void increment(int index, T value) {
        if (CHECKED && index < 0) {
            throw new IllegalArgumentException("Invalid argument (index < 0).");
        }
        if (CHECKED && index >= size) {
            throw new IllegalArgumentException("Invalid argument (index > length).");
        }
        _increment(0, size - 1, 0, index, value);
    }

}
