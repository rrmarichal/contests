using System;
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
	internal int K, max;
	internal List<int> gaps;

	public TestInfo(int K, List<int> gaps, int max) {
		this.K = K;
		this.gaps = gaps;
		this.max = max;
	}
}

public class Solution {
	private int T;
	private List<TestInfo> tests;

	public Solution(InputReader reader) {
		T = reader.NextInt().Value;
		tests = new List<TestInfo>();
		for (int t = 0; t < T; t++) {
			int N = reader.NextInt().Value, K = reader.NextInt().Value, previous = reader.NextInt().Value, max = 1;
			var gaps = new List<int>();
			for (var j = 1; j < N; j++) {
				var next = reader.NextInt().Value;
				if (next - previous > 1) {
					gaps.Add(next - previous);
					if (next - previous > max) {
						max = next - previous;
					}
				}
				previous = next;
			}
			tests.Add(new TestInfo(K, gaps, max));
		}
	}

	private bool _Fits(int gap, TestInfo testInfo) {
		var k = testInfo.K;
		foreach (var g in testInfo.gaps) {
			if (g > gap) {
				k -= (g - 1) / gap;
				if (k < 0) {
					return false;
				}
			}
		}
		return true;
	}

	private int _Solve(TestInfo testInfo) {
		var left = 1;
		var right = testInfo.max;
		while (right > left) {
			var gap = (right + left) / 2;
			if (_Fits(gap, testInfo)) {
				right = gap;
			} else {
				left = gap + 1;
			}
		}
		return left;
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
