using System;
using System.Collections.Generic;
using System.Linq;

namespace AdHocTraversal {

	public class TreeNode {

		public int val;

		public TreeNode left;

		public TreeNode right;

		public TreeNode(int x) {
			val = x;
		}

	}

	public class FlipResult {

		public FlipResult() {
			Success = false;
			Flips = new List<int> { -1 };
		}

		public FlipResult(List<int> flips) {
			Success = true;
			Flips = flips;
		}

		public bool Success { get; }

		public List<int> Flips { get; set; }
        
	}

	public class Solution {

		private int current;

		public IList<int> FlipMatchVoyage(TreeNode root, int[] voyage) {
			current = -1;
			return Flipper(root, voyage).Flips;
		}

		private FlipResult Flipper(TreeNode node, int[] voyage) {
			if (node == null) {
				return new FlipResult(new List<int>());
			}
			++current;
			if (node.val != voyage[current]) {
				return new FlipResult();
			}
			var swapped = false;
			// Swap left and right children and recurse.
			if (node.left != null && node.left.val != voyage[current + 1]) {
				var temp = node.left;
				node.left = node.right;
				node.right = temp;
				swapped = true;
			}
			var lf = Flipper(node.left, voyage);
			var rf = Flipper(node.right, voyage);
			if (!lf.Success || !rf.Success) {
				return new FlipResult();
			}
			if (swapped) {
				var flips = new List<int> { node.val };
				flips.AddRange(lf.Flips);
				flips.AddRange(rf.Flips);
				return new FlipResult(flips);
			}
			return new FlipResult(new List<int>(lf.Flips.Union(rf.Flips)));
		}

	}

}
