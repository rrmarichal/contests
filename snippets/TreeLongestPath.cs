using System;
using System.Collections.Generic;

namespace CRClient
{
	class TreeLongestPath
	{
		public static void Main(string[] args)
		{
			var tree = BuildSampleTree();
			var lp = LongestPath(tree);
			Console.WriteLine(lp.Item2);
			Console.ReadLine();
		}

		private static Tree BuildSampleTree()
		{
			return new Tree(1, new[] { new Tree(2), new Tree(3, new[] { new Tree(5) }), new Tree(4) });
		}

		/// Returns a tuple with values, first: Height of the tree rooted at tree and second: longest path of // any tree rooted at tree
		private static Tuple<int, int> LongestPath(Tree tree)
		{
			if (tree.Children == null)
			{
				return new Tuple<int, int>(0, 0);
			}
			int mh1 = -1, mh2 = -1, childMax = 0;
			foreach (var child in tree.Children)
			{
				var clp = LongestPath(child);
				if (clp.Item2 > childMax)
				{
					childMax = clp.Item2;
				}
				if (clp.Item1 > mh1)
				{
					mh2 = mh1;
					mh1 = clp.Item1;
				}
				else if (clp.Item1 > mh2)
				{
					mh2 = clp.Item1;
				}
			}
			return new Tuple<int, int>(Math.Max(mh1, mh2) + 1, Math.Max(childMax, mh1 + mh2 + 2));
		}
	}

	class Tree
	{
		public Tree(int node) : this(node, null) { }
		
		public Tree(int node, IEnumerable<Tree> children)
		{
			this.Node = node;
			this.Children = children;
		}

		public int Node { get; private set; }
		
		public IEnumerable<Tree> Children { get; private set; }
	}
}
