package contests.segmenttree;

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
            low, high, operation.aggregate(left.getValue(), right.getValue()), left, right);
    }

    private T _value(SegmentTreeNode<T> node, int low, int high) {
        // If no intersection, return default value.
        if (low > node.getHigh() || high < node.getLow()) {
            return operation.nil();
        }
        // Check for full overlap.
        if (node.getLow() >= low && node.getHigh() <= high) {
            return node.getValue();
        }
        // Partial overlap.
        T lv = _value(node.getLeft(), low, high);
        T rv = _value(node.getRight(), low, high);
        return operation.aggregate(lv, rv);
    }

    private void _increment(SegmentTreeNode<T> node, int index, T value) {
        if (node.getLow() == node.getHigh()) {
            node.setValue(operation.update(node.getValue(), value));
        }
        else {
            SegmentTreeNode<T> next = index << 1 <= node.getLow() + node.getHigh()
                ? node.getLeft()
                : node.getRight();
            _increment(next, index, value);
            node.setValue(
                operation.aggregate(node.getLeft().getValue(), node.getRight().getValue()));
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
