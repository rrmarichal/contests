using System;
using System.Collections.Generic;
using System.Linq;
using NUnit.Framework;

namespace SegmentTree.Tests {

	public class MaxLazySegmentTreeTests {

		[Test]
		public void QueryTest0() {
			var values = new List<int> { 1, 2, 3 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.AreEqual(3, tree.Query(0, values.Count - 1));
		}

		[Test]
		public void QueryTest1() {
			var values = new List<int> { 1, 2, -3, -4, 8, 0 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.AreEqual(8, tree.Query(0, values.Count - 1));
		}

		[Test]
		public void QueryTest2() {
			var values = new List<int> { 1, 2, -3, -4, 8, 0 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.Throws(typeof(ArgumentException), () => tree.Query(0, values.Count));
		}

		[Test]
		public void UpdateTest0() {
			var values = new List<int> { 1, 2, -3, -4, 8, 0 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.AreEqual(8, tree.Query(0, values.Count - 1));
			tree.Update(0, values.Count - 1, 20);
			Assert.AreEqual(28, tree.Query(0, values.Count - 1));
		}

		[Test]
		public void UpdateTest1() {
			var values = new List<int> { 1, 2, -3, -4, 8, 0 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.AreEqual(8, tree.Query(0, values.Count - 1));
			tree.Update(2, 2, 20);
			Assert.AreEqual(17, tree.Query(0, values.Count - 1));
		}

		[Test]
		public void UpdateTest2() {
			var values = new List<int> { 1, 2, -3, -4, 8, 0 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.AreEqual(8, tree.Query(0, values.Count - 1));
			tree.Update(3, values.Count - 1, -10);
			Assert.AreEqual(2, tree.Query(0, values.Count - 1));
		}

		[Test]
		public void UpdateTest3() {
			var values = new List<int> { 1, 2, -3, -4, 8, 0 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.AreEqual(8, tree.Query(0, values.Count - 1));
			tree.Update(0, values.Count - 1, 1);
			// 2 3 -2 -3 9 1
			Assert.AreEqual(9, tree.Query(0, values.Count - 1));
			tree.Update(1, values.Count - 1, 2);
			// 2 5 0 -1 11 3
			Assert.AreEqual(11, tree.Query(0, values.Count - 1));
			tree.Update(2, values.Count - 1, 3);
			// 2 5 3 -2 14 6
			Assert.AreEqual(14, tree.Query(0, values.Count - 1));
			tree.Update(3, values.Count - 1, 4);
			// 2 5 3 2 18 10
			Assert.AreEqual(18, tree.Query(0, values.Count - 1));
			tree.Update(4, values.Count - 1, 5);
			// 2 5 3 2 23 15
			Assert.AreEqual(23, tree.Query(0, values.Count - 1));
			tree.Update(5, values.Count - 1, 10);
			// 2 5 3 2 23 25
			Assert.AreEqual(25, tree.Query(0, values.Count - 1));
		}

		[Test]
		public void UpdateTest4() {
			var values = new List<int> { 1, 2, 3, 4, 5, 6 };
			var tree = LazySegmentTree.Create(values, new MaxOperation());
			Assert.AreEqual(6, tree.Query(0, values.Count - 1));
			tree.Update(0, values.Count - 1, 10);
			// 11 12 13 14 15 16
			Assert.AreEqual(16, tree.Query(0, values.Count - 1));
			tree.Update(1, values.Count - 2, 10);
			// 11 22 23 24 25 16
			Assert.AreEqual(25, tree.Query(0, values.Count - 1));
			tree.Update(0, 1, 20);
			// 31 42 23 24 25 16
			Assert.AreEqual(42, tree.Query(0, values.Count - 1));
			Assert.AreEqual(31, tree.Query(0, 0));
			Assert.AreEqual(42, tree.Query(1, 1));
			Assert.AreEqual(23, tree.Query(2, 2));
			Assert.AreEqual(24, tree.Query(3, 3));
			Assert.AreEqual(25, tree.Query(4, 4));
			Assert.AreEqual(16, tree.Query(5, 5));
			tree.Update(1, 3, -20);
			// 31 22 3 4 25 16
			Assert.AreEqual(31, tree.Query(0, values.Count - 1));
		}

		[Test]
		public void RandomTest() {
			const int N = 1000, MAX = 10000, T = 1000, Q = 50;
			var random = new Random();
			var values = new List<int>(N);
			for (int j = 0; j < N; j++) {
				values.Add(random.Next(MAX));
			}
			// Console.Error.WriteLine(values.Aggregate("", (acc, c) => acc + c.ToString() + ", "));
			var tree = LazySegmentTree.Create(values, new MaxOperation());
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
			return values.Skip(low).Take(high - low + 1).Aggregate(int.MinValue, (max, current) => Math.Max(max, current));
		}

		private void BruteForceUpdate(List<int> values, int low, int high, int value) {
			for (int j = low; j <= high; j++) {
				values[j] += value;
			}
		}

	}

}
