package contests.fenwicktree;

/**
 * Fenwick tree with reset feature.
 * 
 * The <code>reset</code> operation restores the initial state of the tree.
 * Runs in O(1) and lazily propagates its effect on subsequent operations
 * to <code>sum</code> and <code>increment</code>.
 */
public class ResetFenwickTree {
    
    enum InitializationType {
        Empty, Value, Values
    }

    private static final boolean CHECKED = true;

    private long[] tree, cummulativeValues;
    private int[] version;
    private long total;
    private int currentVersion, initialValue;
    private InitializationType initializationType;
    
    public static ResetFenwickTree empty(int size) {
        if (CHECKED && size < 0) {
            throw new IllegalArgumentException("Invalid tree size (size < 0).");
        }
        ResetFenwickTree result = new ResetFenwickTree(size);
        result.init();
        return result;
    }

    public static ResetFenwickTree withValue(int size, int value) {
        if (CHECKED && size < 0) {
            throw new IllegalArgumentException("Invalid tree size (size < 0).");
        }
        ResetFenwickTree result = new ResetFenwickTree(size);
        result.init(value);
        return result;
    }

    public static ResetFenwickTree withValues(int[] values) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        ResetFenwickTree result = new ResetFenwickTree(values.length);
        result.init(values);
        return result;
    }

    /**
     * Internal Fenwick array indices from [1..size].
     */
    private ResetFenwickTree(int size) {
        version = new int[size + 1];
        tree = new long[size + 1];
    }

    private void init() {
        initializationType = InitializationType.Empty;
        total = 0;
    }

    private void init(int value) {
        initializationType = InitializationType.Value;
        initialValue = value;
        for (int j = 1; j < tree.length; j++) {
            tree[j] = value * (j&-j);
        }
        total = (this.tree.length - 1) * value;
    }

    private void init(int[] values) {
        initializationType = InitializationType.Values;
        cummulativeValues = new long[values.length+1];
        for (int j = 1; j < tree.length; j++) {
            cummulativeValues[j] = cummulativeValues[j - 1] + values[j-1];
            tree[j] = cummulativeValues[j] - cummulativeValues[j - (j&-j)];
        }
        total = cummulativeValues[tree.length - 1];
    }

    private long getInitialValue(int index) {
        if (initializationType == InitializationType.Empty) {
            return 0;
        }
        else
        if (initializationType == InitializationType.Value) {
            return initialValue * (index&-index);
        }
        else {
            return cummulativeValues[index] - cummulativeValues[index - (index&-index)];
        }
    }

    /**
     * Return the sum of items from 1 to index.
     */
    private long _sum(int index) {
        long sum = 0;
        while (index > 0) {
            if (version[index] == currentVersion) {
                sum += tree[index];
            }
            else {
                sum += tree[index] = getInitialValue(index);
                version[index] = currentVersion;
            }
            index -= index&-index;
        }
        return sum;
    }

    /**
     * Increment item at index.
     */
    private void _increment(int index, int value) {
        while (index < tree.length) {
            if (version[index] == currentVersion) {
                tree[index] += value;
            }
            else {
                tree[index] = getInitialValue(index);
                version[index] = currentVersion;
            }
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

	public void reset() {
        currentVersion++;
        if (initializationType == InitializationType.Empty) {
            total = 0;
        }
        else
        if (initializationType == InitializationType.Value) {
            total = (this.tree.length - 1) * initialValue;
        }
        else {
            total = cummulativeValues[tree.length - 1];
        }
	}
    
}
