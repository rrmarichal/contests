package rangetree;

import java.util.Arrays;

/**
 * This class implements a 2D range tree data structure.
 */
public class _2DRangeTree {

    private static final boolean CHECKED = true;

    public static _2DRangeTree create(_2DPoint[] points) {
        if (CHECKED && points == null) {
            throw new IllegalArgumentException("Invalid argument (points is null).");
        }
        return new _2DRangeTree(points);
    }

    private _2DRangeTreeNode root;

    private _2DRangeTree(_2DPoint[] points) {
        root = buildTree(points);
    }

    private _2DRangeTreeNode buildTree(_2DPoint[] points) {
        Arrays.sort(points, new XYAxisComparator());
        _2DPointEx[] yPoints = new _2DPointEx[points.length];
        for (int j = 0; j < points.length; j++) {
            yPoints[j] = new _2DPointEx(points[j], j);
        }
        Arrays.sort(yPoints, new YXAxisComparator());
        return buildTree(points, yPoints, 0, points.length - 1);
    }

    private _2DRangeTreeNode buildTree(_2DPoint[] xPoints, _2DPointEx[] yPoints, int low, int high) {
        // Build the associated structure on the second dimension.
        _1DRangeTree dual = _1DRangeTree.create(yPoints);
        // Create single node tree.
        if (low == high) {
            return _2DRangeTreeNode.createLeaf(xPoints[low], dual);
        }
        // Create an inner node splitting the points on the median X coordinate.
        int mid = (low + high) / 2;
        _2DPointEx[] leftYPoints = getPointsSortedByY(yPoints, low, mid);
        _2DPointEx[] rightYPoints = getPointsSortedByY(yPoints, mid + 1, high);
        _2DRangeTreeNode left = buildTree(xPoints, leftYPoints, low, mid);
        _2DRangeTreeNode right = buildTree(xPoints, rightYPoints, mid + 1, high);
        return _2DRangeTreeNode.createInnerNode(xPoints[mid], left, right, dual);
    }

    private _2DPointEx[] getPointsSortedByY(_2DPointEx[] ypoints, int low, int high) {
        _2DPointEx[] filtered = new _2DPointEx[high - low + 1];
        int c = 0;
        for (int j = 0; j < ypoints.length; j++) {
            if (ypoints[j].index >= low && ypoints[j].index <= high) {
                filtered[c++] = ypoints[j];
            }
        }
        return filtered;
    }

    private _2DRangeTreeNode findSplitNode(int x1, int x2) {
        _2DRangeTreeNode v = root;
        // Check v is not a leaf or the query interval is outside the current tree.
        while (!v.isLeaf() && (x2 < v.getPoint().x || x1 > v.getPoint().x)) {
            if (x2 < v.getPoint().x) {
                v = v.getLeft();
            }
            else {
                v = v.getRight();
            }
        }
        return v;
    }

    public int query(int x1, int y1, int x2, int y2) {
        _2DRangeTreeNode split = findSplitNode(x1, x2);
        if (split.isLeaf()) {
            return x1 <= split.getPoint().x && split.getPoint().x <= x2
                ? split.getDual().query(y1, y2)
                : 0;
        }
        // Follow the path to x1 and call query(y1, y2) on the subtrees right of the path.
        _2DRangeTreeNode v = split.getLeft();
        int count = 0;
        while (!v.isLeaf()) {
            if (x1 <= v.getPoint().x) {
                count += v.getRight().getDual().query(y1, y2);
                v = v.getLeft();
            }
            else {
                v = v.getRight();
            }
        }
        // Check if we need to report he leaf @ x1.
        if (x1 <= v.getPoint().x) {
            count += v.getDual().query(y1, y2);
        }
        // Follow the path to x2 and call query(y1, y2) on the subtrees left of the path.
        v = split.getRight();
        while (!v.isLeaf()) {
            if (x2 >= v.getPoint().x) {
                count += v.getLeft().getDual().query(y1, y2);
                v = v.getRight();
            }
            else {
                v = v.getLeft();
            }
        }
        // Check if we need to report he leaf @ x2.
        if (x2 >= v.getPoint().x) {
            count += v.getDual().query(y1, y2);
        }
        return count;
    }

}
