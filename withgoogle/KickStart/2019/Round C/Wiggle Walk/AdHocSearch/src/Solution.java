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
	int N,  R, C, sr, sc;
	String moves;

	public TestInfo(int N, int R, int C, int sr, int sc, String moves) {
		this.N = N;
		this.R = R;
		this.C = C;
		this.sr = sr;
		this.sc = sc;
		this.moves = moves;
	}
}

public class Solution {

	private static final int[][] MOVE = new int[][] { {-1, 0}, {1, 0}, {0, 1}, {0, -1} };

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
			int N = in.nextInt(), R = in.nextInt(), C = in.nextInt(), sr = in.nextInt(), sc = in.nextInt();
			String moves = in.nextLine();
            tests[t] = new TestInfo(N, R, C, sr, sc, moves);
        }
    }

	private int _encode(int r, int c) {
		return r * 50000 + c;
	}

	private int _index(char move) {
		switch (move) {
			case 'N': return 0;
			case 'S': return 1;
			case 'E': return 2;
			default: return 3;
		}
	}

	private String _solve(TestInfo test) {
		HashSet<Integer> visited = new HashSet<Integer>();
		int r = test.sr, c = test.sc;
		visited.add(_encode(r, c));
		for (int j = 0; j < test.N; j++) {
			int index = _index(test.moves.charAt(j));
			do {
				r += MOVE[index][0];
				c += MOVE[index][1];
			} while (visited.contains(_encode(r, c)));
			visited.add(_encode(r, c));
		}
		return String.format("%d %d", r, c);
	}

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            result[t] = String.format("Case #%d: %s", t + 1, _solve(tests[t]));
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
