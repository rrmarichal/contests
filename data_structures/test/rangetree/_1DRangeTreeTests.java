package rangetree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class _1DRangeTreeTests {
    
    @Test
    public void simpleTest0() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 6), 0),
            new _2DPointEx(new _2DPoint(4, 2, 8), 0),
            new _2DPointEx(new _2DPoint(5, 3, 2), 0),
            new _2DPointEx(new _2DPoint(3, 4, 7), 0),
            new _2DPointEx(new _2DPoint(2, 5, 4), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(0, 0);
        assertEquals(0, count);
    }

    @Test
    public void simpleTest1() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 6), 0),
            new _2DPointEx(new _2DPoint(4, 2, 8), 0),
            new _2DPointEx(new _2DPoint(5, 3, 2), 0),
            new _2DPointEx(new _2DPoint(3, 4, 7), 0),
            new _2DPointEx(new _2DPoint(2, 5, 4), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(points.length, count);
    }

    @Test
    public void simpleTest2() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 6), 0),
            new _2DPointEx(new _2DPoint(4, 2, 8), 0),
            new _2DPointEx(new _2DPoint(5, 3, 2), 0),
            new _2DPointEx(new _2DPoint(3, 4, 7), 0),
            new _2DPointEx(new _2DPoint(2, 5, 4), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(2, 4);
        assertEquals(3, count);
    }

    @Test
    public void simpleTest3() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 6), 0),
            new _2DPointEx(new _2DPoint(4, 2, 8), 0),
            new _2DPointEx(new _2DPoint(5, 3, 2), 0),
            new _2DPointEx(new _2DPoint(3, 4, 7), 0),
            new _2DPointEx(new _2DPoint(2, 5, 4), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(5, 5);
        assertEquals(1, count);
    }

    @Test
    public void simpleTest4() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 6), 0),
            new _2DPointEx(new _2DPoint(4, 2, 8), 0),
            new _2DPointEx(new _2DPoint(5, 3, 2), 0),
            new _2DPointEx(new _2DPoint(3, 4, 7), 0),
            new _2DPointEx(new _2DPoint(2, 5, 4), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(3, Integer.MAX_VALUE);
        assertEquals(3, count);
    }

    @Test
    public void simpleTest5() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 6), 0),
            new _2DPointEx(new _2DPoint(4, 2, 8), 0),
            new _2DPointEx(new _2DPoint(5, 3, 2), 0),
            new _2DPointEx(new _2DPoint(3, 4, 7), 0),
            new _2DPointEx(new _2DPoint(2, 5, 4), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(4, 4);
        assertEquals(1, count);
    }

    @Test
    public void simpleTest6() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 6), 0),
            new _2DPointEx(new _2DPoint(4, 2, 8), 0),
            new _2DPointEx(new _2DPoint(5, 3, 2), 0),
            new _2DPointEx(new _2DPoint(3, 4, 7), 0),
            new _2DPointEx(new _2DPoint(2, 5, 4), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals(0, count);
    }

    @Test
    public void pointsOnALineTest0() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 1), 0),
            new _2DPointEx(new _2DPoint(2, 1, 2), 0),
            new _2DPointEx(new _2DPoint(4, 1, 3), 0),
            new _2DPointEx(new _2DPoint(7, 1, 4), 0),
            new _2DPointEx(new _2DPoint(11, 1, 5), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(1, 1);
        assertEquals(points.length, count);
    }

    @Test
    public void pointsOnALineTest1() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 1), 0),
            new _2DPointEx(new _2DPoint(2, 1, 2), 0),
            new _2DPointEx(new _2DPoint(4, 1, 3), 0),
            new _2DPointEx(new _2DPoint(7, 1, 4), 0),
            new _2DPointEx(new _2DPoint(11, 1, 5), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(points.length, count);
    }

    @Test
    public void pointsOnALineTest2() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(1, 1, 1), 0),
            new _2DPointEx(new _2DPoint(2, 1, 2), 0),
            new _2DPointEx(new _2DPoint(4, 1, 3), 0),
            new _2DPointEx(new _2DPoint(7, 1, 4), 0),
            new _2DPointEx(new _2DPoint(11, 1, 5), 0)
        };
        _1DRangeTree tree = _1DRangeTree.create(points);
        int count = tree.query(2, Integer.MAX_VALUE);
        assertEquals(0, count);
    }

}
