package intervaltree;

import java.util.*;

class IntervalTreeNode {
    IntervalTree.Interval interval;
    IntervalTreeNode left, right, parent;
    int max;

    public IntervalTreeNode(IntervalTree.Interval interval) {
        this.interval = interval;
        max = interval.right;
    }

    public IntervalTreeNode(IntervalTree.Interval interval, IntervalTreeNode left, IntervalTreeNode right) {
        this.interval = interval;        
        this.left = left;
        this.right = right;
        max = interval.right;
        if (left != null) {
            left.parent = this;
            max = Math.max(max, left.max);
        }
        if (right != null) {
            right.parent = this;
            max = Math.max(max, right.max);
        }
    }

    public void adjustMax() {
        int lmax = left != null ? left.max : Integer.MIN_VALUE;
        int rmax = right != null ? right.max : Integer.MIN_VALUE;
        max = Math.max(max, Math.max(lmax, rmax));
    }
}

class LeftCoordinateIntervalComparator implements Comparator<IntervalTree.Interval> {
    @Override
    public int compare(IntervalTree.Interval x, IntervalTree.Interval y) {
        return Integer.compare(x.left, y.left);
    }
}

/**
 * Interval tree based on a BST implementation.
 * 
 * Supported operations include {@code insert}, {@code delete} and {@code intercept}.
 * 
 * BST is not guaranteed to be optimal after succesive add/remove operations.
 */
public class IntervalTree {

    public static class Interval implements Comparable<Interval> {
        int id, left, right;

        public Interval(int id, int left, int right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Interval o) {
            return left != o.left
                ? Integer.compare(left, o.left)
                : Integer.compare(id, o.id);
        }
    }

    private static final boolean CHECKED = true;

    private HashMap<Integer, IntervalTreeNode> indices;
    private IntervalTreeNode root;
    private int size;

    private static void _checkIntervals(Interval[] intervals) {
        Set<Integer> ids = new HashSet<Integer>();
        for (int j = 0; j < intervals.length; j++) {
            Interval interval = intervals[j];
            if (interval == null) {
                throw new IllegalArgumentException(
                    String.format("Invalid argument (interval at index %d is null).", j + 1));
            }
            if (ids.contains(interval.id)) {
                throw new IllegalArgumentException("Invalid argument (duplicate ID).");
            }
            if (interval.left > interval.right) {
                throw new IllegalArgumentException(
                    String.format("Invalid argument (interval at index %d has left coordinate greater than right coordinate).", j + 1));
            }
            ids.add(intervals[j].id);
        }
    }

    public static IntervalTree create(final Interval[] intervals) {
        if (CHECKED && intervals == null) {
            throw new IllegalArgumentException("Invalid argument (intervals is null).");
        }
        if (CHECKED) {
            _checkIntervals(intervals);
        }
        return new IntervalTree(intervals);
    }

    private IntervalTreeNode _buildTree(final Interval[] intervals, int low, int high) {
        if (low > high) {
            return null;
        }
        if (low == high) {
            indices.put(intervals[low].id, new IntervalTreeNode(intervals[low]));
            return indices.get(intervals[low].id);
        }
        int mid = (low + high) >> 1;
        IntervalTreeNode left = _buildTree(intervals, low, mid - 1);
        IntervalTreeNode right = _buildTree(intervals, mid + 1, high);
        indices.put(intervals[mid].id, new IntervalTreeNode(intervals[mid], left, right));
        return indices.get(intervals[mid].id);
    }

    private void _buildTree(final Interval[] intervals) {
        Interval[] sorted = Arrays.copyOf(intervals, intervals.length);
        Arrays.sort(sorted, new LeftCoordinateIntervalComparator());
        root = _buildTree(sorted, 0, sorted.length - 1);
        size = intervals.length;
    }

    private IntervalTree(final Interval[] intervals) {
        indices = new HashMap<Integer, IntervalTreeNode>();
        _buildTree(intervals);
    }

