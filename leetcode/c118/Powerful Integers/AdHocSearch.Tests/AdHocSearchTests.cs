using NUnit.Framework;
using AdHocSearch;

namespace Tests {

	public class AdHocSearchTests {

		private readonly Solution solution;

		public AdHocSearchTests() {
			solution = new Solution();
		}

		[Test]
		public void Test1() {
			var result = solution.PowerfulIntegers(2, 3, 10);
			Assert.AreEqual(result.Count, 7);
		}

		[Test]
		public void Test2() {
			var result = solution.PowerfulIntegers(3, 5, 15);
			Assert.AreEqual(result.Count, 6);
		}

	}

}
