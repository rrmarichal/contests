using System;
using System.Collections.Generic;

namespace AdHocSorting {

	public class Solution {

		private int FindMax(int[] A, int current) {
			var max = 0;
			for (var j = 1; j <= current; j++) {
				if (A[j] > A[max]) {
					max = j;
				}
			}
			return max;
		}

		private void Flip(int[] A, int current) {
			for (var j = 0; j < (current + 1) / 2; j++) {
				var temp = A[j];
				A[j] = A[current - j];
				A[current - j] = temp;
			}
		}

		public IList<int> PancakeSort(int[] A) {
			var current = A.Length - 1;
			var result = new List<int>();
			for (int j = 0; j < A.Length - 1; j++, current--) {
				var max = FindMax(A, current);
				if (max != current) {
					if (max != 0) {
						result.Add(max + 1);
						Flip(A, max);
					}
					result.Add(current + 1);
					Flip(A, current);
				}
			}
			return result;
		}
	}
    
}
