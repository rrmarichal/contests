import java.io.*;
import java.util.*;

class InputReader {
	private static final int BUFFER_SIZE = 1 << 16;

	private BufferedReader reader;
	private StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
	}

	private boolean ensureTokenizer() {
		if (tokenizer == null || !tokenizer.hasMoreTokens()) {
			String line = nextLine();
			if (line != null) {
				tokenizer = new StringTokenizer(line);
			}
		}
		return tokenizer != null && tokenizer.hasMoreElements();
	}

	public String nextLine() {
		try {
			tokenizer = null;
			return reader.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public String nextToken() {
		if (ensureTokenizer()) {
			return tokenizer.nextToken();
		}
		return null;
	}

	public Integer nextInt() {
		if (ensureTokenizer()) {
			return Integer.parseInt(tokenizer.nextToken());
		}
		return null;
	}

	public Long nextLong() {
		if (ensureTokenizer()) {
			return Long.parseLong(tokenizer.nextToken());
		}
		return null;
	}
}

class SpotInfo implements Comparable<SpotInfo> {
	long X, C;

	public SpotInfo(long X) {
		this.X = X;
	}

	@Override
	public int compareTo(SpotInfo o) {
		return Long.compare(X, o.X);
	}
}

class TestInfo {
	int K, N;
	SpotInfo[] spots;

	public TestInfo(int K, int N, SpotInfo[] spots) {
		this.K = K;
		this.N = N;
		this.spots = spots;
	}
}

/**
 * Priority Queue with a maximum capacity. Invariant is after N {@code add} operations,
 * only the {@code capacity} least elements remain in the queue.
 */
class PriorityQueue {

	private int count, capacity;
	private long[] heap;
	private long sum;

	public PriorityQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity");
		}
		this.capacity = capacity;
		heap = new long[capacity];
		count = 0;
		sum = 0;
	}

	/**
	 * Adds a new item to the queue. If the queue is already full then the maximum element is removed.
	 */
	public void add(long value) {
		if (capacity == 0) {
			return;
		}
		if (count < capacity) {
			sum += value;
			heap[count++] = value;
			heapUp(count - 1);
		}
		else
		if (value < heap[0]) {
			sum -= heap[0] - value;
			heap[0] = value;
			heapDown(0);
		}
	}

	private void heapUp(int k) {
		// item parent is at (k-1)/2
		while (k > 0 && heap[(k - 1) >> 1] < heap[k]) {
			long tmp = heap[(k - 1) >> 1];
			heap[(k - 1) >> 1] = heap[k];
			heap[k] = tmp;
			k = (k - 1) >> 1;
		}
	}

	private void heapDown(int k) {
		// item children are 2i + 1 & 2(i + 1)
		while ((k<<1) + 1 < count) {
			int max = (k<<1) + 1, right = ((k + 1)<<1);
			if (right < count && heap[right] > heap[max]) {
				max = right;
			}
			if (heap[k] < heap[max]) {
				long tmp = heap[max];
				heap[max] = heap[k];
				heap[k] = tmp;
				k = max;
			}
			else break;
		}
	}

	public long sum() {
		return sum;
	}

}

class SparseTree {

	private int[][] tree;
	private int K, N;
	private long[] values;

	public SparseTree(long[] values) {
		this.values = values;
		N = values.length;
		K = (int) (1 + Math.log(N) / Math.log(2));
		tree = new int[N][K];
		_buildTree();
	}
	
	private void _buildTree() {
		for (int j = 0; j < N; j++) {
			tree[j][0] = j;
		}
		for (int k = 1; k < K; k++) {
			for (int j = 0; j + (1 << (k - 1)) < N; j++) {
				int left = tree[j][k - 1];
				int right = tree[j + (1 << (k - 1))][k - 1];
				tree[j][k] = values[left] < values[right]
					? left
					: right;
			}
		}
	}

	public int minIndex(int left, int right) {
		int k = (int) (Math.log(right - left + 1) / Math.log(2));
		int ls = tree[left][k];
		int rs = tree[right - (1<<k) + 1][k];
		return values[ls] < values[rs] ? ls : rs;
	}

}

public class Solution {

	private int T;
	private TestInfo[] tests;

	protected Solution(InputReader in) {
		T = in.nextInt();
		tests = new TestInfo[T];
		for (int t = 0; t < T; t++) {
			int K = in.nextInt(), N = in.nextInt();
			SpotInfo[] spots = new SpotInfo[N];
			for (int j = 0; j < N; j++) {
				spots[j] = new SpotInfo(in.nextInt());
			}
			for (int j = 0; j < N; j++) {
				spots[j].C = in.nextInt();
			}
			tests[t] = new TestInfo(K, N, spots);
		}

	}

	private long _solve(TestInfo test) {
		Arrays.sort(test.spots);
		int l = test.K / 2, r = test.K - l;
		long RR = test.spots[test.N - 1].X + 1;

		PriorityQueue lqueue = new PriorityQueue(l);
		long[] lvalues = new long[test.N];
		for (int j = 0; j < l - 1; j++) {
			lqueue.add(RR - test.spots[j].X + test.spots[j].C);
		}
		for (int j = Math.max(0, l - 1); j < test.N - r; j++) {
			lqueue.add(RR - test.spots[j].X + test.spots[j].C);
			lvalues[j] = lqueue.sum();
		}
		SparseTree ltree = new SparseTree(lvalues);

		PriorityQueue rqueue = new PriorityQueue(r);
		long[] rvalues = new long[test.N];
		for (int j = 0; j < r - 1; j++) {
			rqueue.add(test.spots[test.N - j - 1].X + test.spots[test.N - j - 1].C);
		}
		for (int j = r - 1; j < test.N - l; j++) {
			rqueue.add(test.spots[test.N - j - 1].X + test.spots[test.N - j - 1].C);
			rvalues[test.N - j - 1] = rqueue.sum();
		}
		SparseTree rtree = new SparseTree(rvalues);

		long best = Long.MAX_VALUE;
		// Warehouse placement at spot k.
		for (int k = l; k < test.N - r; k++) {
			// Find shifts to the left (sl) & to the right (sr) of k where C[k] is minimum.
			int sl = 1;
			for (; k - sl >= l; sl++) {
				if (test.spots[k - sl].C < test.spots[k].C) {
					break;
				}
			}
			int sr = 1;
			for (; k + sr < test.N - r; sr++) {
				if (test.spots[k + sr].C < test.spots[k].C) {
					break;
				}
			}
			// Find min of lvalues[k - sl ... k - 1]
			int minLeft = ltree.minIndex(Math.max(0, k - sl), Math.max(0, k - 1));
			// Find min of rvalues[k + 1 ... k + sr]
			int minRight = rtree.minIndex(k + 1, k + sr);
			// Shift them towards k.
			long bl = lvalues[minLeft] - (RR - test.spots[k].X) * l;
			long br = rvalues[minRight] - test.spots[k].X * r;
			if (bl + br + test.spots[k].C < best) {
				best = bl + br + test.spots[k].C;
			}
		}
		return best;
	}

	public String[] solve() {
		String[] result = new String[T];
		for (int t = 0; t < T; t++) {
			result[t] = String.format("Case #%d: %d", t + 1, _solve(tests[t]));
		}
		return result;
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		Solution solution = new Solution(in);
		String[] result = solution.solve();
		for (String c : result) {
			out.println(c);
		}
		out.close();
	}

}
