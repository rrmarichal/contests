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

class DogInfo implements Comparable<DogInfo> {
	int color, offset;

	public DogInfo(int offset) {
		this.offset = offset;
	}

	@Override
	public int compareTo(DogInfo o) {
		return Integer.compare(offset, o.offset);
	}
}

class TestInfo {
	int N, K;
	DogInfo[] dogs;

    public TestInfo(int N, int K, DogInfo[] dogs) {
		this.N = N;
		this.K = K;
		this.dogs = dogs;
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
			DogInfo[] dogs = new DogInfo[N];
			for (int j = 0; j < N; j++) {
				dogs[j] = new DogInfo(in.nextInt());
			}
			for (int j = 0; j < N; j++) {
				dogs[j].color = in.nextInt();
			}
            tests[t] = new TestInfo(N, K, dogs);
        }
    }

	private int _solve(TestInfo test) {
		Arrays.sort(test.dogs);
		int[][] T = new int[test.N][test.K + 1];
		int best = Integer.MAX_VALUE;
		for (int j = 0; j < test.N; j++) {
			T[j][1] = test.dogs[j].offset;
		}
		for (int k = 2; k <= test.K; k++) {
			for (int j = 0; j < test.N; j++) {
				T[j][k] = Integer.MAX_VALUE;
				for (int p = 0; p < test.N; p++) {
					if (p == j) {
						continue;
					}
					if (T[p][k - 1] == 0) {
						continue;
					}
					if (test.dogs[p].color != test.dogs[j].color) {
						T[j][k] = Math.min(T[j][k], T[p][k - 1] + test.dogs[p].offset + test.dogs[j].offset);
					}
					else {
						T[j][k] = Math.min(T[j][k], T[p][k - 1] + Math.abs(test.dogs[j].offset - test.dogs[p].offset));
					}
				}
				if (k == test.K && T[j][k] < best) {
					best = T[j][k];
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
