import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MaxSegmentTreeTests {

    @Test
    public void test0() {
        int[] values = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        MaxSegmentTree t = MaxSegmentTree.create(values);
        assertEquals(7, t.max(0, values.length - 1).index);
        assertEquals(0, t.max(0, 0).index);
        assertEquals(1, t.max(1, 1).index);
        assertEquals(5, t.max(5, 5).index);
    }

    @Test
    public void test1() {
        int[] values = new int[] { 7, 6, 5, 4, 3, 2, 1 };
        MaxSegmentTree t = MaxSegmentTree.create(values);
        assertEquals(0, t.max(0, values.length - 1).index);
    }

    @Test
    public void test2() {
        int[] values = new int[] { 19, 23, -10, 7, 4, 6, 44, 5, 8, 4, 3, -2, 10 };
        MaxSegmentTree t = MaxSegmentTree.create(values);
        assertEquals(6, t.max(0, values.length - 1).index);
        assertEquals(1, t.max(0, 5).index);
        assertEquals(values.length - 1, t.max(7, values.length - 1).index);
    }

}
