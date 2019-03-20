package rangetree;

public class _2DRangeTreeNode {

	public static _2DRangeTreeNode createLeaf(_2DPoint point, _1DRangeTree dual) {
		return new _2DRangeTreeNode(point, dual);
	}

    public static _2DRangeTreeNode createInnerNode(_2DPoint pivot,
        _2DRangeTreeNode left, _2DRangeTreeNode right, _1DRangeTree dual) {
		return new _2DRangeTreeNode(pivot, dual, left, right);
    }

    private _2DPoint pivot;
    private _1DRangeTree dual;
    private _2DRangeTreeNode left;
    private _2DRangeTreeNode right;
    private int size;
    
    private _2DRangeTreeNode(_2DPoint pivot, _1DRangeTree dual) {
        this.pivot = pivot;
        this.dual = dual;
        this.size = 1;
    }

    private _2DRangeTreeNode(_2DPoint pivot, _1DRangeTree dual,
        _2DRangeTreeNode left, _2DRangeTreeNode right) {
        this.pivot = pivot;
        this.dual = dual;
        this.left = left;
        this.right = right;
        this.size = left.size + right.size;
    }

	public boolean isLeaf() {
		return left == null;
	}

	public _2DPoint getPoint() {
		return pivot;
	}

    public _2DRangeTreeNode getLeft() {
        return left;
    }

    public _2DRangeTreeNode getRight() {
        return right;
    }

    public _1DRangeTree getDual() {
        return dual;
    }

}
