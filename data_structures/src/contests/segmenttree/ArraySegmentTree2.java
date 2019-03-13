package contests.segmenttree;

import java.lang.reflect.Array;

public class ArraySegmentTree2<T> {

    private static final boolean CHECKED = true;
    
    public static <T> ArraySegmentTree2<T> create(T[] values, SegmentTreeOperation<T> operation) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
        return new ArraySegmentTree2<T>(values, operation);
    }

    private T[] tree, values;
    private SegmentTreeOperation<T> operation;
    private int size;

    private ArraySegmentTree2(T[] values, SegmentTreeOperation<T> operation) {
        this.operation = operation;
        this.values = values;
        // Normalize the input array to have 2^k items.
        // This way we know what position each node occupies in the segment tree.
        size = nextPowerOfTwo(values.length - 1);
        tree = (T[]) Array.newInstance(values.getClass().getComponentType(), size << 1);
        // Making the root of the tree the element at index 1 makes
        // child operations easier to handle.
        _buildTree(0, size - 1, 1);
    }

    protected int nextPowerOfTwo(int value) {
        return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(value));
    }

    private void _buildTree(int low, int high, int index) {
        if (low == high) {
            // Handle the normalization operation.
            // Items from [values.length to tree.length) are dummy.
            tree[index] = low < values.length
                ? values[low]
                : operation.nil();
        }
        else {
            int mid = (low + high) >> 1,
                left = index << 1,
                right = (index << 1) + 1;
            _buildTree(low, mid, left);
            _buildTree(mid + 1, high, right);
            tree[index] = operation.aggregate(tree[left], tree[right]);
        }
    }
    
    private T _value(int low, int high) {
        // Go up in the tree until finding their common parent P.
        // Include the right children of nodes in the path from 'low' to P and
        // the left children on the path from 'high' to P.
        int keyLow = size + low, keyHigh = size + high;
        T result = operation.aggregate(tree[keyLow], tree[keyHigh]);
        for (; keyLow >> 1 != keyHigh >> 1; keyLow >>= 1, keyHigh >>= 1) {
            int lowParentKey = keyLow >> 1;
            int highParentKey = keyHigh >> 1;
            // Count parent's right child if 'keyLow' is its left child.
            if (lowParentKey << 1 == keyLow) {
                result = operation.aggregate(result, tree[(lowParentKey << 1) + 1]);
            }
            // Count parent's left child if 'keyHigh' is its right child.
            if ((highParentKey << 1) + 1 == keyHigh) {
                result = operation.aggregate(result, tree[highParentKey << 1]);
            }
        }
        return result;
    }

    private void _increment(int index, T value) {
        int key = size + index;
        tree[key] = operation.update(tree[key], value);
        do {
            key = key >> 1;
            tree[key] = operation.aggregate(tree[key<<1], tree[(key<<1) + 1]);
        }
        while (key > 0);
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
        if (CHECKED && high >= values.length) {
            throw new IllegalArgumentException("Invalid range (high > length).");
        }
        return _value(low, high);
    }

    /**
     * Increment item at <code>index</code> by <code>value</code>.
     */
    public void increment(int index, T value) {
        if (CHECKED && index < 0) {
            throw new IllegalArgumentException("Invalid argument (index < 0).");
        }
        if (CHECKED && index >= values.length) {
            throw new IllegalArgumentException("Invalid argument (index > length).");
        }
        _increment(index, value);
    }

}
