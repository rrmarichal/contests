using System;
using System.IO;
using NUnit.Framework;

public class SolutionTests {
	[Test]
	public void Test0() {
		Solution solution = new Solution(new InputReader(new StreamReader("../../../../test_data/0.in")));
		string[] result = solution.Solve();
		Console.WriteLine("Here");
		Assert.AreEqual(new string[] {
			"Case #1: 50"
		}, result);
	}

	[Test]
	public void Test1() {
		Solution solution = new Solution(new InputReader(new StreamReader("../../../../test_data/1.in")));
		string[] result = solution.Solve();
		Console.WriteLine("Here");
		Assert.AreEqual(new string[] {
			"Case #1: 2",
			"Case #2: 3",
			"Case #3: 1"
		}, result);
	}
}
