package fenwicktree;

import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Do not run compute intensive tests by default.")
public class PerformanceTests {

    private static final int SIZE = (int) 1e6;
    private static final int INCREMENT_OPERATIONS = (int) (1e5 * Math.sqrt(1e5));
    private static final int SUM_OPERATIONS = (int) (1e5 * Math.sqrt(1e5));

    private FenwickTree tree;
    private Random random;

    @Before
    public void init() {
        random = new Random();
        tree = FenwickTree.withValue(SIZE, 1);
    }

    @Test
    public void incrementMultipleTest() {
        int[] incrementIndices = new int[INCREMENT_OPERATIONS];
        for (int j = 0; j < INCREMENT_OPERATIONS; j++) {
            incrementIndices[j] = random.nextInt(SIZE);
        }
        long start = System.currentTimeMillis();
        for (int j = 0; j < INCREMENT_OPERATIONS; j++)
            tree.increment(incrementIndices[j], 1);
        System.out.println(
            String.format("contests.fenwick.PerformanceTests.incrementMultipleTest. Elapsed: %d milis. Total: %d",
                System.currentTimeMillis() - start, tree.getTotal()));
    }

    @Test
    public void sumMultipleTest() {
        int[][] sumRanges = new int[SUM_OPERATIONS][2];
        for (int j = 0; j < SUM_OPERATIONS; j++) {
            int length = random.nextInt(SIZE) + 1;
            int left = random.nextInt(SIZE - length + 1);
            sumRanges[j][0] = left;
            sumRanges[j][1] = left + length - 1;
        }
        long start = System.currentTimeMillis();
        long total = 0;
        for (int j = 0; j < SUM_OPERATIONS; j++) {
            total += tree.sum(sumRanges[j][0], sumRanges[j][1]);
        }
        System.out.println(
            String.format("contests.fenwick.PerformanceTests.sumMultipleTest. Elapsed: %d milis. Total: %d",
                System.currentTimeMillis() - start, total));
    }
    
}
