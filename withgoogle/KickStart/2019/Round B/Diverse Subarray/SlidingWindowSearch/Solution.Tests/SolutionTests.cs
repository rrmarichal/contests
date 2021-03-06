using System;
using System.IO;
using NUnit.Framework;

public class SolutionTests {

	[Test]
	public void Test0() {
		Solution solution = new Solution(new InputReader(new StreamReader("../../../../test_data/0.in")));
		string[] result = solution.Solve();
		Assert.AreEqual(new string[] {
			"Case #1: 4",
			"Case #2: 6",
			"Case #3: 4",
			"Case #4: 6"
		}, result);
	}

	[Test]
	public void Test1() {
		Solution solution = new Solution(new InputReader(new StreamReader("../../../../test_data/1.in")));
		string[] result = solution.Solve();
		Assert.AreEqual(new string[] {
			"Case #1: 7",
			"Case #2: 5",
			"Case #3: 3"
		}, result);
	}

}
