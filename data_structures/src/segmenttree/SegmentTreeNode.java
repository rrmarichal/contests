package segmenttree;

public class SegmentTreeNode<T> {

    private T value;
    private SegmentTreeNode<T> left, right;
    private int low, high;

    public SegmentTreeNode(int low, int high, T value) {
        this.low = low;
        this.high = high;
        this.value = value;
    }

    public SegmentTreeNode(int low, int high, T value, SegmentTreeNode<T> left, SegmentTreeNode<T> right) {
        this(low, high, value);
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

	public void setValue(T value) {
        this.value = value;
	}

    public SegmentTreeNode<T> getLeft() {
        return left;
    }

    public SegmentTreeNode<T> getRight() {
        return right;
    }

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

}
