package segmenttree;

import java.lang.reflect.Array;

public class LazySegmentTree<T> {

    private static final boolean CHECKED = true;

    public static <T> LazySegmentTree<T> create(T[] values, LazySegmentTreeOperation<T> operation) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
        return new LazySegmentTree<T>(values, operation);
    }

    private T[] tree, lazy, values;
    private int[] subtreeSize;
    private LazySegmentTreeOperation<T> operation;
    private int size;

    private LazySegmentTree(T[] values, LazySegmentTreeOperation<T> operation) {
        this.operation = operation;
        this.values = values;
        // Normalize the input array to have 2^k items.
        // This way we know what position each node occupies in the segment tree.
        size = nextPowerOfTwo(values.length - 1);
        tree = (T[]) Array.newInstance(values.getClass().getComponentType(), size << 1);
        lazy = (T[]) Array.newInstance(values.getClass().getComponentType(), size << 1);
        subtreeSize = new int[size << 1];
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
            lazy[index] = operation.zero();
            subtreeSize[index] = low < values.length ? 1 : 0;
        }
        else {
            int mid = (low + high) >> 1, left = index << 1, right = left + 1;
            _buildTree(low, mid, left);
            _buildTree(mid + 1, high, right);
            tree[index] = operation.aggregate(tree[left], tree[right]);
            lazy[index] = operation.zero();
            subtreeSize[index] = subtreeSize[left] + subtreeSize[right];
        }
    }
    
    /**
     * Aggregate actual and lazy information from node at <code>index</code>.
     */
    private T _value(int index) {
        return operation.transform(tree[index], lazy[index], subtreeSize[index]);
    }

    private T _value(int low, int high) {
        // Go up in the tree until finding their common parent P.
        // Include the right children of nodes in the path from 'low' to P and
        // the left children on the path from 'high' to P.
        int lowKey = size + low, highKey = size + high, leftSize = 1, rightSize = 1;
        T lowBest = _value(lowKey);
        T highBest = lowKey != highKey
            ? _value(highKey)
            : operation.nil();
        for (; lowKey >> 1 != highKey >> 1; lowKey >>= 1, highKey >>= 1) {
            int lowParentKey = lowKey >> 1;
            // Count parent's right child if 'lowKey' is its left child.
            if (lowParentKey << 1 == lowKey) {
                lowBest = operation.aggregate(lowBest, _value((lowParentKey << 1) + 1));
                leftSize += subtreeSize[(lowParentKey << 1) + 1];
            }
            lowBest = operation.transform(lowBest, lazy[lowParentKey], leftSize);
            int highParentKey = highKey >> 1;
            // Count parent's left child if 'highKey' is its right child.
            if ((highParentKey << 1) + 1 == highKey) {
                highBest = operation.aggregate(highBest, _value(highParentKey << 1));
                rightSize += subtreeSize[highParentKey << 1];
            }
            highBest = operation.transform(highBest, lazy[highParentKey], rightSize);
        }
        T best = operation.aggregate(lowBest, highBest);
        // Aggregate the lazy values in the path up to the root.
        do {
            lowKey >>= 1;
            best = operation.transform(best, lazy[lowKey], high - low + 1);
        }
        while (lowKey > 1);
        return best;
    }

    private void _increment(int low, int high, T value) {
        // Go up on 'low' & 'high' up to their LCA P. Update each node on
        // the way to P. Update lazyness information for low's parent's right
        // children and high's parent's left children.
        int lowKey = size + low, highKey = size + high;
        tree[lowKey] = operation.transform(tree[lowKey], value);
        if (lowKey != highKey) {
            tree[highKey] = operation.transform(tree[highKey], value);
        }
        while (lowKey >> 1 != highKey >> 1) {
            int lowParentKey = lowKey >> 1;
            // Lazyly update parent's right child if 'lowKey' is its left child.
            if (lowParentKey << 1 == lowKey) {
                lazy[(lowParentKey << 1) + 1] = operation.transform(lazy[(lowParentKey << 1) + 1], value);
            }
            tree[lowParentKey] = operation.aggregate(
                _value(lowParentKey << 1), _value((lowParentKey << 1) + 1));
            
            int highParentKey = highKey >> 1;
            // Lazyly update parent's left child if 'highKey' is its left child.
            if ((highParentKey << 1) + 1 == highKey) {
                lazy[(highParentKey << 1)] = operation.transform(lazy[(highParentKey << 1)], value);
            }
            tree[highParentKey] = operation.aggregate(
                _value(highParentKey << 1), _value((highParentKey << 1) + 1));

            lowKey = lowParentKey;
            highKey = highParentKey;
        }
        // 'lowKey >> 1' ('highKey >> 1') is the LCA of 'low' and 'high'.
        // Go up to the root applying the update in the regular way.
        do {
            lowKey = lowKey >> 1;
            tree[lowKey] = operation.aggregate(
                _value(lowKey << 1), _value((lowKey << 1) + 1));
        }
        while (lowKey > 1);
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
    public void increment(int low, int high, T value) {
        if (CHECKED && low > high) {
            throw new IllegalArgumentException("Invalid range (low > high).");
        }
        if (CHECKED && low < 0) {
            throw new IllegalArgumentException("Invalid argument (low < 0).");
        }
        if (CHECKED && high >= values.length) {
            throw new IllegalArgumentException("Invalid argument (high > length).");
        }
        _increment(low, high, value);
    }

}
