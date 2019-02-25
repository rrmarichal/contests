using System;
using System.Collections.Generic;
using System.Linq;

namespace AdHocSearch {

	public class Solution {

		public IList<int> PowerfulIntegers(int x, int y, int bound) {
			var set = new HashSet<int>();
			for (var i = 0; i < 22; i++) {
				var xi = Math.Pow(x, i);
				if (xi > bound) {
					break;
				}
				for (var j = 0; j < 22; j++) {
					var yj = Math.Pow(y, j);
					if (yj > bound) {
						break;
					}
					var result = (int)(xi + yj);
					if (result <= bound && !set.Contains(result)) {
						set.Add(result);
					}
				}
			}
			return set.ToList();
		}
        
	}

}
