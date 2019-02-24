package contests.segment;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

class IntegerMinimumOperation implements SegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        return Math.min(left, right);
    }

    @Override
    public Integer update(Integer current, Integer update) {
        return current + update;
    }

    @Override
    public Integer nil() {
        return Integer.MAX_VALUE;
    }

}

public class RangeMinimumSegmentTreeTests {

    private static final int MINUMUM = -10;

    private Integer[] values;
    private SegmentTree<Integer> tree;

    @Before
    public void init() {
        values = new Integer[] { 1, 2, 3, 4, 5, MINUMUM, 7, 8, 9, 10 };
        tree = SegmentTree.create(values, new IntegerMinimumOperation());
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
    public void incrementDoesNotChangeMinimumTest() {
        int minimum = tree.value(0, values.length - 1);
        tree.increment(new Random().nextInt(values.length), 100);
        assertEquals(minimum, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void incrementByTheMinimumTest() {
        int minimum = tree.value(0, values.length - 1);
        tree.increment(5, MINUMUM);
        assertEquals(minimum + minimum, (int) tree.value(0, values.length - 1));
    }

}