    private IntervalTreeNode _min(IntervalTreeNode x) {
        IntervalTreeNode y = null;
        while (x != null) {
            y = x;
            x = x.left;
        }
        if (y == null) {
            return null;
        }
        return y;
    }

    public Interval min() {
        IntervalTreeNode min = _min(root);
        return min != null ? min.interval : null;
    }

    private IntervalTreeNode _max(IntervalTreeNode x) {
        IntervalTreeNode y = null;
        while (x != null) {
            y = x;
            x = x.right;
        }
        if (y == null) {
            return null;
        }
        return y;
    }

    public Interval max() {
        IntervalTreeNode max = _max(root);
        return max != null ? max.interval : null;
    }

    private IntervalTreeNode _add(Interval interval) {
        IntervalTreeNode x = root;
        IntervalTreeNode y = null;
        IntervalTreeNode z = new IntervalTreeNode(interval);
        while (x != null) {
            y = x;
            x.max = Math.max(x.max, z.interval.right);
            x = z.interval.compareTo(x.interval) < 0
                ? x.left
                : x.right;
        }
        z.parent = y;
        if (y == null) {
            root = z;
        }
        else
        if (z.interval.compareTo(y.interval) < 0) {
            y.left = z;
        }
        else {
            y.right = z;
        }
        return z;
    }

    public void add(Interval interval) {
        if (CHECKED && interval == null) {
            throw new IllegalArgumentException("Invalid argument (interval is null).");
        }
        if (CHECKED && interval.left > interval.right) {
            throw new IllegalArgumentException("Invalid argument (interval's left coordinate greater than right coordinate).");
        }
        if (CHECKED && indices.containsKey(interval.id)) {
            throw new IllegalArgumentException("Invalid argument (an interval with the same ID already exists).");
        }
        IntervalTreeNode node = _add(interval);
        indices.put(interval.id, node);
        size++;
    }

    private void _transplant(IntervalTreeNode u, IntervalTreeNode v) {
        if (u.parent == null) {
            root = v;
        }
        else
        if (u == u.parent.left) {
            u.parent.left = v;
        }
        else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private void _remove(int id) {
        IntervalTreeNode z = indices.get(id);
        if (z.left == null) {
            _transplant(z, z.right);
        }
        else
        if (z.right == null) {
            _transplant(z, z.left);
        }
        else {
            // Find z's successor.
            IntervalTreeNode y = _min(z.right);
            if (y.parent != z) {
                _transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
                y.right.adjustMax();
            }
            _transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.adjustMax();
        }
        // Walk up to the root updating max values.
        while (z.parent != null) {
            z = z.parent;
            z.adjustMax();
        }
    }

    /**
     * Removes the interval with a specific ID.
     */
	public void remove(int id) {
        if (CHECKED && !indices.containsKey(id)) {
            throw new IllegalArgumentException("Invalid argument (no such interval with the specified ID).");
        }
        _remove(id);
        indices.remove(id);
        size--;
    }
    
    private boolean _intercept(Interval interval, int left, int right) {
        return !(left > interval.right || right < interval.left);
    }

    private void _intersection(int left, int right, IntervalTreeNode current, ArrayList<Interval> intervals) {
        if (_intercept(current.interval, left, right)) {
            intervals.add(current.interval);
        }
        if (current.left != null && current.left.max >= left) {
            _intersection(left, right, current.left, intervals);
        }
        if (current.right != null && current.interval.left <= right) {
            _intersection(left, right, current.right, intervals);
        }
    }

    /**
     * Returns the list of intervals that intercept the argument interval defined
     * by [{@code left..right}].
     */
	public Collection<Interval> intersection(int left, int right) {
        if (CHECKED && left > right) {
            throw new IllegalArgumentException("Invalid argument (interval's left coordinate greater than right coordinate).");
        }
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        _intersection(left, right, root, intervals);
        return intervals;
	}

    public int size() {
        return size;
    }

}
