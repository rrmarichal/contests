package contests.segment;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

class IntegerSumOperation implements SegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer update(Integer current, Integer update) {
        return current + update;
    }
    
    @Override
    public Integer nil() {
        return 0;
    }

}

public class RangeSumSegmentTreeTests {

    private Integer[] values;
    private SegmentTree<Integer> tree;

    @Before
    public void init() {
        values = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        tree = SegmentTree.create(values, new IntegerSumOperation());
    }

    @Test
    public void sumsTest0() {
        int result = tree.value(0, values.length - 1);
        assertEquals(55, result);
    }

    @Test
    public void sumsTest1() {
        int index = new Random().nextInt(values.length);
        int result = tree.value(index, index);
        assertEquals(index + 1, result);
    }

}
