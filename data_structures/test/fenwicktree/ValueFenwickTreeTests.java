package fenwicktree;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class ValueFenwickTreeTests {

    private static final int SIZE = 100;

    private FenwickTree tree;
    private Random random;
    private int value;

    @Before
    public void setup() {
        random = new Random();
        value = 1;
        tree = FenwickTree.withValue(SIZE, value);
    }

    @Test
    public void initializationTest0() {
        assertEquals(SIZE * value, tree.sum(0, SIZE - 1));
    }

    @Test
    public void initializationTest1() {
        int length = random.nextInt(SIZE) + 1;
        int left = random.nextInt(SIZE - length + 1);
        assertEquals(length * value, tree.sum(left, left + length - 1));
    }

    @Test
    public void incrementTest0() {
        int increment = random.nextInt();
        tree.increment(random.nextInt(SIZE), increment);
        assertEquals(SIZE * value + increment, tree.sum(0, SIZE - 1));
    }

    @Test
    public void totalTest0() {
        assertEquals(value * SIZE, tree.getTotal());
        tree.increment(random.nextInt(SIZE), 1);
        tree.increment(random.nextInt(SIZE), 2);
        tree.increment(random.nextInt(SIZE), 3);
        assertEquals(value * SIZE + 6L, tree.getTotal());
    }

    @Test
    public void totalTest1() {
        assertEquals(value * SIZE, tree.getTotal());
        int value1 = Integer.MAX_VALUE;
        int value2 = Integer.MAX_VALUE;
        tree.increment(random.nextInt(SIZE), value1);
        tree.increment(random.nextInt(SIZE), value2);
        assertEquals(value * SIZE + (long)value1 + value2, tree.getTotal());
    }

}
