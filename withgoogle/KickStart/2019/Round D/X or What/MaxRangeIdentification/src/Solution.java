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

class QueryItem {
	int index, value;

	public QueryItem(int index, int value) {
		this.index = index;
		this.value = value;
	}
}

class TestInfo {
	int N, Q;
	int[] A;
	QueryItem[] queries;

    public TestInfo(int N, int Q, int[] A, QueryItem[] queries) {
		this.N = N;
		this.Q = Q;
		this.A = A;
		this.queries = queries;
    }
}

public class Solution {

    private int T;
	private TestInfo[] tests;
	boolean[] oddity;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
			int N = in.nextInt(), Q = in.nextInt();
			int[] A = new int[N];
			for (int j = 0; j < N; j++) {
				A[j] = in.nextInt();
			}
			QueryItem[] queries = new QueryItem[Q];
			for (int j = 0; j < Q; j++) {
				queries[j] = new QueryItem(in.nextInt(), in.nextInt());
			}
            tests[t] = new TestInfo(N, Q, A, queries);
        }
    }

	private int[] _solve(TestInfo test) {
		int[] result = new int[test.Q];
		TreeSet<Integer> odds = new TreeSet<Integer>();
		for (int j = 0; j < test.N; j++) {
			if (oddity[test.A[j]]) {
				odds.add(j);
			}
		}
		for (int j = 0; j < test.Q; j++) {
			QueryItem q = test.queries[j];
			if (oddity[test.A[q.index]] != oddity[q.value]) {
				if (oddity[q.value]) {
					odds.add(q.index);
				}
				else {
					odds.remove(q.index);
				}
				test.A[q.index] = q.value;
			}
			if (odds.size() % 2 == 0) {
				result[j] = test.N;
			}
			else {
				result[j] = Math.max(odds.first(), test.N - odds.last() - 1);
				if (test.N - odds.first() - 1 > result[j]) {
					result[j] = test.N - odds.first() - 1;
				}
				if (odds.last() > result[j]) {
					result[j] = odds.last();
				}
			}
		}
		return result;
	}

    public String[] solve() {
		String[] result = new String[T];
		oddity = new boolean[1024];
		for (int j = 0; j < 1024; j++) {
			int k = j, c = 0;
			while (k > 0) {
				if ((k & 1) != 0) c++;
				k >>= 1;
			}
			oddity[j] = c % 2 != 0;
		}
        for (int t = 0; t < T; t++) {
			int[] R = _solve(tests[t]);
			StringBuilder _case = new StringBuilder(String.format("Case #%d:", t + 1));
			for (int j = 0; j < R.length; j++) {
				_case.append(String.format(" %d", R[j]));
			}
            result[t] = _case.toString();
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
