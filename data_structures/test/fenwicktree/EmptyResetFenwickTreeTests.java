package fenwicktree;

import static org.junit.Assert.*;

import org.junit.*;

public class EmptyResetFenwickTreeTests {

    private static final int SIZE = 10;

    private ResetFenwickTree tree;

    @Before
    public void setup() {
        tree = ResetFenwickTree.empty(SIZE);
    }

    @Test
    public void incrementResetTest0() {
        tree.increment(0, 1);
        assertEquals(1, tree.sum(0, SIZE - 1));
        tree.reset();
        assertEquals(0, tree.sum(0, SIZE - 1));
        assertEquals(0, tree.getTotal());
    }

    @Test
    public void incrementResetTest1() {
        for (int j = 0; j < SIZE; j++) {
            tree.increment(j, j);
        }
        assertEquals(SIZE * (SIZE - 1)/2, tree.sum(0, SIZE - 1));
        tree.reset();
        assertEquals(0, tree.sum(0, SIZE - 1));
        assertEquals(0, tree.getTotal());
    }

    @Test
    public void incrementResetIncrementTest0() {
        for (int j = 0; j < SIZE; j++) {
            tree.increment(j, j);
        }
        assertEquals(SIZE * (SIZE - 1)/2, tree.sum(0, SIZE - 1));
        tree.reset();
        assertEquals(0, tree.sum(0, SIZE - 1));
        tree.increment(5, 1000);
        assertEquals(1000, tree.sum(0, SIZE - 1));
        assertEquals(1000, tree.getTotal());
    }

}
