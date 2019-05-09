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

class TestInfo {
    int N, K;
    int[] C, D;

    public TestInfo(int N, int K, int[] C, int[] D) {
        this.N = N;
        this.K = K;
        this.C = C;
        this.D = D;
    }
}

class SegmentTreeNode {
    SegmentTreeNode left, right;
    int low, high, index, value;

    public SegmentTreeNode(int low, int high, int maxIndex, int value) {
        this.low = low;
        this.high = high;
        this.index = maxIndex;
        this.value = value;
    }

    public SegmentTreeNode(int low, int high, int maxIndex, int value, SegmentTreeNode left, SegmentTreeNode right) {
        this(low, high, maxIndex, value);
        this.left = left;
        this.right = right;
    }
}

class MaxSegmentTree {
    public static MaxSegmentTree create(int[] values) {
        return new MaxSegmentTree(values);
    }

    private SegmentTreeNode root;

    private MaxSegmentTree(int[] values) {
        root = buildTree(values, 0, values.length - 1);
    }

    private SegmentTreeNode buildTree(int[] values, int low, int high) {
        if (low == high) {
            return new SegmentTreeNode(low, low, low, values[low]);
        }
        int mid = (low + high) >> 1;
        SegmentTreeNode left = buildTree(values, low, mid);
        SegmentTreeNode right = buildTree(values, mid + 1, high);
        int maxIndex = left.value > right.value
            ? left.index
            : right.index;
        return new SegmentTreeNode(
            low, high, maxIndex, Math.max(left.value, right.value), left, right);
    }

    private SegmentTreeNode _max(SegmentTreeNode node, int low, int high) {
        if (low > node.high || high < node.low) {
            return null;
        }
        if (node.low >= low && node.high <= high) {
            return node;
        }
        SegmentTreeNode lv = _max(node.left, low, high);
        SegmentTreeNode rv = _max(node.right, low, high);
        if (lv == null) {
            return rv;
        }
        if (rv == null) {
            return lv;
        }
        return lv.value > rv.value ? lv : rv;
    }

    public SegmentTreeNode max(int low, int high) {
        return _max(root, low, high);
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), K = in.nextInt();
            int[] C = new int[N], D = new int[N];
            for (int j = 0; j < N; j++) {
                C[j] = in.nextInt();
            }
            for (int j = 0; j < N; j++) {
                D[j] = in.nextInt();
            }
            tests[t] = new TestInfo(N, K, C, D);
        }
    }

    private long _intervals(int l, int r, int pivot) {
        return (long) (pivot - l + 1) * (long) (r - pivot + 1);
    }

    /**
     * Find min index I from interval [l .. r] such that max - scores[I] <= K.
     * Return r if such index does not exist.
     */
    private int _fairMinIndex(MaxSegmentTree tree, int[] scores, int l, int r, int max, int K) {
        if (l == r) {
            return max - scores[l] <= K ? l : l + 1;
        }
        // Do binary search on the interval [l .. r] using tree max information.
        int p = (l + r) >> 1;
        SegmentTreeNode lmax = tree.max(l, p);
        if (max - lmax.value <= K) {
            return _fairMinIndex(tree, scores, l, p, max, K);
        }
        return _fairMinIndex(tree, scores, p + 1, r, max, K);
    }

    /**
     * Find max index I from interval [l .. r] such that max - scores[I] <= K.
     * Return l if such index does not exist.
     */
    private int _fairMaxIndex(MaxSegmentTree tree, int[] scores, int l, int r, int max, int K) {
        if (l == r) {
            return max - scores[l] <= K ? l : l - 1;
        }
        // Do binary search on the interval [l .. r] using tree max information.
        int p = (l + r) >> 1;
        SegmentTreeNode rmax = tree.max(p + 1, r);
        if (max - rmax.value <= K) {
            return _fairMaxIndex(tree, scores, p + 1, r, max, K);
        }
        return _fairMaxIndex(tree, scores, l, p, max, K);
    }

    private long _fair(int l, int r, int[] C, int[] D, MaxSegmentTree ctree, MaxSegmentTree dtree, int K) {
        if (l > r) {
            return 0;
        }
        SegmentTreeNode cmax = ctree.max(l, r);
        SegmentTreeNode dmax = dtree.max(l, r);
        if (cmax.value < dmax.value) {
            return _fair(l, r, D, C, dtree, ctree, K);
        }
        long result = 0;
        // Count intervals from [l .. cmax.maxIndex - 1].
        result += _fair(l, cmax.index - 1, C, D, ctree, dtree, K);
        // Count intervals from [cmax.maxIndex + 1 .. r].
        result += _fair(cmax.index + 1, r, C, D, ctree, dtree, K);
        // Count intervals containing cmax.index.
        int lmax = _fairMaxIndex(dtree, D, l, cmax.index, cmax.value, K);
        int rmin = _fairMinIndex(dtree, D, cmax.index, r, cmax.value, K);
        result += _intervals(l, r, cmax.index) - _intervals(lmax + 1, rmin - 1, cmax.index);
        return result;
    }

    private long _fair(TestInfo test) {
        MaxSegmentTree ctree = MaxSegmentTree.create(test.C);
        MaxSegmentTree dtree = MaxSegmentTree.create(test.D);
        return _fair(0, test.N - 1, test.C, test.D, ctree, dtree, test.K);
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            result[t] = String.format("Case #%d: %d", t + 1, _fair(tests[t]));
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
