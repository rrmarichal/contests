using NUnit.Framework;
using AdHocSorting;

namespace Tests {
    
	public class AdHocSortingTests {
		private readonly Solution solution;

		public AdHocSortingTests() {
			solution = new Solution();
		}

		[Test]
		public void Test1() {
			var result = solution.PancakeSort(new[] { 3, 2, 4, 1 });
			Assert.AreEqual(result.Count, 5);
		}

		[Test]
		public void Test2() {
			var result = solution.PancakeSort(new[] { 1, 2, 3 });
			Assert.AreEqual(result.Count, 0);
		}
	}

}
