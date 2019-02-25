using System;

namespace AdHocTraversal {

	public class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
		public TreeNode(int x) { val = x; }
	}

	public class Solution {

		public int DistributeCoins(TreeNode root) {
			return Distribute(root).Item3;
		}

		private Tuple<int, int, int> Distribute(TreeNode root) {
			if (root == null) {
				return Tuple.Create(0, 0, 0);
			}
			var dl = Distribute(root.left);
			var dr = Distribute(root.right);
			var cost = Math.Abs(1 + dl.Item1 + dr.Item1 - root.val - dl.Item2 - dr.Item2) + dl.Item3 + dr.Item3;
			return Tuple.Create(1 + dl.Item1 + dr.Item1, root.val + dl.Item2 + dr.Item2, cost);
		}

	}
    
}
