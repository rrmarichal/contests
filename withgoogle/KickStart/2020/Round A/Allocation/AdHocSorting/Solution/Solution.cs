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
	internal int balance;
	internal List<int> prices;

	public TestInfo(int balance, List<int> prices) {
		this.balance = balance;
		this.prices = prices;
	}
}

public class Solution {
	private int T;
	private List<TestInfo> tests;

	public Solution(InputReader reader) {
		T = reader.NextInt().Value;
		tests = new List<TestInfo>();
		for (int t = 0; t < T; t++) {
			int N = reader.NextInt().Value;
			int B = reader.NextInt().Value;
			var A = new List<int>();
			for (var j = 0; j < N; j++) {
				A.Add(reader.NextInt().Value);
			}
			tests.Add(new TestInfo(B, A));
		}
	}

	private int _Solve(TestInfo testInfo) {
		testInfo.prices.Sort();
		int current = 0, index = 0;
		while (index < testInfo.prices.Count && current + testInfo.prices[index] <= testInfo.balance) {
			current += testInfo.prices[index++];
		}
		return index;
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
