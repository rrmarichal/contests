using System;
using System.Collections.Generic;

namespace TopSort {

	class Solution {

		private int length;
		private int[] path;
		private byte[] status;

		private bool Dfs(int current , Dictionary<int, List<int>> G) {
			status[current] = 1;
			var result = true;
			if (G.ContainsKey(current)) {
				foreach (var next in G[current]) {
					if (status[next] == 1) {
						return false;
					}
					if (status[next] == 0) {
						result = result && Dfs(next, G);
					}
				}
			}
			path[length++] = current;
			status[current] = 2;
			return result;
		}

		public int[] FindOrder(int numCourses, int[][] prerequisites) {
			var G = new Dictionary<int, List<int>>();
			for (var j = 0; j < prerequisites.Length; j++) {
				if (!G.ContainsKey(prerequisites[j][0])) {
					G.Add(prerequisites[j][0], new List<int>());
				}
				G[prerequisites[j][0]].Add(prerequisites[j][1]);
			}
			status = new byte[numCourses];
			path = new int[numCourses];
			var pathIndex = 0;
			var order = new int[numCourses];
			for (var j = 0; j < numCourses; j++) {
				if (status[j] == 0) {
					length = 0;
					var valid = Dfs(j, G);
					if (!valid) {
						return new int[] {};
					}
					for (var k = 0; k < length; k++) {
						order[pathIndex++] = path[k];
					}
				}
			}
			return order;
		}

		static void Main(string[] args) {
			int N = int.Parse(Console.ReadLine());
			int M = int.Parse(Console.ReadLine());
			int[][] G = new int[M][];
			for (int j = 0; j < M; j++) {
				var line = Console.ReadLine().Split(" ", StringSplitOptions.RemoveEmptyEntries);
				G[j] = new int[] {
					int.Parse(line[0]),
					int.Parse(line[1])
				};
			}
			var order = new Solution().FindOrder(N, G);
			if (order.Length > 0) {
				for (int j = 0; j < order.Length - 1; j++) {
					Console.Write("{0} ", order[j]);
				}
				Console.WriteLine("{0}", order[order.Length - 1]);
			}
			else {
				Console.WriteLine("NO ORDER");
			}
		}

	}

}
