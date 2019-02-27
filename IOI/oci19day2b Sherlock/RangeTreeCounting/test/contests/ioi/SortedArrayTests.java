package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SortedArrayTests {

    @Test
    public void belowOrEqualQueryTest0() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(2, array.belowOrEqual(4));
    }

    @Test
    public void belowOrEqualQueryTest1() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(4, array.belowOrEqual(6));
    }

    @Test
    public void belowOrEqualQueryTest2() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(points.length, array.belowOrEqual(Integer.MAX_VALUE));
    }

    @Test
    public void belowOrEqualQueryTest3() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(points.length, array.belowOrEqual(21));
    }

    @Test
    public void belowOrEqualQueryTest4() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(0, array.belowOrEqual(Integer.MIN_VALUE));
    }

    @Test
    public void aboveOrEqualQueryTest0() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(3, array.aboveOrEqual(4));
    }

    @Test
    public void aboveOrEqualQueryTest1() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(2, array.aboveOrEqual(11));
    }

    @Test
    public void aboveOrEqualQueryTest2() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(points.length, array.aboveOrEqual(1));
    }

    @Test
    public void aboveOrEqualQueryTest3() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(0, array.aboveOrEqual(Integer.MAX_VALUE));
    }

    @Test
    public void aboveOrEqualQueryTest4() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(5, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(points.length, array.aboveOrEqual(Integer.MIN_VALUE));
    }

    @Test
    public void aboveOrEqualQueryTest5() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(1, 3), 0),
            new _2DPointEx(new _2DPoint(2, 3), 0),
            new _2DPointEx(new _2DPoint(3, 3), 0),
            new _2DPointEx(new _2DPoint(4, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(7, array.aboveOrEqual(3));
    }

    @Test
    public void aboveOrEqualQueryTest6() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(1, 3), 0),
            new _2DPointEx(new _2DPoint(2, 3), 0),
            new _2DPointEx(new _2DPoint(3, 3), 0),
            new _2DPointEx(new _2DPoint(4, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(3, array.aboveOrEqual(4));
    }

    @Test
    public void aboveOrEqualQueryTest7() {
        _2DPointEx[] points = new _2DPointEx[] {
            new _2DPointEx(new _2DPoint(5, 1), 0),
            new _2DPointEx(new _2DPoint(1, 3), 0),
            new _2DPointEx(new _2DPoint(2, 3), 0),
            new _2DPointEx(new _2DPoint(3, 3), 0),
            new _2DPointEx(new _2DPoint(4, 3), 0),
            new _2DPointEx(new _2DPoint(5, 6), 0),
            new _2DPointEx(new _2DPoint(5, 11), 0),
            new _2DPointEx(new _2DPoint(5, 21), 0)
        };
        SortedArray array = SortedArray.create(points);
        assertEquals(1, array.aboveOrEqual(21));
    }

}
