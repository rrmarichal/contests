using System;
using System.Collections.Generic;

namespace SingleBinSearch {

    public class Solution {

        public double FindMedianSortedArrays(int[] A, int[] B) {
            if (A.Length > B.Length) {
                var T = A;
                A = B;
                B = T;
            }
            int n = A.Length, m = B.Length, p = (n + m + 1) / 2;
            int l = 0, r = n - 1;
            while (true) {
                int x = l + (r - l - 1) / 2, y = p - x - 2;
                if (x + 1 < n && y >= 0 && A[x + 1] < B[y]) {
                    l = x + 1;
                }
                else
                if (y + 1 < m && x >= 0 && B[y + 1] < A[x]) {
                    r = x - 1;
                }
                else {
                    return (n + m) % 2 == 1
                        ? OneMax(A, B, x, y)
                        : TwoMin(A, B, x, y);
                }
            }
        }

        private double OneMax(int[] A, int[] B, int x, int y) {
            if (x >= 0 && y >= 0) {
                return Math.Max(A[x], B[y]);
            }
            if (x < 0) {
                return B[y];
            }
            return A[x];
        }

        private double TwoMin(int[] A, int[] B, int x, int y) {
            var l = new List<int>();
            l.Add(x >= 0 ? A[x] : Int32.MinValue);
            l.Add(x + 1 < A.Length ? A[x + 1] : Int32.MaxValue);

            l.Add(y >= 0 ? B[y] : Int32.MinValue);
            l.Add(y + 1 < B.Length ? B[y + 1] : Int32.MaxValue);

            l.Sort();
            return (l[1] + l[2]) / 2.0;
        }

    }

}
