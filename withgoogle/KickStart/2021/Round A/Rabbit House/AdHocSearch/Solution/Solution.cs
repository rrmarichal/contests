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
	public TestInfo(int[,] Grid) {
		this.Grid = Grid;
	}

	public int[,] Grid { get; private set; }
}

public class Solution {

	private int T;
	private List<TestInfo> tests;

	public Solution(InputReader reader) {
		T = reader.NextInt().Value;
		tests = new List<TestInfo>();
		for (int t = 0; t < T; t++) {
			int R = reader.NextInt().Value, C = reader.NextInt().Value;
			var G = new int[R, C];
			for (var r = 0; r < R; r++) {
				for (var c = 0; c < C; c++) {
					G[r, c] = reader.NextInt().Value;
				}
			}
			tests.Add(new TestInfo(G));
		}
	}

	private long _Solve(TestInfo testInfo) {
		long blocks = 0;
		var grid = testInfo.Grid;
		var checks = new bool[grid.GetLength(0), grid.GetLength(1)];
		while (true) {
			int maxR = -1, maxC = -1;
			for (var r = 0; r < grid.GetLength(0); r++) {
				for (var c = 0; c < grid.GetLength(1); c++) {
					if (!checks[r, c] && (maxR == -1 || grid[r, c] > grid[maxR, maxC])) {
						maxR = r;
						maxC = c;
					}
				}
			}
			if (maxR == -1 || grid[maxR, maxC] <= 1) {
				break;
			}
			checks[maxR, maxC] = true;

			if (maxR > 0 && grid[maxR - 1, maxC] < grid[maxR, maxC]) {
				var delta = grid[maxR, maxC] - grid[maxR - 1, maxC] - 1;
				grid[maxR - 1, maxC] += delta;
				blocks += delta;
			}

			if (maxR < grid.GetLength(0) - 1 && grid[maxR + 1, maxC] < grid[maxR, maxC]) {
				var delta = grid[maxR, maxC] - grid[maxR + 1, maxC] - 1;
				grid[maxR + 1, maxC] += delta;
				blocks += delta;
			}

			if (maxC > 0 && grid[maxR, maxC - 1] < grid[maxR, maxC]) {
				var delta = grid[maxR, maxC] - grid[maxR, maxC - 1] - 1;
				grid[maxR, maxC - 1] += delta;
				blocks += delta;
			}

			if (maxC < grid.GetLength(1) - 1 && grid[maxR, maxC + 1] < grid[maxR, maxC]) {
				var delta = grid[maxR, maxC] - grid[maxR, maxC + 1] - 1;
				grid[maxR, maxC + 1] += delta;
				blocks += delta;
			}
		}
		return blocks;
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
