package contests.fenwicktree;

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

}
