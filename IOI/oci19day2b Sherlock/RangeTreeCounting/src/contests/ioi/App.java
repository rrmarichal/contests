package contests.ioi;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class XYAxisComparator implements Comparator<_2DPoint> {

    @Override
    public int compare(_2DPoint o1, _2DPoint o2) {
        int cr = Integer.compare(o1.x, o2.x);
        if (cr != 0) {
            return cr;
        }
        return Integer.compare(o1.y, o2.y);
    }
    
}

class YXAxisComparator implements Comparator<_2DPointEx> {

    @Override
    public int compare(_2DPointEx o1, _2DPointEx o2) {
        int cr = Integer.compare(o1.point.y, o2.point.y);
        if (cr != 0) {
            return cr;
        }
        return Integer.compare(o1.point.x, o2.point.x);
    }
    
}

class _2DPoint {

    public int x, y;
    public Object data;

    public _2DPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public _2DPoint(int x, int y, Object data) {
        this(x, y);
        this.data = data;
    }
    
}

class _2DPointEx {

    public _2DPoint point;
    public int index;

    public _2DPointEx(_2DPoint point, int index) {
        this.point = point;
        this.index = index;
    }

}

class AboveOrEqualPointComparator implements Comparator<_2DPointEx> {

    @Override
    public int compare(_2DPointEx o1, _2DPointEx o2) {
        return Integer.compare(o1.point.y, o2.point.y);
    }

}

class SortedArray {

	public static SortedArray create(_2DPointEx[] points) {
		return new SortedArray(points);
    }

    private _2DPointEx[] points;
    
    private SortedArray(_2DPointEx[] points) {
        this.points = points;
    }

    /**
     * Return number of points greater than or equal to y.
     * 
     * Find first y' >= y.
     */
	public int aboveOrEqual(int y) {
        int low = 0, high = points.length;
        while (true) {
            if (low == high) {
                // Either we found it or this is the insertion point.
                return points.length - low;
            }
            int mid = (low + high) >> 1;
            if (y <= points[mid].point.y) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
    }
    
    /**
     * Return number of points less than or equal to y.
     * 
     * Find first y' >= y.
     */
    public int belowOrEqual(int y) {
        int low = -1, high = points.length - 1;
        while (true) {
            if (low == high) {
                // Either we found it or this is the insertion point.
                return 1 + low;
            }
            int mid = (1 + low + high) >> 1;
            if (y >= points[mid].point.y) {
                low = mid;
            }
            else {
                high = mid - 1;
            }
        }
	}

}

class _2DRangeTreeNode {

	public static _2DRangeTreeNode createLeaf(_2DPoint point, SortedArray dual) {
		return new _2DRangeTreeNode(point, dual);
	}

    public static _2DRangeTreeNode createInnerNode(_2DPoint pivot,
        _2DRangeTreeNode left, _2DRangeTreeNode right, SortedArray dual) {
		return new _2DRangeTreeNode(pivot, dual, left, right);
    }

    private _2DPoint pivot;
    private SortedArray dual;
    private _2DRangeTreeNode left;
    private _2DRangeTreeNode right;
    private int size;
    
    private _2DRangeTreeNode(_2DPoint pivot, SortedArray dual) {
        this.pivot = pivot;
        this.dual = dual;
        this.size = 1;
    }

    private _2DRangeTreeNode(_2DPoint pivot, SortedArray dual,
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

    public SortedArray getDual() {
        return dual;
    }

}

class _2DRangeTree {

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
        SortedArray dual = SortedArray.create(yPoints);
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

    public int query(int x1, int x2, int y, boolean belowOrEqual) {
        _2DRangeTreeNode split = findSplitNode(x1, x2);
        if (split.isLeaf()) {
            if (x1 <= split.getPoint().x && split.getPoint().x <= x2) {
                return belowOrEqual
                    ? split.getDual().belowOrEqual(y)
                    : split.getDual().aboveOrEqual(y);
            }
            return 0;
        }
        // Follow the path to x1 and call query(y) on the subtrees right of the path.
        _2DRangeTreeNode v = split.getLeft();
        int count = 0;
        while (!v.isLeaf()) {
            if (x1 <= v.getPoint().x) {
                count += belowOrEqual
                    ? v.getRight().getDual().belowOrEqual(y)
                    : v.getRight().getDual().aboveOrEqual(y);
                v = v.getLeft();
            }
            else {
                v = v.getRight();
            }
        }
        // Check if we need to report he leaf @ x1.
        if (x1 <= v.getPoint().x) {
            count += belowOrEqual
                ? v.getDual().belowOrEqual(y)
                : v.getDual().aboveOrEqual(y);
        }
        // Follow the path to x2 and call below(y) on the subtrees left of the path.
        v = split.getRight();
        while (!v.isLeaf()) {
            if (x2 >= v.getPoint().x) {
                count += belowOrEqual
                    ? v.getLeft().getDual().belowOrEqual(y)
                    : v.getLeft().getDual().aboveOrEqual(y);
                v = v.getRight();
            }
            else {
                v = v.getLeft();
            }
        }
        // Check if we need to report he leaf @ x2.
        if (x2 >= v.getPoint().x) {
            count += belowOrEqual
                ? v.getDual().belowOrEqual(y)
                : v.getDual().aboveOrEqual(y);
        }
        return count;
    }

}

public class App {

	public int[] solve(int[] A, int[][] queries) {
		return null;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int j=0; j < N; j++) {
            A[j] = sc.nextInt();
        }
        App app = new App();
        int Q = sc.nextInt();
        int[][] queries = new int[Q][2];
        for (int j = 0; j < Q; j++) {
            int L = sc.nextInt(), R = sc.nextInt();
            queries[j][0] = L-1;
            queries[j][1] = R-1;
        }
        pw.println(app.solve(A, queries));
        pw.close();
        sc.close();

    }

}
