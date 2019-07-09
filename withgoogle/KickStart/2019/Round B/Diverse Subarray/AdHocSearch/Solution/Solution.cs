using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;

internal class LineTokenizer {
	private string[] tokens;
	private int index;

	public LineTokenizer(string line) : this(line, new[] { ' ' }) {}

	public LineTokenizer(string line, char[] separators) {
		tokens = line.Split(separators, StringSplitOptions.RemoveEmptyEntries);
		index = -1;
	}

	public string Current => tokens[index];

	public bool MoveNext() {
		return ++index < tokens.Length;
	}
}

public class InputReader {
    private TextReader reader;
	private LineTokenizer tokenizer;

    public InputReader(TextReader reader) {
        this.reader = reader;
    }

    private bool EnsureTokenizer() {
        if (tokenizer == null || !tokenizer.MoveNext()) {
            String line = NextLine();
            if (line != null) {
                tokenizer = new LineTokenizer(line);
				return tokenizer.MoveNext();
            }
			return false;
        }
        return true;
    }

    public string NextLine() {
		tokenizer = null;
		return reader.ReadLine();
    }

    public string NextToken() {
        if (EnsureTokenizer()) {
            return tokenizer.Current;
        }
        return null;
    }

    public int? NextInt() {
        if (EnsureTokenizer()) {
            return int.Parse(tokenizer.Current);
        }
        return null;
    }

    public long? NextLong() {
        if (EnsureTokenizer()) {
            return long.Parse(tokenizer.Current);
        }
        return null;
    }
}

class TestInfo {
	public TestInfo(int S, int[] A) {
		this.S = S;
		this.A = A;
	}
	public int S { get; private set; }

	public int[] A { get; private set; }
}

public class Solution {

	private int T;
	private List<TestInfo> tests;

	public Solution(InputReader reader) {
		T = reader.NextInt().Value;
		tests = new List<TestInfo>();
		for (var t = 0; t < T; t++) {
			int N = reader.NextInt().Value, S = reader.NextInt().Value;
			int[] A = new int[N];
			for (var j = 0; j < N; j++) {
				A[j] = reader.NextInt().Value;
			}
			tests.Add(new TestInfo(S, A));
		}
	}

	private int _Solve(TestInfo testInfo) {
		var max = 1;
		for (var j = 0; j < testInfo.A.Length; j++) {
			var occ = new Dictionary<int, int>();
			int current = 0, best = 0;
			for (int k = j; k < testInfo.A.Length; k++) {
				if (!occ.ContainsKey(testInfo.A[k])) {
					occ.Add(testInfo.A[k], 1);
				}
				else {
					occ[testInfo.A[k]]++;
				}
				if (occ[testInfo.A[k]] <= testInfo.S) {
					current++;
				}
				else
				if (occ[testInfo.A[k]] == testInfo.S + 1) {
					current -= testInfo.S;
				}
				if (current > best) {
					best = current;
				}
			}
			if (best > max) {
				max = best;
			}
		}
		return max;
	}

	public string[] Solve() {
		string[] result = new string[T];
		for (int t = 0; t < T; t++) {
			result[t] = String.Format("Case #{0}: {1}", t + 1, _Solve(tests[t]));
		}
		return result;
	}

	public static void Main(string[] args) {
		Solution solution = new Solution(new InputReader(Console.In));
		string[] result = solution.Solve();
		foreach (var tcase in result) {
			Console.WriteLine(tcase);
		}
	}

}
