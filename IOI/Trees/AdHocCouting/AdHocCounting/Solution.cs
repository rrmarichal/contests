using System;

namespace AdHocCounting {

	public class Solution {
        
		private long[] lookup;

		public long PerfectBalancedTressOfWeight(int N) {
			lookup = new long[N + 1];
			return BalancedTrees(N);
		}

		private long BalancedTrees(int N) {
			if (N == 1) {
				return 1;
			}
			if (lookup[N] == 0) {
				long result = (N + 1) / 2;
				for (var b = 2; b <= N / 2; b++) {
					result += BalancedTrees(N / b);
				}
				return lookup[N] = result;
			}
			return lookup[N];
		}

	}

}
