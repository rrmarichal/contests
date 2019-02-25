using NUnit.Framework;

using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace Tests {

	public class SortedSetPerformanceTests {

		[Test]
		public void InsertTest() {
			Stopwatch sw = new Stopwatch();
			sw.Start();
			SortedSet<int> t = new SortedSet<int>();
			Random r = new Random();
			for (int j = 0; j < 1000000; j++) {
				t.Add(r.Next());
			}
			TestContext.Progress.WriteLine("InsertTest -> {0} milis.", sw.Elapsed.TotalMilliseconds);
		}

		[Test]
		public void SuccessiveHeadSetTest() {
			int N = 100000;
			int Q = 10000;
			SortedSet<int> t = new SortedSet<int>();
			Random r = new Random();
			for (int j = 0; j < N; j++) {
				t.Add(r.Next());
			}
			Stopwatch sw = new Stopwatch();
			sw.Start();
			long heads = 0;
			for (int j = 0; j < Q; j++) {
				SortedSet<int> head = t.GetViewBetween(Int32.MinValue, r.Next());
				heads += head.Count;
			}
			TestContext.Progress.WriteLine("SuccessiveHeadSetTest -> {0} milis. {1} heads.", sw.Elapsed.TotalMilliseconds, heads);
		}

	}

}
