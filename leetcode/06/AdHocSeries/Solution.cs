using System;
using System.Text;

namespace AdHocSeries {

    public class Solution {

        public string Convert(string S, int R) {
            if (R == 1) {
                return S;
            }
            var sb = new StringBuilder();
            for (var r = 0; r < R; r++) {
                var mult = 0;
                while (mult * (R - 1) - r < S.Length) {
                    if (mult * (R - 1) - r >= 0) {
                        sb.Append(S[mult * (R - 1) - r]);
                    }
                    if (r > 0 && r < R - 1 && mult * (R - 1) + r < S.Length) {
                        sb.Append(S[mult * (R - 1) + r]);
                    }
                    mult += 2;
                }
            }
            return sb.ToString();
        }

    }

}
