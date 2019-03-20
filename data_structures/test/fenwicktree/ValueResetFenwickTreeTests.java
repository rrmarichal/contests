package fenwicktree;

import static org.junit.Assert.*;

import org.junit.*;

public class ValueResetFenwickTreeTests {

    private static final int SIZE = 10;

    @Test
    public void incrementResetTest0() {
        ResetFenwickTree tree = ResetFenwickTree.withValue(SIZE, 1);
        tree.increment(0, 1);
        assertEquals(SIZE + 1, tree.sum(0, SIZE - 1));
        tree.reset();
        assertEquals(SIZE, tree.sum(0, SIZE - 1));
        assertEquals(SIZE, tree.getTotal());
    }

    @Test
    public void incrementResetTest1() {
        ResetFenwickTree tree = ResetFenwickTree.withValue(SIZE, 1);
        for (int j = 0; j < SIZE; j++) {
            tree.increment(j, j);
        }
        assertEquals(SIZE * (SIZE + 1)/2, tree.sum(0, SIZE - 1));
        tree.reset();
        assertEquals(SIZE, tree.sum(0, SIZE - 1));
        assertEquals(SIZE, tree.getTotal());
    }

    @Test
    public void incrementResetIncrementTest0() {
        ResetFenwickTree tree = ResetFenwickTree.withValue(SIZE, 7);
        for (int j = 0; j < SIZE; j++) {
            tree.increment(j, j);
        }
        assertEquals(SIZE * 7 + SIZE * (SIZE - 1)/2, tree.sum(0, SIZE - 1));
        tree.reset();
        assertEquals(SIZE * 7, tree.sum(0, SIZE - 1));
        tree.increment(5, 1000);
        assertEquals(SIZE * 7 + 1000, tree.sum(0, SIZE - 1));
        assertEquals(SIZE * 7 + 1000, tree.getTotal());
    }

}
