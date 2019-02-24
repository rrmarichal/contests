package fenwicktree;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class EmptyFenwickTreeTests {

    private static final int SIZE = 100;

    private FenwickTree tree;
    private Random random;

    @Before
    public void setup() {
        tree = FenwickTree.empty(SIZE);
        random = new Random();
    }

    @Test
    public void incrementTest0() {
        int value = random.nextInt();
        tree.increment(random.nextInt(SIZE), value);
        assertEquals(value, tree.sum(0, SIZE - 1));
    }

    @Test
    public void incrementTest1() {
        for (int j = 0; j < SIZE; j++) {
            tree.increment(j, j);
        }
        assertEquals(SIZE * (SIZE - 1) / 2, tree.sum(0, SIZE - 1));
    }

    @Test
    public void incrementTest2() {
        int value1 = random.nextInt();
        int value2 = random.nextInt();
        tree.increment(random.nextInt(SIZE), value1);
        tree.increment(random.nextInt(SIZE), value2);
        assertEquals((long)value1 + value2, tree.sum(0, SIZE - 1));
    }

    @Test
    public void incrementTest3() {
        int value1 = Integer.MAX_VALUE;
        int value2 = Integer.MAX_VALUE;
        tree.increment(random.nextInt(SIZE), value1);
        tree.increment(random.nextInt(SIZE), value2);
        assertEquals((long)value1 + value2, tree.sum(0, SIZE - 1));
    }

}
