package rangetree;

/**
 * This class implements a static balanced binary search tree.
 * 
 * Instances are build from a list of 2DPointEx objects. The construction assumes
 * points are sorted on the y dimension and for equal y's, points are sorted on the x dimension.
 */
public class _1DRangeTree {

    public static _1DRangeTree create(_2DPointEx[] points) {
        return new _1DRangeTree(points);
    }

    private _1DRangeTreeNode root;

    private _1DRangeTree(_2DPointEx[] points) {
        root = buildTree(points, 0, points.length - 1);
    }

    private _1DRangeTreeNode buildTree(_2DPointEx[] points, int low, int high) {
        if (low == high) {
            return _1DRangeTreeNode.createLeaf(points[low]);
        }
        int mid = (low + high) / 2;
        _1DRangeTreeNode left = buildTree(points, low, mid);
        _1DRangeTreeNode right = buildTree(points, mid + 1, high);
        return _1DRangeTreeNode.createInnerNode(points[mid], left, right);
    }

    private _1DRangeTreeNode findSplitNode(int y1, int y2) {
        _1DRangeTreeNode v = root;
        // Check v is not a leaf or the query interval is outside the current tree.
        while (!v.isLeaf() && (y2 < v.getPoint().y || y1 > v.getPoint().y)) {
            if (y2 < v.getPoint().y) {
                v = v.getLeft();
            }
            else {
                v = v.getRight();
            }
        }
        return v;
    }

    public int query(int y1, int y2) {
        _1DRangeTreeNode split = findSplitNode(y1, y2);
        if (split.isLeaf()) {
            return y1 <= split.getPoint().y && split.getPoint().y <= y2
                ? split.getSize()
                : 0;
        }
        // Follow the path to y1 and report right children along the path.
        _1DRangeTreeNode v = split.getLeft();
        int count = 0;
        while (!v.isLeaf()) {
            if (y1 <= v.getPoint().y) {
                count += v.getRight().getSize();
                v = v.getLeft();
            }
            else {
                v = v.getRight();
            }
        }
        // Check if we need to report he leaf @ y1.
        if (y1 <= v.getPoint().y) count++;
        // Follow the path to y2 and report left children along the path.
        v = split.getRight();
        while (!v.isLeaf()) {
            if (y2 >= v.getPoint().y) {
                count += v.getLeft().getSize();
                v = v.getRight();
            }
            else {
                v = v.getLeft();
            }
        }
        // Check if we need to report he leaf @ y2.
        if (y2 >= v.getPoint().y) count++;
        return count;
    }

}
