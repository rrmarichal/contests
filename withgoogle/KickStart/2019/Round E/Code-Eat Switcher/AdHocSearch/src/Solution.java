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

class SlotInfo implements Comparable<SlotInfo> {
	int C, E;
	
	public SlotInfo(int C, int E) {
		this.C = C;
		this.E = E;
	}

	@Override
	public int compareTo(SlotInfo o) {
		return Integer.compare(E * o.C, C * o.E);
	}
}

class TestInfo {
	int D, S;
	SlotInfo[] slots, days;

    public TestInfo(int D, int S, SlotInfo[] slots, SlotInfo[] days) {
		this.D = D;
		this.S = S;
		this.slots = slots;
		this.days = days;
    }
}

public class Solution {

    private int T;
	private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
			int D = in.nextInt(), S = in.nextInt();
			SlotInfo[] slots = new SlotInfo[S], days = new SlotInfo[D];
			for (int j = 0; j < S; j++) {
				int c = in.nextInt(), d = in.nextInt();
				slots[j] = new SlotInfo(c, d);
			}
			for (int j = 0; j < D; j++) {
				int a = in.nextInt(), b = in.nextInt();
				days[j] = new SlotInfo(a, b);
			}
            tests[t] = new TestInfo(D, S, slots, days);
        }
    }

	private String _solve(TestInfo test) {
		Arrays.sort(test.slots);
		int[] csum = new int[test.S], esum = new int[test.S];
		csum[0] = test.slots[0].C;
		for (int j = 1; j < test.S; j++) {
			csum[j] = csum[j - 1] + test.slots[j].C;
		}
		esum[test.S - 1] = test.slots[test.S - 1].E;
		for (int j = test.S - 2; j >= 0; j--) {
			esum[j] = esum[j + 1] + test.slots[j].E;
		}
		StringBuilder result = new StringBuilder(test.D);
		for (int day = 0; day < test.D; day++) {
			int index = Arrays.binarySearch(csum, test.days[day].C);
			if (index < 0) {
				index = -index - 1;
			}
			// Invariant is: csum[index] <= test.days[day].C
			if (index < test.S) {
				int cc = index > 0 ? test.days[day].C - csum[index - 1] : test.days[day].C;
				double E = (1 - (double) cc/test.slots[index].C) * test.slots[index].E;
				if (index + 1 < test.S) {
					E += esum[index + 1];
				}
				result.append(E >= test.days[day].E ? 'Y' : 'N');
			}
			else {
				result.append('N');
			}
		}
		return result.toString();
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
