namespace DataStructures.SegmentTree {

	public interface ISegmentTreeOperation {

		int Null { get; }

		int Aggregate(int x, int y);

	}

}
