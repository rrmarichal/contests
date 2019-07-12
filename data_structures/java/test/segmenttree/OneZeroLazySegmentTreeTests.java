package segmenttree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class OneZeroLazySegmentTreeTests {

    private Integer[] values;
    private LazySegmentTree<Integer> tree;
    private LazySegmentTreeOperation<Integer> operation;

    @Before
    public void init() {
        operation = new LazyOneZeroSumOperation();
        values = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1 };
        tree = LazySegmentTree.create(values, operation);
    }

    @Test
    public void sumTest0() {
        int result = tree.value(0, values.length - 1);
        assertEquals(8, result);
    }

    @Test
    public void sumTest1() {
        int index = new Random().nextInt(values.length);
        int result = tree.value(0, index);
        assertEquals((index + 1), result);
    }

    @Test
    public void unitaryDecrementTest() {
        int sum = tree.value(0, values.length - 1);
        tree.increment(4, 4, -1);
        assertEquals(sum - 1, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void rangeDecrementTest0() {
        tree.increment(0, values.length - 1, -1);
        assertEquals(0, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void rangeDecrementTest1() {
        tree.increment(0, 6, -1);
        tree.increment(3, 5, -1);
        assertEquals(1, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void rangeDecrementTest2() {
        for (int j = 0; j < values.length; j++) {
            tree.increment(j, j, -1);
        }
        assertEquals(0, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void rangeDecrementTest3() {
        Random random = new Random();
        for (int j = 0; j < 100; j++) {
            int low = random.nextInt(values.length),
                high = low + random.nextInt(values.length - low);
            tree.increment(low, high, -1);
        }
        assertEquals(0, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void randomQueriesWithUnitaryUpdatesTest() {
        final int SIZE = 1000;
        final int OPERATIONS = 1000;
        final int MAX_VALUE = (int) 1e6;
        Random random = new Random();
        Integer[] values = new Integer[SIZE];
        for (int j = 0; j < SIZE; j++) {
            values[j] = random.nextInt(MAX_VALUE);
        }
        LazySegmentTree<Integer> tree = LazySegmentTree.create(values, operation);
        for (int j = 0; j < OPERATIONS; j++) {
            if (random.nextInt(Integer.MAX_VALUE) % 2 == 0) {
                // Update op.
                int index = random.nextInt(SIZE);
                values[index] = 0;
                tree.increment(index, index, -1);
                // System.out.println(String.format("Update operation at index '%d' with value '%d'", index, incrementValue));
            }
            else {
                // Query op.
                int low = random.nextInt(SIZE),
                    high = low + random.nextInt(SIZE - low);
                int tsum = tree.value(low, high);
                int bsum = bruteForce(values, low, high);
                // System.out.println(String.format("Query operation from [%d..%d] => tsum: '%d', bsum='%d'.", low, high, tsum, bsum));
                assertEquals(bsum, tsum);
            }
        }
    }

    @Test
    public void randomQueriesWithRangeUpdatesTest() {
        final int SIZE = 1000;
        final int OPERATIONS = 1000;
        final int MAX_VALUE = 100;
        Random random = new Random();
        Integer[] values = new Integer[SIZE];
        for (int j = 0; j < SIZE; j++) {
            values[j] = random.nextInt(MAX_VALUE);
        }
        LazySegmentTree<Integer> tree = LazySegmentTree.create(values, operation);
        for (int j = 0; j < OPERATIONS; j++) {
            if (random.nextInt(Integer.MAX_VALUE) % 2 == 0) {
                // Update op.
                int low = random.nextInt(SIZE);
                int high = low + random.nextInt(SIZE - low);
                for (int k = low; k <= high; k++) {
                    values[k] = 0;
                }
                tree.increment(low, high, -1);
                // System.out.println(String.format("Update operation from [%d..'%d'] with value '%d'", low, high, incrementValue));
            }
            else {
                // Query op.
                int low = random.nextInt(SIZE),
                    high = low + random.nextInt(SIZE - low);
                int tsum = tree.value(low, high);
                int bsum = bruteForce(values, low, high);
                // System.out.println(String.format("Query operation from [%d..%d] => tsum: '%d', bsum='%d'.", low, high, tsum, bsum));
                assertEquals(bsum, tsum);
            }
        }
    }

    private int bruteForce(Integer[] values, int low, int high) {
        int result = operation.nil();
        for (int j = low; j <= high; j++) {
            result = operation.aggregate(result, values[j]);
        }
        return result;
    }

}
