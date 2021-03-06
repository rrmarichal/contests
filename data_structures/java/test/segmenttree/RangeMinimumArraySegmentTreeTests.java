package segmenttree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RangeMinimumArraySegmentTreeTests {

    private static final int MINUMUM = -10;

    private Integer[] values;
    private ArraySegmentTree<Integer> tree;

    @Before
    public void init() {
        values = new Integer[] { 1, 2, 3, 4, 5, MINUMUM, 7, 8, 9, 10 };
        tree = ArraySegmentTree.create(values, new IntegerMinimumOperation());
    }

    @Test
    public void nextPowerOfTwoTests() {
        assertEquals(1, tree.nextPowerOfTwo(0));
        assertEquals(2, tree.nextPowerOfTwo(1));
        assertEquals(4, tree.nextPowerOfTwo(2));
        assertEquals(8, tree.nextPowerOfTwo(6));
        assertEquals(64, tree.nextPowerOfTwo(41));
    }

    @Test
    public void sumsTest0() {
        int result = tree.value(0, values.length - 1);
        assertEquals(MINUMUM, result);
    }

    @Test
    public void sumsTest1() {
        int index = new Random().nextInt(values.length);
        int result = tree.value(0, index);
        assertEquals(index < 5 ? 1 : MINUMUM, result);
    }

    @Test
    public void incrementAtOtherThanMinimumDoesNotChangeMinimumTest() {
        int minimum = tree.value(0, values.length - 1);
        tree.increment(3, 100);
        assertEquals(minimum, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void incrementByTheMinimumTest() {
        int minimum = tree.value(0, values.length - 1);
        tree.increment(5, MINUMUM);
        assertEquals(minimum + minimum, (int) tree.value(0, values.length - 1));
    }

}
