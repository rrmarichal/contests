using System;
using System.Collections.Generic;
using System.IO;

class TestInfo {
	internal string B;
	internal Tuple<int, int>[] queries;

	public TestInfo(string B, Tuple<int, int>[] queries) {
		this.B = B;
		this.queries = queries;
	}
}

public class Solution {
	private int T;
	private List<TestInfo> tests;

	public Solution(TextReader reader) {
		T = int.Parse(reader.ReadLine());
		tests = new List<TestInfo>();
		for (int t = 0; t < T; t++) {
			int Q = int.Parse(reader.ReadLine().Split(' ')[1]);
			string B = reader.ReadLine();
			Tuple<int, int>[] queries = new Tuple<int, int>[Q];
			for (int j = 0; j < Q; j++) {
				String[] qs = reader.ReadLine().Split(' ');
				queries[j] = Tuple.Create(int.Parse(qs[0]), int.Parse(qs[1]));
			}
			tests.Add(new TestInfo(B, queries));
		}
	}

	private int _Solve(TestInfo testInfo) {
		int[,] cumm = new int[testInfo.B.Length, 26];
		cumm[0, testInfo.B[0] - 65] = 1;
		for (int j = 1; j < testInfo.B.Length; j++) {
			for (int k = 0; k < 26; k++) {
				cumm[j, k] = cumm[j - 1, k];
				if (k == testInfo.B[j] - 65) {
					cumm[j, k]++;
				}
			}
		}
		int total = 0;
		foreach (var query in testInfo.queries) {
			bool odd = false;
			int j = 0;
			for (j = 0; j < 26; j++) {
				int l = query.Item1 == 1 ? 0 : cumm[query.Item1 - 2, j];
				int r = cumm[query.Item2 - 1, j];
				if ((r - l) % 2 == 1) {
					if (odd) {
						break;
					}
					odd = true;
				}
			}
			if (j == 26) {
				total++;
			}
		}
		return total;
	}

	public string[] Solve() {
		string[] result = new string[T];
		for (int t = 0; t < T; t++) {
			result[t] = String.Format("Case #{0}: {1}", t + 1, _Solve(tests[t]));
		}
		return result;
	}

	public static void Main(string[] args) {
		Solution solution = new Solution(Console.In);
		string[] result = solution.Solve();
		foreach (var tcase in result) {
			Console.WriteLine(tcase);
		}
	}
}
