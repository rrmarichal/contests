package segmenttree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RangeMaximumArraySegmentTree2Tests {

    private static final int MAXIMUM = 100;

    private Integer[] values;
    private ArraySegmentTree2<Integer> tree;
    private IntegerMaximumOperation operation;

    @Before
    public void init() {
        operation = new IntegerMaximumOperation();
        values = new Integer[] { 1, 2, 3, 4, 5, MAXIMUM, 7, 8, 9, 10 };
        tree = ArraySegmentTree2.create(values, operation);
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
    public void maximumTest0() {
        int result = tree.value(0, values.length - 1);
        assertEquals(MAXIMUM, result);
    }

    @Test
    public void maximumTest1() {
        int index = new Random().nextInt(values.length);
        int result = tree.value(0, index);
        assertEquals(index < 5 ? index + 1 : MAXIMUM, result);
    }

    @Test
    public void decrementAtOtherThanMaximumDoesNotChangeMaximumTest() {
        int maximum = tree.value(0, values.length - 1);
        tree.increment(3, -1);
        assertEquals(maximum, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void incrementByTheMaximumTest() {
        int maximum = tree.value(0, values.length - 1);
        tree.increment(5, MAXIMUM);
        assertEquals(maximum + maximum, (int) tree.value(0, values.length - 1));
    }

    @Test
    public void KOTest0() {
        values = new Integer[] { 9, 5, 8, 4  };
        tree = ArraySegmentTree2.create(values, operation);
        assertEquals(8, (int) tree.value(1, 3));
    }

    @Test
    public void randomQueriesTest() {
        final int SIZE = 1000;
        final int OPERATIONS = 100;
        final int MAX_VALUE = Integer.MAX_VALUE;
        Random random = new Random();
        Integer[] values = new Integer[SIZE];
        for (int j = 0; j < SIZE; j++) {
            values[j] = random.nextInt(MAX_VALUE);
        }
        ArraySegmentTree2<Integer> tree = ArraySegmentTree2.create(values, operation);
        for (int j = 0; j < OPERATIONS; j++) {
            if (random.nextInt(Integer.MAX_VALUE) % 2 == 0) {
                // Update op.
                int index = random.nextInt(SIZE);
                int incrementValue = random.nextInt();
                values[index] += incrementValue;
                tree.increment(index, incrementValue);
                // System.out.println(String.format("Update operation at index '%d' with value '%d'", index, incrementValue));
            }
            else {
                // Query op.
                int low = random.nextInt(SIZE),
                    high = low + random.nextInt(SIZE - low);
                int tmax = tree.value(low, high);
                int bmax = bruteForce(values, low, high);
                // System.out.println(String.format("Query operation from [%d..%d] => tmax: '%d', bmax='%d'.", low, high, tmax, bmax));
                assertEquals(tmax, bmax);
            }
        }
    }

    private int bruteForce(Integer[] values, int low, int high) {
        int result = Integer.MIN_VALUE;
        for (int j = low; j <= high; j++) {
            result = operation.aggregate(result, values[j]);
        }
        return result;
    }

}
