package rangetree;

public class _1DRangeTreeNode {

	public static _1DRangeTreeNode createLeaf(_2DPointEx point) {
		return new _1DRangeTreeNode(point);
	}

	public static _1DRangeTreeNode createInnerNode(_2DPointEx pivot, _1DRangeTreeNode left,
			_1DRangeTreeNode right) {
		return new _1DRangeTreeNode(pivot, left, right);
    }

    private _2DPointEx pivot;
    private _1DRangeTreeNode left;
    private _1DRangeTreeNode right;
	private int size;

    private _1DRangeTreeNode(_2DPointEx pivot) {
        this.pivot = pivot;
        this.size = 1;
    }

    private _1DRangeTreeNode(_2DPointEx pivot, _1DRangeTreeNode left, _1DRangeTreeNode right) {
        this.pivot = pivot;
        this.left = left;
        this.right = right;
        this.size = left.size + right.size;
    }

    public boolean isLeaf() {
        return left == null;
    }

    public int getSize() {
        return size;
    }

    public _2DPoint getPoint() {
        return pivot.point;
    }

    public _1DRangeTreeNode getLeft() {
        return left;
    }

    public _1DRangeTreeNode getRight() {
        return right;
    }

}
