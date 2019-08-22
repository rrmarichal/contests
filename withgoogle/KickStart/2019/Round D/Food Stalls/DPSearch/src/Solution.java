import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

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
        }
        catch (IOException e) {
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

	private long _solve(TestInfo test) {
		Arrays.sort(test.spots);
		int l = test.K / 2, r = test.K - l;
		long best = Long.MAX_VALUE;
		long[][] L = new long[test.N][test.N], R = new long[test.N][test.N];
		for (int k = l; k < test.N - r; k++) {
			// Place the warehouse at index k, l stalls to the left and r stalls to the right.
			long bl = l > 0 ? Long.MAX_VALUE : 0, br = Long.MAX_VALUE;
			for (int i = 1; i <= l; i++) {
				// L[i, j] = best placement for i stalls on the spots 0..j
				for (int j = i - 1; j < k - l + i; j++) {
					L[i][j] = j > i - 1 ? L[i][j - 1] : Long.MAX_VALUE;
					long base = j > 0 ? L[i - 1][j - 1] : 0;
					if (L[i][j] > base + test.spots[j].C + test.spots[k].X - test.spots[j].X) {
						L[i][j] = base + test.spots[j].C + test.spots[k].X - test.spots[j].X;
					}
					if (i == l && bl > L[i][j]) {
						bl = L[i][j];
					}
				}
			}
			for (int i = 1; i <= r; i++) {
				// R[i, j] = best placement for i stalls on the spots k+1..j
				for (int j = test.N - i; j > k + r - i; j--) {
					R[i][j] = j < test.N - i ? R[i][j + 1] : Long.MAX_VALUE;
					long base = j < test.N - 1 ? R[i - 1][j + 1] : 0;
					if (R[i][j] > base + test.spots[j].C + test.spots[j].X - test.spots[k].X) {
						R[i][j] = base + test.spots[j].C + test.spots[j].X - test.spots[k].X;
					}
					if (i == r && br > R[i][j]) {
						br = R[i][j];
					}
				}
			}
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
        for (String c: result) {
            out.println(c);
        }
        out.close();
    }

}
