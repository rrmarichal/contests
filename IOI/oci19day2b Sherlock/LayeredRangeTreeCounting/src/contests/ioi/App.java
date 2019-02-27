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

class LayerPointer {

    private int leftPointer, rightPointer;

	public LayerPointer(int leftPointer, int rightPointer) {
        this.leftPointer = leftPointer;
        this.rightPointer = rightPointer;
    }

    public int getLeftPointer() {
        return leftPointer;
    }

    public int getRightPointer() {
        return rightPointer;
    }
    
}

class LayeredSortedArray {

    public static LayeredSortedArray createLeaf(_2DPointEx point) {
        return new LayeredSortedArray(point);
    }

	public static LayeredSortedArray create(_2DPointEx[] points, _2DPointEx[] left, _2DPointEx[] right) {
		return new LayeredSortedArray(points, left, right);
    }

    private _2DPointEx[] points;
    private LayerPointer[] lowerBoundLayer, upperBoundLayer;
    
    private LayeredSortedArray(_2DPointEx point) {
        this.points = new _2DPointEx[] { point };
    }

    private LayeredSortedArray(_2DPointEx[] points, _2DPointEx[] left, _2DPointEx[] right) {
        this.points = points;
        createLayers(left, right);
    }

    private void createLayers(_2DPointEx[] left, _2DPointEx[] right) {
        int leftIndex = 0, rightIndex = 0;
        // Create lower bound layers.
        lowerBoundLayer = new LayerPointer[points.length];
        for (int j = 0; j < points.length; j++) {
            while (leftIndex < left.length && left[leftIndex].point.y < points[j].point.y) {
                leftIndex++;
            }
            while (rightIndex < right.length && right[rightIndex].point.y < points[j].point.y) {
                rightIndex++;
            }
            lowerBoundLayer[j] = new LayerPointer(leftIndex, rightIndex);
        }
        // Create upper bound layers.
        upperBoundLayer = new LayerPointer[points.length];
        leftIndex = left.length - 1;
        rightIndex = right.length - 1;
        for (int j = points.length - 1; j >= 0; j--) {
            while (leftIndex >= 0 && left[leftIndex].point.y > points[j].point.y) {
                leftIndex--;
            }
            while (rightIndex >= 0 && right[rightIndex].point.y > points[j].point.y) {
                rightIndex--;
            }
            upperBoundLayer[j] = new LayerPointer(leftIndex, rightIndex);
        }
    }

    public LayerPointer getLowerBoundLayer(int index) {
        return lowerBoundLayer != null && index >= 0 && index < lowerBoundLayer.length
            ? lowerBoundLayer[index]
            : null;
    }

    public LayerPointer getUpperBoundLayer(int index) {
        return upperBoundLayer != null && index >= 0 && index < upperBoundLayer.length
            ? upperBoundLayer[index]
            : null;
    }

