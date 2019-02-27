package contests.segmenttree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RangeSumWithUpdatesArraySegmentTreeTests {

    private Integer[] values;
    private ArraySegmentTree<Integer> tree;

    @Before
    public void init() {
        values = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        tree = ArraySegmentTree.create(values, new IntegerSumWithUpdatesOperation());
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

    @Test
    public void updatesTest0() {
        int index = new Random().nextInt(values.length);
        int sum = tree.value(0, values.length - 1);
        // Set value at index to 1. Originally index+1.
        tree.increment(index, 1);
        assertEquals(sum - values[index] + 1, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void updatesTest1() {
        // Set all elements to a fixed value.
        for (int j = 0; j < values.length; j++) {
            tree.increment(j, (int) Short.MAX_VALUE);
        }
        assertEquals(values.length * Short.MAX_VALUE, (int) tree.value(0, values.length - 1));
    }

}
