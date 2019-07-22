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

class StoneInfo implements Comparable<StoneInfo> {
	int S, E, L;
	
	public StoneInfo(int S, int E, int L) {
		this.S = S;
		this.E = E;
		this.L = L;
	}

	@Override
	public int compareTo(StoneInfo o) {
		if (S * o.L != o.S * L) {
			return Integer.compare(S * o.L, o.S * L);
		}
		return Integer.compare(E, o.E);
	}

	@Override
	public String toString() {
		return String.format("S: %d E: %d L: %d D: %.2f", S, E, L, (float) E/L);
	}
}

class TestInfo {
	int N;
	StoneInfo[] stones;

    public TestInfo(int N, StoneInfo[] stones) {
		this.N = N;
		this.stones = stones;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
			int N = in.nextInt();
			StoneInfo[] stones = new StoneInfo[N];
			for (int j = 0; j < N; j++) {
				stones[j] = new StoneInfo(in.nextInt(), in.nextInt(), in.nextInt());
			}
			tests[t] = new TestInfo(N, stones);
        }
	}

	private int _solve(TestInfo testInfo) {
		StoneInfo[] stones = Arrays.copyOf(testInfo.stones, testInfo.N);
		Arrays.sort(stones);
		int K = stones[0].S;
		for (int j = 1; j < testInfo.N; j++) {
			K += stones[j].S;
		}
		int[] T = new int[K + 1];
		int best = 0;
		for (int j = 0; j < testInfo.N; j++) {
			StoneInfo stone = stones[j];
			for (int k = K; k >= stone.S; k--) {
				int W = (k - stone.S) * stone.L > stone.E
					? 0
					: stone.E - (k - stone.S) * stone.L;
				T[k] = Math.max(T[k], W + T[k - stone.S]);
				if (T[k] > best) {
					best = T[k];
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
        for (String c: result) {
            out.println(c);
		}
        out.close();
    }

}
