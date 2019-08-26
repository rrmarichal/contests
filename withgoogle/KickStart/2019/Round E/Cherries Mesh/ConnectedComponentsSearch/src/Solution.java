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

class TestInfo {
	int N, M;
	HashMap<Integer, List<Integer>> G;

    public TestInfo(int N, int M, HashMap<Integer, List<Integer>> G) {
		this.N = N;
		this.M = M;
		this.G = G;
    }
}

public class Solution {

    private int T;
	private TestInfo[] tests;
	private boolean[] visited;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
			int N = in.nextInt(), M = in.nextInt();
			HashMap<Integer, List<Integer>> G = new HashMap<Integer, List<Integer>>();
			for (int j = 0; j < M; j++) {
				int c = in.nextInt(), d = in.nextInt();
				c--; d--;

				if (!G.containsKey(c)) {
					G.put(c, new ArrayList<Integer>());
				}
				G.get(c).add(d);

				if (!G.containsKey(d)) {
					G.put(d, new ArrayList<Integer>());
				}
				G.get(d).add(c);
			}
            tests[t] = new TestInfo(N, M, G);
        }
    }

	private int _dfs(int node, HashMap<Integer, List<Integer>> G) {
		visited[node] = true;
		int count = 1;
		if (G.get(node) != null) {
			for (int o: G.get(node)) {
				if (!visited[o]) {
					count += _dfs(o, G);
				}
			}
		}
		return count;
	}

	private int _solve(TestInfo test) {
		visited = new boolean[test.N];
		int cc = 0, blackCovered = 0;
		for (int j = 0; j < test.N; j++) {
			if (!visited[j]) {
				cc++;
				blackCovered += _dfs(j, test.G) - 1;
			}
		}
		return blackCovered + 2 * (cc - 1);
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
