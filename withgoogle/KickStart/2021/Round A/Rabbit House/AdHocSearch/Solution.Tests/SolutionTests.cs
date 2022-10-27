using System;
using System.IO;
using NUnit.Framework;

public class SolutionTests {
	[Test]
	public void Test0() {
		Solution solution = new Solution(new InputReader(new StreamReader("../../../../test_data/0.in")));
		string[] result = solution.Solve();
		Assert.AreEqual(new string[] {
			"Case #1: ",
			"Case #2: ",
			"Case #3: "
		}, result);
	}

}