    public LayerPointer aboveOrEqual(int y) {
        int low = 0, high = points.length;
        while (true) {
            if (low == high) {
                return low < points.length
                    ? lowerBoundLayer[low]
                    : new LayerPointer(Integer.MAX_VALUE, Integer.MAX_VALUE);
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
    
    public int aboveOrEqualFromParentPointer(int offset) {
        return points.length - offset;
    }

    /**
     * Return number of points less than or equal to y.
     */
    public LayerPointer belowOrEqual(int y) {
        int low = -1, high = points.length - 1;
        while (true) {
            if (low == high) {
                return low >= 0
                    ? upperBoundLayer[low]
                    : new LayerPointer(Integer.MIN_VALUE, Integer.MIN_VALUE);
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
    
    public int belowOrEqualFromParentPointer(int offset) {
        return offset + 1;
    }

}

class LayeredRangeTreeNode {

	public static LayeredRangeTreeNode createLeaf(_2DPoint point, LayeredSortedArray dual) {
		return new LayeredRangeTreeNode(point, dual);
	}

    public static LayeredRangeTreeNode createInnerNode(_2DPoint pivot,
        LayeredRangeTreeNode left, LayeredRangeTreeNode right, LayeredSortedArray dual) {
		return new LayeredRangeTreeNode(pivot, dual, left, right);
    }

    private _2DPoint pivot;
    LayeredSortedArray dual;
    LayeredRangeTreeNode left;
    LayeredRangeTreeNode right;
    
    private LayeredRangeTreeNode(_2DPoint pivot, LayeredSortedArray dual) {
        this.pivot = pivot;
        this.dual = dual;
    }

    private LayeredRangeTreeNode(_2DPoint pivot, LayeredSortedArray dual,
        LayeredRangeTreeNode left, LayeredRangeTreeNode right) {
        this.pivot = pivot;
        this.dual = dual;
        this.left = left;
        this.right = right;
    }

	public boolean isLeaf() {
		return left == null;
	}

	public _2DPoint getPoint() {
		return pivot;
	}

}

class LeftRightFiltering {

    private _2DPointEx[] left, right;

	public LeftRightFiltering(_2DPointEx[] points, int low, int high) {
        int leftIndex = 0, rightIndex = 0, mid = (low + high) >> 1;
        left = new _2DPointEx[mid - low + 1];
        right = new _2DPointEx[high - mid];
        for (int j = 0; j < points.length; j++) {
            if (points[j].index >= low && points[j].index <= mid) {
                left[leftIndex++] = points[j];
            }
            else {
                right[rightIndex++] = points[j];
            }
        }
    }

    public _2DPointEx[] getLeft() {
        return left;
    }

    public _2DPointEx[] getRight() {
        return right;
    }
    
}

class LayeredRangeTree {

    private static final boolean CHECKED = true;

    public static LayeredRangeTree create(_2DPoint[] points) {
        if (CHECKED && points == null) {
            throw new IllegalArgumentException("Invalid argument (points is null).");
        }
        return new LayeredRangeTree(points);
    }

    private LayeredRangeTreeNode root;

    private LayeredRangeTree(_2DPoint[] points) {
        root = buildTree(points);
    }

    private LayeredRangeTreeNode buildTree(_2DPoint[] points) {
        Arrays.sort(points, new XYAxisComparator());
        _2DPointEx[] yPoints = new _2DPointEx[points.length];
        for (int j = 0; j < points.length; j++) {
            yPoints[j] = new _2DPointEx(points[j], j);
        }
        Arrays.sort(yPoints, new YXAxisComparator());
        return buildTree(points, yPoints, 0, points.length - 1);
    }

    private LayeredRangeTreeNode buildTree(_2DPoint[] xPoints, _2DPointEx[] yPoints, int low, int high) {
        // Create single node tree.
        if (low == high) {
            LayeredSortedArray dual = LayeredSortedArray.createLeaf(yPoints[0]);
            return LayeredRangeTreeNode.createLeaf(xPoints[low], dual);
        }
        // Create an inner node splitting the points on the median X coordinate.
        LeftRightFiltering filtered = new LeftRightFiltering(yPoints, low, high);
        // Build the associated structure on the second dimension.
        LayeredSortedArray dual = LayeredSortedArray.create(
            yPoints, filtered.getLeft(), filtered.getRight());
        int mid = (low + high) >> 1;
        LayeredRangeTreeNode left = buildTree(xPoints, filtered.getLeft(), low, mid);
        LayeredRangeTreeNode right = buildTree(xPoints, filtered.getRight(), mid + 1, high);
        return LayeredRangeTreeNode.createInnerNode(xPoints[mid], left, right, dual);
    }

    private LayeredRangeTreeNode findSplitNode(int x1, int x2) {
        LayeredRangeTreeNode v = root;
        // Check v is not a leaf or the query interval is outside the current tree.
        while (!v.isLeaf() && (x2 < v.getPoint().x || x1 > v.getPoint().x)) {
            if (x2 < v.getPoint().x) {
                v = v.left;
            }
            else {
                v = v.right;
            }
        }
        return v;
    }

    private boolean isLeafBelowOrEqual(LayeredRangeTreeNode leaf, int x1, int x2, int y) {
        return x1 <= leaf.getPoint().x && leaf.getPoint().x <= x2 && leaf.getPoint().y <= y;
    }

    public int cleanBelowOrEqual(int x1, int x2, int y) {
        LayeredRangeTreeNode split = findSplitNode(x1, x2);
        if (split.isLeaf()) {
            return isLeafBelowOrEqual(split, x1, x2, y) ? 1 : 0;
        }
        // Follow the path to x1 and call aboveOrEqual(y) on the subtrees right of the path.
        LayeredRangeTreeNode v = split.left;
        int count = 0;
        while (!v.isLeaf()) {
            if (x1 <= v.getPoint().x) {
                count++;
                v = v.left;
            }
            else {
                v = v.right;
            }
        }
        // Check if we need to report he leaf @ x1.
        if (x1 <= v.getPoint().x && isLeafBelowOrEqual(v, x1, x2, y)) {
            count++;
        }
        // Follow the path to x2 and call below(y) on the subtrees left of the path.
        v = split.right;
        while (!v.isLeaf()) {
            if (x2 >= v.getPoint().x) {
                count++;
                v = v.right;
            }
            else {
                v = v.left;
            }
        }
        // Check if we need to report he leaf @ x2.
        if (x2 >= v.getPoint().x && isLeafBelowOrEqual(v, x1, x2, y)) {
            count++;
        }
        return count;
    }

    public int belowOrEqual(int x1, int x2, int y) {
        LayeredRangeTreeNode split = findSplitNode(x1, x2);
        if (split.isLeaf()) {
            return isLeafBelowOrEqual(split, x1, x2, y) ? 1 : 0;
        }
        // Follow the path to x1 and call aboveOrEqual(y) on the subtrees right of the path.
        LayerPointer splitLayerPointer = split.dual.belowOrEqual(y);
        LayeredRangeTreeNode v = split.left;
        LayerPointer currentLayerPointer = v.dual.getUpperBoundLayer(splitLayerPointer.getLeftPointer());
        int count = 0;
        while (!v.isLeaf() && currentLayerPointer != null) {
            if (x1 <= v.getPoint().x) {
                count += v.right.dual.belowOrEqualFromParentPointer(currentLayerPointer.getRightPointer());
                v = v.left;
                currentLayerPointer = v.dual.getUpperBoundLayer(currentLayerPointer.getLeftPointer());
            }
            else {
                v = v.right;
                currentLayerPointer = v.dual.getUpperBoundLayer(currentLayerPointer.getRightPointer());
            }
        }
        // Check if we need to report he leaf @ x1.
        if (x1 <= v.getPoint().x && isLeafBelowOrEqual(v, x1, x2, y)) {
            count++;
        }
        // Follow the path to x2 and call below(y) on the subtrees left of the path.
        v = split.right;
        currentLayerPointer = v.dual.getUpperBoundLayer(splitLayerPointer.getRightPointer());
        while (!v.isLeaf() && currentLayerPointer != null) {
            if (x2 >= v.getPoint().x) {
                count += v.left.dual.belowOrEqualFromParentPointer(currentLayerPointer.getLeftPointer());
                v = v.right;
                currentLayerPointer = v.dual.getUpperBoundLayer(currentLayerPointer.getRightPointer());
            }
            else {
                v = v.left;
                currentLayerPointer = v.dual.getUpperBoundLayer(currentLayerPointer.getLeftPointer());
            }
        }
        // Check if we need to report he leaf @ x2.
        if (x2 >= v.getPoint().x && isLeafBelowOrEqual(v, x1, x2, y)) {
            count++;
        }
        return count;
    }

    private boolean isLeafAboveOrEqual(LayeredRangeTreeNode leaf, int x1, int x2, int y) {
        return x1 <= leaf.getPoint().x && leaf.getPoint().x <= x2 && leaf.getPoint().y >= y;
    }

    public int cleanAboveOrEqual(int x1, int x2, int y) {
        LayeredRangeTreeNode split = findSplitNode(x1, x2);
        if (split.isLeaf()) {
            return isLeafAboveOrEqual(split, x1, x2, y) ? 1 : 0;
        }
        // Follow the path to x1 and call aboveOrEqual(y) on the subtrees right of the path.
        LayeredRangeTreeNode v = split.left;
        int count = 0;
        while (!v.isLeaf()) {
            if (x1 <= v.getPoint().x) {
                count++;
                v = v.left;
            }
            else {
                v = v.right;
            }
        }
        // Check if we need to report he leaf @ x1.
        if (x1 <= v.getPoint().x && isLeafAboveOrEqual(v, x1, x2, y)) {
            count++;
        }
        // Follow the path to x2 and call below(y) on the subtrees left of the path.
        v = split.right;
        while (!v.isLeaf()) {
            if (x2 >= v.getPoint().x) {
                count++;
                v = v.right;
            }
            else {
                v = v.left;
            }
        }
        // Check if we need to report he leaf @ x2.
        if (x2 >= v.getPoint().x && isLeafAboveOrEqual(v, x1, x2, y)) {
            count++;
        }
        return count;
    }

    public int aboveOrEqual(int x1, int x2, int y) {
        LayeredRangeTreeNode split = findSplitNode(x1, x2);
        if (split.isLeaf()) {
            return isLeafAboveOrEqual(split, x1, x2, y) ? 1 : 0;
        }
        // Follow the path to x1 and call aboveOrEqual(y) on the subtrees right of the path.
        LayerPointer splitLayerPointer = split.dual.aboveOrEqual(y);
        LayeredRangeTreeNode v = split.left;
        LayerPointer currentLayerPointer = v.dual.getLowerBoundLayer(splitLayerPointer.getLeftPointer());
        int count = 0;
        while (!v.isLeaf() && currentLayerPointer != null) {
            if (x1 <= v.getPoint().x) {
                count += v.right.dual.aboveOrEqualFromParentPointer(currentLayerPointer.getRightPointer());
                v = v.left;
                currentLayerPointer = v.dual.getLowerBoundLayer(currentLayerPointer.getLeftPointer());
            }
            else {
                v = v.right;
                currentLayerPointer = v.dual.getLowerBoundLayer(currentLayerPointer.getRightPointer());
            }
        }
        // Check if we need to report he leaf @ x1.
        if (x1 <= v.getPoint().x && isLeafAboveOrEqual(v, x1, x2, y)) {
            count++;
        }
        // Follow the path to x2 and call below(y) on the subtrees left of the path.
        v = split.right;
        currentLayerPointer = v.dual.getLowerBoundLayer(splitLayerPointer.getRightPointer());
        while (!v.isLeaf() && currentLayerPointer != null) {
            if (x2 >= v.getPoint().x) {
                count += v.left.dual.aboveOrEqualFromParentPointer(currentLayerPointer.getLeftPointer());
                v = v.right;
                currentLayerPointer = v.dual.getLowerBoundLayer(currentLayerPointer.getRightPointer());
            }
            else {
                v = v.left;
                currentLayerPointer = v.dual.getLowerBoundLayer(currentLayerPointer.getLeftPointer());
            }
        }
        // Check if we need to report he leaf @ x2.
        if (x2 >= v.getPoint().x && isLeafAboveOrEqual(v, x1, x2, y)) {
            count++;
        }
        return count;
    }

}

class QueryInfoComparator implements Comparator<QueryInfo> {

    private int sqrtN;

    public QueryInfoComparator(int sqrtN) {
        this.sqrtN = sqrtN;
    }

    @Override
    public int compare(QueryInfo o1, QueryInfo o2) {
        int slot1 = o1.L / sqrtN;
        int slot2 = o2.L / sqrtN;
        if (slot1 == slot2) {
            return Integer.compare(o1.R, o2.R);
        }
        return Integer.compare(slot1, slot2);
    }

}

class QueryInfo { 
    int L, R, index;

    public QueryInfo(int L, int R, int index) {
        this.L = L;
        this.R = R;
        this.index = index;
    }

}

public class App {

	public long[] solve(int[] A, QueryInfo[] queries) {
        int sqrtN = (int) Math.sqrt(A.length);
        // Sort the queries using sqrt(N) slots arrangement.
        Arrays.sort(queries, new QueryInfoComparator(sqrtN));
        // Create the 2DRangeTree with items as points.
        _2DPoint[] points = new _2DPoint[A.length];
        for (int j = 0; j < A.length; j++) {
            points[j] = new _2DPoint(j, A[j]);
        }
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        long[] result = new long[queries.length];
        int left = 0, right = 0;
        long inversions = 0;
        for (int j = 0; j < queries.length; j++) {
            if (j == 0 || queries[j].L / sqrtN != queries[j-1].L / sqrtN) {
                left = queries[j].L;
                right = -1;
            }
            if (left != queries[j].L) {
                // Move left pointer to the left.
                if (queries[j].L < left) {
                    while (queries[j].L < left) {
                        inversions += tree.belowOrEqual(left, right, A[left - 1] - 1);
                        left--;
                    }
                }
                // Move left pointer to the right.
                else {
                    while (queries[j].L > left) {
                        inversions -= tree.belowOrEqual(left + 1, right, A[left] - 1);
                        left++;
                    }
                }
            }
            if (right == -1) {
                inversions = 0;
                right = left;
            }
            // Move the right pointer to reach the query interval right coordinate.
            while (right < queries[j].R) {
                inversions += tree.aboveOrEqual(left, right, A[right + 1] + 1);
                right++;
            }
            result[queries[j].index] = inversions;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        long inputStart = System.currentTimeMillis();
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int j=0; j < N; j++) {
            A[j] = sc.nextInt();
        }
        long processStart = System.currentTimeMillis();
        App app = new App();
        int Q = sc.nextInt();
        QueryInfo[] queries = new QueryInfo[Q];
        for (int j = 0; j < Q; j++) {
            int L = sc.nextInt(), R = sc.nextInt();
            queries[j] = new QueryInfo(L-1, R-1, j);
        }
        long[] inversions = app.solve(A, queries);
        long outputStart = System.currentTimeMillis();
        for (long inv: inversions) pw.println(inv);
        pw.println(String.format("\nInput: %d. Process: %d. Output: %d", processStart - inputStart, outputStart - processStart, System.currentTimeMillis() - outputStart));
        pw.close();
        sc.close();
    }

}
