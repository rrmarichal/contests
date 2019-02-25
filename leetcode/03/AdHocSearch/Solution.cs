using System;
using System.Collections.Generic;

namespace AdHocSearch {

    public class Solution {

        public int LengthOfLongestSubstring(string s) {
            var last = new Dictionary<char, int>();
            var start = 0;
            var max = 0;
            for (var j = 0; j < s.Length; j++) {
                var c = s[j];
                if (!last.ContainsKey(c)) {
                    last.Add(c, j);
                }
                else {
                    start = Math.Max(start, last[c] + 1);
                    last[c] = j;
                }
                if (j - start + 1 > max) {
                    max = j - start + 1;
                }
            }
            return max;
        }

    }

}
