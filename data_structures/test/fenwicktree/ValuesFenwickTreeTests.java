package fenwicktree;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class ValuesFenwickTreeTests {

    private static final int SIZE = 10;

    private Random random;
    private FenwickTree tree;
    private int[] values;

    @Before
    public void setup() {
        random = new Random();
        values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        tree = FenwickTree.withValues(values);
    }

    @Test
    public void initializationTest0() {
        assertEquals(SIZE * (SIZE + 1) / 2, tree.sum(0, SIZE - 1));
    }

    @Test
    public void incrementTest0() {
        int increment = random.nextInt();
        tree.increment(random.nextInt(SIZE), increment);
        assertEquals(SIZE * (SIZE + 1) / 2 + increment, tree.sum(0, SIZE - 1));
    }

    @Test
    public void totalTest0() {
        assertEquals(SIZE * (SIZE + 1) / 2, tree.getTotal());
        tree.increment(random.nextInt(SIZE), 1);
        tree.increment(random.nextInt(SIZE), 2);
        tree.increment(random.nextInt(SIZE), 3);
        assertEquals(SIZE * (SIZE + 1) / 2 + 6L, tree.getTotal());
    }

    @Test
    public void totalTest1() {
        assertEquals(SIZE * (SIZE + 1) / 2, tree.getTotal());
        int value1 = Integer.MAX_VALUE;
        int value2 = Integer.MAX_VALUE;
        tree.increment(random.nextInt(SIZE), value1);
        tree.increment(random.nextInt(SIZE), value2);
        assertEquals(SIZE * (SIZE + 1) / 2 + (long)value1 + value2, tree.getTotal());
    }

}
