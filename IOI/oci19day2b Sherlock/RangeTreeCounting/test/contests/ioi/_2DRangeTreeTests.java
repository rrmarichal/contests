package contests.ioi;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class _2DRangeTreeTests {
    
    @Test
    public void simpleTest0_0() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(5, 3, 2),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(0, 10, 0, false);
        assertEquals(points.length, count);
    }

    @Test
    public void simpleTest0_1() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(5, 3, 2),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(0, 10, 10, true);
        assertEquals(points.length, count);
    }

    @Test
    public void simpleTest1() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(5, 3, 2),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(0, 1, 1, true);
        assertEquals(1, count);
    }

    @Test
    public void simpleTest2() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(5, 3, 2),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(0, Integer.MIN_VALUE, Integer.MIN_VALUE, true);
        assertEquals(0, count);
    }

    @Test
    public void simpleTest3() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(5, 3, 2),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(3, 6, 2, false);
        assertEquals(3, count);
    }

    @Test
    public void randomTest0_0() {
        int max = 1000;
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[max];
        for (int j = 0; j < max; j++) {
            points[j] = new _2DPoint(random.nextInt(), random.nextInt());
        }
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, false);
        assertEquals(max, count);
    }

    @Test
    public void randomTest0_1() {
        int max = 1000;
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[max];
        for (int j = 0; j < max; j++) {
            points[j] = new _2DPoint(random.nextInt(), random.nextInt());
        }
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        assertEquals(max, count);
    }

    @Test
    public void randomTest1_0() {
        int max = 100000;
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[max];
        for (int j = 0; j < max; j++) {
            points[j] = new _2DPoint(random.nextInt(), random.nextInt());
        }
        _2DRangeTree tree = _2DRangeTree.create(points);
        // Count positive coordinates points.
        int count = tree.query(0, Integer.MAX_VALUE, 0, false);
        assertEquals(linearCounter(points, 0, Integer.MAX_VALUE, 0, false), count);
    }

    @Test
    public void randomTest1_1() {
        int max = 100000;
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[max];
        for (int j = 0; j < max; j++) {
            points[j] = new _2DPoint(random.nextInt(), random.nextInt());
        }
        _2DRangeTree tree = _2DRangeTree.create(points);
        // Count positive coordinates points.
        int count = tree.query(0, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        assertEquals(linearCounter(points, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, true), count);
    }

    @Test
    public void randomTest2() {
        int maxPoints = 100000;
        int maxQueries = maxPoints * (int) Math.sqrt(maxPoints);
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[maxPoints];
        for (int j = 0; j < maxPoints; j++) {
            points[j] = new _2DPoint(random.nextInt(1000000), random.nextInt(maxPoints));
        }
        _2DRangeTree tree = _2DRangeTree.create(points);
        int[][] queries = new int[maxQueries][4];
        for (int j = 0; j < maxQueries; j++) {
            int x1 = random.nextInt(1000000), x2 = random.nextInt(1000000),
                y1 = random.nextInt(maxPoints), y2 = random.nextInt(maxPoints);
            queries[j][0] = Math.min(x1, x2);
            queries[j][1] = Math.min(y1, y2);
            queries[j][2] = Math.max(x1, x2);
            queries[j][3] = Math.max(y1, y2);
        }
        int[] queriesResults = new int[maxQueries];
        long start = System.currentTimeMillis();
        for (int j = 0; j < maxQueries; j++) {
            queriesResults[j] = tree.query(queries[j][0], queries[j][1], queries[j][2], queries[j][3] % 2 == 0);
        }
        System.out.println(String.format("randomTest2 %d queries in %d milis.", maxQueries, System.currentTimeMillis() - start));
    }
    
    private int linearCounter(_2DPoint[] points, int x1, int x2, int y, boolean belowOrEqual) {
        int count = 0;
        for (_2DPoint p: points) {
            if (p.x >= x1 && p.x <= x2) {
                if (belowOrEqual && p.y <= y || p.y >= y) {
                    count++;
                }
            }
        }
        return count;
    }

}
