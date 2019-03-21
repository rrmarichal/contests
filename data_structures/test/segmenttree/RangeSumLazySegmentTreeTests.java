package segmenttree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RangeSumLazySegmentTreeTests {

    private Integer[] values;
    private LazySegmentTree<Integer> tree;
    private LazySegmentTreeOperation<Integer> operation;

    @Before
    public void init() {
        operation = new LazyIntegerSumOperation();
        values = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        tree = LazySegmentTree.create(values, operation);
    }

    @Test
    public void sumTest0() {
        int result = tree.value(0, values.length - 1);
        assertEquals(36, result);
    }

    @Test
    public void maximumTest1() {
        int index = new Random().nextInt(values.length);
        int result = tree.value(0, index);
        assertEquals((index + 1) * (index + 2) / 2, result);
    }

    @Test
    public void unitaryIncrementTest() {
        int sum = tree.value(0, values.length - 1);
        tree.increment(4, 4, 10);
        assertEquals(sum + 10, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void rangeIncrementTest0() {
        int sum = tree.value(0, values.length - 1);
        int delta = new Random().nextInt((int) 10);
        tree.increment(0, values.length - 1, delta);
        assertEquals(sum + values.length * delta, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void rangeIncrementTest1() {
        int sum = tree.value(0, values.length - 1);
        int delta1 = new Random().nextInt((int) 1e8);
        tree.increment(0, 6, delta1);
        int delta2 = new Random().nextInt((int) 1e8);
        tree.increment(3, 5, delta2);
        assertEquals(sum + 7 * delta1 + 3 * delta2, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void customArrayTest() {
        values = new Integer[] { 9, 5, 8, 4  };
        tree = LazySegmentTree.create(values, operation);
        assertEquals(5 + 8 + 4, (int) tree.value(1, 3));
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
                int incrementValue = random.nextInt(MAX_VALUE);
                values[index] += incrementValue;
                tree.increment(index, index, incrementValue);
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
                int incrementValue = random.nextInt(MAX_VALUE);
                for (int k = low; k <= high; k++) {
                    values[k] += incrementValue;
                }
                tree.increment(low, high, incrementValue);
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
