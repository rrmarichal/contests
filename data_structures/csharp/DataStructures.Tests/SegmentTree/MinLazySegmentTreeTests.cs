using System;
using System.Collections.Generic;
using System.Linq;
using DataStructures;
using NUnit.Framework;

namespace DataStructures.SegmentTree.Tests {

	public class MinLazySegmentTreeTests {

		[Test]
		public void RandomTest() {
			const int N = 1000, MAX = 10000, T = 1000, Q = 50;
			var random = new Random();
			var values = new List<int>(N);
			for (int j = 0; j < N; j++) {
				values.Add(random.Next(MAX));
			}
			// Console.Error.WriteLine(values.Aggregate("", (acc, c) => acc + c.ToString() + ", "));
			var tree = LazySegmentTree.Create(values, new MinOperation());
			for (int t = 0; t < T; t++) {
				// Update a random interval then query.
				int low = random.Next(N);
				int high = low + random.Next(N - low);
				int value = random.Next(MAX);
				// Console.Error.WriteLine("UPD: t: {0}. low: {1}. high: {2}. value: {3}", t, low, high, value);
				tree.Update(low, high, value);
				BruteForceUpdate(values, low, high, value);
				for (int q = 0; q < Q; q++) {
					int l = random.Next(N);
					int h = l + random.Next(N - l);
					// Console.Error.WriteLine("QRY: t: {0}. q: {1}. l: {2}. h: {3}", t, q, l, h);
					Assert.AreEqual(BruteForceQuery(values, l, h), tree.Query(l, h));
				}
			}
		}

		private int BruteForceQuery(List<int> values, int low, int high) {
			return values.Skip(low).Take(high - low + 1).Aggregate(int.MaxValue, (max, current) => Math.Min(max, current));
		}

		private void BruteForceUpdate(List<int> values, int low, int high, int value) {
			for (int j = low; j <= high; j++) {
				values[j] += value;
			}
		}

	}

}
