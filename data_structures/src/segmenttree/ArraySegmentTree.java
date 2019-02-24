package segmenttree;

import java.lang.reflect.Array;

public class ArraySegmentTree<T> {

    private static final boolean CHECKED = true;
    
    public static <T> ArraySegmentTree<T> create(T[] values, SegmentTreeOperation<T> operation) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("");
        }
        return new ArraySegmentTree<T>(values, operation);
    }

    private T[] tree;
    private SegmentTreeOperation<T> operation;

    private ArraySegmentTree(T[] values, SegmentTreeOperation<T> operation) {
        this.operation = operation;
        buildTree(values);
    }

    private void buildTree(T[] values) {
        tree = (T[]) Array.newInstance(values.getClass().getComponentType(), values.length);
        for (int j = values.length - 1; j >= values.length/2; j--) {
            tree[j] = values[j];
        }
        // Aggregate the inner nodes of the tree.
        for (int j = values.length/2 - 1; j >= 0; j--) {
            tree[j] = operation.aggregate(getLeftChild(j), getRightChild(j));
        }
    }

    private T getLeftChild(int parent) {
        return 2*parent + 1 < tree.length
            ? tree[2*parent + 1]
            : null;
    }

    private T getRightChild(int parent) {
        return 2*(parent + 1) < tree.length
            ? tree[2*(parent + 1)]
            : null;
    }

    private T _value(int low, int high, int key, int nodeLow, int nodeHigh) {
        // If no intersection, return default value.
		if (low > nodeHigh || high < nodeLow) {
            return operation.nil();
        }
        // Check for full overlap
        if (nodeLow >= low && nodeHigh <= high) {
            return tree[key];
        }
        // Partial overlap
        int mid = (nodeLow + nodeHigh) / 2;
        T lv = _value(low, high, 2*key + 1, nodeLow, mid);
        T rv = _value(low, high, 2*(key + 1), mid + 1, nodeHigh);
        return operation.aggregate(lv, rv);
    }

    private void _increment(int index, T value) {
        while (index >= 0) {
            // Update the leafs.
            if (index >= tree.length/2) {
                tree[index] = operation.update(tree[index], value);
            }
            // Aggregate the inner nodes.
            else {
                tree[index] = operation.aggregate(getLeftChild(index), getRightChild(index));
            }
            index = (index - 1)/2;
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
        if (CHECKED && high >= tree.length) {
            throw new IllegalArgumentException("Invalid range (high > length).");
        }
        return _value(low, high, 0, 0, tree.length - 1);
    }

    /**
     * Increment item at <code>index</code> by <code>value</code>.
     */
    public void increment(int index, T value) {
        if (CHECKED && index < 0) {
            throw new IllegalArgumentException("Invalid argument (index < 0).");
        }
        if (CHECKED && index >= tree.length) {
            throw new IllegalArgumentException("Invalid argument (index > length).");
        }
        _increment(index, value);
    }

}
