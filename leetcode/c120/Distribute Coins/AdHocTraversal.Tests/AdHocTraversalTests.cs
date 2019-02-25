using NUnit.Framework;
using AdHocTraversal;

namespace Tests {

	public class AdHocTraversalTests {

		private readonly Solution solution;

		public AdHocTraversalTests() {
			solution = new Solution();
		}

		[Test]
		public void Test0() {
			var t = new TreeNode(3);
			t.left = new TreeNode(0);
			t.right = new TreeNode(0);
			Assert.AreEqual(2, solution.DistributeCoins(t));
		}

		[Test]
		public void Test1() {
			var t = new TreeNode(1);
			t.left = new TreeNode(0);
			t.left.right = new TreeNode(3);
			t.right = new TreeNode(0);
			Assert.AreEqual(4, solution.DistributeCoins(t));
		}

		[Test]
		public void Test2() {
			var t = new TreeNode(0);
			t.left = new TreeNode(3);
			t.right = new TreeNode(0);
			Assert.AreEqual(3, solution.DistributeCoins(t));
		}

		[Test]
		public void Test3() {
			var t = new TreeNode(1);
			t.left = new TreeNode(0);
			t.right = new TreeNode(2);
			Assert.AreEqual(2, solution.DistributeCoins(t));
		}

		[Test]
		public void Test4() {
			var t = new TreeNode(0);
			t.left = new TreeNode(0);
			t.right = new TreeNode(1);

			t.left.left = new TreeNode(7);
			t.left.right = new TreeNode(0);
			t.left.right.left = new TreeNode(0);

			t.right.left = new TreeNode(0);
			t.right.right = new TreeNode(0);
			Assert.AreEqual(16, solution.DistributeCoins(t));
		}

	}

}
