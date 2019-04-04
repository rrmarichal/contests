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

class TreeNode {
    public int min, max;

    public TreeNode(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

class OrderedList {
    
    private boolean[] removed;
    private SegmentInfo[] segments;

    public static OrderedList create(SegmentInfo[] segments) {
        return new OrderedList(segments);
    }

    private OrderedList(SegmentInfo[] segments) {
        this.segments = segments;
        removed = new boolean[segments.length];
    }

    public void remove(int key) {
        removed[key] = true;
    }

    public ArrayList<SegmentIntersection> intersect(int low, int high) {
        ArrayList<SegmentIntersection> result = new ArrayList<SegmentIntersection>();
        for (int j = 0; j < segments.length; j++) {
            if (!removed[j] && !(segments[j].L > high || segments[j].R < low)) {
                if (segments[j].L <= low && segments[j].R >= high) {
                    result.add(new SegmentIntersection(j, high - low + 1));
                }
                else
                if (segments[j].L >= low && segments[j].R <= high) {
                    result.add(new SegmentIntersection(j, segments[j].R - segments[j].L + 1));
                }
                else
                if (segments[j].L <= low) {
                    result.add(new SegmentIntersection(j, segments[j].R - low + 1));
                }
                else {
                    result.add(new SegmentIntersection(j, high - segments[j].L + 1));
                }
            }
        }
        return result;
    }

}

class MinMaxSegmentTree {

    private int N, size;
    private SegmentInfo[] S;
    private TreeNode[] tree;
    private int[] lazy;
    private IndexedPriorityQueue segmentsQueue;
    private OrderedList segmentsList;

	public static MinMaxSegmentTree create(int N, SegmentInfo[] S) {
		return new MinMaxSegmentTree(N, S);
    }
    
    private MinMaxSegmentTree(int N, SegmentInfo[] S) {
        this.S = S;
        this.N = N;
        this.size = nextPowerOfTwo(N - 1);
        _buildTree();
        segmentsQueue = IndexedPriorityQueue.create(S.length + 1);
        segmentsList = OrderedList.create(S);
        for (int j = 0; j < S.length; j++) {
            _update(S[j].L, S[j].R, 0, 0, size - 1, 1);
        }
        _update(0, N - 1, 0, 0, size - 1, 1);
        segmentsQueue.update(S.length, 1);
    }

    private void _buildTree() {
        tree = new TreeNode[(size << 1) - 1];
        lazy = new int[(size << 1) - 1];
        for (int j = 0; j < (size << 1) - 1; j++) {
            tree[j] = new TreeNode(0, 0);
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

    private void _push(int low, int high, int key, int klow, int khigh, int value, boolean counted) {
        if (_max(key) == 1 && !counted && value < 0) {
            ArrayList<SegmentIntersection> segments = segmentsList.intersect(klow, khigh);
            for (SegmentIntersection si: segments) {
                segmentsQueue.update(si.id, si.intersection);
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
            MinMaxSegmentTree minMax = MinMaxSegmentTree.create(N, S);
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < Q + 1; j++) {
                int max = minMax.poll();
                if (max < min) {
                    min = max;
                }
            }
            result[t] = String.format("Case #%d: %d", t + 1, min);
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        out.println(solution.solve());
        out.close();
    }

}
