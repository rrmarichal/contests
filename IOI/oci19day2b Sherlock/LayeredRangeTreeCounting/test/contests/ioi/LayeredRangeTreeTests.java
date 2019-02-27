package contests.ioi;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

public class LayeredRangeTreeTests {
    
    @Test
    public void simpleTest0_0() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.aboveOrEqual(0, 10, 0);
        assertEquals(points.length, count);
    }

    @Test
    public void simpleTest0_1() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.aboveOrEqual(0, 10, 5);
        assertEquals(4, count);
    }

    @Test
    public void simpleTest0_2() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.aboveOrEqual(0, 10, Integer.MAX_VALUE);
        assertEquals(0, count);
    }

    @Test
    public void simpleTest0_3() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.aboveOrEqual(0, 10, Integer.MIN_VALUE);
        assertEquals(points.length, count);
    }

    @Test
    public void simpleTest1_0() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.belowOrEqual(0, 10, 10);
        assertEquals(points.length, count);
    }

    @Test
    public void simpleTest1_1() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.belowOrEqual(0, 10, 3);
        assertEquals(2, count);
    }

    @Test
    public void simpleTest1_2() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.belowOrEqual(0, 10, Integer.MAX_VALUE);
        assertEquals(points.length, count);
    }

    @Test
    public void simpleTest1_3() {
        _2DPoint[] points = new _2DPoint[] {
            new _2DPoint(0, 7, 0),
            new _2DPoint(1, 9, 1),
            new _2DPoint(2, 3, 2),
            new _2DPoint(3, 5, 3),
            new _2DPoint(4, 1, 4),
            new _2DPoint(5, 6, 5),
            new _2DPoint(6, 4, 6)
        };
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.belowOrEqual(0, 10, Integer.MIN_VALUE);
        assertEquals(0, count);
    }

    @Test
    public void randomTest0_0() {
        int max = 1000;
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[max];
        for (int j = 0; j < max; j++) {
            points[j] = new _2DPoint(random.nextInt(), random.nextInt());
        }
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.aboveOrEqual(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);
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
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int count = tree.belowOrEqual(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
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
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        // Count positive coordinates points.
        int count = tree.aboveOrEqual(0, Integer.MAX_VALUE, 0);
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
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        // Count positive coordinates points.
        int count = tree.belowOrEqual(0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(linearCounter(points, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, true), count);
    }

    @Test
    // @Ignore("Only for performance measures purposes.")
    public void randomTest2() {
        int maxPoints = 10000;
        int maxQueries = maxPoints * (int) Math.sqrt(maxPoints);
        Random random = new Random();
        _2DPoint[] points = new _2DPoint[maxPoints];
        for (int j = 0; j < maxPoints; j++) {
            points[j] = new _2DPoint(random.nextInt(1000000), random.nextInt(maxPoints));
        }
        LayeredRangeTree tree = LayeredRangeTree.create(points);
        int[][] queries = new int[maxQueries][4];
        for (int j = 0; j < maxQueries; j++) {
            int x1 = random.nextInt(1000000), x2 = random.nextInt(1000000),
                y = random.nextInt(maxPoints), type = random.nextInt(maxPoints);
            queries[j][0] = Math.min(x1, x2);
            queries[j][1] = Math.max(x1, x2);
            queries[j][2] = y;
            queries[j][3] = type;
        }
        int[] queriesResults = new int[maxQueries];
        long start = System.currentTimeMillis();
        for (int j = 0; j < maxQueries; j++) {
            if (queries[j][3] % 2 == 0) {
                queriesResults[j] = tree.aboveOrEqual(queries[j][0], queries[j][1], queries[j][2]);
            }
            else {
                queriesResults[j] = tree.belowOrEqual(queries[j][0], queries[j][1], queries[j][2]);
            }
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
