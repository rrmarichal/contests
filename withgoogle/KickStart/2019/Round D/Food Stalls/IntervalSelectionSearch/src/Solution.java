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

	private int _min(TestInfo test, int l, int r) {
		int min = l;
		for (int j = l + 1; j <= r; j++) {
			if (test.spots[j].C < test.spots[min].C) {
				min = j;
			}
		}
		return min;
	}

	long _best2Left(TestInfo test, int index, int count) {
		long[] items = new long[index + 1];
		for (int j = 0; j <= index; j++) {
			items[j] = test.spots[j].C + test.spots[index].X - test.spots[j].X;
		}
		Arrays.sort(items);
		long best = 0;
		for (int j = 0; j < count; j++) {
			best += items[j];
		}
		return best;
	}

	long _best2Right(TestInfo test, int index, int count) {
		long[] items = new long[test.N - index];
		for (int j = test.N - 1; j >= index; j--) {
			items[test.N - 1 - j] = test.spots[j].C + test.spots[j].X - test.spots[index].X;
		}
		Arrays.sort(items);
		long best = 0;
		for (int j = 0; j < count; j++) {
			best += items[j];
		}
		return best;
	}

	private long _solve(TestInfo test) {
		Arrays.sort(test.spots);
		int l = test.K / 2, r = test.K - l;
		long best = Long.MAX_VALUE;
		for (int j = l - 1; j < test.N - r; j++) {
			for (int k = test.N - r; k > j + 1; k--) {
				long bl = _best2Left(test, j, l);
				long br = _best2Right(test, k, r);
				int windex = _min(test, j + 1, k - 1);
				long leftShift = j >= 0 ? (test.spots[windex].X - test.spots[j].X) * l : 0;
				long rightShift = (test.spots[k].X - test.spots[windex].X) * r; 
				long b = bl + leftShift + br + rightShift + test.spots[windex].C;
				if (b < best) {
					best = b;
				}
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
