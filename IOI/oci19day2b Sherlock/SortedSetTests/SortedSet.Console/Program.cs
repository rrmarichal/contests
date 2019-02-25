using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace SortedSet.Console {

	class Program {

		static void Main(string[] args) {
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
			System.Console.WriteLine("SuccessiveHeadSetTest -> {0} milis.", sw.Elapsed.TotalMilliseconds);
		}
        
	}

}
