using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;

public class LineTokenizer {
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
	internal int N, K, P;
	internal List<List<int>> plates;

	public TestInfo(int N, int K, int P, List<List<int>> plates) {
		this.N = N;
		this.K = K;
		this.P = P;
		this.plates = plates;
	}
}

public class Solution {
	private int T;
	private List<TestInfo> tests;

	public Solution(InputReader reader) {
		T = reader.NextInt().Value;
		tests = new List<TestInfo>();
		for (int t = 0; t < T; t++) {
			int N = reader.NextInt().Value, K = reader.NextInt().Value, P = reader.NextInt().Value;
			var plates = new List<List<int>>();
			for (var j = 0; j < N; j++) {
				plates.Add(new List<int>());
				plates[j].Add(0);
				for (var k = 1; k <= K; k++) {
					plates[j].Add(plates[j][k - 1] + reader.NextInt().Value);
				}
			}
			tests.Add(new TestInfo(N, K, P, plates));
		}
	}

	private int _Solve(TestInfo testInfo) {
		var D = new int[2, Math.Max(testInfo.K, testInfo.P) + 1];
		for (var k = 1; k <= Math.Min(testInfo.K, testInfo.P); k++) {
			D[0, k] = testInfo.plates[0][k];
		}
		var best = D[0, Math.Min(testInfo.K, testInfo.P)];
		for (var j = 1; j < testInfo.N; j++) {
			for (var p = 1; p <= testInfo.P; p++) {
				D[j % 2, p] = D[(j - 1) % 2, p];
				for (var k = 1; k <= Math.Min(p, testInfo.K); k++) {
					D[j % 2, p] = Math.Max(D[j % 2, p], testInfo.plates[j][k] + D[(j - 1) % 2, p - k]);
				}
				if (D[j % 2, p] > best) {
					best = D[j % 2, p];
				}
			}
		}
		return best;
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
