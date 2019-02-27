package contests.segmenttree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RangeSumWithIncrementsArraySegmentTreeTests {

    private Integer[] values;
    private ArraySegmentTree<Integer> tree;

    @Before
    public void init() {
        values = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        tree = ArraySegmentTree.create(values, new IntegerSumWithIncrementsOperation());
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
    public void incrementsTest0() {
        int index = new Random().nextInt(values.length);
        int sum = tree.value(0, values.length - 1);
        tree.increment(index, 1);
        assertEquals(sum + 1, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void incrementsTest1() {
        int sum = tree.value(0, values.length - 1);
        for (int j = 0; j < values.length; j++) {
            tree.increment(j, values[j]);
        }
        assertEquals(2 * sum, (int) tree.value(0, values.length - 1));
    }

}
