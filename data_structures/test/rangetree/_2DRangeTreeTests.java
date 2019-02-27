package rangetree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class _2DRangeTreeTests {
    
    @Test
    public void simpleTest0() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(5, 3, 2),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(0, 0, 10, 10);
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
        int count = tree.query(0, 0, 1, 1);
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
        int count = tree.query(0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);
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
        int count = tree.query(3, 2, 6, 5);
        assertEquals(3, count);
    }

    @Test
    public void pointsWithEqualXY0() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(1, 7, 3),
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(7, 4, 0),
            new _2DPoint(5, 3, 2),
            new _2DPoint(6, 1, 1),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8),
            new _2DPoint(5, 5, 5)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(0, 0, 10, 10);
        assertEquals(points.length, count);
    }

    @Test
    public void pointsWithEqualXY1() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(1, 7, 3),
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(7, 4, 0),
            new _2DPoint(5, 3, 2),
            new _2DPoint(6, 1, 1),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8),
            new _2DPoint(5, 5, 5)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(1, 1, 6, 1);
        assertEquals(2, count);
    }

    @Test
    public void pointsWithEqualXY2() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(1, 7, 3),
            new _2DPoint(3, 4, 7),
            new _2DPoint(1, 1, 6),
            new _2DPoint(7, 4, 0),
            new _2DPoint(5, 3, 2),
            new _2DPoint(6, 1, 1),
            new _2DPoint(2, 5, 4),
            new _2DPoint(4, 2, 8),
            new _2DPoint(5, 5, 5)
        };
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(1, 0, -1, -10);
        assertEquals(0, count);
    }

    @Test
    public void randomTest0() {
        int max = 1000;
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[max];
        for (int j = 0; j < max; j++) {
            points[j] = new _2DPoint(random.nextInt(), random.nextInt());
        }
        _2DRangeTree tree = _2DRangeTree.create(points);
        int count = tree.query(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(max, count);
    }

    @Test
    public void randomTest1() {
        int max = 100000;
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[max];
        for (int j = 0; j < max; j++) {
            points[j] = new _2DPoint(random.nextInt(), random.nextInt());
        }
        _2DRangeTree tree = _2DRangeTree.create(points);
        // Count positive coordinates points.
        int count = tree.query(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(linearCounter(points, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE), count);
    }

    @Test
    public void randomTest2() {
        int maxPoints = 10000;
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
            queriesResults[j] = tree.query(queries[j][0], queries[j][1], queries[j][2], queries[j][3]);
        }
        System.out.println(String.format("randomTest2 %d queries in %d milis.", maxQueries, System.currentTimeMillis() - start));
    }
    
    private int linearCounter(_2DPoint[] points, int x1, int y1, int x2, int y2) {
        int count = 0;
        for (_2DPoint p: points) {
            if (p.x >= x1 && p.x <= x2 && p.y >= y1 && p.y <= y2) {
                count++;
            }
        }
        return count;
    }

}
