using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;

class LazySegmentTree {

	private readonly int count, size;
	private readonly int[] tree, lazy;

	public static LazySegmentTree Create(List<int> values) {
		return new LazySegmentTree(values);
	}

	protected LazySegmentTree(List<int> values) {
		count = values.Count;
		size = NextPowerOfTwo(values.Count);
		tree = new int[(size << 1) - 1];
		lazy = new int[(size << 1) - 1];
		BuildTree(values, 0, 0, size - 1);
	}

	private static int NextPowerOfTwo(int value) {
		if (value == 0) {
			return 1;
		}
		// Check value is a power of two.
		if ((value & (value - 1)) == 0) {
			return value;
		}
		int trail = 0;
		while (value > 0) {
			trail++;
			value >>= 1;
		}
		return 1 << trail;
	}

	private void BuildTree(List<int> values, int current, int low, int high) {
		if (low == high) {
			tree[current] = low < values.Count ? values[low] : int.MinValue;
		}
		else {
			int mid = (low + high) >> 1, left = (current << 1) + 1, right = left + 1;
			BuildTree(values, left, low, mid);
			BuildTree(values, right, mid + 1, high);
			tree[current] = Math.Max(tree[left], tree[right]);
		}
	}


	public int Query(int low, int high) {
		if (low < 0 || low > high || high >= count) {
			throw new ArgumentException("Invalid range.");
		}
		return Query(0, 0, size - 1, low, high);
	}

	private int Query(int current, int treeLow, int treeHigh, int low, int high) {
		// No overlap.
		if (treeHigh < low || treeLow > high) {
			return int.MinValue;
		}
		int left = (current << 1) + 1, right = left + 1;
		if (lazy[current] != 0) {
			tree[current] += lazy[current];
			// If current is not a leaf, propagate updates to its children.
			if (current < size - 1) {
				lazy[left] += lazy[current];
				lazy[right] += lazy[current];
			}
			lazy[current] = 0;
		}
		// Total overlap.
		if (low <= treeLow && high >= treeHigh) {
			return tree[current];
		}
		// Partial overlap.
		int mid = (treeLow + treeHigh) >> 1;
		int ml = Query(left, treeLow, mid, low, high);
		int mr = Query(right, mid + 1, treeHigh, low, high);
		return Math.Max(ml, mr);
	}

	public void Update(int low, int high, int value) {
		if (low < 0 || low > high || high >= count) {
			throw new ArgumentException("Invalid range.");
		}
		Update(0, 0, size - 1, low, high, value);
	}

	private void Update(int current, int treeLow, int treeHigh, int low, int high, int value) {
		// No overlap.
		if (treeHigh < low || treeLow > high) {
			return;
		}
		int left = (current << 1) + 1, right = left + 1;
		// Total overlap.
		if (low <= treeLow && high >= treeHigh) {
			tree[current] += lazy[current] + value;
			// If current is not a leaf, propagate updates to its children.
			if (current < size - 1) {
				lazy[left] += lazy[current] + value;
				lazy[right] += lazy[current] + value;
			}
			lazy[current] = 0;
		}
		// Partial overlap.
		else {
			int mid = (treeLow + treeHigh) >> 1;
			Update(left, treeLow, mid, low, high, value);
			Update(right, mid + 1, treeHigh, low, high, value);
			tree[current] = Math.Max(tree[left] + lazy[left], tree[right] + lazy[right]);
		}
	}

}

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
		int max = 1;
		var indices = new Dictionary<int, List<int>>();
		var values = new List<int>();
		var occurrences = new Dictionary<int, int>();
		int current = 0;
		for (int j = 0; j < testInfo.A.Length; j++) {
			if (!occurrences.ContainsKey(testInfo.A[j])) {
				occurrences.Add(testInfo.A[j], 1);
			}
			else {
				occurrences[testInfo.A[j]]++;
			}
			if (occurrences[testInfo.A[j]] <= testInfo.S) {
				current++;
				if (current > max) {
					max = current;
				}
			}
			else
			if (occurrences[testInfo.A[j]] == testInfo.S + 1) {
				current -= testInfo.S;
			}
			values.Add(current);
			if (!indices.ContainsKey(testInfo.A[j])) {
				indices.Add(testInfo.A[j], new List<int>());
			}
			indices[testInfo.A[j]].Add(j);
		}
		var tree = LazySegmentTree.Create(values);
		var offset = new Dictionary<int, int>();
		for (int j = 1; j < testInfo.A.Length; j++) {
			int prev = testInfo.A[j - 1];
			if (!offset.ContainsKey(prev)) {
				offset.Add(prev, 0);
			}
			int x = 0, y = -1;
			if (offset[prev] + testInfo.S < indices[prev].Count) {
				x = indices[prev][offset[prev] + testInfo.S] - 1;
				if (offset[prev] + testInfo.S + 1 < indices[prev].Count) {
					y = indices[prev][offset[prev] + testInfo.S + 1] - 1;
				}
				else {
					y = testInfo.A.Length - 1;
				}
			}
			else {
				x = testInfo.A.Length - 1;
			}
			tree.Update(j - 1, x, -1);
			if (y != -1) {
				tree.Update(x + 1, y, testInfo.S);
			}
			offset[testInfo.A[j - 1]]++;
			var cmax = tree.Query(0, values.Count - 1);
			if (cmax > max) {
				max = cmax;
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
