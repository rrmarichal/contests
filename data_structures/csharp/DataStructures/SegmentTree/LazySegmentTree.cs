using System;
using System.Collections.Generic;

namespace DataStructures.SegmentTree {

	public class LazySegmentTree {

		private readonly ISegmentTreeOperation operation;
		private readonly int count, size;
		private readonly int[] tree, lazy;

		public static LazySegmentTree Create(List<int> values, ISegmentTreeOperation operation) {
			if (values == null) {
				throw new ArgumentNullException("values");
			}
			return new LazySegmentTree(values, operation);
		}

		protected LazySegmentTree(List<int> values, ISegmentTreeOperation operation) {
			this.operation = operation;
			count = values.Count;
			size = Utils.NextPowerOfTwo(values.Count);
			tree = new int[(size << 1) - 1];
			lazy = new int[(size << 1) - 1];
			BuildTree(values, 0, 0, size - 1);
		}

		private void BuildTree(List<int> values, int current, int low, int high) {
			if (low == high) {
				tree[current] = low < values.Count ? values[low] : operation.Null;
			}
			else {
				int mid = (low + high) >> 1, left = (current << 1) + 1, right = left + 1;
				BuildTree(values, left, low, mid);
				BuildTree(values, right, mid + 1, high);
				tree[current] = operation.Aggregate(tree[left], tree[right]);
			}
		}


		public int Query(int low, int high) {
			if (low < 0 || low > high || high >= count) {
				throw new ArgumentException("Invalid range.");
			}
			return Query(0, 0, size - 1, low, high);
		}

		private int Query(int current, int treeLow, int treeHigh, int low, int high) {
			// No overlap.
			if (treeHigh < low || treeLow > high) {
				return operation.Null;
			}
			int left = (current << 1) + 1, right = left + 1;
			if (lazy[current] != 0) {
				tree[current] += lazy[current];
				// If current is not a leaf, propagate updates to its children.
				if (current < size - 1) {
					lazy[left] += lazy[current];
					lazy[right] += lazy[current];
				}
				lazy[current] = 0;
			}
			// Total overlap.
			if (low <= treeLow && high >= treeHigh) {
				return tree[current];
			}
			// Partial overlap.
			int mid = (treeLow + treeHigh) >> 1;
			int ml = Query(left, treeLow, mid, low, high);
			int mr = Query(right, mid + 1, treeHigh, low, high);
			return operation.Aggregate(ml, mr);
		}

		public void Update(int low, int high, int value) {
			if (low < 0 || low > high || high >= count) {
				throw new ArgumentException("Invalid range.");
			}
			Update(0, 0, size - 1, low, high, value);
		}

		private void Update(int current, int treeLow, int treeHigh, int low, int high, int value) {
			// No overlap.
			if (treeHigh < low || treeLow > high) {
				return;
			}
			int left = (current << 1) + 1, right = left + 1;
			// Total overlap.
			if (low <= treeLow && high >= treeHigh) {
				tree[current] += lazy[current] + value;
				// If current is not a leaf, propagate updates to its children.
				if (current < size - 1) {
					lazy[left] += lazy[current] + value;
					lazy[right] += lazy[current] + value;
				}
				lazy[current] = 0;
			}
			// Partial overlap.
			else {
				int mid = (treeLow + treeHigh) >> 1;
				Update(left, treeLow, mid, low, high, value);
				Update(right, mid + 1, treeHigh, low, high, value);
				tree[current] = operation.Aggregate(tree[left] + lazy[left], tree[right] + lazy[right]);
			}
		}

	}

}
