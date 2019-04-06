import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QueueItem {
    public int key, value;

    public QueueItem(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class IndexedPriorityQueue {
    
    private int size;
    private int[] map;
    private QueueItem[] heap;

    public static IndexedPriorityQueue create(int capacity) {
        return new IndexedPriorityQueue(capacity);
    }

    private IndexedPriorityQueue(int capacity) {
        this.size = capacity;
        _init(capacity);
    }

    private void _init(int size) {
        heap = new QueueItem[size];
        map = new int[size];
        for (int j = 0; j < size; j++) {
            heap[j] = new QueueItem(j, 0);
            map[j] = j;
        }
    }

    private int _compare(int x, int y) {
        return -Integer.compare(x, y);
    }

    private void _push(int index) {
        QueueItem pivot = heap[index];
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
        QueueItem pivot = heap[index];
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

    private void _update(int key, int value) {
        int old = heap[map[key]].value;
        heap[map[key]].value += value;
        int cr = _compare(heap[map[key]].value, old);
        if (cr < 0) {
            _pull(map[key]);
        }
        else
        if (cr > 0) {
            _push(map[key]);
        }
    }

    private QueueItem _poll() {
        if (size < 1) {
            return null;
        }
        QueueItem min = heap[0];
        heap[0] = heap[--size];
        _push(0);
        return min;
    }

    private QueueItem _peek() {
        if (size < 1) {
            return null;
        }
        return heap[0];
    }

    public void update(int key, int value) {
        _update(key, value);
    }

    public QueueItem peek() {
        return _peek();
    }

    public QueueItem poll() {
        return _poll();
    }

	public int size() {
		return size;
	}

}

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
class IntervalTree {

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

    private HashMap<Integer, IntervalTreeNode> indices;
    private IntervalTreeNode root;
    private int size;

    public static IntervalTree create(final Interval[] intervals) {
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
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        _intersection(left, right, root, intervals);
        return intervals;
	}

    public int size() {
        return size;
    }

}

class MinMaxSegmentTreeNode {
    public int min, max;

    public MinMaxSegmentTreeNode(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

class MinMaxSegmentTree {

    private int N, size;
    private SegmentInfo[] S;
    private MinMaxSegmentTreeNode[] tree;
    private int[] lazy;
    private IndexedPriorityQueue segmentsQueue;
    private IntervalTree segmentsList;

	public static MinMaxSegmentTree create(int N, SegmentInfo[] S) {
		return new MinMaxSegmentTree(N, S);
    }
    
    private MinMaxSegmentTree(int N, SegmentInfo[] S) {
        this.S = S;
        this.N = N;
        this.size = nextPowerOfTwo(N - 1);

        long start = System.currentTimeMillis();

        _buildTree();

        System.out.println(String.format("Completed MinMaxSegmentTree creation: %d", System.currentTimeMillis() - start));
        start = System.currentTimeMillis();

        segmentsQueue = IndexedPriorityQueue.create(S.length + 1);

        System.out.println(String.format("Completed IndexedPriorityQueue creation: %d", System.currentTimeMillis() - start));
        start = System.currentTimeMillis();

        IntervalTree.Interval[] intervals = new IntervalTree.Interval[S.length];
        for (int j = 0; j < S.length; j++) {
            intervals[j] = new IntervalTree.Interval(j, S[j].L, S[j].R);
        }
        segmentsList = IntervalTree.create(intervals);

        System.out.println(String.format("Completed IntervalTree creation: %d", System.currentTimeMillis() - start));
        start = System.currentTimeMillis();

        for (int j = 0; j < S.length; j++) {
            _update(S[j].L, S[j].R, 0, 0, size - 1, 1);
        }
        _update(0, N - 1, 0, 0, size - 1, 1);
        segmentsQueue.update(S.length, 1);

        System.out.println(String.format("Completed intervals insertion: %d", System.currentTimeMillis() - start));
    }

    private void _buildTree() {
        tree = new MinMaxSegmentTreeNode[(size << 1) - 1];
        lazy = new int[(size << 1) - 1];
        for (int j = 0; j < (size << 1) - 1; j++) {
            tree[j] = new MinMaxSegmentTreeNode(0, 0);
        }
    }

    protected int nextPowerOfTwo(int value) {
        return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(value));
    }

    private int _min(int key) {
        if (lazy[key] + tree[key].min == 0) {
            return Integer.MAX_VALUE;
        }
        return lazy[key] + tree[key].min;
    }

    private int _max(int key) {
        return lazy[key] + tree[key].max;
    }

    private int _intersection(int sleft, int sright, int low, int high) {
        if (sleft <= low && sright >= high) {
            return high - low + 1;
        }
        else
        if (sleft >= low && sright <= high) {
            return sright - sleft + 1;
        }
        else
        if (sleft <= low) {
            return sright - low + 1;
        }
        else {
            return high - sleft + 1;
        }
    }

    private void _push(int low, int high, int key, int klow, int khigh, int value, boolean counted) {
        if (_max(key) == 1 && !counted && value < 0) {
            Collection<IntervalTree.Interval> segments = segmentsList.intersection(klow, khigh);
            for (IntervalTree.Interval si: segments) {
                segmentsQueue.update(si.id, _intersection(si.left, si.right, klow, khigh));
            }
            counted = true;
        }
        if (key < size - 1) {
            int min = _min(key);
            boolean push = min == 1 || (value < 0 && min == Integer.MAX_VALUE) || (value > 0 && min == 2);
            if (push) {
                int lkey = (key << 1) + 1,
                    rkey = lkey + 1,
                    mid = (klow + khigh) >> 1;

                lazy[lkey] += lazy[key];
                lazy[rkey] += lazy[key];
                lazy[key] = 0;
                
                _push(low, high, lkey, klow, mid, value, counted);
                _push(low, high, rkey, mid + 1, khigh, value, counted);

                tree[key].min = Math.min(_min(lkey), _min(rkey));
                tree[key].max = Math.max(_max(lkey), _max(rkey));
            }
        }
    }

    private void _update(int low, int high, int key, int klow, int khigh, int value) {
        if (low > khigh || high < klow) {
            return;
        }
        if (klow >= low && khigh <= high) {
            lazy[key] += value;
            _push(low, high, key, klow, khigh, value, false);
        }
        else {
            int lkey = (key << 1) + 1,
                rkey = lkey + 1,
                mid = (klow + khigh) >> 1;

            lazy[lkey] += lazy[key];
            lazy[rkey] += lazy[key];
            lazy[key] = 0;

            _update(low, high, lkey, klow, mid, value);
            _update(low, high, rkey, mid + 1, khigh, value);

            tree[key].min = Math.min(_min(lkey), _min(rkey));
            tree[key].max = Math.max(_max(lkey), _max(rkey));
        }
    }

    public int poll() {
        QueueItem max = segmentsQueue.poll();
        if (max.key == S.length) {
            _update(0, N - 1, 0, 0, size - 1, -1);
            return Integer.MAX_VALUE;
        }
        segmentsList.remove(max.key);
        _update(S[max.key].L, S[max.key].R, 0, 0, size - 1, -1);
        return max.value;
    }

}

class SegmentInfo {
    int L, R;

    public SegmentInfo(int L, int R) {
        this.L = L;
        this.R = R;
    }
}

class SegmentIntersection {
    int id, intersection;

    public SegmentIntersection(int id, int intersection) {
        this.id = id;
        this.intersection = intersection;
    }
}

class TestInfo {
    int N, Q;
    SegmentInfo[] S;

    public TestInfo(int N, int Q, SegmentInfo[] S) {
        this.N = N;
        this.Q = Q;
        this.S = S;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), Q = in.nextInt();
            SegmentInfo[] S = new SegmentInfo[Q];
            for (int j = 0; j < Q; j++) {
                S[j] = new SegmentInfo(in.nextInt() - 1, in.nextInt() - 1);
            }
            tests[t] = new TestInfo(N, Q, S);
        }
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            int N = tests[t].N, Q = tests[t].Q;
            SegmentInfo[] S = tests[t].S;
            
            long tstart = System.currentTimeMillis();

            MinMaxSegmentTree minMax = MinMaxSegmentTree.create(N, S);

            System.out.println(String.format("Completed tree creation for test %d: %d", t + 1, System.currentTimeMillis() - tstart));
            long pstart = System.currentTimeMillis();

            int min = Integer.MAX_VALUE;
            for (int j = 0; j < Q + 1; j++) {
                int max = minMax.poll();
                if (max < min) {
                    min = max;
                }
            }
            System.out.println(String.format("Completed test %d processing: %d", t + 1, System.currentTimeMillis() - pstart));
            System.out.println(String.format("Completed test %d total: %d", t + 1, System.currentTimeMillis() - tstart));
            
            result[t] = String.format("Case #%d: %d", t + 1, min);
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        for (String c: result) {
            out.println(c);
        }
        out.close();
    }

}
