using System;

namespace DataStructures.SegmentTree.Tests {

	internal class MaxOperation : ISegmentTreeOperation {

		public int Null => int.MinValue;

		public int Aggregate(int x, int y) {
			return Math.Max(x, y);
		}

	}

	internal class MinOperation : ISegmentTreeOperation {

		public int Null => int.MaxValue;

		public int Aggregate(int x, int y) {
			return Math.Min(x, y);
		}

	}

}
