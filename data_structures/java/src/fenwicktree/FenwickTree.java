package fenwicktree;

public class FenwickTree {
    
    private static final boolean CHECKED = true;

    private long[] tree;
    private long total;
    
    public static FenwickTree empty(int size) {
        if (CHECKED && size < 0) {
            throw new IllegalArgumentException("Invalid tree size (size < 0).");
        }
        return new FenwickTree(size);
    }

    public static FenwickTree withValue(int size, int value) {
        if (CHECKED && size < 0) {
            throw new IllegalArgumentException("Invalid tree size (size < 0).");
        }
        FenwickTree result = new FenwickTree(size);
        result.init(value);
        return result;
    }

    public static FenwickTree withValues(int[] values) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        FenwickTree result = new FenwickTree(values.length);
        result.init(values);
        return result;
    }

    /**
     * Internal Fenwick array indices from [1..size].
     */
    private FenwickTree(int size) {
        tree = new long[size + 1];
    }

    private void init(int value) {
        total = (this.tree.length - 1) * value;
        for (int j = 1; j < tree.length; j++) {
            tree[j] = value * (j&-j);
        }
    }

    private void init(int[] values) {
        long[] cummulative = new long[values.length+1];
        for (int j = 1; j < tree.length; j++) {
            cummulative[j] = cummulative[j - 1] + values[j-1];
            tree[j] = cummulative[j] - cummulative[j - (j&-j)];
        }
        total = cummulative[tree.length - 1];
    }

    /**
     * Return the sum of items from 1 to index.
     */
    private long _sum(int index) {
        long sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index&-index;
        }
        return sum;
    }

    /**
     * Increment item at index.
     */
    private void _increment(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index&-index;
        }
    }

    /**
     * Return the sum of items from low to high.
     */
    public long sum(int low, int high) {
        if (CHECKED && low > high) {
            throw new IllegalArgumentException("Invalid range (low > high).");
        }
        if (CHECKED && low < 0) {
            throw new IllegalArgumentException("Invalid range (low < 0).");
        }
        if (CHECKED && high >= tree.length - 1) {
            throw new IllegalArgumentException("Invalid range (high > length).");
        }
        long ls = (low > 0) ? _sum(low) : 0;
        return _sum(high + 1) - ls;
    }
    
    /**
     * Increment item at <code>index</code> by <code>value</code>.
     */
    public void increment(int index, int value) {
        total += value;
        if (CHECKED && index < 0) {
            throw new IllegalArgumentException("Invalid argument (index < 0).");
        }
        if (CHECKED && index >= tree.length - 1) {
            throw new IllegalArgumentException("Invalid argument (index > length).");
        }
        _increment(index + 1, value);
    }

    public long getTotal() {
        return total;
    }
    
}
