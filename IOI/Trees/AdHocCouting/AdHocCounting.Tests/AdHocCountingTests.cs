using System.Diagnostics;
using AdHocCounting;
using NUnit.Framework;

namespace Tests {

	public class AdHocCountingTests {

		private readonly Solution solution;

		public AdHocCountingTests() {
			solution = new Solution();
		}

		[TestCase(1, ExpectedResult = 1)]
		[TestCase(2, ExpectedResult = 1)]
		[TestCase(3, ExpectedResult = 2)]
		[TestCase(4, ExpectedResult = 3)]
		[TestCase(5, ExpectedResult = 4)]
		[TestCase(6, ExpectedResult = 6)]
		[TestCase(8, ExpectedResult = 9)]
		[TestCase(10, ExpectedResult = 13)]
		[TestCase(16, ExpectedResult = 29)]
		[TestCase(100, ExpectedResult = 658)]
		[TestCase(1000000, ExpectedResult = 5242627404)]
		public long Test1(int N) {
			return solution.PerfectBalancedTressOfWeight(N);
		}

		[TestCase(10000000, ExpectedResult = 280520110076)]
		public long Test10_7(int N) {
			return solution.PerfectBalancedTressOfWeight(N);
		}

		[TestCase(100000000, ExpectedResult = 15004522714920)]
		public long Test10_8(int N) {
			return solution.PerfectBalancedTressOfWeight(N);
		}

		[Test]
		[Ignore("")]
		public void Test2() {
			var tj1 = 0L;
			for (var j = 1; j < 1000; j++) {
				var tj = solution.PerfectBalancedTressOfWeight(j);
				System.Console.WriteLine("W: {0} -> {1} -> {2}", j, tj, tj - tj1);
				tj1 = tj;
			}
			Assert.Fail();
		}

		[Test]
		[Ignore("")]
		public void Test3() {
			var sw = new Stopwatch();
			sw.Start();
			var W = 1000000;
			System.Console.WriteLine("W: {0} -> {1}", W, solution.PerfectBalancedTressOfWeight(W));
			System.Console.WriteLine("Millis: {0}", sw.ElapsedMilliseconds);
			Assert.Fail();
		}

	}
    
}
