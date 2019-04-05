package segmenttree;

class SegmentTreeNode<T> {
    T value;
    SegmentTreeNode<T> left, right;
    int low, high;

    public SegmentTreeNode(int low, int high, T value) {
        this.low = low;
        this.high = high;
        this.value = value;
    }

    public SegmentTreeNode(int low, int high, T value, SegmentTreeNode<T> left, SegmentTreeNode<T> right) {
        this(low, high, value);
        this.left = left;
        this.right = right;
    }
}

public class SegmentTree<T> {

    private static final boolean CHECKED = true;

    public static <T> SegmentTree<T> create(T[] values, SegmentTreeOperation<T> operation) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
        return new SegmentTree<T>(values, operation);
    }

    private int size;
    private SegmentTreeNode<T> root;
    private SegmentTreeOperation<T> operation;

    private SegmentTree(T[] values, SegmentTreeOperation<T> operation) {
        this.operation = operation;
        this.size = values.length;
        root = buildTree(values, 0, values.length - 1);
    }

    private SegmentTreeNode<T> buildTree(T[] values, int low, int high) {
        // Create single node
        if (low == high) {
            return new SegmentTreeNode<T>(low, high, values[low]);
        }
        int mid = (low + high) / 2;
        SegmentTreeNode<T> left = buildTree(values, low, mid);
        SegmentTreeNode<T> right = buildTree(values, mid + 1, high);
        return new SegmentTreeNode<T>(
            low, high, operation.aggregate(left.value, right.value), left, right);
    }

    private T _value(SegmentTreeNode<T> node, int low, int high) {
        // If no intersection, return default value.
        if (low > node.high || high < node.low) {
            return operation.nil();
        }
        // Check for full overlap.
        if (node.low >= low && node.high <= high) {
            return node.value;
        }
        // Partial overlap.
        T lv = _value(node.left, low, high);
        T rv = _value(node.right, low, high);
        return operation.aggregate(lv, rv);
    }

    private void _increment(SegmentTreeNode<T> node, int index, T value) {
        if (node.low == node.high) {
            node.value = operation.transform(node.value, value);
        }
        else {
            SegmentTreeNode<T> next = index << 1 <= node.low + node.high
                ? node.left
                : node.right;
            _increment(next, index, value);
            node.value = operation.aggregate(node.left.value, node.right.value);
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
        return _value(root, low, high);
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
        _increment(root, index, value);
    }

}
