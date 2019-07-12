package fenwicktree;

import static org.junit.Assert.*;

import org.junit.*;

public class ValuesResetFenwickTreeTests {

    @Test
    public void incrementResetTest0() {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ResetFenwickTree tree = ResetFenwickTree.withValues(values);
        tree.increment(0, 1);
        assertEquals(values.length * (values.length + 1) / 2 + 1, tree.sum(0, values.length - 1));
        tree.reset();
        assertEquals(values.length * (values.length + 1) / 2, tree.sum(0, values.length - 1));
        assertEquals(values.length * (values.length + 1) / 2, tree.getTotal());
    }

    @Test
    public void incrementResetTest1() {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ResetFenwickTree tree = ResetFenwickTree.withValues(values);
        for (int j = 0; j < values.length; j++) {
            tree.increment(j, j+1);
        }
        assertEquals(2 * values.length * (values.length + 1) / 2, tree.sum(0, values.length - 1));
        tree.reset();
        assertEquals(values.length * (values.length + 1) / 2, tree.sum(0, values.length - 1));
        assertEquals(values.length * (values.length + 1) / 2, tree.getTotal());
    }

    @Test
    public void incrementResetIncrementTest0() {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ResetFenwickTree tree = ResetFenwickTree.withValues(values);
        for (int j = 0; j < values.length; j++) {
            tree.increment(j, 25);
        }
        assertEquals(25 * values.length + values.length * (values.length + 1)/2, tree.sum(0, values.length - 1));
        tree.reset();
        assertEquals(values.length * (values.length + 1)/2, tree.sum(0, values.length - 1));
        tree.increment(5, 1000);
        tree.increment(8, 1000);
        tree.increment(1, 1000);
        assertEquals(values.length * (values.length + 1)/2 + 3000, tree.sum(0, values.length - 1));
        assertEquals(values.length * (values.length + 1)/2 + 3000, tree.getTotal());
    }

}
